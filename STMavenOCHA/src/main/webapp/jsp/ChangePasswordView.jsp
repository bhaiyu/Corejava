<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.ocha.controller.ChangePasswordCtl"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<html>
<body>
	<form action="<%=ORSView.CHANGE_PASSWORD_CTL%>">
		<%--     <%@ include file="Header.jsp"%> --%>
		<jsp:useBean id="dto" class="in.co.sunrays.ocha.bean.UserDTO"
			scope="request"></jsp:useBean>

		<center>
			<input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
			<input type="hidden" name="createdDateTime"
				value="<%=dto.getCreatedDatetime()%>">
			<h1>Change Password</h1>

			<H2>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</H2>

			<input type="hidden" name="id" value="<%=dto.getId()%>">

			<table>

				<tr>
					<th>Old Password*</th>
					<td><input type="password" name="oldPassword"
						value=<%=DataUtility
					.getString(request.getParameter("oldPassword") == null ? ""
							: DataUtility.getString(request
									.getParameter("oldPassword")))%>><font
						color="red"> <%=ServletUtility.getErrorMessage("oldPassword", request)%></font></td>
				</tr>

				<tr>
					<th>New Password*</th>
					<td><input type="password" name="newPassword"
						value=<%=DataUtility
					.getString(request.getParameter("newPassword") == null ? ""
							: DataUtility.getString(request
									.getParameter("newPassword")))%>><font
						color="red"> <%=ServletUtility.getErrorMessage("newPassword", request)%></font></td>
				</tr>

				<tr>
					<th>Confirm Password*</th>
					<td><input type="password" name="confirmPassword"
						value=<%=DataUtility.getString(request
					.getParameter("confirmPassword") == null ? "" : DataUtility
					.getString(request.getParameter("confirmPassword")))%>><font
						color="red"> <%=ServletUtility
					.getErrorMessage("confirmPassword", request)%></font></td>
				</tr>

				<tr>
					<th></th>
					<td colspan="2"><input type="submit" name="operation"
						style="background-color: green; font-weight: bold; color: white;"
						value="<%=ChangePasswordCtl.OP_CHANGE_MY_PROFILE%>">
						&nbsp; <input type="submit" name="operation"
						style="background-color: green; font-weight: bold; color: white;"
						value="<%=ChangePasswordCtl.op_SAVE%>"> &nbsp;</td>
				</tr>

			</table>
			<H3>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
			</H3>
	</form>
	</center>
	<%--   <%@ include file="Footer.jsp"%> --%>
</body>
</html>