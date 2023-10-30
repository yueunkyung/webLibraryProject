<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>즐거운 라라 도서관</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="../static/favicon/favicon.ico" type="image/x-icon">
<link rel="icon" href="../static/favicon/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="../static/css/common.css">
<link rel="stylesheet" href="../static/css/main.css">
<script src="../static/js/jslib/jquery-3.7.1.min.js"></script>
<!-- 
<script src="static/js/common.js"></script>
-->
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
		$(".btn_search").click(function() {
			console.log("---both--", $("#library_id").val());
			if($("#library_id").val() && $("#book_name").val() ) {
				console.log("---");
			}
			location.href = "bookList.go?library_id=" + $("#library_id").val() + "&book_name=" + $("#book_name").val();
		});
		//엔터 Trigger
		document.querySelector("#book_name").addEventListener("keyup", function(e) {
	        if (e.keyCode === 13) {
	            document.querySelector(".btn_search").click();
	        }
	    });
		
		//초기화 버튼/
		document.querySelector(".btn_reset").addEventListener("click", function(e) {
			/*
			document.querySelector("#book_name").value="";
			document.querySelector("#library_id").value="";
			*/
			history.replaceState({}, null, location.pathname);
			location.reload();
	    });
		/*
		$.fn.reset = function(frm) {
			$(this).on("click", function(e) {
				$(frm + " input:text").val("");
				$(frm + " select").find("option:first").attr("selected", "selected");
			});
		};
		$(".btn_reset").reset(".search_box")
		*/
		
		//새로고침
		window.onkeyup = function(e) {
			key = (e)?e.keyCode:event.keyCode;
			if(key == 116 || (event.ctrlKey && event.keyCode == 82)) {
				history.replaceState({}, null, location.pathname);
				location.reload();
			}
		};
		
	} //End
</script>
</head>

<body>
	<!-- #wrap -->
	<div id="wrap" class="bg_dark">
		<!-- .hd_container -->
		<header class="hd_container">
			<div class="header_top">
				<c:if test="${userInfo!=null}">
					<p class="welcome_msg">${userInfo.user_name}님 환영합니다😊</p>
				</c:if>
				<ul class="util_wrap">
					<c:choose>
						<c:when test="${userInfo==null}">
							<li><a href="../signinCheck.go">SignIn</a></li>
							<li><a href="#">회원가입</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="#">마이페이지</a></li>
							<li><a href="../auth/signout.go">SignOut</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
			<h1 class="logo">
				<a href="bookList.go">LALA LIBRARY</a>
			</h1>
			<!-- .header_gnb -->
			<div class="header_gnb">
				<nav class="dis_center">
					<!-- #gnb_wrap -->
					<ul id="gnb_wrap">
						<li>
							<a href="#">도서 통합 검색</a>
							<div class="gnb_2depth">
								<div class="dis_center">
									<ul class="l_book">
										<li><a href="#">도서 검색</a></li>
										<li><a href="#">도서 주문</a></li>
									</ul>
								</div>
							</div>
						</li>
						<li>
							<a href="#">분실물 보관</a>
							<div class="gnb_2depth">
								<div class="dis_center">
									<ul class="l_lost">
										<li><a href="#">분실물 찾기</a></li>
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
		<section class="booklist_banner dis_center"></section>
		<!-- .booklist_container -->
		<section class="booklist_container">
			<div class="search_box">
				<div class="search_set">
					<span>도서관 선택</span>
					<select id="library_id">
						<option value="">전체</option>
						<c:forEach items="${librarylist}" var="library">
							<option value="${library.library_id}" ${library.library_id.equals(param.library_id)?"selected" : ""}>${library.library_name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="search_set">
					<span>도서명</span>
					<input type="text" id="book_name" value="${param.book_name}" placeholder="도서 이름을 입력해주세요." />
				</div>
				<button class="btn_search">조회</button>
				<button class="btn_reset">초기화</button>
			</div>
			<div class="total_count">
				총 <span>${booklist.size()}</span>건의 도서가 있습니다.
			</div>
			<div class="list_wrap">
				<table>
					<colgroup>
						<col width="8%">
						<col width="50%">
						<col width="15%">
						<col width="12%">
						<col width="15%">
					</colgroup>
					<thead>
						<tr>
							<th>NO</th>
							<th>도서명</th>
							<th>도서관</th>
							<th>도서 분류 번호</th>
							<th>대출</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${booklist}" var="book" varStatus="bookStatus">
							<tr seq="${bookStatus.count}">
								<td>${bookStatus.count}</td>
								<td class="txt_left">${book.book_name}</td>
								<td>${book.library_name}</td>
								<td>${book.book_type}</td>
								<td>
									<c:choose>
										<c:when test="${book.borrow_status=='Y'}">
											<button class="btn_borrow" disabled>도서 대출중</button>
										</c:when>
										<c:otherwise>
											<button class="btn_borrow">도서 대출 신청</button>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
						<c:if test="${booklist.size() == 0}">
							<tr>
								<td class="empty_result" colspan="5">
									<div class="msg">찾으시는 도서가 현재 도서관에 존재하지 않습니다.</div>
								</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
			
			<c:if test="${booklist.size() != 0}">
			<!--pageMasterWrap-->
			    <div class=pagination>
			        <div class="pageLeft">
			            <button class="btnFirst">첫페이지</button>
			            <button class="btnPrev">이전페이지</button>
			        </div>
			        <ul class="pagingWrap">
			            <li><a href="">1</a></li>
			            <li><a href="">2</a></li>
			            <li><a href="">3</a></li>
			            <li><a href="">4</a></li>
			            <li><a href="">5</a></li>
			            <li><a href="">6</a></li>
			            <li><a href="">7</a></li>
			            <li><a href="">8</a></li>
			            <li><a href="">9</a></li>
			            <li><a href="">10</a></li>
			            <li><a href="">11</a></li>
			            <li><a href="">12</a></li>
			        </ul>
			        <div class="pageRight">
			            <button class="btnNext">다음페이지</button>
			            <button class="btnLast">마지막페이지</button>
			        </div>
			    </div>
			<!--//pageMasterWrap-->
			</c:if>
		</section>
		<!-- //.booklist_container -->
	</div>
	<!-- //#wrap -->
</body>
</html>