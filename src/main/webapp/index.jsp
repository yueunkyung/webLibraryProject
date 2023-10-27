<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>도서 검색</title>
<meta charset="UTF-8">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="static/css/common.css">
<link rel="stylesheet" href="static/css/main.css">
<script src="static/js/jslib/jquery-3.7.1.min.js"></script>
<!-- 
<script src="static/js/common.js"></script>
-->
<script>
	$(function() {
		$("#gnb_wrap>li").on("mouseover focusin", function() {
			$(this).children().stop().fadeIn(500);
			$(this).children("a").addClass("on");
		});
		$("#gnb_wrap>li").on("mouseout focusout", function() {
			$(this).children("div").stop().fadeOut(500);
			$(this).children("a").removeClass("on");
		});

		$("#btnSearch").click(function(){
			location.href = "welcomeLALA.go?library_id="+$("#library_id").val()+"&book_title="+$("#book_title").val();
		});
	}); //End
</script>
</head>

<body class="bg_dark">
	<header id="hd_wrap">
		<div class="header_top">
			<ul class="util_wrap">
				<li><a href="#">로그인</a></li>
				<li><a href="#">회원가입</a></li>
			</ul>
		</div>
		<h1 class="logo">
			<a href="/">LALA LIBRARY</a>
		</h1>
		<div class="header_gnb">
			<nav class="dis_center">
				<ul id="gnb_wrap">
					<li><a href="#">도서 통합 검색</a>
						<div class="gnb_2depth">
							<div class="dis_center">
								<ul class="l_book">
									<li><a href="#">도서 검색</a></li>
									<li><a href="#">도서 주문</a></li>
								</ul>
							</div>
						</div>
					</li>
					<li><a href="#">분실물 보관</a>
						<div class="gnb_2depth">
							<div class="dis_center">
								<ul class="l_lost">
									<li><a href="#">분실물 찾기</a></li>
								</ul>
							</div>
						</div>
					</li>
				</ul>
			</nav>
		</div>
	</header>
	<div class="container booklist_container">
		<div class="search_box">
			<div class="search_set">
				<span>도서관 선택</span> 
				<select id="library_id">
					<option value="">전체</option>
					<option value="lali_001">라라 도서관</option>
					<option value="lali_002">서울 도서관</option>
					<option value="lali_003">광명시 도서관</option>
					<option value="lali_004">부천시 도서관</option>
				</select>
			</div>
			<div class="search_set">
				<span>도서명</span> <input type="text" id="book_title" placeholder="도서 이름을 입력해주세요." />
			</div>
			<button id="btnSearch" class="btn_search">조회</button>
		</div>
		<div class="total_count">
			총 <span>316</span>건의 도서가 있습니다.
		</div>
		<div class="list_wrap">
			<table>
				<colgroup>
					<col width="8%">
					<col width="57%">
					<col width="20%">
					<col width="15%">
				</colgroup>
				<thead>
					<tr>
						<th>NO</th>
						<th>도서명</th>
						<th>도서 타입</th>
						<th>대여상태</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${booklist}" var="book" varStatus="bookStatus">
						<tr seq="${bookStatus.count}">
							<td>${bookStatus.count}</td>
							<td class="txt_left">${book.book_name}</td>
							<td>${book.book_type}</td>
							<td>${book.borrow_status}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>