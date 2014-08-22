<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sign" uri="/WEB-INF/struts-html.tld"%>
<sign:form action="StrutsAction.do" method="post">     
    <div id="error" style="text-align:justify; text-justify:inter-word;">
        <sign:errors property="unameexists"/>
        <sign:errors property="emailexists"/>  
        <sign:errors property="done"/>
    </div>
    
    <table>
	<caption><font size=5><b>SIGNUP FORM</b></font></caption>
	<tr>
	<td>First Name:</td>
        <td><sign:text property="fname"></sign:text></td>
        <td id="error"><sign:errors property="fname"></sign:errors></td>
        </tr>
        
        <tr>
	<td>Last Name:</td>
	<td><sign:text property="lname"></sign:text></td>
        <td id="error"><sign:errors property="lname"></sign:errors></td>
        </tr>  
               
	<tr>
	<td>Username:</td>
	<td><sign:text property="uname"></sign:text></td>
        <td id="error"><sign:errors property="uname"></sign:errors></td>
	</tr>

	<tr>
	<td>Password:</td>
        <td><sign:password property="pass" maxlength="10"></sign:password></td>
        <td id="error" style="width: 100px;"><sign:errors property="pass"></sign:errors></td>        
	</tr>
	
	<tr>
	<td>Confirm your password:</td>
	<td><sign:password property="cpass"></sign:password></td>
        <td id="error"><sign:errors property="cpass"></sign:errors></td>
	</tr>
        
	<tr>            
            <td>Date of Birth:</td>
        <td>
        <sign:select property="day" size="1">
                <sign:option value="Day" disabled="true"></sign:option>
                <sign:option value="1"></sign:option>
                <sign:option value="2"></sign:option>
                <sign:option value="3"></sign:option>
                <sign:option value="4"></sign:option>
                <sign:option value="5"></sign:option>
                <sign:option value="6"></sign:option>
                <sign:option value="7"></sign:option>
                <sign:option value="8"></sign:option>
                <sign:option value="9"></sign:option>
                <sign:option value="10"></sign:option>
                <sign:option value="11"></sign:option>
                <sign:option value="12"></sign:option>
                <sign:option value="13"></sign:option>
                <sign:option value="14"></sign:option>
                <sign:option value="15"></sign:option>
                <sign:option value="16"></sign:option>
                <sign:option value="17"></sign:option>
                <sign:option value="18"></sign:option>
                <sign:option value="19"></sign:option>
                <sign:option value="20"></sign:option>
                <sign:option value="21"></sign:option>
                <sign:option value="22"></sign:option>
                <sign:option value="23"></sign:option>
                <sign:option value="24"></sign:option>
                <sign:option value="25"></sign:option>
                <sign:option value="26"></sign:option>
                <sign:option value="27"></sign:option>
                <sign:option value="28"></sign:option>
                <sign:option value="29"></sign:option>
                <sign:option value="30"></sign:option>
                <sign:option value="31"></sign:option>
            </sign:select>
            
            <sign:select property="month" size="1">
                <sign:option value="Month" disabled="true"></sign:option>
                <sign:option value="January"></sign:option>
                <sign:option value="February"></sign:option>
                <sign:option value="March"></sign:option>
                <sign:option value="April"></sign:option>
                <sign:option value="May"></sign:option>
                <sign:option value="June"></sign:option>
                <sign:option value="July"></sign:option>
                <sign:option value="August"></sign:option>
                <sign:option value="September"></sign:option>
                <sign:option value="October"></sign:option>
                <sign:option value="November"></sign:option>
                <sign:option value="December"></sign:option>
            </sign:select>   
            
            <sign:select property="year" size="1">
              <sign:option value="Year" disabled="true"></sign:option>
              <sign:option value="1990"></sign:option>
              <sign:option value="1991"></sign:option>
              <sign:option value="1992"></sign:option>
              <sign:option value="1993"></sign:option>
              <sign:option value="1994"></sign:option>
              <sign:option value="1995"></sign:option>
              <sign:option value="1996"></sign:option>
              <sign:option value="1997"></sign:option>
              <sign:option value="1998"></sign:option>
              <sign:option value="1999"></sign:option>
              <sign:option value="2000"></sign:option>
            </sign:select>
        </td>        
        <td id="error"><sign:errors property="date"></sign:errors></td>
	</tr>
       
	<tr>
	<td>Gender:</td>	
        <td>
        <sign:radio property="gender" value="Male">Male</sign:radio>
        <sign:radio property="gender" value="Female">Female</sign:radio>
        </td>   
        <td id="error"><sign:errors property="gender"></sign:errors></td>
	</tr>
        
	<tr>
	<td>Phone:</td>
        <td><sign:text property="phone" maxlength="10"></sign:text></td>
        <td id="error"><sign:errors property="phone"></sign:errors></td>
	</tr>

	<tr>
	<td>Email Address:</td>
        <td><sign:text property="email"></sign:text></td>
        <td id="error"><sign:errors property="email"></sign:errors></td>
	</tr>
				
	<tr>
            <td><sign:checkbox property="tandc" value="Agree"></sign:checkbox></td>
        <td>I agree with the <a href="tandc.jsp">terms and conditions.</a></td>
            <td id="error"><sign:errors property="tandc"></sign:errors></td>
	</tr>
       
	<tr>
            <td colspan="2"><sign:submit></sign:submit></td>	
	</tr>      
</table>
</sign:form>