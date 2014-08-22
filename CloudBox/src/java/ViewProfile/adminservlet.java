package ViewProfile;

import HelperClasses.ProvideDataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class adminservlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        ProvideDataSource ds=new ProvideDataSource();
       
        try 
        {
            con=ds.getDs().getConnection();
            ps=con.prepareStatement("select * from signup where uname !='Admin'");
            rs=ps.executeQuery();
            if(!rs.next())
            {
                out.println("No user exists!");
                rs.close();                
            }
            else
            {
                rs=ps.executeQuery();
                out.println("<div style=text-align:justify;>");
                while(rs.next())
                {                    
                        String fname=rs.getString(1);
                        String lname=rs.getString(2);
                        String name=fname+" "+lname;
                        out.println("<table>");
                        out.println("<tr><td rowspan='3'><img src='Files/Icon-user.png' height='75' width='50'/>");
                        out.println("</td><td>");
                        out.write("<a href=\"Admin_Profile_of_others_view.jsp?username="+rs.getString(8)+"\"><b>"+name+"</b></a>");
                        out.println("</td></tr><tr><td colspan='2'>");
                        
                        out.println("(Username: "+rs.getString(8)+")");
                        out.println("</td></tr><tr><td>");
                        out.println("<a href=\"Admin_Delete.jsp?username="+rs.getString(8)+"\"><b>Delete Account</b></a>");
                        out.println("</td></tr><br><br>");
                        out.println("</table>");
                }
                out.println("</div>");
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
            throws ServletException, IOException 
    {
        processRequest(request, response);
        /*Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        ProvideDataSource ds=new ProvideDataSource();
        String uname=request.getParameter("user");
        try
        {
            con=ds.getDs().getConnection();
            ps=con.prepareStatement("select * from ");
        }*/
        
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