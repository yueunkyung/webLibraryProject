<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../views/commonImport.jsp" %>
</head>
<body>
	<!-- #wrap -->
	<div id="wrap" class="bg_dark">
		<%@include file="../views/header.jsp" %>
		<!-- .bookdetail_container -->
		<section class="bookdetail_container">
			<section class="con_left">
				<div class="img_wrap">
					<img src="../static/images/book/${bookDetailInfo.book_id}.jpg" alt="책" />
				</div>
			</section>
			<section class="con_right">
				<div class="tit">도서 상세 정보</div>
				<div class="info_wrap">
					<div class="info_set">
						<div>도서 이름</div>
						<div>${bookDetailInfo.book_name}</div>
					</div>
					<div class="info_set">
						<div>도서 아이디 /<br>도서 분류 번호</div>
						<div>${bookDetailInfo.book_id} / ${bookDetailInfo.book_type}</div>
					</div>
					<div class="info_set">
						<div>도서관</div>
						<div>${bookDetailInfo.library_name}</div>
					</div>
					<div class="info_set">
						<div>대출 상태</div>
						<div>
							<c:choose>
								<c:when test="${bookDetailInfo.borrow_status=='Y'}">
									도서 대출중
								</c:when>
								<c:otherwise>
									도서 대출 가능
								</c:otherwise>
							</c:choose>
							
						</div>
					</div>
					<div class="info_set">
						<div>대출 반납 예정일</div>
						<div>${bookDetailInfo.return_date}</div>
					</div>
					<div class="btn_wrap">
						<c:choose>
							<c:when test="${bookDetailInfo.borrow_status=='Y'}">
								<button class="btn_borrow" disabled>도서 대출중</button>
							</c:when>
							<c:otherwise>
								<button class="btn_borrow" onclick="location.href='${appPath}/book/bookBorrow.go?book_id=${bookDetailInfo.book_id}';">도서 대출 신청</button>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</section>
		</section>
		<!-- //.bookdetail_container -->
	</div>
	<!-- //#wrap -->
</body>
</html>