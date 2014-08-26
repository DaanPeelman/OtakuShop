<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Uw mandje - OtakuShop</title>
	<jsp:include page="head.jsp" />
</head>
<body>
	<div id="wrapper">
		<jsp:include page="menu.jsp" />
		<div id="header" class="clearfix">
			<div class="content_wrap">
				<a href="index.html" title="OtakuShop"><img src="${pageContext.servletContext.contextPath}/images/logo.gif" alt="OtakuShop" /></a>
			</div> <!-- END .content_wrap -->
		</div> <!-- END #header -->
		<div id="main_content" class="clearfix">
			<div class="content_wrap clearfix">
			<c:url value='/producten' var="productenurl" />
				<p><a href="${productenurl}" title="terug naar productoverzicht">&lt; Verder shoppen</a></p>
				<h2>Uw mandje</h2>
				<c:if test="${not empty mandje}">
				<table id="winkelmandje">
					<thead>
						<tr>
							<td class="product">Artikel</td>
							<td class="aantal">Aantal</td>
							<td class="prijs_header">Subtotaal</td>
							<td class="verwijderen">&nbsp;</td>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="bestellijn" items="${mandje.bestelbonlijnen}">
					<spring:url var="producturl" value="/producten/{id}">
						<spring:param name="id" value="${bestellijn.product.id}" />
					</spring:url>
						<tr>
							<td class="product_mandje"><img src="${pageContext.servletContext.contextPath}/images/producten/${bestellijn.product.id}.jpg" alt="${bestellijn.product.titel}"/><h3><a href="${producturl}" title="meer details over ${bestellijn.product.titel}">${bestellijn.product.titel}</a></h3>&euro;<fmt:formatNumber value="${bestellijn.product.prijs}" minFractionDigits="2" maxFractionDigits="2" /></td>
							<td><input type="text" value="1" title="voer het aantal in dat u wil bestellen" /></td>
							<td class="prijs">&euro;43.77</td>
							<td><form action="#" method="post"><div><input type="submit" value="X" title="verwijder dit product uit uw mandje" class="delete_btn" /></div></form></td>
						</tr>
					</c:forEach>
						<tr>
							<td>&nbsp;</td>
							<td class="winkelmandje_overzicht totaal">Totaal:</td>
							<td class="winkelmandje_overzicht prijs">&euro;143.31</td>
							<td class="winkelmandje_overzicht">&nbsp;</td>
						</tr>
					</tbody>
				</table>
				<form id="mandjeform" action="#" method="post">
					<h2>Afleveradres</h2>
					<p><label for="straat">Straat:</label></p>
					<p><input type="text" id="straat" title="voer uw straat in" /></p>
					<p><label for="huisnummer">Huisnummer:</label></p>
					<p><input type="text" id="huisnummer" title="voer uw huisnummer" /></p>
					<p><label for="postcode">Postcode:</label></p>
					<p><input type="text" id="postcode" title="voer uw postcode in" /></p>
					<p><label for="gemeente">Gemeente:</label></p>
					<p><input type="text" id="gemeente" title="voer uw gemeente in" /></p>
					<p><input type="submit" value="Bestelling afronden" title="rond uw bestelling af" /></p>
				</form>
				</c:if>
				<c:if test="${empty mandje}">
					<p>Er zijn geen producten in uw mandje.</p>
				</c:if>
			</div> <!-- END .content_wrap -->
		</div> <!-- END #main_content -->
		<div class="push"></div>
	</div> <!-- END #wrapper -->
	<jsp:include page="footer.jsp" />
</body>
</html>