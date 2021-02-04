<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Enumeration"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		
		$(".btn_like_hate").click(function() {
			if(${sessionScope.login eq null or sessionScope.login == false}) {
				alert("로그인이 필요합니다.");
			}
			else {
				var data = "bno=${param.bno}&lh=" + $(this).attr("id");
				var obj = $(this);
				$.ajax({
					url : "update_like_hate.do",
					data : data,
					method : 'get',
					success : function(d) {
						d = d.trim();
						console.log(d);
						$(obj).children("span").html(d);
					},
					error:function(request, status, error) {
						console.log("error_code ="+request.status);
						console.log("message ="+request.responseText);
						console.log("error ="+error);
						console.log("status="+status);
					}
				});
			}
		});
		
		$(".like_hate").click(function() {
			if(${sessionScope.login == null or sessionScope.login == false}) {
				alert("로그인이 필요합니다");
			}
			else {
				var obj = $(this);
				var data = "cno="+obj.siblings("input").val() + "&lh="+$(this).attr("id");
				console.log(obj.siblings("input").val());
				$.ajax({
					url:'update_comment_like_hate.do',
					data : data,
					mehtod: 'get',
					success : function(d) {
						d = d.trim();
						$(obj).children("span").html(d);
					}
				});
			}
		});
		
		$("textarea").keyup(function() {
			$(".length").html( $(this).val().length+"/500");
		});
		
		
		$("#comment_write a").click(function() {
			var data = $("#comment").serialize();
			console.log(data);
			$.ajax({
				url:'insert_comment.do',
				data:data,
				method:'get',
				success: function(d) {
					location.reload();
				}
			});
		});
		
		$("#file_view button").click(function() {
			console.log($(this));
			$(this).siblings("span").toggle();
		});
		
		$("#comment_sort").change(function() {
			alert($(this).val());
			var data = "sort=" + $(this).val() + "&bno=${param.bno}";
			$.ajax({
				url:"comment_view_sort.do",
				data:data,
				method:"get",
				success : function(result) {
					var json = JSON.parse(result);
					console.log(json.comment_list.length);
					
					var str = "";
					for(i=0;i<json.comment_list.length;i++) {
						str += "<div><input type='hidden' name='cno' value='"+json.comment_list[i].cno+"'>";
						str += "<h3>"+json.comment_list[i].writer+"</h3>";
						str += "<p>"+json.comment_list[i].date+"</p><p>"+json.comment_list[i].content+"</p>";
						if(${sessionScope.login != null and sessionScope.login == true}) {
							if(json.comment_list[i].writer == "${sessionScope.id}") {
								str += "<a href='#none'>수정</a> / <a href='#none'>삭제</a>";
							}
						}
						str += "<a href='#none' class='like_hate' id='chate'><img src='/img/hate.png'><span>"+json.comment_list[i].hate+"</span></a>";
						str += "<a href='#none' class='like_hate' id='chate'><img src='/img/like.png'><span>"+json.comment_list[i].like+"</span></a></div>";
					}
					$("#comment_view").html(str);
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
		width:100%;
	}
	
	nav {
		margin:0 auto;
		margin-top:20px;
		margin-bottom:20px;
		width:800px;
	}
	
	nav table{
		width:800px;
		border-collapse: collapse;
		margin:0 auto;
	}
	nav th{
		width:100px !important;
		text-align: right;
		padding:5px;
	}
	nav td{
		width: 500px;
		padding:5px;
		height: 40px;
	}
	nav td > input{
		width: 100%;
		height:40px;
		box-sizing:border-box;
		border-width : 1px;
		border-radius: 5px;
	}
	h2{
		text-align: center;
	}
	.btn{
		text-decoration: none;
		background-color: #e8e8e8;
		display: inline-block;
		margin:5px 10px;
		padding:5px 20px;
		font-weight:normal;
		border : 1px solid #585858;
		text-align:center;
		color : black;
		font-size:14px;
		box-sizing: border-box;
	}
	.btn:hover{
		background-color: #282828;
		color:#FFFFFF
	}
	td > div {
		width:100%;
		height: 300px;
		resize: none;
		box-sizing: border-box;
	}
	
	td img {
		width:30px;
		height: 30px;
		flaot:left;
		padding-right:10px;
	}
	
	td span {
		float:right;
		font-size:20px;
		padding:0px 15px;
	}
	
	#delete {
		float:right;
	}
	
	#update {
		float:right;
	}
	
		
	.btn_like_hate {
		text-decoration: none;
		display: inline-block;
		margin:5px 10px;
		padding:5px 10px;
		font-weight:normal;
		text-align:center;
		color : black;
		font-size:14px;
		box-sizing: border-box;
		border-radius: 10px;
		background-color: white;
		margin-left:15%;
	}
	
	#like {
		color: blue;
		border:2px solid blue;
	}
	
	#hate {
		color: red;
		border:2px solid red;
	}

	
	#prev {
		float: left;
	}
	
	#next {
		float: right;
	}
	
	#list {
		float:left;
		margin-left:30%;
	}
	
	#comment_write {
		border:1px solid gray;
		margin:0 auto;
		width:800px;
		padding:20px;
		padding-bottom:0px;
		box-sizing: border-box;
	}
	
	#comment_write textarea {
		width:100%;
		height: 80px;
		border:none;
		outline:none;
		resize: none;
		box-sizing: border-box;
		font-size:14px;
		
		
	}
	
	#comment_write a {
		border:1px solid #2CDC00;
		box-sizing: border;
		width: 80px;
		height: 40px;
		font-weight: bold;
		padding-top:10px;
		display:inline-block;
	}
	
	#comment_write a:link,#comment_write a:visited{
		text-decoration: none;
		background-color: #2CDC00;
		color:white;
		font-size:20px;
		text-align: center;
	}
	
	#comment_write a:hover {
		border:1px solid green;
	}
	
	.length {
		text-align: right;
		padding-right:20px;
	}
	
	#comment_view {
		width:800px;
		margin:0 auto;
		margin-top:20px;
		margin-bottom:20px;
	}
	
	#comment_view > div {
		margin: 10px 0px;
		padding:20px;
	}
	
	#comment_view p:first-of-type {
		margin:0;
		padding:0;
		font-size:12px;
		color:silver;
		font-style: italic;
	}
	
	#comment_view h3{
		text-align: left;
		
	}
	
	#comment_view p {
		font-size:14px;
		color:black;
		margin:10px 0px;
	}
	
	#comment_view a {
		font-size:12px;
		text-decoration: none;
	}
	
	#comment_view .like_hate {
		float:right;
		margin:0px 20px;
		font-size:14px;
		text-decoration: none;
		text-align: center;
		display: inline-block;
	}
	
	#comment_view img {
		width:13px;
		height: 13px;
		margin-right:10px;
		margin-top:5px;
	}
	
	#comment_sort {
		float:right;
		margin-right:40px;
		font-size:15px;
	}
	
	#file_view span{
		display:none;
	}
	
	#file_view img, #file_view video {
		width:200px;
		height: 150px;
	}
	
	
	
