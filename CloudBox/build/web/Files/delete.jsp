<%
 try
            {
                if(!request.getAttribute("failure_delete").equals(null))
                {
%>
<head>
    <script type="text/javascript">
        alert("Invalid Password, Failed to Delete Account!");
    </script>
</head>
<%                    
                }
            }
catch(Exception ex){}
 
 
 //on delete in the action below it goes to the servlet delete accoumt as its mapped like that in the web.xml file
%>

<body>
<form action="Delete" method="post">
    <table>
        <tr>
            <td>Enter your Password:</td>
            <td> <input type="password" name="deletepass"></td>
        </tr>
        
        <tr>
            <td><input type="submit" value="Submit" /></td>
        </tr>
    </table>
</form>
</body>