<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#emp").click(function() {
			$.ajax({
				url : "search_all_employee.do",
				method : "get",
				success : function(result) {
					console.log(result);
					$("div").html(result);
				}
			});
		});
	});
	
</script>
</head>
<body>

	<a href="#" id="emp">사원 정보</a>
	<div>
		
	</div>
</body>
</html>