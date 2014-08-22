package Controller;

import HelperClasses.CheckInternet;
import HelperClasses.ProvideDataSource;
import HelperClasses.passGen;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.sql.*;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForgotPassword extends HttpServlet {

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
        passGen new_pass=new passGen();
        String newpass=new_pass.getPass();
        String success_set="done";
        String fail_set="yup";
        String no_internet="no";
        String email=request.getParameter("email");
        Connection con=null;
        PreparedStatement ps=null,ps1=null;
        ResultSet rs=null;
        ProvideDataSource ds=new ProvideDataSource();
        
        CheckInternet ci=new CheckInternet();
        if(!ci.isInternetReachable()) //in case the content recieved is null in the checkinternet class function, then null shall be returned and hence attribute set to no_internet
        {
            request.setAttribute("no_internet",no_internet);
            RequestDispatcher rd=request.getRequestDispatcher("ForgotPassword.jsp");
            rd.forward(request,response);                        
        }        
        try 
        {
            con=ds.getDs().getConnection();
            ps=con.prepareStatement("select * from signup where email='"+email+"'");
            rs=ps.executeQuery();
            if(rs.next())
            {
                String host = "smtp.gmail.com"; 
                String user = "cloudbox.website@gmail.com"; 
                String pass = "cloudbox_website"; 
                String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
                String from1 = "cloudbox.website@gmail.com";  
                String to1 = email; 
                String subject1 = "New Password";
                String messageText = "Your new password is: "+newpass;
                Properties props = System.getProperties();
                props.put("mail.host", host);
                props.put("mail.transport.protocol.", "smtp");
                props.put("mail.smtp.port", "465");
                props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
                Session mailSession = Session.getDefaultInstance(props, null); //for seference: http://docs.oracle.com/javaee/5/api/javax/mail/Session.html#getDefaultInstance(java.util.Properties, javax.mail.Authenticator)
                Message msg = new MimeMessage(mailSession);
                try
                {
                    InternetAddress  fr = new InternetAddress(from1);
                    msg.setFrom(fr);
                    InternetAddress[] address = { new InternetAddress(to1) };
                    msg.setRecipients(Message.RecipientType.TO, address);
                    msg.setSubject(subject1);
                    msg.setContent(messageText, "text/html");
                    Transport transport = mailSession.getTransport("smtp");                    
                    transport.connect(host, user, pass);
                    transport.sendMessage(msg, msg.getAllRecipients());
                    transport.close();
                    ps1=con.prepareStatement("update signup set pass='"+newpass+"' where email='"+email+"'"); //IMPRTANT: update in databse too
                    ps1.executeUpdate();                      
                }
                catch (Exception ex){}
            }
            else
            {
                        request.setAttribute("fail_set",fail_set);
                        RequestDispatcher rd=request.getRequestDispatcher("ForgotPassword.jsp");
                        rd.forward(request,response);
            }
            request.setAttribute("success_set",success_set);
            RequestDispatcher rd=request.getRequestDispatcher("ForgotPassword.jsp");
            rd.forward(request,response);
        }
        catch(Exception ex){}
        finally 
        {    
            new_pass.setPass("");
            out.close(); 
            try
            {
                rs.close();
                ps.close();
                ps1.close();
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