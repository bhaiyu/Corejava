
<%@page import="in.co.sunrays.ocha.bean.UserDTO"%>
<%@page import="in.co.sunrays.ocha.controller.UserListCtl"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

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

	<%-- <%@include file="Header.jsp"%> --%>

	<center>
	
		<h1>User List</h1>

		<form action="<%=ORSView.USER_LIST_CTL%>">

			<table width="100%">
				<tr>
					<td align="center"><label>First Name :</label> <input
						type="text" name="firstName" size="25"
						value="<%=ServletUtility.getParameter("firstName", request)%>">
						&emsp; <label>Last Name :</label> <input type="text"
						name="lastName" size="25"
						value="<%=ServletUtility.getParameter("lastName", request)%>">
						&emsp; <label>Login Id:</label> <input type="text" name="login"
						size="25"
						value="<%=ServletUtility.getParameter("login", request)%>">
						&emsp; <input type="submit" name="operation"
						style="background-color: green; font-weight: bold; color: white;"
						value="<%=UserListCtl.OP_SEARCH%>"></td>
				</tr>
			</table>
			<br>

			<table border="1" width="100%">
				<tr>
					<th align="left"><input type="checkbox" onClick="check(this.form.Delete)">Mark All</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Login Id</th>
					<th>Gender</th>
					<th>DOB</th>
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
					Iterator<UserDTO> it = list.iterator();
					while (it.hasNext()) {
						UserDTO dto = it.next();
				%>
				<tr>
					<td><input type="checkbox" name="Delete" value="<%=dto.getId()%>"></td>
					<td><%=dto.getFirstName()%></td>
					<td><%=dto.getLastName()%></td>
					<td><%=dto.getLogin()%></td>
					<td><%=dto.getGender()%></td>
					<td><%=dto.getDob()%></td>
					<td><a href="UserCtl.do?id=<%=dto.getId()%>">Edit</a></td>
				</tr>
				<%
					}
				%>
			</table>
			<table width="100%">
				<tr>
					<td><input type="submit" name="operation"
						style="background-color: green; font-weight: bold; color: white;"
						value="<%=UserListCtl.OP_PREVIOUS%>"></td>
					<td><input type="submit" name="operation"
						style="background-color: green; font-weight: bold; color: white;"
						value="<%=UserListCtl.OP_DELETE%>"></td>
					<td align="right"><input type="submit" name="operation"
						style="background-color: green; font-weight: bold; color: white;"
						value="<%=UserListCtl.OP_NEXT%>"></td>
				</tr>
			</table>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
		</form>
	</center>
	
</body>
</html>