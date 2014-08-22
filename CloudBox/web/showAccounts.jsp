

<%
 try
            {
                if(!request.getAttribute("success_del").equals(null))
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


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${sessionScope.LoginSession eq null}">
 <jsp:forward page="index.jsp"/>        
</c:if>

<body>
<%@taglib prefix="tiles" uri="http://struts.apache.org/tags-tiles"%>
<tiles:insert page="AdminInnerMasterPage.jsp">
<tiles:put name="title" value="adminProfile"/>
<tiles:put name="body" value="adminservlet"/>
</tiles:insert>
</body>