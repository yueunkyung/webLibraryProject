<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="appPath" scope="application" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>즐거운 라라 도서관</title>
<link rel="stylesheet" href="${appPath}/static/css/common.css">
</head>
<body>
	<!-- error_wrap -->
	<div class="error_wrap">
		<div class="img_wrap">
			<img src="${appPath}/static/images/error/img_error_404.jpg" alt="404 error" />
		</div>
		<div class="error_message">
			<h1 class="tit">PAGE NOT FOUND</h1>
			<p class="txt">We cannot find the page you are looking for.</p>
			<div class="btn_wrap">
				<a href="javascript:window.history.back();" class="btn_prev">Back to the Previous Page&gt;&gt;</a>
			</div>
		</div>
	</div?>
	<!-- //error_wrap -->
</body>
</html>