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
		
		//수정 버튼 클릭시
		document.querySelector(".btn_update").onclick = function() {
			//history.back();
			location.href = "userUpdate.go";
		};
	} //End
</script>
</head>
<body>
	<!-- #wrap -->
	<div id="wrap" class="bg_dark">
		<%@include file="../views/header.jsp" %>
		<!-- .mypage_container -->
		<section class="userinfo_container">
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
			<div class="list_wrap">
				<div class="tit">도서 대출 상태</div>
				<div class="total_count">
					총 <span>${booklist.size()}</span>건의 대출이 있습니다.
				</div>
				<table>
					<colgroup>
						<col width="8%">
						<col width="62%">
						<col width="15%">
						<col width="15%">
					</colgroup>
					<thead>
						<tr>
							<th>NO</th>
							<th>도서명</th>
							<th>도서관</th>
							<th>도서 분류 번호</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${booklist}" var="book" varStatus="bookStatus">
							<tr seq="${bookStatus.count}">
								<td>${bookStatus.count}</td>
								<td class="txt_left">${book.book_name}</td>
								<td>${book}</td>
								<td>${book.book_type}</td>
							</tr>
						</c:forEach>
						<c:if test="${booklist.size() == 0}">
							<tr>
								<td class="empty_result" colspan="4">
									<div class="msg">대출 도서 목록이 없습니다.</div>
								</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</section>
		<!-- //.mypage_container -->
	</div>
	<!-- //#wrap -->
</body>
</html>