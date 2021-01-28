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
		$("button").click(function() {
			var param = $("#frm").serialize();
			$.ajax({
				url: "search.do",
				data : param,
				method : 'get',
				success: function(result) {
					$("div").html(result);
				},
				error: function(xhr,status,error) {
					$("div").html(xhr.status + " "+ xhr.responseText +" " + status);
				}
				
			});
		});
	});
	
</script>
</head>
<body>
	<form action="" id="frm">
		이름 : <input type="text" name="name" > <button type="button">검색</button>
	</form>
	<hr>
	<div>
		
	</div>
</body>
</html>