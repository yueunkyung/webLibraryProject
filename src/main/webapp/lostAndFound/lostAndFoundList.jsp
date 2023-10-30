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
			location.href = "lostAndFound.go?library_id=" + $("#library_id").val() + "&property_name=" + $("#property_name").val();
		});
		
		//엔터 Trigger
		document.querySelector("#property_name").addEventListener("keyup", function(e) {
	        if (e.keyCode === 13) {
	            document.querySelector(".btn_search").click();
	        }
	    });
		
		//초기화 버튼/
		document.querySelector(".btn_reset").addEventListener("click", function(e) {
			history.replaceState({}, null, location.pathname);
			location.reload();
	    });
		//새로고침
		/*
		window.onkeyup = function(e) {
			key = (e)?e.keyCode:event.keyCode;
			if(key == 116 || (event.ctrlKey && event.keyCode == 82)) {
				history.replaceState({}, null, location.pathname);
				location.reload();
			}
		};
		*/
		
	} //End
</script>
</head>
<body>
	<!-- #wrap -->
	<div id="wrap" class="bg_dark">
		<%@include file="../views/header.jsp" %>
		<!-- .booklist_container -->
		<section class="lost_container">
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
					<span>분실물</span>
					<input type="text" id="property_name" value="${param.property_name}" placeholder="분실물명을 입력해주세요." />
				</div>
				<button class="btn_search">조회</button>
				<button class="btn_reset">초기화</button>
			</div>
			<div class="total_count">
				총 <span>${lostlist.size()}</span>건의 분실물이 있습니다.
			</div>
			<div class="list_wrap">
				<table>
					<colgroup>
						<col width="8%">
						<col width="15%">
						<col width="62%">
						<col width="15%">
					</colgroup>
					<thead>
						<tr>
							<th>NO</th>
							<th>습득 도서관</th>
							<th>분실물명</th>
							<th>습득 날짜</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${lostlist}" var="item" varStatus="bookStatus">
							<tr seq="${bookStatus.count}">
								<td>${bookStatus.count}</td>
								<td>
									<c:forEach items="${librarylist}" var="library">
										${library.library_id.equals(item.library_id)?library.library_name:""}
									</c:forEach>
								</td>
								<td>${item.property_name}</td>
								<td>${item.found_date}</td>
							</tr>
						</c:forEach>
						<c:if test="${lostlist.size() == 0}">
							<tr>
								<td class="empty_result" colspan="4">
									<div class="msg">찾으시는 분실물이 현재 도서관에 존재하지 않습니다.</div>
								</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</section>
		<!-- //.booklist_container -->
	</div>
	<!-- //#wrap -->
</body>
</html>