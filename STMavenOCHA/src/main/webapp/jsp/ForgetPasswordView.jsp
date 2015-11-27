<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.ocha.controller.ForgetPasswordCtl"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<html>
<body>
	<form action="<%=ORSView.FORGET_PASSWORD_CTL%>">

		<%--  <%@ include file="Header.jsp"%>
 --%>
		<jsp:useBean id="dto" class="in.co.sunrays.ocha.bean.UserDTO"
			scope="request"></jsp:useBean>

		<center>
			<input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
			<input type="hidden" name="createdDateTime"
				value="<%=dto.getCreatedDatetime()%>">
			<h1>Forgot your password?</h1>
			<input type="hidden" name="id" value="<%=dto.getId()%>">

			<table>
				<label>Submit your email address and we'll send you
					password.</label>
				<br>
				<br>
				<label>Email Id :</label>&emsp;
				<input type="text" name="login" placeholder="Enter ID Here " size=30
					value="<%=ServletUtility.getParameter("login", request)%>">&emsp;
				<input type="submit" name="operation"
					style="background-color: green; font-weight: bold; color: white;"
					value="<%=ForgetPasswordCtl.OP_GO%>">
				<br>
				<font color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font>
			</table>

			<H2>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
			</H2>
			<H2>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</H2>
	</form>

	<%--  <%@ include file="Footer.jsp"%> --%>
</body>
</html>