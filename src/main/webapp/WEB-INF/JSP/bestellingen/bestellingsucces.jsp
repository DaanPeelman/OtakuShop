<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Uw bestelling is geplaatst - OtakuShop</title>
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
				<p><a href="producten.html" title="terug naar productoverzicht">&lt; Verder shoppen</a></p>
				<h2>Uw bestelling is succesvol geplaatst</h2>
				<p>Bedankt voor het bestellen bij OtakuShop. Hieronder vindt u meer informatie over uw net geplaatste bestelling.</p>
				<table id="winkelmandje">
					<thead>
						<tr>
							<td class="product">Artikel</td>
							<td class="aantal">Aantal</td>
							<td class="prijs_header">Subtotaal</td>
						</tr>
					</thead>
					<tbody>
					<c:set var="totaal" value="0"/>
					<c:forEach var="bestellijn" items="${bestelbon.bestelbonlijnen}">
					<spring:url var="producturl" value="/producten/{id}">
						<spring:param name="id" value="${bestellijn.product.id}" />
					</spring:url>
						<tr>
							<td class="product_mandje"><img src="${pageContext.servletContext.contextPath}/images/producten/${bestellijn.product.id}.jpg" alt="${bestellijn.product.titel}"/><h3><a href="${producturl}" title="meer details over ${bestellijn.product.titel}">${bestellijn.product.titel}</a></h3></td>
							<td>${bestellijn.aantal}</td>
							<td class="prijs">&euro;<fmt:formatNumber value="${bestellijn.product.prijs * bestellijn.aantal}" minFractionDigits="2" maxFractionDigits="2" /></td>
						</tr>
						<c:set var="totaal" value="${totaal + (bestellijn.product.prijs * bestellijn.aantal)}" />
					</c:forEach>
						<tr>
							<td>&nbsp;</td>
							<td class="winkelmandje_overzicht totaal">Totaal:</td>
							<td class="winkelmandje_overzicht prijs">&euro;<fmt:formatNumber value="${totaal}" minFractionDigits="2" maxFractionDigits="2" /></td>
						</tr>
					</tbody>
				</table>
				<h2>Afleveradres</h2>
				<p>${bestelbon.leverAdres.straat}&nbsp;${bestelbon.leverAdres.nummer},<br />${bestelbon.leverAdres.postcode}&nbsp;${bestelbon.leverAdres.gemeente}</p>
			</div> <!-- END .content_wrap -->
		</div> <!-- END #main_content -->
		<div class="push"></div>
	</div> <!-- END #wrapper -->
	<jsp:include page="../footer.jsp" />
</body>
</html>