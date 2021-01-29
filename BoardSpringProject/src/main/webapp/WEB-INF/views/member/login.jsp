<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

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
		padding-top:200px;
		height: 400px;
	}
	
	button {
		display: inline-block;
	}
</style>
</head>
<body>
	<div id="container">
		<jsp:include page="../template/header.jsp" flush="false"></jsp:include>
		
		<nav>
			<form action="login.do" method="post">
				아이디 : <input type="text" name="id">  <br>
				암호 : <input type="password" name="pass"> <br>
				<button type="submit">로그인</button>
				<a href="" type="button">회원가입</a>
				<a href="" type="button" >비밀번호 찾기</a>
			</form>
		</nav>
		
		<jsp:include page="../template/footer.jsp" flush="false"></jsp:include>
		
	</div>
</body>
</html>