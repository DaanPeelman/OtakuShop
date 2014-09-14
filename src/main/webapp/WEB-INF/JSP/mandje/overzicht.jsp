<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
	<title>Overzicht van uw mandje - OtakuShop</title>
	<jsp:include page="../head.jsp" />
</head>
<body>
	<div id="wrapper">
		<jsp:include page="../menu.jsp" />
		<div id="header" class="clearfix">
			<div class="content_wrap">
				<a href="<c:url value='/' />" title="OtakuShop"><img src="${pageContext.servletContext.contextPath}/images/logo.gif" alt="OtakuShop" /></a>
			</div> <!-- END .content_wrap -->
		</div> <!-- END #header -->
		<div id="main_content" class="clearfix">
			<div class="content_wrap clearfix">
				<h2>Overzicht van uw mandje</h2>
				<c:if test="${not empty bestelbon}">
				<c:url var="url" value="/bestellingen" />
				<table id="winkelmandje">
					<thead>
						<tr>
							<td class="product">Artikel</td>
							<td class="aantal">Aantal</td>
							<td class="prijs_header">Subtotaal</td>
						</tr>
					</thead>
					<tbody>
					<c:set var="totaal" value="0" />
					<c:forEach items="${bestelbon.bestelbonlijnen}" var="lijn"> 
					<spring:url var="producturl" value="/producten/{id}">
						<spring:param name="id" value="${lijn.product.id}" />
					</spring:url>
						<tr>
							<td class="product_mandje" title="meer details over ${lijn.product.titel}"><img src="${pageContext.servletContext.contextPath}/images/producten/${lijn.product.id}.jpg" alt="${lijn.product.titel}"/><h3><a href="${producturl}" title="meer details over ${lijn.product.titel}">${lijn.product.titel}</a></h3>&euro;<fmt:formatNumber value="${lijn.product.prijs}" minFractionDigits="2" maxFractionDigits="2" /><span class="id">${lijn.product.id}</span></td>
							<td>${lijn.aantal}</td>
							<td class="prijs">&euro;<fmt:formatNumber value="${lijn.product.prijs * lijn.aantal}" minFractionDigits="2" maxFractionDigits="2" /></td>
						</tr>
						<c:set var="totaal" value="${totaal + (lijn.product.prijs * lijn.aantal)}" />
					</c:forEach>
						<tr>
							<td>&nbsp;</td>
							<td class="winkelmandje_overzicht totaal">Totaal:</td>
							<td class="winkelmandje_overzicht prijs">&euro;<fmt:formatNumber value="${totaal}" minFractionDigits="2" maxFractionDigits="2" /></td>
						</tr>
					</tbody>
				</table>
				
					<h2>Uw afleveradres</h2>
					<p>${bestelbon.leverAdres.straat}&nbsp;${bestelbon.leverAdres.nummer}, ${bestelbon.leverAdres.postcode}&nbsp;${bestelbon.leverAdres.gemeente}</p>
					<p><a href="<c:url value="/mandje" />" title="wijzig uw bestelling">Wijzig bestelling</a></p>
					<form action="${url}" method="post">
						<p><input type="submit" value="Bestelling afronden" title="rond uw bestelling af" /></p>
						<security:csrfInput/>
					</form>
				</c:if>
			</div> <!-- END .content_wrap -->
		</div> <!-- END #main_content -->
		<div class="push"></div>
	</div> <!-- END #wrapper -->
	<jsp:include page="../footer.jsp" />
</body>
</html>