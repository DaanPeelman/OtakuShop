<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Uw gegevens - OtakuShop</title>
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
				<h2>Welkom ${gebruiker.voornaam}</h2>
				<h3>Uw recente bestellingen</h3>
				<c:forEach var="bestelling" items="${gebruiker.bestellingen}">
					<c:set var="totaal" value="0" />
					<c:forEach var="bestelbonlijn" items="${bestelling.bestelbonlijnen}">
						<c:set var="totaal" value="${totaal + (bestelbonlijn.aantal * bestelbonlijn.prijs)}" />
					</c:forEach>
					<spring:url var="bestellingurl" value="/bestellingen/{id}">
						<spring:param name="id" value="${bestelling.id}"/>
					</spring:url>
					<p><a href="${bestellingurl}" title="bekijk de details van de bestelling"><fmt:formatDate value="${bestelling.datum}" dateStyle="short"/> &euro;${totaal}</a></p>
					<c:set var="totaal" value="0" />
				</c:forEach>
				<h3>Uw gegevens</h3>
				<p>Naam: ${gebruiker.voornaam}&nbsp;${gebruiker.familienaam}</p>
				<p>Adres: ${gebruiker.adres.straat}&nbsp;${gebruiker.adres.nummer},&nbsp;${gebruiker.adres.postcode}&nbsp;${gebruiker.adres.gemeente}</p>
			</div> <!-- END .content_wrap -->
		</div> <!-- END #main_content -->
		<div class="push"></div>
	</div> <!-- END #wrapper -->
	<jsp:include page="../footer.jsp" />
</body>
</html>