<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.ocha.controller.RoleListCtl"%>
<%@page import="in.co.sunrays.ocha.controller.RoleCtl"%>
<%@page import="in.co.sunrays.ocha.bean.RoleDTO"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
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
	<%-- <%@include file="Header.jsp"%> --%>
	<center>
		<h1>Role List</h1>
		<form action="RoleListCtl.do">
			<table width="100%">
				<tr>
					<td align="center"><label>Name :</label> <input type="text"
						name="name"
						value="<%=ServletUtility.getParameter("name", request)%>">
						&nbsp; <input type="submit" name="operation"
						style="background-color: green; font-weight: bold; color: white;"
						value="<%=RoleListCtl.OP_SEARCH%>"></td>
				</tr>
			</table>
			<table border="1" width="100%">
				<tr>
					<th align="left"><input type="checkbox" onClick="check(this.form.Delete)">Mark All</th>
					<th>S.No.</th>
					<!-- <th>ID</th> -->
					<th>Name</th>
					<th>Description</th>
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
					Iterator<RoleDTO> it = list.iterator();
					while (it.hasNext()) {
						RoleDTO dto = it.next();
				%>
				<tr>
					<td><input type="checkbox" NAME="Delete" VALUE='<%=dto.getId()%>'></td>
					<td><%=index++%></td>
					<%-- <td><%=dto.getId()%></td> --%>
					<td><%=dto.getName()%></td>
					<td><%=dto.getDescription()%></td>
					<td><a href="RoleCtl.do?id=<%=dto.getId()%>">Edit</a></td>
				</tr>
				<%
					}
				%>
			</table>
			<table width="100%">
				<tr>
					
					<td><input type="submit" name="operation"
						style="background-color: green; font-weight: bold; color: white;"
						value="<%=RoleListCtl.OP_PREVIOUS%>"></td>
					<td></td>
					<td><input type="submit" name="operation"
						style="background-color: green; font-weight: bold; color: white;"
						value="<%=RoleListCtl.OP_DELETE%>"></td>
					<td></td>
					<td align="right"><input type="submit" name="operation"
						style="background-color: green; font-weight: bold; color: white;"
						value="<%=RoleListCtl.OP_NEXT%>"></td>
				</tr>
			</table>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
		</form>
	</center>
	<%-- <%@include file="Footer.jsp"%> --%>
</body>
</html>
