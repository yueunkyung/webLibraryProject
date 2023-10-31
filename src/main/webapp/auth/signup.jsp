<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="../views/commonImport.jsp" %>
<style>
	html,body{height: 100%;}
</style>
<script>
window.onload = call;	

function call() {
	
	//거주지 옵션
	const locArr = ["종로","중구","성동","용산","광진","동대문","중랑","성북","강북","도봉","노원","은평","서대문","마포","양천","강서","구로","금천","영등포","동작","관악","서초","강남","송파","강동"];
	const selectLoc = document.querySelector(".loc");
	locArr.forEach(function(ele, i) {
		var opt = document.createElement("option");
		opt.setAttribute("value", ele);
		opt.innerText = ele;
		selectLoc.appendChild(opt);
	});
	
	//취소 버튼 클릭시
	document.querySelector(".btn_cancel").onclick = function() {
		history.back();
		//location.href = "../signinCheck.go";
	};

	let message = "${signupResult}";
	if(message!= "") alert(message);
	
}
</script>
</head>
<body>
	<div class="login_wrapper">
		<div class="login_bg"></div>
		<div class="login_unit">
			<div class="title">LALA 도서관 회원가입</div>
			<form action="signup.go" method="post">
				<div class="input_set">
					<span>ID</span>					
					<input class="user_id" type="text" name="user_id" placeholder="아이디를 입력하세요.">
				</div>
				<div class="input_set">
					<span>PASSWORD</span>
					<input class="password" type="password" name="password" placeholder="비밀번호를 숫자로 입력하세요." />
				</div>
				<div class="input_set">
					<span>이름</span>
					<input class="user_name" type="text" name="user_name" placeholder="이름을 입력하세요." />
				</div>
				<div class="input_set">
					<span>주도서관 선택</span>
					<select class="library_id" name="library_id">
						<option value="" selected disabled hidden>전체</option>
						<c:forEach items="${librarylist}" var="library">
							<option value="${library.library_id}" ${library.library_id.equals(param.library_id)?"selected" : ""}>${library.library_name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="input_set">
					<span>거주지</span>
					<select class="loc" name="loc">
						<option value="" selected disabled hidden>전체</option>
					</select>
				</div>
				<div class="input_set">
					<span>핸드폰번호</span>
					<input class="phone_no" type="text" name="phone_no" placeholder="핸드폰 번호를 입력하세요." />
				</div>
				<div class="btn_wrap">
					<button type="submit" class="btn_signup">회원가입</button>
					<button type="button" class="btn_cancel">취소</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>