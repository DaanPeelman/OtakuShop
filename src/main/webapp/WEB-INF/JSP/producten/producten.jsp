<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<title>Onze producten - OtakuShop</title>
	<jsp:include page="../head.jsp" />
	<link rel="stylesheet" type="text/css" href="http://code.jquery.com/ui/1.10.2/themes/ui-lightness/jquery-ui.css" />
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="http://code.jquery.com/ui/1.10.2/jquery-ui.min.js"></script>
	<script type="text/javascript">
		var pMax = parseInt("${maxPrijs}");
		var selectStartPrijs = parseInt("${selectStartPrijs}");
		var selectEindPrijs = parseInt("${selectEindPrijs}");
		
		if(isNaN(selectStartPrijs)) {
			selectStartPrijs = 0;
		}
		if(isNaN(selectEindPrijs)) {
			selectEindPrijs = pMax;
		}
		
		$(function() {
			$( "#slider-range-prijs" ).slider({
				range: true,
				min: 0,
				max: pMax,
				values: [selectStartPrijs, selectEindPrijs],
				slide: function( event, ui ) {
					$("#vanPrijs").val(ui.values[0]);
					$("#totPrijs").val(ui.values[1]);
				}
			});
			$("#vanPrijs").val($("#slider-range-prijs").slider("values", 0));
			$("#totPrijs").val($("#slider-range-prijs").slider("values", 1));
		});
		
		var dMin = parseInt("${minDatum}");
		var dMax = parseInt("${maxDatum}");
		var selectStartJaar = parseInt("${selectStartJaar}");
		var selectEindJaar = parseInt("${selectEindJaar}");
		
		if(isNaN(selectStartJaar)) {
			selectStartJaar = dMin;
		}
		if(isNaN(selectEindJaar)) {
			selectEindJaar = dMax;
		}
		
		$(function() {
			$( "#slider-range-datum" ).slider({
				range: true,
				min: dMin,
				max: dMax,
				values: [selectStartJaar, selectEindJaar],
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
					<c:url var="zoekurl" value='/producten' />
					<form:form id="zoekform" commandName="zoekForm" action="${zoekurl}" method="get">
						<h2>Verfijn</h2>
						<p><form:label path="titel" for="naam">Naam:</form:label></p>
						<p><form:input path="titel" type="text" id="naam" title="voer een naam in" /></p>
						<p><form:label path="serie" for="serie">Serie:</form:label></p>
						<p><form:input path="serie" type="text" id="serie" title="voer een serie in" /></p>
						<p><form:label path="uitgever" for="uitgever">Uitgever:</form:label></p>
						<p><form:select path="uitgever" id="uitgever" title="selecteer een uitgever">
							<form:option value="-" label="-" />
							<form:options items="${uitgevers}" />
						</form:select></p>
						<p><label>Prijs:</label></p>
						<p class="slidertekst clearfix"><form:input path="startPrijs" type="text" id="vanPrijs" title="startprijs" readonly="readonly" /><form:input path="eindPrijs" type="text" id="totPrijs" title="eindprijs" readonly="readonly" /></p>
						<div class="slider" id="slider-range-prijs" title="selecteer een prijs gebied" ></div>
						<p><label>Datum:</label></p>
						<p class="slidertekst clearfix"><form:input path="startJaar" type="text" id="vanDatum" title="startdatum" readonly="readonly" /><form:input path="eindJaar" type="text" id="totDatum" title="einddatum" readonly="readonly" /></p>
						<div class="slider" id="slider-range-datum" title="selecteer datum gebied" ></div>
						<p><input type="submit" value="Zoek" title="verfijn uw zoekresultaten" /></p>
					</form:form>
				</div> <!-- END .aside -->
				<div id="zoek_resultaten">
					<h2>Onze producten</h2>
					<c:if test="${not empty producten}">
					<c:forEach var="product" items="${producten}">
					<spring:url var="producturl" value="/producten/{id}">
						<spring:param name="id" value="${product.id}" />
					</spring:url>
					<div class="product_rij clearfix">
						<div class="foto">
							<img src="${pageContext.servletContext.contextPath}/images/producten/${product.id}.jpg" alt="${product.titel}"/>
						</div> <!-- END .foto -->
						<div class="info">
							<h3><a href="${producturl}" title="meer details over <c:out value='${product.titel}' />"><c:out value="${product.titel}" /></a></h3>
							<c:if test="${product.stock == 0}"><p class="stock niet_in_stock clearfix">Niet in voorraad</p></c:if>
							<c:if test="${product.stock == 1}"><p class="stock laatste_in_stock clearfix">Laatste in voorraad</p></c:if>
							<c:if test="${(product.stock > 1) && (product.stock < 4)}"><p class="stock laatste_in_stock clearfix">Laatste ${product.stock} in voorraad</p></c:if>
							<c:if test="${product.stock > 3}"><p class="stock in_stock clearfix">In voorraad</p></c:if>
							<span class="prijs">&euro;<fmt:formatNumber value="${product.prijs}" minFractionDigits="2" maxFractionDigits="2"/></span>
							<form class="bestelform" action="#" method="post">
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