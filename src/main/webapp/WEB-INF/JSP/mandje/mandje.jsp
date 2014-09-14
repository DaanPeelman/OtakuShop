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
	<title>Uw mandje - OtakuShop</title>
	<jsp:include page="../head.jsp" />
	<script type="text/javascript">
		$(document).ready(function(){
			if (($('#straat').val() == "${adresGebruiker.straat}") && ($('#huisnummer').val() == "${adresGebruiker.nummer}") && ($('#gemeente').val() == "${adresGebruiker.gemeente}") && ($('#postcode').val() == "${adresGebruiker.postcode}")) {
				$('#cbxThuisAdres').prop('checked', true);
			}

			$('#cbxThuisAdres').change(
				function() {
					if ($('#cbxThuisAdres').is(':checked')) {
						$('#straat').val("${adresGebruiker.straat}");
						$('#huisnummer').val("${adresGebruiker.nummer}");
						$('#gemeente').val("${adresGebruiker.gemeente}");
						$('#postcode').val("${adresGebruiker.postcode}");
					} else {
						$('#straat').val("");
						$('#huisnummer').val("");
						$('#gemeente').val("");
						$('#postcode').val("");
					}
			});
		});
	</script>
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
			<c:url value='/producten' var="productenurl" />
				<p><a href="${productenurl}" title="terug naar productoverzicht">&lt; Verder shoppen</a></p>
				<h2>Uw mandje</h2>
				<c:if test="${not empty productenInMandje}">
				<c:url var="url" value="/mandje" />
				<form:form id="mandjeform" commandName="mandjeForm" action="${url}" method="put">
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
					<c:set var="totaal" value="0" />
					<c:forEach items="${productenInMandje.bestelbonlijnen}" var="lijn" varStatus="i"> 
					<spring:url var="producturl" value="/producten/{id}">
						<spring:param name="id" value="${lijn.product.id}" />
					</spring:url>
						<tr>
							<td class="product_mandje" title="meer details over ${lijn.product.titel}"><img src="${pageContext.servletContext.contextPath}/images/producten/${lijn.product.id}.jpg" alt="${lijn.product.titel}"/><h3><a href="${producturl}" title="meer details over ${lijn.product.titel}">${lijn.product.titel}</a></h3>&euro;<span id="lijnen${i.index}Prijs"><fmt:formatNumber value="${lijn.product.prijs}" minFractionDigits="2" maxFractionDigits="2" /></span><span class="id">${lijn.product.id}</span></td>
							<td>
							<form:input path="lijnen[${i.index}].id" type="hidden" value="${lijn.product.id}" />
							<form:input path="lijnen[${i.index}].aantal" id="lijnen${i.index}" type="text" title="voer het aantal in dat u wil bestellen" value="${lijn.aantal}"/><form:errors path="lijnen[${i.index}].aantal" cssClass="fout" /></td>
							<td class="prijs">&euro;<span id="lijnen${i.index}Totaal"><fmt:formatNumber value="${lijn.product.prijs * lijn.aantal}" minFractionDigits="2" maxFractionDigits="2" /></span></td>
							<spring:url var="verwijderurl" value="/mandje/{id}/verwijder">
								<spring:param name="id" value="${lijn.product.id}" />
							</spring:url>
							<td><a href="${verwijderurl}" title="verwijder dit product uit uw mandje">X</a></td>
						</tr>
						<c:set var="totaal" value="${totaal + (lijn.product.prijs * lijn.aantal)}" />
					</c:forEach>
						<tr>
							<td>&nbsp;</td>
							<td class="winkelmandje_overzicht totaal">Totaal:</td>
							<td class="winkelmandje_overzicht prijs">&euro;<span id="totaal"><fmt:formatNumber value="${totaal}" minFractionDigits="2" maxFractionDigits="2" /></span></td>
							<td class="winkelmandje_overzicht">&nbsp;</td>
						</tr>
					</tbody>
				</table>
				<c:if test="${nietGenoegInStock}">
					<div class="outsideForm">
						<p><span class="fout">Een of meerdere producten in uw mandje hebben niet genoeg stock.</span></p>
					</div>
				</c:if>
					<h2>Afleveradres</h2>
					<c:if test="${not empty adresGebruiker}">
					<div>
						<p>
							<input type="checkbox" id="cbxThuisAdres" title="gebruik uw thuisadres als afleveradres"/>
							<label for="cbxThuisAdres">Gebruik mijn thuisadres als afleveradres</label>
						</p>
					</div>
					</c:if>
					<div>
					<p><form:label path="adres.straat" for="straat">Straat:</form:label></p>
					<p><form:input path="adres.straat" type="text" id="straat" title="voer uw straat in" /></p>
					<p><form:errors path="adres.straat" cssClass="fout"/></p>
					</div>
					<div>
					<p><form:label path="adres.nummer" for="huisnummer">Huisnummer:</form:label></p>
					<p><form:input path="adres.nummer" type="text" id="huisnummer" title="voer uw huisnummer" /></p>
					<p><form:errors path="adres.nummer" cssClass="fout"/></p>
					</div>
					<div>
					<p><form:label path="adres.postcode" for="postcode">Postcode:</form:label></p>
					<p><form:input path="adres.postcode" type="text" id="postcode" title="voer uw postcode in" /></p>
					<p><form:errors path="adres.postcode" cssClass="fout"/></p>
					</div>
					<div>
					<p><form:label path="adres.gemeente" for="gemeente">Gemeente:</form:label></p>
					<p><form:input path="adres.gemeente" type="text" id="gemeente" title="voer uw gemeente in" /></p>
					<p><form:errors path="adres.gemeente" cssClass="fout"/></p>
					</div>
					<div>
					<p><input type="submit" value="Bestelling afronden" title="rond uw bestelling af" /></p>
					</div>
				</form:form>
				</c:if>
				<c:if test="${empty productenInMandje}">
					<p>Er zijn geen producten in uw mandje.</p>
				</c:if>
			</div> <!-- END .content_wrap -->
		</div> <!-- END #main_content -->
		<div class="push"></div>
	</div> <!-- END #wrapper -->
	<jsp:include page="../footer.jsp" />
</body>
</html>