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

public class ManageFriends extends HttpServlet {

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
        Connection con=null;
        PreparedStatement ps=null,ps1=null;
        ResultSet rs=null,rs1=null;
        ProvideDataSource ds=new ProvideDataSource();
        HttpSession session=request.getSession();
        String uname=(String)session.getAttribute("LoginSession");
        try 
        {
            con=ds.getDs().getConnection();
            ps=con.prepareStatement("select * from friends where request_to='"+uname+"' and status='Friends' or request_from='"+uname+"' and status='Friends'");
            rs=ps.executeQuery();
            if(!rs.next())
            {
                out.println("Friend List Empty.");
                rs.close();
            }
            else
            {
                rs=ps.executeQuery(); 
                out.println("<table>");
                while(rs.next())
                {
                    String one=rs.getString(2);
                    String two=rs.getString(3);
                    if(one.equals(uname))
                    {
                        ps1=con.prepareStatement("select * from signup where uname='"+two+"'");
                        rs1=ps1.executeQuery();
                    }
                    else if(two.equals(uname))
                    {
                        ps1=con.prepareStatement("select * from signup where uname='"+one+"'");
                        rs1=ps1.executeQuery();
                    }
                    if(rs1.next())
                    {
                        String fname=rs1.getString(1);
                        String lname=rs1.getString(2);
                        String name=fname+" "+lname;
                        out.println("<tr><td rowspan='3'><img src='Files/Icon-user.png' height='75' width='50'/>");
                        out.write("<td><a href=\"Profile_of_others_view.jsp?username="+rs1.getString(8)+"\"><b>"+name+"</b></a></td></tr>");
                        out.println("<tr><td>(Username: "+rs1.getString(8)+")</td></tr>");
                        out.write("<tr><td><a href=\"manage_friends?uname="+rs1.getString(8)+"\"><b>Delete Friend</b></a></td></tr>");
                    }
                }
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
        String uname=request.getParameter("uname");
        Connection con=null;
        PreparedStatement ps=null;
        ProvideDataSource ds=new ProvideDataSource();
        try
        {
            con=ds.getDs().getConnection();
            ps=con.prepareStatement("delete from friends where request_from='"+uname+"' or request_to='"+uname+"'");
            ps.executeUpdate();
            response.sendRedirect("ManageFriends.jsp");
        }
        catch(Exception ex){}
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
