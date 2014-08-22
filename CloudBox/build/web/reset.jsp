<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${sessionScope.LoginSession eq null}">
 <jsp:forward page="index.jsp"/>        
</c:if>

<body>
<%@taglib prefix="tiles" uri="http://struts.apache.org/tags-tiles"%>
<tiles:insert page="InnerMasterPage.jsp">
<tiles:put name="title" value="Reset Password"/>
<tiles:put name="body" value="Files/reset.jsp"/>
</tiles:insert>
</body>