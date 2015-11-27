<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.ocha.controller.CollegeListCtl"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.ocha.bean.CollegeDTO"%>
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
	<%-- <%@include file="Header.jsp"%>  --%>
	<center>

		<h1>College List</h1>
		<H2>
			<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
			</font>
		</H2>
		<H2>
			<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
			</font>
		</H2>
		<form action="CollegeListCtl.do">
			<table width="100%">
				<tr>
					<td align="center"><label> Name :</label> <input type="text"
						name="name" size="30"
						value="<%=ServletUtility.getParameter("name", request)%>" />&emsp;
						<label>City :</label> <input type="text" name="city" size="30"
						value="<%=ServletUtility.getParameter("city", request)%>" /> <input
						type="submit" name="operation"
						style="background-color: green; font-weight: bold; color: white;"
						value="<%=CollegeListCtl.OP_SEARCH%>" /></td>

				</tr>
			</table>
			<table border="1" width="100%">
				<tr>
					<th align="left"><input type="checkbox" onClick="check(this.form.Delete)">Mark
						All</th>
					<th>S.No</th>
					<!-- <th>ID</th> -->
					<th>Name</th>
					<th>Address</th>
					<th>State</th>
					<th>City</th>
					<th>PhoneNo</th>
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
					Iterator<CollegeDTO> it = list.iterator();

					while (it.hasNext()) {

						CollegeDTO dto = it.next();
				%>
				<tr>
					<td><input type="checkbox" NAME="Delete"
						VALUE='<%=dto.getId()%>'></td>
					<td><%=index++%></td>
					<%--  <td><%=dto.getId()%></td> --%>
					<td><%=dto.getName()%></td>
					<td><%=dto.getAddress()%></td>
					<td><%=dto.getState()%></td>
					<td><%=dto.getCity()%></td>
					<td><%=dto.getPhoneNo()%></td>
					<td><a href="CollegeCtl.do?id=<%=dto.getId()%>">Edit</a></td>
				</tr>
				<%
					}
				%>
			</table>
			<table width="100%">
				<tr>
					<td><input type="submit" name="operation"
						style="background-color: green; font-weight: bold; color: white;"
						value="<%=CollegeListCtl.OP_PREVIOUS%>" /></td>

					<td><input type="submit" name="operation"
						style="background-color: green; font-weight: bold; color: white;"
						value="<%=CollegeListCtl.OP_DELETE%>" /></td>

					<td align="right"><input type="submit" name="operation"
						style="background-color: green; font-weight: bold; color: white;"
						value="<%=CollegeListCtl.OP_NEXT%>" /></td>
				</tr>
			</table>
			<input type="hidden" name="pageNo" value="<%=pageNo%>" /> <input
				type="hidden" name="pageSize" value="<%=pageSize%>" />
		</form>
	</center>
	<%-- <%@include file="Footer.jsp"%> --%>
</body>
</html>
