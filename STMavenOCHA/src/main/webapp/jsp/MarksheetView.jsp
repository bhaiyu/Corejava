
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.ocha.controller.MarksheetCtl"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<html>
<body>
	<%-- <%@ include file="Header.jsp"%> --%>

	<jsp:useBean id="dto" class="in.co.sunrays.ocha.bean.MarksheetDTO"
		scope="request"></jsp:useBean>

	<%
		List list = (List) request.getAttribute("studentList");
	%>

	<center>
		<input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
		<input type="hidden" name="createdDateTime"
			value="<%=dto.getCreatedDatetime()%>"> <br>
		<%
			if (dto.getId() == 0) {
		%>
		<h1>
			<font style="color: black; font-size: 30px;">ADD MARKSHEET</font>
		</h1>

		<%
			}
			if (dto.getId() != 0 || dto.getId() > 0) {
		%>
		<h1>
			<font style="color: black; font-size: 30px;">EDIT MARKSHEET</font>
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
		<form action="<%=ORSView.MARKSHEET_CTL%>" method="post">

			<input type="hidden" name="id" value="<%=dto.getId()%>">

			<table cellpadding="4" cellspacing="4">
				<tr>
					<td><b>Roll No</b></td>
					<td><input type="text" name="rollNo"
						placeholder="Enter Roll NO" size="21"
						value="<%=DataUtility.getStringData(dto.getRollNo())%>"
						<%=(dto.getId() > 0) ? "readonly" : ""%>> <font
						color="red"> <%=ServletUtility.getErrorMessage("rollNo", request)%></font></td>
					</font>
					</td>
				</tr>
				<tr>
					<td><b>Name</b></td>
					<td><%=HTMLUtility.getList("studentId",
					String.valueOf(dto.getStudentId()), list)%></td>
				</tr>
				<tr>
					<td><b>Physics</b></td>
					<td><input type="text" name="physics"
						placeholder="Enter Physics Marks" size="21"
						value="<%=DataUtility.getStringData(dto.getPhysics())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("physics", request)%></font></td>
				</tr>
				<tr>
					<td><b>Chemistry</b></td>
					<td><input type="text" name="chemistry"
						placeholder="Enter Chemistry Marks" size="21"
						value="<%=DataUtility.getStringData(dto.getChemistry())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("chemistry", request)%></font></td>
				</tr>
				<tr>
					<td><b>Maths</b></td>
					<td><input type="text" name="maths"
						placeholder="Enter Maths Marks" size="21"
						value="<%=DataUtility.getStringData(dto.getMaths())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("maths", request)%></font></td>

				</tr>
				<tr>
					<th></th>
					<td colspan="2"><input type="submit" name="operation"
						style="background-color: green; font-weight: bold; color: white;"
						value="<%=MarksheetCtl.op_SAVE%>"> <%
 	if (dto.getId() > 0) {
 %><input type="submit" name="operation"
						style="background-color: green; font-weight: bold; color: white;"
						value="<%=MarksheetCtl.OP_DELETE%>"> <%
 	}
 %> &nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" name="operation"
						style="background-color: green; font-weight: bold; color: white;"
						value="<%=MarksheetCtl.OP_CANCEL%>"></td>
				</tr>
			</table>
		</form>
	</center>
	<%--   <%@ include file="Footer.jsp"%> --%>
</body>
</html>