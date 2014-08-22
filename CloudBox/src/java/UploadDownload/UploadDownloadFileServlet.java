package UploadDownload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import HelperClasses.ProvideDataSource;

@WebServlet("/UploadDownloadFileServlet")
public class UploadDownloadFileServlet extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
    private ServletFileUpload uploader = null;
    @Override
    public void init() throws ServletException
    {
        DiskFileItemFactory fileFactory = new DiskFileItemFactory();
        File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
        fileFactory.setRepository(filesDir);
        this.uploader = new ServletFileUpload(fileFactory);
    }        
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String fileId = request.getParameter("fileId");
        String fileName="";
        if(fileId == null || fileId.equals(""))
        {
                throw new ServletException("File Id can't be null or empty");
        }                
        ProvideDataSource ds=new ProvideDataSource();		
        String mimeType="";
        int filelength=0;
        byte[] bufferData=new byte[2];
        try
        {                       
            con=ds.getDs().getConnection();                                
            ps=con.prepareStatement("select * from files where id=?");
            ps.setString(1, fileId);
            rs=ps.executeQuery();
            while(rs.next())
            {
                mimeType=rs.getString(4);
                Blob b=rs.getBlob(6);
                fileName=rs.getString(3);
                bufferData=new byte[(int)b.length()];
                bufferData=b.getBytes(1, (int)b.length());
                filelength=Integer.parseInt(rs.getString(5));
            }
        }
        catch(Exception ex)
        {
            System.out.println("Exception:  "+ex);
        }    
        finally
        {
            try
            {
                rs.close();
                ps.close();
                con.close();
            }
            catch(Exception ex){}
        }
        response.setContentType(mimeType != null? mimeType:"application/octet-stream");
        response.setContentLength(filelength);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");		
        ServletOutputStream os = response.getOutputStream();		
        os.write(bufferData);		
        os.flush();
        os.close();		
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        if(!ServletFileUpload.isMultipartContent(request))
        {
            throw new ServletException("Content type is not multipart/form-data");
        }
        Connection con=null;
        PreparedStatement ps=null;
        String success="uploaded";
        String failure="fail";
        String nothing="nothing";
        HttpSession session=request.getSession();
        String uname=(String)session.getAttribute("LoginSession");
        ProvideDataSource ds=new ProvideDataSource();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.write("<html><head></head><body>");
        try 
        {
            List<FileItem> fileItemsList = uploader.parseRequest(request);
            Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
            while(fileItemsIterator.hasNext())
            {
                FileItem fileItem = fileItemsIterator.next();		
                File file = new File(request.getServletContext().getAttribute("FILES_DIR")+File.separator+fileItem.getName());
                fileItem.write(file);
                FileInputStream fis=new FileInputStream(file);
                Long ll=fileItem.getSize();
                String filesize=ll.toString();
                if(fileItem.getContentType().equals("application/x-msdownload"))
                {
                    request.setAttribute("failure_upload",failure);
                    RequestDispatcher rd=request.getRequestDispatcher("Uploads.jsp");
                    rd.forward(request,response);
                } 
                else
                {
                    try
                    {                               
                        con=ds.getDs().getConnection();                                
                        ps=con.prepareStatement("insert into files(uname,filename,filetype,filesize,content,sharing) values(?,?,?,?,?,?)");
                        ps.setString(1,uname);
                        ps.setString(2,fileItem.getName());
                        ps.setString(3,fileItem.getContentType());
                        ps.setString(4,filesize);
                        ps.setBinaryStream(5,fis,(int)file.length());                                
                        ps.setString(6,"Private");
                        ps.executeUpdate();                                   
                        file.delete();
                        request.setAttribute("success_upload",success);
                        RequestDispatcher rd=request.getRequestDispatcher("Uploads.jsp");
                        rd.forward(request,response);
                    }
                    catch(Exception ex)
                    {
                        out.println("Exception "+ex);
                    }
                    finally
                    {
                        try
                        {                            
                            ps.close();
                            con.close();
                        }
                        catch(Exception ex){}
                    }
                }
            }
        } 
        catch (FileUploadException e) 
        {
            out.write("Exception "+e.getMessage());
        } 
        catch (Exception e)
        {
            request.setAttribute("nothing_upload",nothing);
            RequestDispatcher rd=request.getRequestDispatcher("Uploads.jsp");
            rd.forward(request,response);
            out.write("Exception "+e.getMessage());
        }                
    out.write("</body></html>");
    }
}