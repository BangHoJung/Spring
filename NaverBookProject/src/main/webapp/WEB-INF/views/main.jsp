<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="lib/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#btn_search").click( function() {
			var data = "search="+$("#search").val();
			$.ajax({
				url : "search_book.do",
				data : data,
				method : "get",
				success : function(result) {
					var json = JSON.parse(result);
					console.log(json);
					var arr = json.resultArr;
					console.log(arr);
					var str = "";
					for(i=0;i<arr.length;i++) {
						str += "<div><img src='"+arr[i].image+"'>";
						str += "<span><a href='"+arr[i].link+"'><h3>"+arr[i].title +"</h3></a>";
						str += "저자 : "+arr[i].author + " , 출판사 : "+arr[i].publisher+"<br>";
						str += "<p>"+arr[i].description+"</p></span></div>";
					}
					$("article").html(str);
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
		border:1px solid black;
		box-sizing:border-box;
		text-align:center;
		margin: 0 auto;
		width:1000px;
		padding-top:20px;
	}
	
	nav {
		width:100%;
		font-size: 25px;
	}
	
	input {
		font-size: 25px;
	}
	
	button {
		font-size:22px;
	}
	
	article {
		font-size:15px;
		text-decoration: none;
		text-align: left;
		
	}
	article p{
		font-size:12px;
		line-height: 15px;
		
	}
	article div {
		margin:15px 0px;
		display: inline-block;
		position: static;
		line-height: 30px;
		
		overflow:hidden; 
		text-overflow:ellipsis; 
		white-space:normal;
		word-wrap: break-word;
	}
	article img {
		width:100px;
		height:100px;
		display: inline-block;
		float: left;
		position: relative;
		padding:0px 20px;
	}
	
	article span {
		width:850px;
		display: inline-block;
		float: left;
		position: relative;
	}
	
	article a:link,article a:visited {
		text-decoration: none;
		color:black;
		text-align: left;
		white-space: nowrap;
	}
	article a:hover {
		color:blue;
	}
</style>
</head>
<body>
	<div id="container">
		<nav>
			<input type="text" id="search" placeholder="도서명 입력해주세요"> <button id="btn_search">검색</button>
		</nav>
		<br><hr><br>
		<article>
			
		</article>
	</div>

</body>
</html>