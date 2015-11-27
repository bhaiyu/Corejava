
<%@page import="in.co.sunrays.util.*"%>
<%@page import="in.co.sunrays.ocha.controller.CollegeCtl"%>
<html>
<body>
	<form action="CollegeCtl.do" method="post">
		<%-- <%@ include file="Header.jsp"%> --%>

		<jsp:useBean id="dto" class="in.co.sunrays.ocha.bean.CollegeDTO"
			scope="request"></jsp:useBean>

		<center>
			<input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
			<input type="hidden" name="createdDateTime"
				value="<%=dto.getCreatedDatetime()%>"> <br>

			<%
				if (dto.getId() == 0) {
			%>
			<h1>
				<font style="color: black; font-size: 30px;">ADD COLLEGE</font>
			</h1>

			<%
				}
				if (dto.getId() != 0 || dto.getId() > 0) {
			%>
			<h1>
				<font style="color: black; font-size: 30px;">EDIT COLLEGE</font>
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

			<table>
				<tr>
					<th>Name</th>
					<td><input type="text" name="name" size="30"
						value="<%=DataUtility.getStringData(dto.getName())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font></td>
				</tr>
				<tr>

					<th>Address</th>
					<td><textarea name="address" cols="22" rows="2">
                        <%=DataUtility.getStringData(dto.getAddress())%></textarea><font
						color="red"> <%=ServletUtility.getErrorMessage("address", request)%></font>


					</td>
				</tr>
				<tr>
					<th>State</th>
					<td><input type="text" name="state" size="30"
						value="<%=DataUtility.getStringData(dto.getState())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("state", request)%></font></td>
				</tr>
				<tr>
					<th>City</th>
					<td><input type="text" name="city" size="30"
						value="<%=DataUtility.getStringData(dto.getCity())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("city", request)%></font></td>
				</tr>
				<tr>
					<th>Mobile No</th>
					<td><input type="text" name="phoneNo" size="30" maxlength="10"
						value="<%=DataUtility.getStringData(dto.getPhoneNo())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("phoneNo", request)%></font></td>
				</tr>


				<tr>
					<th></th>
					<td colspan="2"><input type="submit" name="operation"
						style="background-color: green; font-weight: bold; color: white;"
						&emsp;  value="<%=CollegeCtl.op_SAVE%>"> <%
 	if (dto.getId() > 0) {
 %> &emsp;<input type="submit" name="operation"
						style="background-color: green; font-weight: bold; color: white;"
						value="<%=CollegeCtl.OP_DELETE%>"> <%
 	}
 %>&emsp; <input type="submit" name="operation"
						style="background-color: green; font-weight: bold; color: white;"
						value="<%=CollegeCtl.OP_CANCEL%>"></td>
				</tr>
			</table>
	</form>
	</center>
	<%-- <%@ include file="Footer.jsp"%> --%>
</body>





</html>