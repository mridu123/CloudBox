<%
 try
            {
                if(!request.getAttribute("failure_reset").equals(null))
                {
%>
<head>
    <script type="text/javascript">
        alert("Password incorrect .. Try again!");
    </script>
</head>
<%                    
                }
            }
catch(Exception ex){}
%>

<body>
<form action="Reset" method="post">
    <table>
        <tr>
            <td colspan="5">New Password:</td>
            <td colspan="5"><input type="password" name="resetpass"></td>
        </tr>
        
        <tr>
            <td colspan="5">Confirm Password:</td>
            <td colspan="5"><input type="password" name="confirmresetpass"/></td>
        </tr>
        
        <tr>
            <td colspan="5"><input type="submit" value="Submit" /></td>
        </tr>
    </table>
</form>
</body>