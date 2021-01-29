<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
	var nextPage = 1;
	$(function() {
		$("#qna_list").accordion();
		
		$("#btn_more").click(function() {
			var data = "nextPage="+nextPage;
			var obj = $(this);
			console.log("data :" + data);
			$.ajax({
				url : "next_qnaList.do",
				data : data,
				method : 'get',
				success : function(d) {
					var json = JSON.parse(d);
					console.log(json);
					nextPage = json.nextPage;
					if(nextPage=="0") {
						$("#btn_more").css("display","none");
					}
					var result="";
					for(i=0;i<json.array.length;i++) {
						result+="<h3><ul>";
						result+="<li>제목:"+json.array[i].title	+"</li>";
						result+="<li>작성자:"+json.array[i].writer+"</li>";
						result+="<li>작성일:"+json.array[i].qdate+"</li>";
						if(json.array[i].status == 0) {
							result+="<li>읽지않음</li>";
						}
						else if(json.array[i].status == 1) {
							result+="<li>읽었음</li>";
						}
						else {
							result+="<li>답변완료</li>";
						}
						result+="</ul></h3><div style = 'white-space:pre;'>";
						result+="<p>"+json.array[i].content+"</p>";
						if(json.array[i].status == 2) {
							result+="<p> 답변 <br>"+json.array[i].response+"</p>";
						}
						result+="</div>";
					}
					console.log(result);
					$("#qna_list").append(result);
					$("#qna_list").accordion("refresh");
				}
			}); 
		});
		
		$("#btn_more").click();
	});
</script>
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
		width:700px;
		margin:0 auto;
	}
	#qna_form {
		width:100%;
	}
	
	#qna_form table {
		border-collapse: collapse;
		box-sizing: border-box;
		width:100%;
	}
	
	#qna_form td {
		padding:5px;
	}
	
	#qna_form input {
		width:550px;
		font-size:18px;
		border-radius: 5px;
		padding:5px;
	}
	
	#qna_form textarea {
		width:550px;
		height:100px;
		font-size:16px;
		padding:5px;
	}
	
	#qna_form button {
		width:100px;
		height:150px;
	}
	
	#qna_list {
		width:100%;
		text-align: left;
	}
	
	#btn_more {
		width:100%;
		padding:10px;
		font-size:20px;
	}
	
</style>
</head>
<body>
	<%-- <c:if test="${sessionScope.login == null or sessionScope.login == false }">
		<script>
			alert("로그인이 필요합니다");
			location.href="${sessionScope.lastBoard}";
		</script>
	</c:if>
	<c:choose>
		<c:when test="${pageContext.request.queryString != null }">
			<c:set var="last" value="${pageContext.request.requestURI}?${pageContext.request.queryString }" scope="session" />
		</c:when>
		<c:otherwise>
			<c:set var="last" value="${pageContext.request.requestURI}" scope="session" />
		</c:otherwise>
	</c:choose>
	<c:out value="last:${last}"/> --%>

	<div id="container">
		<jsp:include page="../template/header.jsp" flush="false"></jsp:include>
		
		<nav>
			<div id="qna_form">
				<form action="register_qna.do" method="post">
					<table>
						<tr>
							<td><input type="text" name="title" placeholder="문의 제목"></td>
							<td rowspan="2"><button type="submit">문의 등록</button></td>
						</tr>
						<tr>
							<td><textarea name="content" placeholder="문의 내용을 입력해주세요"></textarea></td>
						</tr>
					</table>
					
				</form>
				<hr>
			</div>
			<div id="qna_list">
				<%-- <c:forEach var="dto" items="${requestScope.list }">
					<h3>
						<ul>
							<li>제목:${dto.title }</li>  <li>작성자:${dto.writer }</li> <li>작성일:${dto.qdate }</li>
							<li>	
								<c:choose>
									<c:when test="${dto.status == 0}"> 
										읽지않음
									</c:when>
									<c:when test="${dto.status == 1}">
										읽었음
									</c:when>
									<c:otherwise>
										답변완료
									</c:otherwise>
								</c:choose>
							</li>
						</ul>
					</h3>
					<div style="white-space:pre;" >
						<p>${dto.content }</p>
						<c:if test="${dto.status == 2 }">
							<br>
							<p>답변<br>${dto.response }</p>
						</c:if>
					</div>
				</c:forEach> --%>
			</div> 
			<button id="btn_more">더보기</button>
		</nav>
		
		<jsp:include page="../template/footer.jsp" flush="false"></jsp:include>
		
	</div>
</body>
</html>