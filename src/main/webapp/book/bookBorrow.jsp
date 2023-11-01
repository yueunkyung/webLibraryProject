<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../views/commonImport.jsp" %>
</head>
<body>
	<!-- #wrap -->
	<div id="wrap" class="bg_dark">
		<%@include file="../views/header.jsp" %>
		<!-- .bookborrow_container -->
		<h1 style="font-size: 100px; background:#fff;">bookborrow_container 미완성으로<br>뒤로가기 눌러주세요...</h1>
		<section class="bookborrow_container">
			<div class="btn_wrap">
				<button class="btn_update">개인정보 수정</button>
			</div>
			<div class="info_wrap">
				<div class="info_set">
					<div>아이디</div>
					<div>${userInfo.user_id}</div>
				</div>
				<div class="info_set">
					<div>이름</div>
					<div>${userInfo.user_name}</div>
				</div>
				<div class="info_set">
					<div>주도서관</div>
					<div>${libraryName}</div>
				</div>
				<div class="info_set">
					<div>거주지</div>
					<div>${userInfo.loc}</div>
				</div>
				<div class="info_set">
					<div>핸드폰 번호</div>
					<div>${userInfo.phone_no}</div>
				</div>
				<div class="info_set">
					<div></div>
					<div></div>
				</div>
			</div>
		</section>
		<!-- //.bookborrow_container -->
	</div>
	<!-- //#wrap -->
</body>
</html>