<%
 try
            {
                if(!request.getAttribute("success_set").equals(null))
                {
%>
<head>
    <script type="text/javascript">
        alert("New Password E-mailed to you!");
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
                if(!request.getAttribute("fail_set").equals(null))
                {
%>
<head>
    <script type="text/javascript">
        alert("This E-mail is not registered with us!");
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
                if(!request.getAttribute("no_internet").equals(null))
                {
%>
<head>
    <script type="text/javascript">
        alert("No stable internet connection found. Please try again.");
    </script>
</head>
<%                    
                }
            }
catch(Exception ex){}
 
//check xml file for the action location in the form below 
 
%>
<form action="forgot_pass" method="post"> 
    Enter your Registered E-mail address:<input type="text" name="email" value=""/>
    <input type="submit"/>
</form>