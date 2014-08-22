package Controller;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import HelperClasses.ProvideDataSource;
import java.sql.*;

public class LoginForm extends org.apache.struts.action.ActionForm 
{    
    private String user;
    private String pass;
    ProvideDataSource ds=new ProvideDataSource();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;         

    /**
     * @return
     */
    public String getUser() {
        return user;
    }

    /**
     * @param string
     */
    public void setUser(String string) {
        this.user = string;
    }

    /**
     * @return
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param i
     */
    public void setPass(String i) {
        this.pass = i;
    }

    /**
     *
     */
    public LoginForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) 
    {
    ActionErrors errors = new ActionErrors();
    if(!user.equalsIgnoreCase("Admin") && !pass.equals("Admin@1234"))
    {        
        try
        {
            con=ds.getDs().getConnection();
            ps=con.prepareStatement("select * from signup where uname='"+user+"' and pass='"+pass+"'");
            rs=ps.executeQuery();
            if(!rs.next())
            {
                errors.add("nomatch", new ActionMessage("error.nomatch.required"));
            }
        }
        catch(Exception ex)
        {}
    finally
    {
        try
        {
            rs.close();
            ps.close();
            con.close();
        }
        catch(Exception ex){}
    }        
    }  
    else if(user.equalsIgnoreCase("Admin") && !pass.equals("Admin@1234"))
    {
        errors.add("nomatch", new ActionMessage("error.nomatch.required"));
    }
    return errors;
    }
}