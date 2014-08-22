<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    session=request.getSession();
    String user=(String)session.getAttribute("LoginSession");
    //System.out.println(session.getAttribute("LoginSession")); //why was this written here?
   try
   {
        if(user.equalsIgnoreCase("Admin"))
        {
            response.sendRedirect("adminProfile.jsp");
        }
        if(!user.equalsIgnoreCase("Admin") && !user.equals(null))
        {
            response.sendRedirect("Profile.jsp");
        }
   }
   catch(Exception ex){}
%>

<body>
<%@taglib prefix="tiles" uri="http://struts.apache.org/tags-tiles"%>
<tiles:insert page="MasterPage.jsp">
<tiles:put name="title" value="CloudBox"/>
<tiles:put name="body" value="Files/Welcome.jsp"/>
<tiles:put name="login" value="Files/Login.jsp"/>
</tiles:insert>
</body>