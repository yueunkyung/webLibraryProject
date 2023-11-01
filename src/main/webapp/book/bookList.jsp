<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
		$(".btn_search").click(function() {
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
		
		//도서대출신청 버튼 클릭시
		/*
		const buttons = document.querySelectorAll(".btn_borrow")
		for (const button of buttons) {
		  button.addEventListener('click', function(event) {
			location.href = "bookDetail.go?";
		  })
		}
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
	<%-- #wrap --%>
	<div id="wrap" class="bg_dark">
		<%@include file="../views/header.jsp" %>
		<section class="list_banner booklist_banner dis_center">
		</section>
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
								<td class="txt_left">
									<a href="${appPath}/book/bookDetail.go?book_id=${book.book_id}">${book.book_name}</a>
								</td>
								<td>${book.library_name}</td>
								<td>${book.book_type}</td>
								<td>
									<c:choose>
										<c:when test="${book.borrow_status=='Y'}">
											<button class="btn_borrow" disabled>도서 대출중</button>
										</c:when>
										<c:otherwise>
											<button class="btn_borrow" onclick="location.href='${appPath}/book/bookBorrow.go?book_id=${book.book_id}';">도서 대출 신청</button>
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
			
			<%--pageMasterWrap--%>
			<%-- 
			<c:if test="${booklist.size() != 0}">
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
			</c:if>
			--%>
			<%-- //pageMasterWrap --%>
		</section>
		<%-- //.booklist_container --%>
	</div>
	<%-- //#wrap --%>
</body>
</html>