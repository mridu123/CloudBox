<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://struts.apache.org/tags-tiles"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><tiles:getAsString name="title"/></title>        
        <link rel="Stylesheet" href="Files/Styling.css" type="text/css">      
    </head>
    
    <body>
        <table align="center" id="table">            
                <tr>
                    <td id="header">CloudBoX</td>
                    <td id="header2"><tiles:insert name="login"/></td>
                </tr>
               
                <tr id="links">
                    <td><a href="AboutUs.jsp">About Us</a></td>
                </tr>
                
                <tr>
                    <td id="frontimage" align="center"><img src="Files/cloud.jpg" height="250" width="350"/></td>
                    <td align="center"><tiles:insert name="body"/></td>                    
                </tr> 
                
                <tr>
                    <td align="center" colspan="2" id="footer">
                        All rights reserved &reg; <br>
                        Copyrights &COPY; by <a href="index.jsp">www.cloudbox.com</a>
                    </td>                    
                </tr>         
        </table>
    </body>
</html>