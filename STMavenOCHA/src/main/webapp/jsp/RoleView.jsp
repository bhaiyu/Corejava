<%@page import="in.co.sunrays.ocha.controller.RoleCtl"%>
<%@page import="in.co.sunrays.ocha.controller.BaseCtl"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<html>
<body>
	<form action="<%=ORSView.ROLE_CTL%>">

		<%-- <%@ include file="Header.jsp"%> --%>
		<jsp:useBean id="dto" class="in.co.sunrays.ocha.bean.RoleDTO"
			scope="request"></jsp:useBean>
		<center>
			<input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
			<input type="hidden" name="createdDateTime"
				value="<%=dto.getCreatedDatetime()%>"> <br>
			<marquee direction="left">
				<font style="color: red; font-weight: bold; font-size: 20px;">Welcome
					to Online Result System</font>
			</marquee>
			<%
				if (dto.getId() == 0) {
			%>
			<h1>
				<font style="color: black; font-size: 30px;">ADD ROLE</font>
			</h1>

			<%
				}
				if (dto.getId() != 0 || dto.getId() > 0) {
			%>
			<h1>
				<font style="color: black; font-size: 30px;">EDIT ROLE</font>
			</h1>

			<%
				}
			%>

			<H2>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
			</H2>
			<H2>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</H2>

			<input type="hidden" name="id" value="<%=dto.getId()%>">

			<table cellpadding="3" cellspacing="3">
				<tr>
					<td><b>Name</td>
					<td><input type="text" name="name" size="32"
						placeholder="Enter Name"
						value="<%=DataUtility.getStringData(dto.getName())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font></td>
				</tr>
				<tr>
					<td><b>Description</td>
					<td><textarea cols="24" rows="3" name="description"
							placeholder="Enter Description"><%=DataUtility.getStringData(dto.getDescription())%></textarea><font
						color="red"> <%=ServletUtility.getErrorMessage("description", request)%></font></td>

				</tr>
				<tr>
					<th></th>
					<td colspan="3"><input type="submit" name="operation"
						style="background-color: green; font-weight: bold; color: white;"
						value="<%=RoleCtl.op_SAVE%>">&emsp;&emsp;&emsp;&emsp;&emsp;
						<input type="submit" name="operation"
						style="background-color: green; font-weight: bold; color: white;"
						value="<%=RoleCtl.OP_CANCEL%>"></td>
				</tr>
			</table>
	</form>
	</center>
	<%-- <%@ include file="Footer.jsp"%> --%>
</body>
</html>