<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="views/commonImport.jsp" %>
<style>
	html,body{height: 100%;}
</style>
<script>
window.onload = call;	

function call() {
	document.querySelector(".btn_signup").onclick = function() {
		location.href = "auth/signup.go";
	};
	document.querySelector(".btn_search_book").onclick = function() {
		location.href = "book/bookList.go";
	};
	
	let message = "${signinResult}";
	if(message!= "") alert(message);
	
}
</script>
</head>
<body>
	<div class="login_wrapper">
		<div class="login_bg"></div>
		<div class="login_unit">
			<div class="title">LALA 도서관</div>
			<form action="signinCheck.go" method="post">
				<div class="input_set">
					<span>ID</span>
					<input class="user_id" type="text" name="user_id" placeholder="아이디를 입력하세요.">
				</div>
				<div class="input_set">
					<span>PASSWORD</span>
					<input class="user_password" type="password" name="password" placeholder="비밀번호를 입력하세요." />
				</div>
				<div class="btn_wrap">
					<button type="submit" class="btn_signin">로그인</button>
					<button type="button" class="btn_signup">회원가입</button>
					<button type="button" class="btn_search_book">도서통합검색</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>