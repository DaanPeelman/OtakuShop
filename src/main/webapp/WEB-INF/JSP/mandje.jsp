<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
						<tr>
							<td class="product_mandje"><img src="images/nadeko.jpg" alt="Nendoroid Nadeko"/><h3><a href="product.html" title="meer details over Nendoroid Nadeko Sengoku">Nendoroid Nadeko Sengoku</a></h3>&euro;43.77</td>
							<td><input type="text" value="1" title="voer het aantal in dat u wil bestellen" /></td>
							<td class="prijs">&euro;43.77</td>
							<td><form action="#" method="post"><div><input type="submit" value="X" title="verwijder dit product uit uw mandje" class="delete_btn" /></div></form></td>
						</tr>
						<tr>
							<td class="product_mandje"><h3><img src="images/shimakaze.jpg" alt="Nendoroid Shimakaze"/><a href="#" title="meer details over Nendoroid Shimakaze">Nendoroid Shimakaze</a></h3>&euro;49.77</td>
							<td><input type="text" value="2" title="voer het aantal in dat u wil bestellen" /></td>
							<td class="prijs">&euro;99.54</td>
							<td><form action="#" method="post"><div><input type="submit" value="X" title="verwijder dit product uit uw mandje" class="delete_btn" /></div></form></td>
						</tr>
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
			</div> <!-- END .content_wrap -->
		</div> <!-- END #main_content -->
		<div class="push"></div>
	</div> <!-- END #wrapper -->
	<jsp:include page="footer.jsp" />
</body>
</html>