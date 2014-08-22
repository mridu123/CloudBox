package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import HelperClasses.ProvideDataSource;
import javax.servlet.RequestDispatcher;

public class DeleteAccount extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session=request.getSession();
        String uname=(String)session.getAttribute("LoginSession");        
        Connection con=null;
        String success="delete"; 
        String fail="failure_delete";
        String pass=request.getParameter("deletepass"); 
        ResultSet rs1=null;
        PreparedStatement ps1=null,ps2=null,ps3=null,ps4=null;
        PrintWriter out = response.getWriter();
        ProvideDataSource ds=new ProvideDataSource();
        try 
        { 
            con=ds.getDs().getConnection();
            ps1=con.prepareStatement("select * from signup where uname='"+uname+"' and pass='"+pass+"'");
            rs1=ps1.executeQuery();
            if(rs1.next())
            {
                ps2=con.prepareStatement("delete from files where uname='"+uname+"'");  
                ps3=con.prepareStatement("delete from friends where request_from='"+uname+"' or request_to='"+uname+"'");
                ps4=con.prepareStatement("delete from signup where uname='"+uname+"'");               
                ps2.executeUpdate();
                ps3.executeUpdate();
                ps4.executeUpdate();
                session.invalidate();
                request.setAttribute("success_delete",success);
                RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
                rd.forward(request,response);           
            }  
            else
            {
                request.setAttribute("failure_delete",fail);
                RequestDispatcher rd=request.getRequestDispatcher("delete.jsp");
                rd.forward(request,response);
            }
        }
        catch(Exception ex)
        { 
            System.out.println(ex.getMessage());
        } 
        finally
        {
            out.close();
            try
            {
                rs1.close();
                ps1.close();
                ps2.close();
                ps3.close();
                con.close();
            }
            catch(Exception ex){}
            }   
    }
}