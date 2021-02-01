<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/lib/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("tbody tr").click(function() {
			var bno = $(this).children().eq(0).html();
			console.log("${param.currPage} , bno : "+bno);
			location.href = "board_view.do?currPage=${param.currPage}&bno="+bno;
			
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
	
	#search {
		text-align: center;
		font-size:18px;
		height: 30px;
		margin:20px 0px;
	}
	
	#search > input{
		width:300px;
		margin: 0px 20px;
		height: 100%;
	}
	
	#select {
		display: inline-block;
		width:100px;
		height: 100%;
	}
	
	nav table {
		margin:0 auto;
		margin-top:20px;
		margin-bottom:20px;
		text-align: center;
		font-size:0px;
		border-collapse: collapse;
	}
	nav th, nav td {
		padding:5px;
		font-size:16px;
	}
	
	nav th {
		border-top:2px solid black;
		border-bottom:2px solid black;
	}
	
	nav tbody tr:hover {
		border:1px solid black;
		background: silver;
		box-sizing: border;
	}
	
	nav td:nth-of-type(2) {
		width:300px;
	}
	
	nav td:nth-of-type(3) {
		width:150px;
	}
	
	nav td:nth-of-type(4) {
		width:200px;
	}
	
	nav tr:last-child {
		border-bottom:2px solid black;
	}
	
	.btn{
		text-decoration: none;
		background-color: #e8e8e8;
		width: 80px;
		display: inline-block;
		padding:5px 0px;
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
	
	tfoot a {
		color:black;
		font-size:18px;
	}
	
	tfoot td a:link,tfoot td a:visited {
		color : black;
		text-decoration: none;
		font-size:18px;
		margin:0px 10px;
	}
	
	tfoot td a:hover {
		font-weight: bold;
		color:red;
	}
	
	tfoot td > span {
		float:right;
	}
	
	#page_select {
		color:blue;
	}
	
</style>
</head>
<body>
	<%-- <c:if test="${requestScope.board_list eq null or requestScope.board_list.size == 0 }">
		<script type="text/javascript">
			location.href="main.do";
		</script>
	</c:if> --%>
	
	<div id="container">
		<jsp:include page="template/header.jsp" flush="false"></jsp:include>
		
		<div id="search">
			<div id="select">
				제목
			</div>
			<input type="text">
			<a href="#" class="btn">검색</a>
		</div>
		<nav>
			<!-- 
			<table>
				<thead>
					<tr>
						<th>글번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th><a href="main.do?currPage=1&sort=bcount">조회수</a></th>
						<th><a href="main.do?currPage=1&sort=blike">좋아요</a></th>
						<th><a href="main.do?currPage=1&sort=bhate">싫어요</a></th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="dto" items="${requestScope.board_list}">
					<tr>
						<td>${dto.bno }</td>
						<td>${dto.title }
						<c:forEach var="map" items="${requestScope.map }">
							<c:if test="${map.key == dto.bno}">
								[${map.value}]
							</c:if>
						</c:forEach>
						</td>
						<td>${dto.writer }</td>
						<td>${dto.date }</td>
						<td>${dto.count }</td>
						<td>${dto.like }</td>
						<td>${dto.hate }</td>
					</tr>
				</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="7">
							<c:if test="${requestScope.pageVO.prevPageGroup }">
								<a href="main.do?currPage=${requestScope.pageVO.startPageOfPageGroup-1 }&sort=${param.sort}">◀</a>
							</c:if>
							<c:forEach var="index" begin="${requestScope.pageVO.startPageOfPageGroup}" end="${requestScope.pageVO.endPageOfPageGroup}">
								<c:choose>
									<c:when test="${index == param.currPage}">
										<a href="#" id="page_select">${index}</a>
									</c:when>
									<c:otherwise>
										<a href="main.do?currPage=${index}&sort=${param.sort}">${index}</a>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:if test="${requestScope.pageVO.nextPageGroup}">
								<a href="main.do?currPage=${requestScope.pageVO.endPageOfPageGroup+1}&sort=${param.sort}">▶</a>
							</c:if>
							<span><a href="write_board_view.do" class="btn">글쓰기</a></span>
							
						</td>
					</tr>
				</tfoot>
			</table>
			 -->
		</nav>
		
		<jsp:include page="template/footer.jsp" flush="false"></jsp:include>
		
	</div>
</body>
</html>