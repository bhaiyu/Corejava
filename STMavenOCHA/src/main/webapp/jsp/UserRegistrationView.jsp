<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.ocha.controller.UserRegistrationCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<html>
<body>
	<form action="<%=ORSView.USER_REGISTRATION_CTL%>">

		<%--  <%@ include file="Header.jsp"%> --%>
		<script type="text/javascript" src="../js/calendar.js"></script>
		<jsp:useBean id="dto" class="in.co.sunrays.ocha.bean.UserDTO"
			scope="request"></jsp:useBean>

		<center>
			<input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
			<input type="hidden" name="createdDateTime"
				value="<%=dto.getCreatedDatetime()%>">
			<h1>User Registration</h1>

			<H2>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
			</H2>
			<H2>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</H2>

			<input type="hidden" name="id" value="<%=dto.getId()%>">

			<table>
				<tr>
					<th>First Name</th>
					<td><input type="text" name="firstName" size=30
						value="<%=DataUtility.getStringData(dto.getFirstName())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
				</tr>
				<tr>
					<th>Last Name</th>
					<td><input type="text" name="lastName" size=30
						value="<%=DataUtility.getStringData(dto.getLastName())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
				</tr>
				<tr>
					<th>LoginId</th>
					<td><input type="text" name="login" size=30
						value="<%=DataUtility.getStringData(dto.getLogin())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
				</tr>
				<tr>
					<th>Password</th>
					<td><input type="password" name="password" size=30
						value="<%=DataUtility.getStringData(dto.getPassword())%>"><font
						color="red"><%=ServletUtility.getErrorMessage("password", request)%></font></td>
				</tr>
				<tr>
					<th>Confirm Password</th>
					<td><input type="password" name="confirmPassword" size=30
						value="<%=DataUtility.getStringData(dto.getConfirmPassword())%>"><font
						color="red"> <%=ServletUtility
					.getErrorMessage("confirmPassword", request)%></font></td>
				</tr>
				<tr>
					<th>Gender</th>
					<td width="10px;">
						<%
							HashMap map = new HashMap();
							map.put("M", "Male");
							map.put("F", "Female");

							String htmlList = HTMLUtility.getList("gender", dto.getGender(),
									map);
						%> <%=htmlList%>

					</td>
				</tr>

				<tr>
					<th>Date Of Birth (mm/dd/yyyy)</th>
					<td><input type="text" name="dob" size=30
						value="<%=DataUtility.getDateString(dto.getDob())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
					<td><a href="javascript:getCalendar(document.forms[0].dob);">
							<img src="../imgages/cal.jpg" width="16" height="15" border="0"
							alt="Calender">
					</a></td>
				</tr>
				<tr>
					<th></th>
					<td colspan="2">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
						&nbsp; <input type="submit" name="operation"
						style="background-color: green; font-weight: bold; color: white;"
						value="<%=UserRegistrationCtl.OP_SIGN_UP%>">
					</td>
				</tr>
			</table>
	</form>
	</center>
	<%-- <%@ include file="Footer.jsp"%> --%>
</body>
</html>
