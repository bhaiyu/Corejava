
<%@page import="in.co.sunrays.ocha.bean.UserDTO"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<html>
<body>
	<table width="100%" border="1">
		<tr>
			<td>
				<table>
					<tr>
						<td width="90%"><a href="<%=ORSView.WELCOME_CTL%>"><b>Welcome</b></a>&nbsp;&nbsp;&nbsp;

							<%
								UserDTO user = (UserDTO) session.getAttribute("user");
								if (user == null) {
							%> <a href="<%=ORSView.LOGIN_CTL%>"><b>Login</b></a>&nbsp;&nbsp;&nbsp;&nbsp;

							<%
								}
							%> <%
 					UserDTO user1 = (UserDTO) session.getAttribute("user");
 					System.out.println("Login:" + user);
 					if (user1 != null) {
 						if (user.getRoleId() == 1) {
 							%>							
 							<a href="<%=ORSView.MARKSHEET_CTL%>"><b>Add Marksheet</b></a>&nbsp;&nbsp;
							<a href="<%=ORSView.MARKSHEET_LIST_CTL%>"><b>MarksheetList</b></a>&nbsp;&nbsp; 
							<a href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><b>Marksheet Merit List</b></a>&nbsp;&nbsp; 
							<a href="<%=ORSView.GET_MARKSHEET_CTL%>"><b>GetMarksheet</b></a>&nbsp;&nbsp;
							<a href="<%=ORSView.USER_CTL%>"><b>Add User</b></a>&nbsp;&nbsp; 
							<a href="<%=ORSView.USER_LIST_CTL%>"><b>User List</b></a>&nbsp;&nbsp; 
							<a href="<%=ORSView.COLLEGE_CTL%>"><b>Add College</b></a>&nbsp;&nbsp; 
							<a href="<%=ORSView.COLLEGE_LIST_CTL%>"><b>College List</b></a>&nbsp;&nbsp; 
							<a href="<%=ORSView.STUDENT_CTL%>"><b>Add Student</b></a>&nbsp;&nbsp; 
							<a href="<%=ORSView.STUDENT_LIST_CTL%>"><b>Student List</b></a>&nbsp;&nbsp; 
							<a href="<%=ORSView.ROLE_CTL%>"><b>Add Role</b></a>&nbsp;&nbsp; 
							<a href="<%=ORSView.ROLE_LIST_CTL%>"><b>Role List</b></a>&nbsp;&nbsp; 
							<a href="<%=ORSView.JAVA_DOC_VIEW%>"><b>Java Doc</b></a>&nbsp;&nbsp;<br>
							<h3>
								<font color="green"> Welcome : <%=user.getFirstName()%>&nbsp;[<%=session.getAttribute("role")%>]
								</font>
							</h3> 
							
							<a href="<%=ORSView.LOGIN_CTL%>?operation=logout"><b>Logout</b></a>&nbsp;&nbsp;
							<a href="<%=ORSView.MY_PROFILE_CTL%>"><b>MyProfile</b></a>&nbsp;&nbsp;
							<a href="<%=ORSView.CHANGE_PASSWORD_CTL%>"><b>Change Password</b></a>&nbsp;&nbsp; <br> <%
 							}
 					if (user.getRoleId() == 2) {
 							%> 						
 							<a href="<%=ORSView.FORGET_PASSWORD_CTL%>"><b>Forget Password</b></a>&nbsp;&nbsp; 
							<a href="<%=ORSView.GET_MARKSHEET_CTL%>"><b>Get Marksheet</b></a>&nbsp;&nbsp; 
							<a href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><b>Marksheet Merit List</b></a>&nbsp;&nbsp;
							<a href="<%=ORSView.JAVA_DOC_VIEW%>"><b>Java Doc</b></a>&nbsp;&nbsp; 
							
							<h3>
								<font color="green"> Welcome : <%=user.getFirstName()%>&nbsp;[<%=session.getAttribute("role")%>]
								</font>
							</h3> 
							<a href="<%=ORSView.LOGIN_CTL%>?operation=logout"><b>Logout</b></a>&nbsp;&nbsp;
							<a href="<%=ORSView.MY_PROFILE_CTL%>"><b>MyProfile</b></a>&nbsp;&nbsp;
							<a href="<%=ORSView.CHANGE_PASSWORD_CTL%>"><b>Change Password</b></a>&nbsp;&nbsp; <br>
							<%
								}
					if (user.getRoleId() == 3) {
							%> 
							
							
							<a href="<%=ORSView.STUDENT_CTL%>"><b>Add Student</b></a>&nbsp;&nbsp; 
							<a href="<%=ORSView.STUDENT_LIST_CTL%>"><b>Student List</b></a>&nbsp;&nbsp;
							<a href="<%=ORSView.MARKSHEET_CTL%>"><b>Add Marksheet</b></a>&nbsp;&nbsp;
							<a href="<%=ORSView.MARKSHEET_LIST_CTL%>"><b>MarksheetList</b></a>&nbsp;&nbsp;  
							 <a href="<%=ORSView.GET_MARKSHEET_CTL%>"><b>GetMarksheet</b></a>&nbsp;&nbsp;
							<a href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><b>Marksheet Merit List</b></a>&nbsp;&nbsp; 
							<a href="<%=ORSView.JAVA_DOC_VIEW%>"><b>Java Doc</b></a>&nbsp;&nbsp;

							<h3>
								<font color="green"> Welcome : <%=user.getFirstName()%>&nbsp;[<%=session.getAttribute("role")%>]
								</font>
							</h3> 
							<a href="<%=ORSView.LOGIN_CTL%>?operation=logout"><b>Logout</b></a>&nbsp;&nbsp;
						    <a href="<%=ORSView.MY_PROFILE_CTL%>"><b>MyProfile</b></a>&nbsp;&nbsp;
							<a href="<%=ORSView.CHANGE_PASSWORD_CTL%>"><b>Change Password</b></a>&nbsp;&nbsp;
							<%
								}
					if (user.getRoleId() == 4) {
							%> 
							
							<a href="<%=ORSView.FORGET_PASSWORD_CTL%>"><b>Forget Password</b></a>&nbsp;&nbsp; 
							<a href="<%=ORSView.GET_MARKSHEET_CTL%>"><b>Get Marksheet</b></a>&nbsp;&nbsp; 
							<a href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><b>Marksheet Merit List</b></a>&nbsp;&nbsp; 
							<a href="<%=ORSView.JAVA_DOC_VIEW%>"><b>Java Doc</b></a>&nbsp;&nbsp;

							<h3>
								<font color="green"> Welcome : <%=user.getFirstName()%>&nbsp;[<%=session.getAttribute("role")%>]
								</font>
							</h3> 
							<a href="<%=ORSView.LOGIN_CTL%>?operation=logout"><b>Logout</b></a>&nbsp;&nbsp;
							<a href="<%=ORSView.MY_PROFILE_CTL%>"><b>MyProfile</b></a>&nbsp;&nbsp;
							<a href="<%=ORSView.CHANGE_PASSWORD_CTL%>"><b>Change Password</b></a>&nbsp;&nbsp;
							<%
								}

						} else {

								}
							%></td>
						<td width="20%">
							<h1 align="Right">
								<img src="../img/customLogo.png" width="200" height="70">
							</h1>
						</td>
					</tr>
				</table>
				<hr>
</body>
</html>