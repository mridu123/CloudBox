<%@taglib prefix="tiles" uri="http://struts.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.LoginSession ne null}">
    <c:redirect url="Profile.jsp"/>
</c:if>
<tiles:insert page="MasterPage.jsp">
<tiles:put name="title" value="About Us"/>
<tiles:put name="body" value="Files/AboutUs.jsp"/>
<tiles:put name="login" value="Files/Login.jsp"/>
</tiles:insert>