package ViewProfile;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import HelperClasses.ProvideDataSource;
import javax.servlet.http.HttpSession;

public class ViewUploads extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
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
        String other_user=request.getParameter("other_user");
        HttpSession session=request.getSession();
        String user=(String)session.getAttribute("LoginSession");
        Connection con=null;
        PreparedStatement ps=null,ps1=null,ps2=null;
        ResultSet rs=null,rs1=null,rs2=null;
        ProvideDataSource ds=new ProvideDataSource();
        try 
        {
           con=ds.getDs().getConnection();
           ps=con.prepareStatement("select * from files where uname='"+other_user+"' and sharing='Public'");
           ps1=con.prepareStatement("select * from friends where request_from='"+user+"' and request_to='"+other_user+"' and status='Friends' or request_from='"+other_user+"' and request_to='"+user+"' and status='Friends'");
           ps2=con.prepareStatement("select * from files where uname='"+other_user+"' and sharing='Friends'");                   
           rs=ps.executeQuery();
           rs1=ps1.executeQuery();
           rs2=ps2.executeQuery();
           if(!rs.next() && !rs2.next())
           {
               //if(!rs2.next())
               //{
                   out.println("No Files to display here.");
                   rs.close();
                   rs2.close();
               //}
           }
           /*else if(!rs2.next())
           {
               if(!rs.next())
               {
                   out.println("No Files to display here.");
                   rs.close();
                   rs2.close();
                   rs=ps.executeQuery();
                   rs2=ps2.executeQuery();
               }
           }*/
           else
           {
               rs=ps.executeQuery();
               out.println("<table>");
               while(rs.next())
               {
                    String type=rs.getString(4);                
                    out.println("<tr><td rowspan='3'>");
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
                    out.println("Name: "+rs.getString(3));
                    out.println("</td></tr><tr><td>");
                    out.println("Size: "+rs.getString(5)+" KB");
                    out.println("</td></tr><tr><td>");
                    out.write("<a href=\"UploadDownloadFileServlet?fileId="+rs.getString(1)+"\"><b>Download</b></a>");
                    out.print("</td></tr>");                
               }
               if(rs1.next())
               {  
                   rs2=ps2.executeQuery();
                   while(rs2.next())
                   {
                        String type=rs2.getString(4);                
                        out.println("<tr><td rowspan='3'>");
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
                        out.println("Name: "+rs2.getString(3));
                        out.println("</td></tr><tr><td>");
                        out.println("Size: "+rs2.getString(5)+" KB");
                        out.println("</td></tr><tr><td>");
                        out.write("<a href=\"UploadDownloadFileServlet?fileId="+rs2.getString(1)+"\"><b>Download</b></a>");
                        out.print("</td></tr>");                
                   }
               }
               out.println("</table>");
           }
        }
        catch(Exception ex){System.out.println(ex.getMessage());}
        finally 
        {            
            //out.close();
            try
            {
                rs.close();
                ps.close();
                con.close();
            }
            catch(Exception ex){}
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
            throws ServletException, IOException {
        processRequest(request, response);
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
