<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Index - OtakuShop</title>
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
				<h2>Nieuwste producten</h2>
				<div id="laatste_producten">
				<c:url var="url" value="/mandje" />
					<c:forEach var='product' items='${producten}'>
					<spring:url var="producturl" value="/producten/{id}">
						<spring:param name="id" value="${product.id}" />
					</spring:url>
						<div class="product_rij clearfix" title="meer details over ${product.titel}">
							<div class="foto">
								<img src="${pageContext.servletContext.contextPath}/images/producten/${product.id}.jpg" alt="<c:out value='${product.titel}' />"/>
							</div> <!-- END .foto -->
							<div class="info">
								<h3><a href="${producturl}" title="meer details over <c:out value='${product.titel}' />"><c:out value='${product.titel}' /></a></h3>
								<p><c:out value='${product.omschrijvingEn}' /></p>
								<span class="prijs">&euro;<fmt:formatNumber value='${product.prijs}' minFractionDigits="2" maxFractionDigits="2"/></span>
								<form:form class="bestelform" commandName="productAankoopForm" action="${url}" method="post">
									<p><form:input path="productId" type="hidden" value="${product.id}"/>
									<form:input path="aantal" type="hidden" />
									<input type="submit" value="Voeg toe aan mandje" title="voeg dit product toe aan uw mandje" /></p>
								</form:form>
							</div> <!-- END .info -->
						</div> <!-- END .product_rij -->
					</c:forEach>
				</div> <!-- END #laatste_producten -->
			</div> <!-- END .content_wrap -->
		</div> <!-- END #main_content -->
		<div class="push"></div>
	</div> <!-- END #wrapper -->
	<jsp:include page="footer.jsp" />
</body>
</html>