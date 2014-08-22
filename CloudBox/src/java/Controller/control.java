package Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class control extends org.apache.struts.action.Action 
{
    /* forward name="success" path="" */
    private static final String SUCCESS = "loginsuccess";
    private static final String SUCCESS1 = "adminloginsuccess";
    private final static String FAILURE = "loginfailure";   
    /**
     * This is the action called from the Struts framework.
     *
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
        LoginForm loginForm = (LoginForm) form;
        HttpSession session = request.getSession();
        session.setAttribute("LoginSession", loginForm.getUser());   
        if(loginForm.getUser().equalsIgnoreCase("Admin") && loginForm.getPass().equals("Admin@1234"))
        {
            return mapping.findForward(SUCCESS1); 
        }
        return mapping.findForward(SUCCESS);
        
        //in case of failure, it is redirected from structs action itself
    }
}