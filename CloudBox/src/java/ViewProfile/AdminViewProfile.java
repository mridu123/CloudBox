package ViewProfile;

import HelperClasses.ProvideDataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AdminViewProfile extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String username=request.getParameter("username");
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        ProvideDataSource ds=new ProvideDataSource();
        try 
        {
           con=ds.getDs().getConnection();
           ps=con.prepareStatement("select * from signup where uname='"+username+"'");
           rs=ps.executeQuery();
           if(rs.next())
           {   
               out.println("<form action='AdminViewUploads.jsp' method='POST'>");
               out.println("<table><tr><td colspan='2'>");
               out.println("Name:</td><td>");
               out.println(rs.getString(1)+" "+rs.getString(2));
               out.println("</td></tr><tr><td colspan='2'>");
               out.println("Date of Birth:</td><td>");
               out.println(rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6));
               out.println("</td></tr><tr><td colspan='2'>");
               out.println("Gender:</td><td>");
               out.println(rs.getString(10));
               out.println("</td></tr><tr><td colspan='2'>");
               out.println("Username:</td><td>");
               out.println(rs.getString(8));
               out.println("</td></tr><tr><td colspan='2'>");
               out.println("Email:</td><td>");
               out.println(rs.getString(9));
               out.println("</td></tr></tr><td colspan='2'>");
               out.println("Phone Number:</td><td>");
               out.println(rs.getString(7));
               out.println("</td></tr>");
               out.println("<tr><td colspan='2'>");
              
            
               out.println("</td><td><input type='hidden' value='"+username+"' name='hidden'></td></tr>");
               out.println("<tr><td>");
              out.println("<input type='submit' value='View Uploads'");
              out.println("</td></tr>");
               out.println("</table>");              
           }
        }
        catch(Exception ex){}
        finally 
        {            
            //out.close();
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
