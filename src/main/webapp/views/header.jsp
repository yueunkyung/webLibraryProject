<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="appPath" scope="application" value="${pageContext.request.contextPath}" />
<!-- .hd_container -->
<header class="hd_container">
	<div class="header_top">
		<c:choose>
			<c:when test="${userInfo!=null}">
				<p class="welcome_msg">${userInfo.user_name}님 환영합니다😊</p>
				<ul class="util_wrap">
					<li><a href="${appPath}/auth/mypage.go?userid=${userInfo.user_id}">마이페이지</a></li>
					<li><a href="${appPath}/auth/signout.go">SignOut</a></li>
				</ul>
			</c:when>
			<c:otherwise>
				<p class="welcome_msg">게스트님 입장하였습니다😊</p>
				<ul class="util_wrap">
					<li><a href="${appPath}/signinCheck.go">SignIn</a></li>
					<li><a href="${appPath}/auth/signup.go">회원가입</a></li>
				</ul>
			</c:otherwise>
		</c:choose>
	</div>
	<h1 class="logo">
		<a href="${appPath}/book/bookList.go">LALA LIBRARY</a>
	</h1>
	<!-- .header_gnb -->
	<div class="header_gnb">
		<nav class="dis_center">
			<!-- #gnb_wrap -->
			<ul id="gnb_wrap">
				<li>
					<a href="${appPath}/book/bookList.go">도서 통합 검색</a>
					<div class="gnb_2depth">
						<div class="dis_center">
							<ul class="l_book">
								<li><a href="${appPath}/book/bookList.go">도서 검색</a></li>
								<li><a href="#">도서 주문</a></li>
							</ul>
						</div>
					</div>
				</li>
				<li>
					<a href="${appPath}/lostAndFound/lostAndFound.go">분실물 보관</a>
					<div class="gnb_2depth">
						<div class="dis_center">
							<ul class="l_lost">
								<li><a href="${appPath}/lostAndFound/lostAndFound.go">분실물 찾기</a></li>
							</ul>
						</div>
					</div>
				</li>
			</ul>
			<!-- //#gnb_wrap -->
		</nav>
	</div>
	<!-- //.header_gnb -->
</header>
<!-- //.hd_container -->