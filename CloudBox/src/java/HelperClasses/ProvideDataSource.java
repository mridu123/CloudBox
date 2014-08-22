package HelperClasses;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ProvideDataSource
{ 
    private DataSource ds;
    public ProvideDataSource()
    {
        try
        {
            InitialContext ct=new InitialContext();
            ds=(DataSource)ct.lookup("conn");
        }
        catch(Exception ex)
        {
            System.out.println("Exception is "+ex);
        }
    }
    public DataSource getDs()
    {
        return ds;
    }    
}