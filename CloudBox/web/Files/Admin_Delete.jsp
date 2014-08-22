<%
 try
            {
                if(!request.getAttribute("failure_del").equals(null))
                {
%>
<head>
    <script type="text/javascript">
        alert(" Failed to Delete Account!");
    </script>
</head>
<%                    
                }
            }
catch(Exception ex){}
%>

<body>
<form action="AdminDelete" method="post">
    <table>
        <tr>
            <td>Enter your Password:</td>
            <td> <input type="password" name="deletepass"></td>
        </tr>
        
        
<tr><td>
        <input type="hidden" name="insert" value="<%=request.getParameter("username")%>">  
    </td></tr>

      
      
        <tr>
            <td><input type="submit" value="Submit" /></td>
        </tr>
    </table>
</form>
</body>