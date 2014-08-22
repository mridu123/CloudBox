package ViewProfile;

import HelperClasses.ProvideDataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ViewProfile extends HttpServlet {

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
        HttpSession session=request.getSession();
        String from=(String)session.getAttribute("LoginSession");
        String username=request.getParameter("username");
        Connection con=null;
        PreparedStatement ps=null,ps1=null,ps2=null,ps3=null;
        ResultSet rs=null,rs1=null,rs2=null,rs3=null;
        ProvideDataSource ds=new ProvideDataSource();
        out.println("<head><script type='text/javascript' src='Files/friends.js'></script></head>");      
        try 
        {
           con=ds.getDs().getConnection();
           ps=con.prepareStatement("select * from signup where uname='"+username+"'");
           ps1=con.prepareStatement("select * from friends where request_to='"+username+"' and request_from='"+from+"' and status='Requested'");
           ps2=con.prepareStatement("select * from friends where request_to='"+from+"' and status='Requested'");
           ps3=con.prepareStatement("select * from friends where request_from='"+from+"' and request_to='"+username+"' and status='Friends' or request_from='"+username+"' and request_to='"+from+"' and status='Friends'");
           rs=ps.executeQuery();
           rs1=ps1.executeQuery();
           rs2=ps2.executeQuery();
           rs3=ps3.executeQuery();
           if(rs.next())
           {
               out.println("<form name='myform2'>");
               out.println("<table><tr><td>");
               out.println("Name:</td><td>");
               out.println(rs.getString(1)+" "+rs.getString(2));
               out.println("</td></tr><tr><td>");
               out.println("Date of Birth:</td><td>");
               out.println(rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6));
               out.println("</td></tr><tr><td>");
               out.println("Gender:</td><td>");
               out.println(rs.getString(10));
               out.println("</td></tr><tr><td>");
               out.println("Username:</td><td>");
               out.println(rs.getString(8));
               out.println("</td></tr><tr><td>");
               out.println("Email:</td><td>");
               out.println(rs.getString(9));
               out.println("</td></tr></tr><td>");
               out.println("Phone Number:</td><td>");
               out.println(rs.getString(7));
               out.println("</td></tr>");
               out.println("<tr><td>");
               if(rs1.next())
               { 
                   out.println("<input type='button' value='Request Sent'/>");                   
               }   
               else if(rs2.next())
               {
                   out.println("<input type='button' value='Request Recieved'/>");
               }
               else if(rs3.next())
               {
                   out.println("<input type='button' value='Friends'/>");
               }
               else
               {                 
                   out.println("<input type='button' value='Add Friend' onclick='add_friend()'/>");
               }
               out.println("</td><td>");
               out.println("<input type='button' value='View Uploads' onclick='view_uploads()'/>");
               out.println("</table>");
               out.println("<input type='hidden' value="+username+" name='other_user'>");
               out.println("</form>");
           }
        }
        catch(Exception ex){}
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