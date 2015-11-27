<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.ocha.controller.MyProfileCtl"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>



<html>
<body>

	<form action="<%=ORSView.MY_PROFILE_CTL%>">

		<%-- <%@ include file="Header.jsp"%> --%>
		<script type="text/javascript" src="../js/calendar.js"></script>
		<jsp:useBean id="dto" class="in.co.sunrays.ocha.bean.UserDTO"
			scope="request"></jsp:useBean>

		<center>
			<input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
			<input type="hidden" name="createdDateTime"
				value="<%=dto.getCreatedDatetime()%>">
			<h1>My Profile</h1>

			<H2>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</H2>
			<input type="hidden" name="id" value="<%=dto.getId()%>">

			<table>
				<tr>
					<th>LoginId</th>
					<td><input type="text" name="login"
						value="<%=DataUtility.getStringData(dto.getLogin())%>"
						readonly="readonly"><font color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
				</tr>

				<tr>
					<th>First Name</th>
					<td><input type="text" name="firstName"
						value="<%=DataUtility.getStringData(dto.getFirstName())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
				</tr>
				<tr>
					<th>Last Name</th>
					<td><input type="text" name="lastName"
						value="<%=DataUtility.getStringData(dto.getLastName())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
				</tr>
				<tr>
					<th>Gender</th>
					<td>
						<%
							HashMap map = new HashMap();
							map.put("S", "Select");
							map.put("M", "Male");
							map.put("F", "Female");

							String htmlList = HTMLUtility.getList("gender", dto.getGender(),
									map);
						%> <%=htmlList%>
					</td>
				</tr>
				<tr>
					<th>Mobile No</th>
					<td><input type="text" name="mobileNo" maxlength="10"
						value="<%=DataUtility.getStringData(dto.getMobileNo())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("mobileNo", request)%></font></td>
				</tr>

				<tr>
					<th>Date Of Birth(mm/dd/yyyy)</th>
					<td><input type="text" name="dob" readonly="readonly"
						value="<%=DataUtility.getDateString(dto.getDob())%>"> <a
						href="javascript:getCalendar(document.forms[0].dob);"> <img
							src="../imgages/cal.jpg" width="16" height="15" border="0"
							alt="Calender">
					</a><font color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
				</tr>

				<H2>
					<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
					</font>
				</H2>

				<tr>
					<th></th>
					<td colspan="2"><input type="submit" name="operation"
						style="background-color: green; font-weight: bold; color: white;"
						value="<%=MyProfileCtl.OP_CHANGE_MY_PASSWORD%>"> &nbsp; <input
						type="submit" name="operation"
						style="background-color: green; font-weight: bold; color: white;"
						value="<%=MyProfileCtl.op_SAVE%>"> &nbsp;</td>
				</tr>
			</table>
	</form>
	</center>
	<%-- <%@ include file="Footer.jsp"%> --%>
</body>
</html>