<%
 try
            {
                if(!request.getAttribute("success_signup").equals(null))
                {
%>
<head>
    <script type="text/javascript">
        alert("Signup Successful!");
    </script>
</head>
<%                    
                }
            }
catch(Exception ex){}
%>

<%
 try
            {
                if(!request.getAttribute("success_delete").equals(null))
                {
%>
<head>
    <script type="text/javascript">
        alert("Account deleted Successfully!");
    </script>
</head>
<%                    
                }
            }
catch(Exception ex){}
%>

<h2 align="center">
    Hi! <br>
Welcome to CloudBox. Save and share your data on our cloud, and access it anywhere.
</h2>