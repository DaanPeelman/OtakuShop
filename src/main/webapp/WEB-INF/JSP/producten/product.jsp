<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title><c:out value='${product.titel}' /> - OtakuShop</title>
	<jsp:include page="../head.jsp" />
	<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/styles/lightbox.css" />
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/javascript/lightbox.min.js"></script>
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/javascript/external.js"></script>

</head>
<body>
	<div id="wrapper">
		<jsp:include page="../menu.jsp" />
		<div id="header" class="clearfix">
			<div class="content_wrap">
				<a href="<c:out value='/' />" title="OtakuShop"><img src="${pageContext.servletContext.contextPath}/images/logo.gif" alt="OtakuShop" /></a>
			</div> <!-- END .content_wrap -->
		</div> <!-- END #header -->
		<div id="main_content" class="clearfix">
			<div class="content_wrap clearfix">
				<p><a href="<c:url value='/producten' />" title="terug naar productoverzicht">&lt; Terug naar productoverzicht</a></p>
				<div id="product_overzicht">
				<c:url var="url" value="/mandje" />
					<div class="clearfix">
						<div id="foto">
							<a href="${pageContext.servletContext.contextPath}/images/producten/${product.id}.jpg" title="<c:out value='${product.titel}' />" rel="lightbox"><img src="${pageContext.servletContext.contextPath}/images/producten/${product.id}.jpg" alt="<c:out value='${product.titel}' />" /></a>
						</div> <!-- END #foto -->
						<div id="info">
							<h2><c:out value='${product.titel}' /></h2>
							<p>Serie: <c:out value='${product.serie.titel}' /></p>
							<p>Uitgever: <c:out value='${product.uitgever.naam}' /></p>
							<p>Hoogte: ${product.hoogte}mm</p>
							<p>Uitgiftedatum: <fmt:formatDate value='${product.uitgifte}'/></p>
							<c:if test='${product.stock == 0}'><p class="stock niet_in_stock clearfix">Niet in voorraad</p></c:if>
							<c:if test='${product.stock == 1}'><p class="stock laatste_in_stock clearfix">Laatste in voorraad</p></c:if>
							<c:if test='${(product.stock > 1) && (product.stock < 4)}'><p class="stock laatste_in_stock clearfix">Laatste ${product.stock} in voorraad</p></c:if>
							<c:if test='${product.stock > 3}'><p class="stock in_stock clearfix">In voorraad</p></c:if>
							<span class="prijs">&euro;<fmt:formatNumber value='${product.prijs}' minFractionDigits='2' maxFractionDigits='2' /></span>
							<form:form class="bestelform" commandName="productAankoopForm" action="${url}" method="post">
								<p><form:input path="productId" type="hidden" value="${product.id}" /><form:label path="aantal" for="aantal">Aantal:</form:label>
								<form:input path="aantal" id="aantal" type="text" title="voer het aantal in dat u wil bestellen" />
								<input type="submit" value="Voeg toe aan mandje" <c:if test="${product.stock > 0}">title="voeg dit product toe aan uw mandje"</c:if><c:if test="${product.stock == 0}">title="dit product is niet meer in voorraad" disabled="disabled"</c:if> /></p>
								<p><form:errors path="aantal" cssClass="fout" /></p>
							</form:form>
						</div> <!-- END #info -->
					</div> <!-- END .clearfix -->
					<div id="omschrijving">
						<p><c:out value='${product.omschrijvingEn}' /></p>
					</div> <!-- END #omschrijving -->
				</div> <!-- END #product_overzicht -->
			</div> <!-- END .content_wrap -->
		</div> <!-- END #main_content -->
		<div class="push"></div>
	</div> <!-- END #wrapper -->
	<jsp:include page="../footer.jsp" />
</body>
</html>