</style>
</head>
<body>
	<div id="container">
		<jsp:include page="../template/header.jsp" flush="false"></jsp:include>
		
		<nav>
			<h2>작성글 조회</h2>
			<form action="/process/board_write_process" method="post">
				<table>
					<tr>
						<th></th>
						<td>
							<c:if test="${sessionScope.login != null and sessionScope.login == true }">
								<c:if test="${requestScope.dto.writer eq sessionScope.id }">
									<a href="update_board_view.do?bno=${param.bno}&writer=${requestScope.dto.writer}" id="update" class="btn">수정</a>
									<a href="delete_board_view.do?bno=${param.bno}&writer=${requestScope.dto.writer}" id="delete" class="btn">삭제</a>
								</c:if>
							</c:if>
						</td>
								
					</tr>
					<tr>
						<th>작성일</th>
						<td>${requestScope.dto.date}</td>
					</tr>
					<tr>
						<th>조회수</th>
						<td>${requestScope.dto.count}</td>
					</tr>
					<tr>
						<th>제목</th>
						<td><input type="hidden" name="title" value="${requestScope.dto.title}">
							${requestScope.dto.title}
						</td>
					</tr>
					<tr>
						<th>작성자</th>
						<td>
							<input type="hidden" name="writer" value="${requestScope.dto.writer}">
							${requestScope.dto.writer}					
						</td>
					</tr>
					<tr>
						<th style="vertical-align: top;">내용</th>
						<td>
							<div style="white-space:pre;">${requestScope.dto.content}</div>
						</td>
					</tr>
					<tr>
						<th>
						</th>
						<td>
							<a href="#none" id="like" class="btn_like_hate">
								<img src="/img/like.png"> 
								 <span>${requestScope.dto.like}</span>
							</a> 
							<a href="#none" id="hate" class="btn_like_hate">
								<img src="/img/hate.png"> 
								<span>${requestScope.dto.hate}</span>
							</a> 
							
						</td>
					</tr>
					<tr>
						<th>
							<a href="prev_board.do?bno=${param.bno }" class="btn" id="prev">이전글</a>
						</th>
						<td>
							<a href="${sessionScope.lastBoard}" class="btn" id="list">목록으로</a>
							<a href="next_board.do?bno=${param.bno }" class="btn" id="next">다음글</a>
						</td>
					</tr>
				</table>
			</form>
			<hr>
			<div id="file_view">
				<c:if test="${requestScope.file_list.size() > 0 }">첨부파일<br></c:if>
				<c:forEach var="file" items="${requestScope.file_list }">
					<p>
						<a href="file_download.do?writer=${file.writer}&fileName=${file.fileName}">${file.writer} / ${file.fileName}</a>
						<c:if test="${file.type eq 'image' }">
							<button>이미지보기</button><br>
							<span><img src="image_load.do?writer=${file.writer}&fileName=${file.fileName}" ></span>
						</c:if>
						<c:if test="${file.type eq 'video' }">
							<button>동영상보기</button><br>
							<span><video controls src="image_load.do?writer=${file.writer}&fileName=${file.fileName}" ></video></span>
						</c:if>
						<br>
					</p>
				</c:forEach>
			</div>
			<hr>
			<br>
			<c:if test="${sessionScope.login != null and sessionScope.login == true }">
				<div id="comment_write">
					<form id="comment">
						<input type="hidden" name="bno" value="${param.bno}">
						<input type="hidden" name="writer" value="${sessionScope.id }">
						<span>${sessionScope.id }</span><br><hr>
						<textarea placeholder="댓글을 입력하세요" maxlength="500" name="content"></textarea>
						<p class="length">0/500</p><hr>
						<p style="text-align: right"><a href="#none">등 록</a></p>
					</form>
				</div>
			</c:if>
			<br><hr><br>
			<c:if test="${requestScope.comment_list.size() > 0 }">
				<select id="comment_sort">
					<option value="cdate" selected>최신순</option>
					<option value="clike">좋아요순</option>
					<option value="chate">싫어요순</option>
				</select>
			</c:if>
			<div id="comment_view">
				<c:forEach var="list" items="${requestScope.comment_list }">
					<div>
						<input type="hidden" name="cno" value="${list.cno }">
						<h3>${list.writer}</h3>
						<p>${list.date}</p>
						<p>${list.content}</p>
						<c:if test="${sessionScope.login != null and sessionScope.login == true }">
							<c:if test="${list.writer == sessionScope.id}">
								<a href="#none">수정</a> / <a href="#none">삭제</a>
							</c:if>
						</c:if>
						<a href="#none" class="like_hate" id="chate"><img src="/img/hate.png"><span>${list.hate}</span></a>
						<a href="#none" class="like_hate" id="clike"><img src="/img/like.png"><span>${list.like}</span></a>
					</div>
				</c:forEach>
			</div>
		</nav>
		
		<jsp:include page="../template/footer.jsp" flush="false"></jsp:include>
		
	</div>
</body>
</html>