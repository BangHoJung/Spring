<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/lib/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#btn_submit").click(function() {
			var data = $("#frm").serialize();
			$.ajax({
				url: "search_all.do",
				data : data,
				method : 'get',
				success : function result(data) {
					var json = JSON.parse(data);
					console.log(json);
					var arr = json.result;
					var str = "";
					if(json.code == 500) {
						str = json.message;
					}
					else {
						for(i=0;i<arr.length;i++) {
							str += arr[i].sno +"\t"+arr[i].name +"\t"+arr[i].major +"\t"+arr[i].score+"<br>";
						}
					}
					$("div").html(str);
					
				}
			});
		});
		
		$("#btn_submit").click();
	});
</script>
</head>
<body>
	<form action="" id="frm">
		<select name="kind">
			<option value="sno">학번</option>
			<option value="name">이름</option>
			<option value="major">학과</option>
		</select> <input type="text" name="search">
		<button id="btn_submit" type="button">검색</button>
		<button type="reset">리셋</button>
	</form>
	<hr>
	<div>
		
	</div>
</body>
</html>