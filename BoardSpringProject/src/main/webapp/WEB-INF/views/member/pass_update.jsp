<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="/error/error.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	* {
		margin:0;
		padding:0;
	}
	
	#container {
		width:100%;
		text-align: center;
	}
	
	nav {
		height: 600px;
	}
	
	form {
		padding-top:200px;
	}
	
	button {
		display: inline-block;
	}
</style>
</head>
<body>
	<%-- <%
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		
		MemberService.getInstance().searchMemberVO(id,name);
		
	%>
	<div id="container">
		<jsp:include page="../template/header.jsp" flush="false"></jsp:include>
		
		<nav>
			<form action="<%=request.getContextPath() %>/member/process/pass_update_process.jsp" method="post">
				아이디 : <input type="text" name="id" value=<%=id %> readonly><br>
				새 암호 : <input type="password" name="pass_update" required><br>
				<button type="submit">제출</button>
			</form>
		</nav>
		<jsp:include page="../template/footer.jsp" flush="false"></jsp:include>
		
	</div> --%>
</body>
</html>

	


