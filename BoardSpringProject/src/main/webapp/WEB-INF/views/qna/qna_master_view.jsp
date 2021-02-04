<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
		width:800px;
		margin:0 auto;
	}
	
	#qna_view {
		
	}
	
	#qna_view li {
		margin:0px 20px;
	}
	
	#qna_form table {
		width:600px;
		height: 100px;
		margin:0 auto;
	}
	
	#qna_form textarea {
		width:80%;
		height: 100px;
	}
	
	#qna_form button {
		width:20%;
		height: 100%;
	}
	
</style>
</head>
<body>
	
	<div id="container">
		<jsp:include page="../template/header.jsp" flush="false"></jsp:include>
		
		<nav>
			<div id="qna_view">
				<h4>
					<ul>
						<li>제목:${requestScope.dto.title }</li>  
						<li>작성자:${requestScope.dto.writer }</li> 
						<li>작성일:${requestScope.dto.qdate }</li>
						<li>	
							<c:choose>
								<c:when test="${requestScope.dto.status == 0}"> 
									읽지않음
								</c:when>
								<c:when test="${requestScope.dto.status == 1}">
									읽었음
								</c:when>
								<c:otherwise>
									답변완료
								</c:otherwise>
							</c:choose>
						</li>
					</ul>
				</h4>
				<div style="white-space:pre;" >
					<p>${dto.content }</p>
					<c:if test="${requestScope.dto.status == 2 }">
						<br>
						<p>답변<br>${requestScope.dto.response }</p>
					</c:if>
				</div>
			</div> 
			<hr>
			<div id="qna_form">
				<form action="update_qna_response.do" method="post">
					<table>
						<tr>
							<td>
								<input type="hidden" name="qid" value="${requestScope.dto.qid }">
								<textarea name="response">${requestScope.dto.response}</textarea>
							</td>
							<td><button type="submit">답변 등록</button></td>
							<td><a href="qna_view.do">목록으로</a></td>
						</tr>
					</table>
					
				</form>
			</div>
		</nav>
		
		<jsp:include page="../template/footer.jsp" flush="false"></jsp:include>
		
	</div>
</body>
</html>