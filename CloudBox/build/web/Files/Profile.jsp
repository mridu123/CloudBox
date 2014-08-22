<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>    

<%
 try
            {
                if(!request.getAttribute("success_reset").equals(null))
                {
%>
<head>
    <script type="text/javascript">
        alert("Password reset Successfully!");
    </script>
</head>
<%                    
                }
            }
catch(Exception ex){}
%>

<jsp:useBean id="pr" scope="page" class="HelperClasses.ProvideDataSource" />

<sql:query var="pro" dataSource="${pr.ds}">
    select * from signup where uname='${sessionScope.LoginSession}'   
</sql:query>
 
<table>
    <c:forEach var="row" items="${pro.rowsByIndex}">
        <tr>
            <td>
                <c:out value="Name:"/>
            </td>
            <td>
                <c:set var="column" value="${row[0]}"/>
                <c:out value="${column}"/>
                <c:set var="column" value="${row[1]}"/>
                <c:out value="${column}"/>
            </td>
        </tr>
        
        <tr>
            <td>
                <c:out value="Date of Birth:"/>
            </td>
            <td>
                <c:set var="column" value="${row[3]}"/>
                <c:out value="${column}"/>
                <c:set var="column" value="${row[4]}"/>
                <c:out value="${column}"/>
                <c:set var="column" value="${row[5]}"/>
                <c:out value="${column}"/>                
            </td>
        </tr>
        
        <tr>
            <td>
                <c:out value="Gender:"/>
            </td>
            <td>
                <c:set var="column" value="${row[9]}"/>
                <c:out value="${column}"/>
            </td>
        </tr>
        
         <tr>
            <td>
                <c:out value="Username:"/>
            </td>
            <td>
                <c:set var="column" value="${row[7]}"/>
                <c:out value="${column}"/>
            </td>
        </tr>
        
        <tr>
            <td>
                <c:out value="Email:"/>
            </td>
            <td>
                <c:set var="column" value="${row[8]}"/>
                <c:out value="${column}"/>
            </td>
        </tr>
        
        <tr>
            <td>
                <c:out value="Phone Number:"/>
            </td>
            <td>
                <c:set var="column" value="${row[6]}"/>
                <c:out value="${column}"/>
            </td>
        </tr>       
    </c:forEach>
</table>