<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../views/commonImport.jsp" %>
<script>
	window.onload = call;

	function call() {
		$("#gnb_wrap>li").on("mouseover focusin", function() {
			$(this).children().stop().fadeIn(500);
			$(this).children("a").addClass("on");
		});
		$("#gnb_wrap>li").on("mouseout focusout", function() {
			$(this).children("div").stop().fadeOut(500);
			$(this).children("a").removeClass("on");
		});

		//거주지 옵션
		const locArr = ["종로","중구","성동","용산","광진","동대문","중랑","성북","강북","도봉","노원","은평","서대문","마포","양천","강서","구로","금천","영등포","동작","관악","서초","강남","송파","강동"];
		const selectLoc = document.querySelector(".loc");
		let selectVal = "${userInfo.loc}";
		locArr.forEach(function(ele, i) {
			var opt = document.createElement("option");
			opt.setAttribute("value", ele);
			selectVal==ele? opt.setAttribute("selected", "selected") : "";
			opt.innerText = ele;
			selectLoc.appendChild(opt);
		});

		
		let message = "${userUpdateResult}";
		if(message!= "") alert(message);
		

	} //End
</script>
</head>
<body>
	<!-- #wrap -->
	<div id="wrap" class="bg_dark">
		<%@include file="../views/header.jsp" %>
		<!-- .mypage_container -->
		<section class="userinfo_container">
			<form action="userUpdate.go" method="post">
				<div class="btn_wrap">
					<button type="submit">수정</button>
				</div>
				<div class="info_wrap">
					<div class="info_set">
						<div>아이디</div>
						<div>
							<input type="text" value="${userInfo.user_id}" disabled />
							<input name="user_id" type="hidden" value="${userInfo.user_id}" />
						</div>
					</div>
					<div class="info_set">
						<div>이름</div>
						<div>
							<input name="user_name" type="text" value="${userInfo.user_name}" />
						</div>
					</div>
					<div class="info_set">
						<div>주도서관</div>
						<div>
							<select class="library_id" name="library_id">
								<option value="" selected disabled hidden>전체</option>
								<c:forEach items="${librarylist}" var="library">
									<option value="${userInfo.library_id}" ${library.library_id.equals(userInfo.library_id)?"selected" : ""}>${library.library_name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="info_set">
						<div>거주지</div>
						<div>
							<select class="loc" name="loc">
							</select>
						</div>
					</div>
					<div class="info_set">
						<div>핸드폰 번호</div>
						<div>
							<input name="phone_no" type="text" value="${userInfo.phone_no}" />
						</div>
					</div>
					<div class="info_set">
						<div>비밀번호</div>
						<div>
							<input name="password" type="text" value="${userInfo.password}" />
						</div>
					</div>
				</div>
			</form>
		</section>
		<!-- //.mypage_container -->
	</div>
	<!-- //#wrap -->
</body>
</html>