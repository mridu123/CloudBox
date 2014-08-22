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
            if(abc=="ResetPassword")
                {
                    window.location.href="reset.jsp";
                }
            if(abc=="DeleteAccount")
                {
                    window.location.href="delete.jsp";
                }    
            if(abc=="ManageFriends")
                {
                    window.location.href="ManageFriends.jsp";
                }
            if(abc=="FriendRequests")
                {
                    window.location.href="AcceptFriendRequest.jsp";
                }
            }
            
            function func2()
            {
                var user=document.myform.search.value;
                document.myform.hfield.value=user;
                document.myform.submit();
            }
            
            function func3()
            {
                if(event.keyCode==13)
                    {
                        func2();
                    }
            }
        </script>
</head>

<body>
   
    <table id="table">            
                
    <table id="table">            
                <tr>
                    <td colspan="5" id="header3">CloudBoX
                        <form action="Profile_of_others.jsp" method="post" name="myform">
                        <input type="text" size="55" placeholder="Enter first name to search" name="search" value="" onkeydown="func3()"/>
                        <input type="hidden" name="hfield" value=""/>
                        <input type="button" value="Search" onclick="func2()"/>                    
                        </form>
                    </td>
                </tr>
                
                
                <tr>
                <td id="menu">
                <center>
<ul>
<li><a href="Profile.jsp">Profile</a></li>
<li><a href="MyUploads.jsp">My Uploads</a></li>
<li><a href="Uploads.jsp">Upload a New File</a></li>
<li><select id="account" onchange="func()">
                            <option>Account Management</option>
                            <option value="FriendRequests">Friend Requests</option>
                            <option value="ManageFriends">Manage Friends</option>
                            <option value="ResetPassword">Reset Password</option>
                            <option value="DeleteAccount">Delete Account</option>
                            <option value="Logout">Logout</option>
                        </select></li>
</ul>
                </center>
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