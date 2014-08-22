package Controller;

import HelperClasses.ProvideDataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ResetPassword extends HttpServlet 
{
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    HttpSession session=request.getSession();
    String uname=(String)session.getAttribute("LoginSession");
    Connection con=null;
    String success="reset"; 
    String failure="fail";
    PreparedStatement ps=null;           
    ProvideDataSource ds=new ProvideDataSource();
    String pass=request.getParameter("resetpass");
    String confirmpass=request.getParameter("confirmresetpass");
    String passregex="((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@!#*=])(?=[\\S]+$).{8,10})";
    if(pass.matches(confirmpass) && (pass.matches(passregex)))
    {         
        try 
        {
            con=ds.getDs().getConnection();
            ps=con.prepareStatement("UPDATE signup SET pass='"+pass+"' where uname='"+uname+"'");  
            int i=ps.executeUpdate();  
            if(i>0)  
            {
                request.setAttribute("success_reset",success);
                RequestDispatcher rd=request.getRequestDispatcher("Profile.jsp");
                rd.forward(request,response);
            }         
        }
        catch(Exception ex)
        {
        }
        finally
        {
            out.close();
            try
            {                
                ps.close();
                con.close();
            }
            catch(Exception ex){}
        }
    }
    else
    {
        request.setAttribute("failure_reset",failure);
        RequestDispatcher rd=request.getRequestDispatcher("reset.jsp");
        rd.forward(request,response);
    }
    }    
}