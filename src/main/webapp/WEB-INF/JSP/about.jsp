<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>Over ons - OtakuShop</title>
	<jsp:include page="head.jsp" />
</head>
<body>
	<div id="wrapper">
		<jsp:include page="menu.jsp" />
		<div id="header" class="clearfix">
			<div class="content_wrap">
				<a href="<c:url value='/' />" title="OtakuShop"><img src="${pageContext.servletContext.contextPath}/images/logo.gif" alt="OtakuShop" /></a>
			</div> <!-- END .content_wrap -->
		</div> <!-- END #header -->
		<div id="main_content" class="clearfix">
			<div class="content_wrap clearfix">
				<h2>Over ons</h2>
				<p>OtakuShop is het eindproject van <a href="http://be.linkedin.com/pub/daan-peelman/95/5b9/a78" title="bekijk mijn LinkedIn pagina" rel="blank">Daan Peelman</a>.</p>
			</div> <!-- END .content_wrap -->
		</div> <!-- END #main_content -->
		<div class="push"></div>
	</div> <!-- END #wrapper -->
	<jsp:include page="footer.jsp" />
</body>
</html>