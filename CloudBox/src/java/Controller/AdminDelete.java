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

public class AdminDelete extends HttpServlet
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
        PreparedStatement ps1=null,ps2=null,ps3=null;
        String username=request.getParameter("insert");
        
       
        PrintWriter out = response.getWriter();
        ProvideDataSource ds=new ProvideDataSource();
        try 
        { 
                con=ds.getDs().getConnection();
                 //ps1=con.prepareStatement("select * from signup where pass='"+pass+"'");
                //rs1=ps1.executeQuery();
               if(pass.equals("Admin@1234"))
                {
                    ps2=con.prepareStatement("delete from files where uname='"+username+"'");                          
                    ps3=con.prepareStatement("delete from signup where uname='"+username+"'");
                    ps2.executeUpdate();
                    ps3.executeUpdate();
                  
                        request.setAttribute("success_file_del",success);
                        RequestDispatcher rd=request.getRequestDispatcher("showAccounts.jsp");
                        rd.forward(request,response);         
                    
                } 
               else
               {         request.setAttribute("failure_file_del",fail);
                        RequestDispatcher rd=request.getRequestDispatcher("Admin_Delete.jsp");
                        rd.forward(request,response);
                                   
               
               }
                
        }
        catch(Exception ex)
        { } 
        finally
        {
            out.close();
            try
            {
                
                ps1.close();
                ps2.close();
                ps3.close();
                con.close();
            }
            catch(Exception ex){}
            }   
    }
}