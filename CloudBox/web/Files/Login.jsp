 <%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
 
 <head>
     <link rel="Stylesheet" href="Files/Styling.css" type="text/css">      
 </head> 
 
     <%
        try
        {
         if(!session.getAttribute("LoginSession").equals(null))
        
         {
             
      %>         
         <jsp:forward page="success.jsp"/>         
      <%
         }       
        }
        catch(Exception ex){}
      %>
      
      <div style="color: white">SIGN IN</div>     
      <html:form action="control.do" method="post">        
            <table align='center'>      
                <tr>
                    <td style="color: white">User Name:</td>
                    <td><html:text property="user" /></td>                   
                    <td rowspan="2"><html:submit value="Login" /></td>
                    <td id='error'><html:errors property="nomatch"/></td>
                </tr>
                
                <tr>
                    <td style="color: white">Password:</td>
                    <td><html:password property="pass"/></td>
                </tr>  
                
                <tr>
                    <td></td>
                    <td id="error"><a href="ForgotPassword.jsp">Forgot Password?</a></td>
                </tr>
                <tr>
                    <td></td>
                    <td id="error"><a href="Signup.jsp">Sign Up</a></td>
                </tr>
            </table>                
      </html:form>