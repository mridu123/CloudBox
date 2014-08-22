package ViewProfile;

import HelperClasses.ProvideDataSource;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.http.HttpSession;

public class AViewUploads extends HttpServlet 
{
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ProvideDataSource ds=new ProvideDataSource();
        String uname=request.getParameter("hidden");
        Connection con=null;
        PreparedStatement ps1=null;
        ResultSet rs1=null;
        try
        {
            con=ds.getDs().getConnection();
            ps1=con.prepareStatement("select * from files where uname='"+uname+"'");
            rs1=ps1.executeQuery();
            if(!rs1.next())
            {
                out.println("No files to display");
                rs1.close();
            }
            else
            {                        
                rs1=ps1.executeQuery();
                out.println("<div style=text-align:justify;>");
                while(rs1.next())
                {       
                    String type=rs1.getString(4);
                    out.println("<table>");
                    out.println("<tr><td rowspan='6'>");
                    if(type.equals("audio/mp3") || type.equals("video/avi") || type.equals("video/mp4"))
                    {
                        out.println("<img src='Files/multimedia.png' height='200' width='175'/>");
                    }
                    else if(type.equals("image/jpeg") || type.equals("image/png"))
                    {
                        out.println("<img src='Files/photo.png' height='200' width='175'/>");
                    }
                    else if(type.equals("application/pdf"))
                    {
                        out.println("<img src='Files/pdf.png' height='200' width='175'/>");
                    }
                    else if(type.equals("text/plain") || type.equals("application/msword") || type.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document") || type.equals("application/vnd.openxmlformats-officedocument.presentationml.presentation") || type.equals("application/vnd.ms-powerpoint") || type.equals("application/vnd.ms-excel") || type.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    {
                        out.println("<img src='Files/office.png' height='200' width='175'/>");
                    }
                    else
                    {
                    out.println("<img src='Files/other.png' height='200' width='175'/>");
                    }
                    out.println("</td><td>");
                    out.println("Name: "+rs1.getString(3));
                    out.println("</td></tr><tr><td>");
                    out.println("Size: "+rs1.getString(5)+" KB");
                    out.println("</td></tr><tr><td>");
                    out.println("Sharing: "+rs1.getString(7));
                    out.println("</td></tr><tr><td>");
                    out.write("<a href=\"UploadDownloadFileServlet?fileId="+rs1.getString(1)+"\"><b>Download</b></a>");
                    out.println("</td></tr><tr><td>");                                 
                    out.write("<a href=\"AViewUploads?fileId="+rs1.getString(1)+"\"><b>Delete</b></a>");
                    out.println("</td></tr><tr><td>");
                    out.print("</td></tr>");
                    out.println("<br>");
                    out.println("<br>");
                    out.println("</table>");
                }                                    
                out.println("</div>");
                out.println("<br><br>");
            }                                
        }
        catch(Exception ex)
        {
            System.out.println("Exception "+ex);
        }
        finally
        {
            try
            {
                rs1.close();
                ps1.close();
                con.close();
            }
            catch(Exception ex){System.out.println(ex.getMessage());}
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        processRequest(request, response);
        String fileId = request.getParameter("fileId");           
        ProvideDataSource ds=new ProvideDataSource();
        Connection con=null;
        PreparedStatement ps=null,ps1=null;
        ResultSet rs1=null;
        String hidden=null;
        try
        {                       
            con=ds.getDs().getConnection();                                
            ps=con.prepareStatement("delete from files where id='"+fileId+"'");
            ps1=con.prepareStatement("select * from files where id='"+fileId+"'");
            rs1=ps1.executeQuery();
            if(rs1.next())
            {
                hidden=rs1.getString(2);
            }
            ps.executeUpdate();                    
        }
        catch(Exception ex)
        {}
        finally
        {
            try
            {                
                ps.close();
                con.close();
            }
            catch(Exception ex){}
        }
                response.sendRedirect("AdminViewUploads.jsp?hidden="+hidden+"");
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
