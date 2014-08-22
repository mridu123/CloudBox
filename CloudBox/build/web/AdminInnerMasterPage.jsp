<%@taglib prefix="tiles" uri="http://struts.apache.org/tags-tiles"%>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><tiles:getAsString name="title"/></title>        
        <link rel="Stylesheet" href="Files/Styling.css" type="text/css">  
         <script type="text/javascript">
            function func()
            {
                var abc=document.getElementById("account").value;
            
            if(abc=="Logout")
                {
                    window.location="logout";
                }          
            }
            </script>       
</head>


<body>
   
    <table id="table">            
                <tr>
                    <td colspan="5" id="header3">CloudBoX</td>
                </tr>
                <tr align="center"  id="links">
                    <td colspan="4"><a href="showAccounts.jsp"> Show Accounts</a></td>
                                     
                  
                    <td>
                        <select id="account" onchange="func()">
                            <option>Account Management</option>
                            <option value="Logout">Logout</option>
                           
                        </select>
                    </td>                         
                </tr>
                
               
       
                <tr>
                   <td colspan="5" align="center"><tiles:insert name="body"/></td>                    
                </tr>     
                
                <tr>
                    <td align="center" colspan="5" id="footer">
                        All rights reserved &reg; <br>
                        Copyrights &COPY; by <a href="index.jsp">www.cloudbox.com</a>
                    </td>                    
                </tr>         
    </table>
    
</body>
</html>