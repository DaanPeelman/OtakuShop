<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<title>Onze producten - OtakuShop</title>
	<jsp:include page="../head.jsp" />
	<link rel="stylesheet" type="text/css" href="http://code.jquery.com/ui/1.10.2/themes/ui-lightness/jquery-ui.css" />
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="http://code.jquery.com/ui/1.10.2/jquery-ui.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$( "#slider-range-prijs" ).slider({
				range: true,
				min: 0,
				max: 500,
				values: [0, 500],
				slide: function( event, ui ) {
					$("#vanPrijs").val(ui.values[0]);
					$("#totPrijs").val(ui.values[1]);
				}
			});
			$("#vanPrijs").val($("#slider-range-prijs").slider("values", 0));
			$("#totPrijs").val($("#slider-range-prijs").slider("values", 1));
		});
		$(function() {
			$( "#slider-range-datum" ).slider({
				range: true,
				min: 2000,
				max: 2014,
				values: [2000, 2014],
				slide: function( event, ui ) {
					$("#vanDatum").val(ui.values[0]);
					$("#totDatum").val(ui.values[1]);
				}
			});
			$("#vanDatum").val($("#slider-range-datum").slider("values", 0));
			$("#totDatum").val($("#slider-range-datum").slider("values", 1));
		});
	</script>
</head>
<body>
	<jsp:include page="../menu.jsp" />
	<div id="wrapper">
		<div id="header" class="clearfix">
			<div class="content_wrap">
				<a href="<c:url value='/' />" title="OtakuShop"><img src="images/logo.gif" alt="OtakuShop" /></a>
			</div> <!-- END .content_wrap -->
		</div> <!-- END #header -->
		<div id="main_content" class="clearfix">
			<div class="content_wrap clearfix">
				<div class="aside">
					<form id="zoekform" action="#" method="get">
						<h2>Verfijn</h2>
						<p><label for="naam">Naam:</label></p>
						<p><input type="text" id="naam" title="voer een naam in" /></p>
						<p><label for="serie">Serie:</label></p>
						<p><input type="text" id="serie" title="voer een serie in" /></p>
						<p><label for="uitgever">Uitgever:</label></p>
						<p><input type="text" id="uitgever" title="voer een uitgever in" /></p>
						<p><label>Prijs:</label></p>
						<p class="slidertekst clearfix"><input type="text" id="vanPrijs" title="startprijs" readonly="readonly" /><input type="text" id="totPrijs" title="eindprijs" readonly="readonly" /></p>
						<div class="slider" id="slider-range-prijs" title="selecteer een prijs gebied" ></div>
						<p><label>Datum:</label></p>
						<p class="slidertekst clearfix"><input type="text" id="vanDatum" title="startdatum" readonly="readonly" /><input type="text" id="totDatum" title="einddatum" readonly="readonly" /></p>
						<div class="slider" id="slider-range-datum" title="selecteer datum gebied" ></div>
						<p><input type="submit" value="Zoek" title="verfijn uw zoekresultaten" /></p>
					</form>
				</div> <!-- END .aside -->
				<div id="zoek_resultaten">
					<h2>Onze producten</h2>
					<c:if test="${not empty producten}">
					<c:forEach var="product" items="${producten}">
					<div class="product_rij clearfix">
						<div class="foto">
							<img src="${pageContext.servletContext.contextPath}/images/producten/${product.id}.jpg" alt="${product.titel}"/>
						</div> <!-- END .foto -->
						<div class="info">
							<h3><a href="#" title="meer details over ${product.titel}"><c:out value="${product.titel}" /></a></h3>
							<c:if test="${product.stock == 0}"><p class="stock niet_in_stock clearfix">Niet in voorraad</p></c:if>
							<c:if test="${(product.stock > 0) && (product.stock <= 4)}"><p class="stock laatste_in_stock clearfix">Laatste ${product.stock} in voorraad</p></c:if>
							<c:if test="${product.stock > 3}"><p class="stock in_stock clearfix">In voorraad</p></c:if>
							<span class="prijs">&euro;<fmt:formatNumber value="${product.prijs}" minFractionDigits="2" maxFractionDigits="2"/></span>
							<form action="#" method="post">
								<p><label>Aantal:
								<input type="text" value="1" title="voer het aantal in dat u wil bestellen" /></label>
								<input type="submit" value="Voeg toe aan mandje" <c:if test="${product.stock > 0}">title="voeg dit product toe aan uw mandje"</c:if><c:if test="${product.stock == 0}">title="dit product is niet meer in voorraad" disabled="disabled"</c:if> /></p>
							</form>
						</div> <!-- END .info -->
					</div> <!-- END .product_rij -->
					</c:forEach>
					</c:if>
				</div> <!-- END #zoek_resultaten -->
			</div> <!-- END .content_wrap -->
		</div> <!-- END #main_content -->
		<div class="push"></div>
	</div> <!-- END #wrapper -->
	<jsp:include page="../footer.jsp" />
</html>