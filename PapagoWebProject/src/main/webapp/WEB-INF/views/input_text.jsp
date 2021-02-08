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
		$("#container a").click( function() {
			var data = $("#frm").serialize();
			$.ajax({
				url : 'translate.do',
				data : data,
				method : "get",
				success : function(result) {
					/* var json = JSON.parse(result);
					var msg = ""+json.message.result.translatedText;
					console.log(msg);
					$("#result p").html(msg); */
					$("#result p").html(result);
				},error : function(xhr,response,error) {
					console.log(xhr.status);
					switch(xhr.status) {
					case 1000:
						alert("IO EXCEPTION");
						break;
					default:
						alert("SYSTEM ERROR");
						break;
					}
				}
				
			});
			
		});
		
	});
</script>
<style type="text/css">
	* {
		margin:0;
		padding:0;
	}
	
	#container {
		box-sizing:border-box;
		margin: 0 auto;
		width:900px;
		height: 500px;
		position: relative;
	}
	
	#container select {
		margin-top:30px;
		font-size:20px;
		padding:5px;
		margin-left:40%;
	}
	
	#container > div {
		box-sizing:border-box;
		display:inline-block;
		width:400px;
		height:100%;
		float:left;
	}
	
	#container h2 {
		margin: 40px 0px;
		text-align: center;
	}
	
	#container p{
		margin: 0 auto;
		width:75.5%;
		height:60.5%;
		border:1px solid black;
		box-sizing: border-box;
	}
	
	#input {
		border-right:2px solid silver;
	}
	
	#result {
		border-left:2px solid silver;
	}
	
	img {
		position: relative;
		width:60px;
		height: 60px;
		right:48%;
		top:40%;
	}
	
	textarea {
		border-style: none;
		text-decoration: none;
	}
	
</style>
</head>
<body>
	<form id="frm" >
		<div id="container">
			<div id="input">
				<select name="source">
					<option value="ko" selected="selected">한국어</option>
					<option value="en">영어</option>
					<option value="ja">일본어</option>
				</select>
				<h2>번역할 내용</h2>
				<p>
					<textarea rows="20" cols="40" name="text"></textarea>
				</p>
			</div>
			<div id="result">
				<select name="target">
					<option value="ko">한국어</option>
					<option value="en" selected="selected">영어</option>
					<option value="ja">일본어</option>
				</select>
				<h2>번역된 내용</h2>
				<p>
				</p>
			</div>
			<a href="#none"><img src="/img/translate.png"></a>
		</div>
	</form>
</body>
</html>