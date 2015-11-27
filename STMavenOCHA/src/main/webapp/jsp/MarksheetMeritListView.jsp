
<%@page import="in.co.sunrays.ocha.controller.MarksheetMeritListCtl"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.ocha.bean.MarksheetDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<html>
<body>
	<%-- <%@include file="Header.jsp"%> --%>
	<center>
		<h1>Marksheet Merit List</h1>

		<form action="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>">

			<table border="1" width='100%' >
				<tr>

					<th>S.No.</th>
					<!-- <th>Id</th> -->
					<th>Roll No</th>
					<th>Name</th>
					<th>Physics</th>
					<th>Chemistry</th>
					<th>Maths</th>
					<th>Total</th>
				</tr>
				<tr>
					<td colspan="8"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td>
				</tr>
				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;

					List list = ServletUtility.getList(request);
					Iterator<MarksheetDTO> it = list.iterator();

					while (it.hasNext()) {

						MarksheetDTO dto = it.next();
				%>
				<tr>
					<td><%=index++%></td>
					<%-- 	<td><%=dto.getId()%></td> --%>
					<td><%=dto.getRollNo()%></td>
					<td><%=dto.getName()%></td>
					<td><%=dto.getPhysics()%></td>
					<td><%=dto.getChemistry()%></td>
					<td><%=dto.getMaths()%></td>
					<td><%=dto.getMaths()+dto.getChemistry()+dto.getPhysics() %></td>
				</tr>

				<%
					}
				%>
			</table>
			<table>
				<tr>
					<td align="right"><input type="submit" name="operation"
						style="background-color: green; font-weight: bold; color: white;"
						value="<%=MarksheetMeritListCtl.OP_BACK%>"></td>
				</tr>
			</table>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
		</form>
	</center>
	<%-- <%@include file="Footer.jsp"%> --%>
</body>
</html>