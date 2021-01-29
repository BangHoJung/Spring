<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		height: 400px;
		padding-top:200px;
	}	
</style>
</head>
<body>
	<div id="container">
		<jsp:include page="../template/header.jsp"></jsp:include>
		<!-- 아이디, 암호, 이름, 나이 입력받는 회원가입 폼 -->
		<nav>
			<form action="register_member.do" method="post">
				<label>아이디 <input type="text" name="id" placeholder="아이디를 입력하세요"></label> <br>
				<label>암호 <input type="password" name="pass"  placeholder="암호를 입력하세요"></label><br>
				<label>이름 <input type="text" name="name"  placeholder="이름을 입력하세요"></label><br>
				<label>나이 <input type="text" name="age"  placeholder="나이를 입력하세요"></label><br>
				<button type="submit">회원가입</button>
				<button type="button">뒤로가기</button>
			</form>
		</nav>
		<jsp:include page="../template/footer.jsp"></jsp:include>
	</div>
	
</body>
</html>