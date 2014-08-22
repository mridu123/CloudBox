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

public class AcceptFriendRequest extends HttpServlet {

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
        String to=(String)session.getAttribute("LoginSession");
        out.println("<head><script type='text/javascript' src='Files/friend_requests.js'></script></head>");      
        try 
        {
            con=ds.getDs().getConnection();
            ps=con.prepareStatement("select * from friends where request_to='"+to+"' and status='Requested'");
            rs=ps.executeQuery();
            if(!rs.next())
            {
                out.println("No Friend Requests.");
                rs.close();
            }
            else
            {
                rs=ps.executeQuery();
                out.println("<table><form action='dealing_with_friend_requests' method='post' name='myform3'>");
                while(rs.next())
                {
                    ps1=con.prepareStatement("select * from signup where uname='"+rs.getString(2)+"'");
                    rs1=ps1.executeQuery(); 
                    if(rs1.next())
                    {
                        String fname=rs1.getString(1);
                        String lname=rs1.getString(2);
                        String name=fname+" "+lname;
                        String accept_button="Accept "+rs1.getString(8);
                        String cancel_button="Cancel "+rs1.getString(8);
                        //System.out.println(accept_button);
                        //System.out.println(cancel_button);
                        out.println("<tr><td rowspan='3'><img src='Files/Icon-user.png' height='75' width='50'/>");
                        out.write("<td><a href=\"Profile_of_others_view.jsp?username="+rs1.getString(8)+"\"><b>"+name+"</b></a></td></tr>");
                        out.println("<tr><td>(Username: "+rs1.getString(8)+")</td></tr>");
                        //out.println("<input type='hidden' name='hiddenval' value=''/>");
                        out.println("<form method='post'>");
                        out.write("<tr><td><input type='submit' value='"+accept_button+"' name='acceptbutton' onclick='acceptrequest()'/> <input type='submit' value='"+cancel_button+"' name='cancelbutton' onclick='cancel()'/></td></tr>"); 
                        out.println("</form>");
                        rs1.close();
                        ps1.close();
                    }
                }
                out.println("</form></table>");
            }
        } 
        catch(Exception ex)
        {           
        }
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
