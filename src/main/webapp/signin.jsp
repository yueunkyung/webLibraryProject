<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="static/favicon/favicon.ico" type="image/x-icon">
<link rel="icon" href="static/favicon/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="static/css/common.css">
<link rel="stylesheet" href="static/css/main.css">
<title>즐거운 라라 도서관</title>
<style>
	html,body{height: 100%;}
</style>
<script>
window.onload = call;	

function call() {
	document.querySelector("#btn_signup").onclick = function() {
		location.href = "signinCheck.go";
	};
	document.querySelector("#btn_signin").onclick = function() {
		location.href = "book/bookList.go";
	};
	document.querySelector("#btn_search_book").onclick = function() {
		location.href = "book/bookList.go";
	};
	
	let message = "${signinResult}";
	if(message!= "") alert(message);
	
	//password에서 엔터시
	document.querySelector(".user_password").addEventListener("keyup", function(e) {
        if (e.keyCode === 13) {
            document.querySelector("#btn_signin").click();
        }
    });
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
					<input type="text" name="user_id" placeholder="아이디를 입력하세요.">
				</div>
				<div class="input_set">
					<span>PASSWORD</span>
					<input class="user_password" type="password" name="user_password" placeholder="비밀번호를 입력하세요." />
				</div>
			</form>
			<div class="btn_wrap">
				<button id="btn_signin">로그인</button>
				<button id="btn_signup">회원가입</button>
				<button id="btn_search_book">도서통합검색</button>
			</div>
		</div>
	</div>
</body>
</html>