<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <style>
		header {
			border:2px solid black;
			margin:0 auto;
			width:60%;
			height: 50px;
		}
		ul {
			list-style: none;
			text-align: center;
		}
		li {
			display: inline-block;
			padding: 15px 15px 0px 15px;
		}
		li a:link, li a:visited {
			text-decoration: none;
			color:black;
		}
		li a:hover {
			color:red;
		}
		
		#name {
			font-size:15px;
			color:blue;
			float:right;
			display: inline-block;
		}
		
		#name > img {
			width:30px;
			height: 30px;
		}
		
		#name span {
			padding-bottom:15px;
		}
	</style>

	<header>
		<ul>
			<li><a href="/">HOME</a></li>
			<c:choose>
				<c:when test="${sessionScope.login !=null and sessionScope.login eq true }">
					<li><a href="logout.do">로그아웃</a></li>
					<li><a href="update_member_view.do">회원정보변경</a></li>
					<c:if test="${sessionScope.grade eq 0}">
						<li><a href="member_manage.do">회원관리</a></li>
					</c:if>
					<li><a href="qna_view.do">문의하기</a></li>
					<li id="name"><img src="/img/${sessionScope.grade_name}.png"><span>${sessionScope.name}님 로그인 하셨습니다.</span></li>
				</c:when>
				<c:otherwise>
					<li><a href="register_view.do">회원가입</a></li>
					<li><a href="login_view.do">로그인</a></li>
					<li><a href="#none">회원관리</a></li>
					<li><a href="qna_view.do">문의하기</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</header>