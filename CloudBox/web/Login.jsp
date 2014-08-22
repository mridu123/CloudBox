<%@page import="java.sql.*"%>
<%@page import="javax.sql.*"%>
<%@taglib prefix="tiles" uri="http://struts.apache.org/tags-tiles"%>
<tiles:insert page="MasterPage.jsp">
<tiles:put name="title" value="Login"/>
<tiles:put name="body" value="Files/Login.jsp"/>
</tiles:insert>