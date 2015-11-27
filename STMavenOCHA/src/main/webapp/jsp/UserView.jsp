
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.ocha.controller.UserCtl"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<html>
<body>
	<form action="<%=ORSView.USER_CTL%>">
		<%-- <%@ include file="Header.jsp"%> --%>
		<script type="text/javascript" src="../js/calendar.js"></script>
		<jsp:useBean id="dto" class="in.co.sunrays.ocha.bean.UserDTO"
			scope="request"></jsp:useBean>

		<%
			List l = (List) request.getAttribute("roleList");
		%>

		<center>
			<input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
			<input type="hidden" name="createdDateTime"
				value="<%=dto.getCreatedDatetime()%>">
			<h1>Add User</h1>

			<H2>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</H2>

			<H2>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
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
					<td><input type="text" name="lastName" size="30"
						value="<%=DataUtility.getStringData(dto.getLastName())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
				</tr>
				<tr>
					<th>LoginId</th>
					<td><input type="text" name="login" size="30"
						placeholder=""
						value="<%=DataUtility.getStringData(dto.getLogin())%>"
						<%=(dto.getId() > 0) ? "readonly" : ""%>><font color="red">
							<%=ServletUtility.getErrorMessage("login", request)%></font></td>
				</tr>
				<tr>
					<th>Password</th>
					<td><input type="password" name="password" size="30"
						value="<%=DataUtility.getStringData(dto.getPassword())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
				</tr>
				<tr>
					<th>Confirm Password</th>
					<td><input type="password" name="confirmPassword" size="30"
						value="<%=DataUtility.getStringData(dto.getPassword())%>"><font
						color="red"> <%=ServletUtility
					.getErrorMessage("confirmPassword", request)%></font></td>
				</tr>
				<tr>
					<th>Gender</th>
					<td>
						<%
							HashMap map = new HashMap();

							map.put("M", "Male");
							map.put("F", "Female");

							String htmlList = HTMLUtility.getList("gender", dto.getGender(),
									map);
						%> <%=htmlList%> <b>Role </b> <%=HTMLUtility.getList("roleId",
					String.valueOf(dto.getRoleId()), l)%></td>
				</tr>

				<tr>
					<th>Date Of Birth (mm/dd/yyyy)</th>
					<td><input type="text" name="dob" readonly="readonly" size=30
						value="<%=DataUtility.getDateString(dto.getDob())%>"> <a
						href="javascript:getCalendar(document.forms[0].dob);"> <img
							src="../imgages/cal.jpg" width="16" height="15" border="0"
							alt="Calender">
					</a><font color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
				</tr>
				<tr>
					<th></th>
					<td colspan="2"><input type="submit" name="operation"
						style="background-color: green; font-weight: bold; color: white;"
						value="<%=UserCtl.op_SAVE%>">&emsp; <input type="submit"
						name="operation"
						style="background-color: green; font-weight: bold; color: white;"
						value="<%=UserCtl.OP_CANCEL%>"></td>
				</tr>
			</table>
	</form>
	</center>
	<%--  <%@ include file="Footer.jsp"%> --%>
</body>
</html>
