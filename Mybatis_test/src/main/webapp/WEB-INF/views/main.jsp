<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$(".update").click(function() {
			$(this).parent().parent().find("td input[type=hidden]").val("update");
		});
		$(".delete").click(function() {
			$(this).parent().parent().find("td input[type=hidden]").val("delete");
		});
	});
	
</script>
</head>
<body>
	<form action="insert_student.do" method="post">
		<input type="text" name="sno" placeholder="학생번호"><br>
		<input type="text" name="name" placeholder="학생이름"><br>
		<input type="text" name="major" placeholder="전공"><br>
		<input type="text" name="score" placeholder="학점"><br>
		<button>학생등록</button>
	</form>
	<hr>
	<table>
		<c:forEach var="std" items="${requestScope.list }" varStatus="idx">
			<form action="alter_student.do" method="post">
				<tr>
					<td><input type="text" name="sno" value="${std.sno }" readonly></td>
					<td><input type="text" name="name" value="${std.name }"></td>
					<td><input type="text" name="major" value="${std.major }"></td>
					<td><input type="text" name="score" value="${std.score }"></td>
					<td><input type="hidden" name="type"></td>
					<td><button class="update">수정</button> / <button class="delete">삭제</button> </td>
				</tr>
			</form>
		</c:forEach>
	</table>
</body>
</html>