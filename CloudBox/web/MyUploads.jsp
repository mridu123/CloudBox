<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${sessionScope.LoginSession eq null}">
 <jsp:forward page="index.jsp"/>        
</c:if>

<%
 try
       {
           if(!request.getAttribute("success_update").equals(null))
           {
%>
<head>
    <script type="text/javascript">
        alert("Privacy of files Updated Successfully!");
    </script>
</head>
<%
           }
       }catch(Exception ex){}
%>

<body>
<%@taglib prefix="tiles" uri="http://struts.apache.org/tags-tiles"%>
<tiles:insert page="InnerMasterPage.jsp">
<tiles:put name="title" value="My Uploads"/>
<tiles:put name="body" value="UploadHandler"/>
</tiles:insert>
</body>