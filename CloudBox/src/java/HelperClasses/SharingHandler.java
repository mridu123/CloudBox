package HelperClasses;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SharingHandler extends HttpServlet
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ProvideDataSource ds=new ProvideDataSource();
        Connection con=null;
        PreparedStatement ps=null;        
        String success_update="success";
        String string_from_js=request.getParameter("hvalue");        
        String[] splitted1=string_from_js.split("-"); 
        String one="",h1;
        for(String abc:splitted1)
            {
                h1= abc;
                one=one+h1;
            }        
        String privacy_only;
        privacy_only=one.replaceAll("[0-9]","");
        String[] privacy=privacy_only.split(",");        
        String id_only;
        id_only=one.replaceAll("[A-Za-z]","");
        String[] id=id_only.split(",");              
        int loop=id.length;        
        try
        {
            con=ds.getDs().getConnection();
            for(int i=0;i<loop;i++)
            {
                ps=con.prepareStatement("update files set sharing='"+privacy[i]+"' where id='"+id[i]+"'");
                ps.executeUpdate();            
            }
            request.setAttribute("success_update",success_update);
            RequestDispatcher rd=request.getRequestDispatcher("MyUploads.jsp");
            rd.forward(request,response);
        }
        catch(Exception ex){}
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