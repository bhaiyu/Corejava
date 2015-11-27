<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.ocha.controller.LoginCtl"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<html>
<body>
	<form action="<%=ORSView.LOGIN_CTL%>" method="post">
		<%-- <%@ include file="Header.jsp"%> --%>

		<jsp:useBean id="dto" class="in.co.sunrays.ocha.bean.UserDTO"
			scope="request"></jsp:useBean>

		<center>
			<h1>Login</h1>
		
			<H2>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</H2>

			<input type="hidden" name="id" value="<%=dto.getId()%>">

			<table>
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
						color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
				</tr>
				<tr>
					<th></th>
					<td colspan="2"><input type="submit" name="operation"
						style="background-color: green; font-weight: bold; color: white;"
						value="<%=LoginCtl.OP_SIGN_IN%>"> &nbsp;&nbsp;&nbsp;&nbsp;
						<input type="submit" name="operation"
						style="background-color: green; font-weight: bold; color: white;"
						value="<%=LoginCtl.OP_SIGN_UP%>"> &nbsp;</td>
				</tr>
				<tr>
					<th></th>
					<td><a href="<%=ORSView.FORGET_PASSWORD_CTL%>"><b>Forget
								my password</b></a>&nbsp;</td>
				</tr>
			</table>
	</form>
	</center>
	<%-- <%@ include file="Footer.jsp"%> --%>
</body>
</html>
