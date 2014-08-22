package StrutsAction;

import HelperClasses.ProvideDataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.*;
import javax.servlet.RequestDispatcher;

public class StrutsAction extends org.apache.struts.action.Action
{
    /* forward name="success" path="" */
    String success="signup";
    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";
    ProvideDataSource ds=new ProvideDataSource();    
    Connection con=null;
    PreparedStatement ps=null;    
    /**
     * This is the action called from the Struts framework.     *
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception 
    {        
        con=ds.getDs().getConnection();
        ps=con.prepareStatement("insert into signup values(?,?,?,?,?,?,?,?,?,?)");
        
        
        StrutsActionForm saf=(StrutsActionForm)form;
        
        
        ps.setString(1,saf.getFname());
        ps.setString(2,saf.getLname());
        ps.setString(3,saf.getPass());
        ps.setString(4,saf.getDay());
        ps.setString(5,saf.getMonth());
        ps.setString(6,saf.getYear());
        ps.setString(7,saf.getPhone());
        ps.setString(8,saf.getUname());
        ps.setString(9,saf.getEmail());
        ps.setString(10,saf.getGender());        
        int i=ps.executeUpdate();  
        if(i>0)
        {
            ps.close();
            con.close();
            request.setAttribute("success_signup",success);
            RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
            rd.forward(request,response);
            return mapping.findForward(SUCCESS);
        }       
        ps.close();
        con.close();
        return mapping.findForward(FAILURE); 
    }
}