<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#ajax").click(function() {
			$.ajax({
				url : "ajax.do",
				method : "get",
				success : function(result) {
					alert(result);
				}
			});
		});
	});
	
</script>
</head>
<body>
 	첫번째 페이지
 	<button id="ajax">ajaxTest</button>
 	<form action="login_action.do" method="post">
 		아이디 : <input type="text" name="id">
 		암호 : <input type="password" name="pass">
 		<button>전송</button>
 	</form>
 	<div>
 		${sessionScope.id } , ${sessionScope.grade }
 	</div>
</body>
</html>