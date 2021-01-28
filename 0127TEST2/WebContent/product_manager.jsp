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
				url: "productInput.do",
				data : param,
				method : 'get',
				success: function(result) {
					var rs = JSON.parse(result);
					var text="";
					$.each(rs.list, function(index, item) {
						text += item.pno + " , " + item.pname + " , " +item.maker + " , " +item.price + " , " +item.ea + "<br>";
					});
					$("div").html(text);
					
				},
				error: function(xhr,status,error) {
					//$("div").html(xhr.status + " "+ xhr.responseText +" " + status);
					switch(xhr.status) {
					case 500 :
					case 1001 :
						alert("입력한 데이터 오류");
						break;
					case 1002 :
						alert("금액과 제고는 숫자로 입력하세요");
						break;
					case 1003 :
						alert("금액이나 제고는 양수만 입력하세요");
						break;
					}
				}
				
			});
		});
	});
	
</script>
</head>
<body>
	<form id="frm" method="get">
		<input type="text" name="pno" placeholder="상품번호 입력">
		<input type="text" name="pname" placeholder="상품명 입력">
		<input type="text" name="maker" placeholder="제조사 입력">
		<input type="text" name="price" placeholder="금액 입력">
		<input type="text" name="ea" placeholder="재고 개수 입력">
		<button type="button">상품등록</button>
	</form>
	<hr>
	<div>
		
	</div>
</body>
</html>