<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
				<h2><spring:message code="indexHeader" /></h2>
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. In eleifend <a href="#" title="lorem ipsum dolor sit amet">nunc a volutpat</a> fringilla. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac mattis neque. Aenean ipsum arcu, sagittis at porttitor et, condimentum sagittis lectus. Nam lacinia lacus vel libero convallis suscipit. Nam hendrerit non lacus et elementum. Sed egestas, lacus at congue faucibus, elit diam aliquet orci, ac sodales quam nibh sit amet nibh. Quisque eu vulputate lectus. Mauris fringilla cursus mauris, mattis tristique enim varius nec. Ut felis sapien, lobortis a dui id, ultricies suscipit urna. Vestibulum non scelerisque nulla, a imperdiet neque.</p>
				<p>Donec at vehicula lacus. Ut vitae erat ut tellus euismod posuere. Pellentesque ultrices libero ut lectus semper vestibulum. Morbi ullamcorper pharetra facilisis. Phasellus malesuada aliquam iaculis. Nulla bibendum pulvinar velit, at malesuada lacus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam at facilisis tellus. Vestibulum tincidunt ipsum in quam aliquet vulputate. Donec porta sapien sit amet lectus pretium porttitor. Sed nulla lacus, sodales et dapibus nec, adipiscing a sapien. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Quisque cursus turpis lacinia odio sodales, nec rhoncus libero feugiat. Interdum et malesuada fames ac ante ipsum primis in faucibus.</p>
				<h2>Nieuwste producten</h2>
				<div id="laatste_producten">
					<c:forEach var='product' items='${producten}'>
					<spring:url var="producturl" value="/producten/{id}">
						<spring:param name="id" value="${product.id}" />
					</spring:url>
						<div class="product_rij clearfix">
							<div class="foto">
								<img src="${pageContext.servletContext.contextPath}/images/producten/${product.id}.jpg" alt="<c:out value='${product.titel}' />"/>
							</div> <!-- END .foto -->
							<div class="info">
								<h3><a href="${producturl}" title="meer details over <c:out value='${product.titel}' />"><c:out value='${product.titel}' /></a></h3>
								<p><c:out value='${product.omschrijvingEn}' /></p>
								<span class="prijs">&euro;<fmt:formatNumber value='${product.prijs}' minFractionDigits="2" maxFractionDigits="2"/></span>
								<form class="bestelform" action="#" method="post">
									<p><label>Aantal:
									<input type="text" value="1" title="voer het aantal in dat u wil bestellen" /></label>
									<input type="submit" value="Voeg toe aan mandje" title="voeg dit product toe aan uw mandje" /></p>
								</form>
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