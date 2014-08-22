package StrutsAction;

import HelperClasses.ProvideDataSource;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class StrutsActionForm extends org.apache.struts.action.ActionForm 
{    
    private String fname,lname,uname,pass,cpass,day,month,year,gender,phone,email,tandc;
    ProvideDataSource ds=new ProvideDataSource();
    Connection con=null;
    PreparedStatement ps1=null,ps2=null;
    ResultSet rs1=null,rs2=null;   
    /**
     *
     */
    public StrutsActionForm() {
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
        String passregex="((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@!#*=])(?=[\\S]+$).{8,10})";
        String phoneregex="^\\d{10,10}$";  
        String emailregex="\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";
        Integer yearcheck=Integer.parseInt(year);        
        if (fname.length()==0) 
        {
            errors.add("fname", new ActionMessage("error.fname.required"));
        }        
        if (lname.length()==0) 
        {
            errors.add("lname", new ActionMessage("error.lname.required"));
        }
        if (!pass.matches(passregex)) 
        {
            errors.add("pass", new ActionMessage("error.pass.required"));
        }
        if (!cpass.equals(pass)) 
        {
            errors.add("cpass", new ActionMessage("error.cpass.required"));
        }
        if (!phone.matches(phoneregex)) 
        {
            errors.add("phone", new ActionMessage("error.phone.required"));
        }      
        if (gender==null) 
        {
            errors.add("gender", new ActionMessage("error.gender.required"));
        }
        if (!email.matches(emailregex)) 
        {
            errors.add("email", new ActionMessage("error.email.required"));
        }
        if (tandc == null) 
        {
            errors.add("tandc", new ActionMessage("error.tandc.required"));
        }
        if (uname.length()==0) 
        {
            errors.add("uname", new ActionMessage("error.uname.required"));
        }
        if(day.equals("31"))
        {
            if(month.equals("April") || month.equals("June") || month.equals("September") || month.equals("November"))
            {
                errors.add("date", new ActionMessage("error.date.required"));
            }
        }
        if(month.equals("February"))
        {
            if(day.equals("30") || day.equals("31"))
            {
                errors.add("date", new ActionMessage("error.date.required"));
            }
        }
        if(month.equals("February") && day.equals("29"))
        {
           if(!(yearcheck%100==0 && yearcheck%400==0))
           {
               if(!(yearcheck%4==0))
               {
                   errors.add("date", new ActionMessage("error.date.required"));
               }
           }
           else if(!(yearcheck%4==0))
           {
               errors.add("date", new ActionMessage("error.date.required"));
           }                            
        }  
        try
        {
            con=ds.getDs().getConnection();
            ps1=con.prepareStatement("select * from signup where uname='"+uname+"'");
            ps2=con.prepareStatement("select * from signup where email='"+email+"'");
            rs1=ps1.executeQuery();
            rs2=ps2.executeQuery();
            if(rs1.next())
            {
                errors.add("unameexists", new ActionMessage("error.unameexists.required"));               
            }
            if(rs2.next())
            {                    
                errors.add("emailexists", new ActionMessage("error.emailexists.required"));         
            }                
        }
        catch(Exception ex){}  
        finally
        {
            try
            {
                rs1.close();
                rs2.close();
                ps1.close();
                ps2.close();
                con.close();
            }
            catch(Exception ex){}
        }
        return errors;
    }

    /**
     * @return the fname
     */
    public String getFname() {
        return fname;
    }

    /**
     * @param fname the fname to set
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * @return the lname
     */
    public String getLname() {
        return lname;
    }

    /**
     * @param lname the lname to set
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     * @return the uname
     */
    public String getUname() {
        return uname;
    }

    /**
     * @param uname the uname to set
     */
    public void setUname(String uname) {
        this.uname = uname;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * @return the cpass
     */
    public String getCpass() {
        return cpass;
    }

    /**
     * @param cpass the cpass to set
     */
    public void setCpass(String cpass) {
        this.cpass = cpass;
    }

    /**
     * @return the day
     */
    public String getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * @return the month
     */
    public String getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
  
    /**
     * @return the tandc
     */
    public String getTandc() {
        return tandc;
    }

    /**
     * @param tandc the tandc to set
     */
    public void setTandc(String tandc) {
        this.tandc = tandc;
    }
}