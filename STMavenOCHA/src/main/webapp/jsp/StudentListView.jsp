<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.ocha.controller.StudentListCtl"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.ocha.bean.StudentDTO"%>
<%@page import="in.co.sunrays.ocha.controller.StudentListCtl"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	var checkflag = "false";
	function check(field) {
		if (checkflag == "false") {
			for (var i = 0; i < field.length; i++) {
				field[i].checked = true;
			}
			checkflag = "true";
			return;
		} else {
			for (i = 0; i < field.length; i++) {
				field[i].checked = false;
			}
			checkflag = "false";
			return;
		}
	}
</script>

</head>

<body>
	<%--  <%@include file="Header.jsp"%> --%>
	<center>
		<h1>Student List</h1>

		<form action="<%=ORSView.STUDENT_LIST_CTL%>">
			<table width="100%">
				<tr>
					<td align="center"><label> First Name :</label> <input
						type="text" name="firstName" size="25"
						value="<%=ServletUtility.getParameter("firstName", request)%>">&emsp;
						<label>Last Name :</label> <input type="text" name="lastName"
						size="25"
						value="<%=ServletUtility.getParameter("lastName", request)%>">&emsp;
						<label>Email Id :</label> <input type="text" name="email"
						size="25"
						value="<%=ServletUtility.getParameter("email", request)%>">&emsp;
						<input type="submit" name="operation"
						style="background-color: green; font-weight: bold; color: white;"
						value="<%=StudentListCtl.OP_SEARCH%>"></td>
				</tr>
			</table>
			<br>
			<table border="1" width="100%">
				<tr>
					<th align="left"><input type="checkbox" onClick="check(this.form.Delete)">Mark
						All</th>
					<th>S.No</th>
					<!--  <th>ID</th> -->
					<!-- <th>College</th> -->
					<th>First Name</th>
					<th>Last Name</th>
					<th>Date Of Birth</th>
					<th>Mobil No</th>
					<th>Email ID</th>
					<th>Edit</th>
				</tr>
				<tr>
					<td colspan="8"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td>
				</tr>
				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;

					List list = ServletUtility.getList(request);
					Iterator<StudentDTO> it = list.iterator();

					while (it.hasNext()) {

						StudentDTO dto = it.next();
				%>
				<tr>
					<td><input type="checkbox" name="Delete"
						value="<%=dto.getId()%>"></td>
					<td><%=index++%></td>
					<%--  <td><%=dto.getId()%></td> --%>
					<%--  <td><%=dto.getCollegeId()%></td> --%>
					<td><%=dto.getFirstName()%></td>
					<td><%=dto.getLastName()%></td>
					<td><%=dto.getDob()%></td>
					<td><%=dto.getMobileNo()%></td>
					<td><%=dto.getEmail()%></td>
					<td><a href="StudentCtl.do?id=<%=dto.getId()%>">Edit</a></td>
				</tr>
				<%
					}
				%>
			</table>
			<table width="100%">
				<tr>
					<td><input type="submit" name="operation"
						style="background-color: green; font-weight: bold; color: white;"
						value="<%=StudentListCtl.OP_PREVIOUS%>"></td>
					<td><input type="submit" name="operation"
						style="background-color: green; font-weight: bold; color: white;"
						value="<%=StudentListCtl.OP_DELETE%>" /></td>
					<td align="right"><input type="submit" name="operation"
						style="background-color: green; font-weight: bold; color: white;"
						value="<%=StudentListCtl.OP_NEXT%>"></td>
				</tr>
			</table>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"><input
				type="hidden" name="pageSize" value="<%=pageSize%>">

		</form>
	</center>
</body>
</html>