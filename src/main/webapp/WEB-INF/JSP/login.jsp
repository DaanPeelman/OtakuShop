<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<title>Meld u aan of registreer u - OtakuShop</title>
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
				<div id="aanmelden">
				<c:url var="loginurl" value="/login" />
					<form action="${loginurl}" method="post">
						<h2>Ik ben al geregistreerd</h2>
						<p class="formLabel"><label for="emailadres">Emailadres:</label></p>
						<p><input name="username" type="text" id="emailadres" title="voer uw emailadres in" /></p>
						<p class="formLabel"><label for="wachtwoord">Wachtwoord:</label></p>
						<p><input name="password" type="password" id="wachtwoord" title="voer uw wachtwoord in" /></p>
						<security:csrfInput/>
						<c:if test="${param.error != null}">
							<p>Verkeerde gebruikersnaam of wachtwoord</p>
						</c:if>
						<p><input type="submit" value="Aanmelden" title="meld u aan" /></p>
					</form>
				</div> <!-- END #aanmelden -->
				<div id="registreer">
				<c:url var='url' value='/gebruiker' />
					<form:form action="${url}" commandName="gebruiker" method="post">
						<h2>Ik ben nieuw hier</h2>
						<p class="formLabel"><form:label path="voornaam" for="voornaam">Voornaam:</form:label></p>
						<p><form:input path="voornaam" type="text" id="voornaam" title="voer uw voornaam in" /></p>
						<p><form:errors path="voornaam" cssClass="fout" /></p>
						<p class="formLabel"><form:label path="familienaam" for="familienaam">Familienaam:</form:label></p>
						<p><form:input path="familienaam" type="text" id="familienaam" title="voer uw familienaam in" /></p>
						<p><form:errors path="familienaam" cssClass="fout" /></p>
						<p class="formLabel"><form:label path="emailadres" for="emailadres_nieuw">Emailadres:</form:label></p>
						<p><form:input path="emailadres" type="text" id="emailadres_nieuw" title="voer uw emailadres in" /></p>
						<p><form:errors path="emailadres" cssClass="fout" /></p>
						<p class="formLabel"><form:label path="adres.straat" for="straat">Straat:</form:label></p>
						<p><form:input path="adres.straat" type="text" id="straat" title="voer uw straat in" /></p>
						<p><form:errors path="adres.straat" cssClass="fout" /></p>
						<p class="formLabel"><form:label path="adres.nummer" for="huisnr">Huisnummer:</form:label></p>
						<p><form:input path="adres.nummer" type="text" id="huisnr" title="voer uw huisnummer in" /></p>
						<p><form:errors path="adres.nummer" cssClass="fout" /></p>
						<p class="formLabel"><form:label path="adres.postcode" for="postcode">Postcode:</form:label></p>
						<p><form:input path="adres.postcode" type="text" id="postcode" title="voer uw postcode in" /></p>
						<p><form:errors path="adres.postcode" cssClass="fout" /></p>
						<p class="formLabel"><form:label path="adres.gemeente" for="gemeente">Gemeente:</form:label></p>
						<p><form:input path="adres.gemeente" type="text" id="gemeente" title="voer uw gemeente in" /></p>
						<p><form:errors path="adres.gemeente" cssClass="fout" /></p>
						<p class="formLabel"><form:label path="wachtwoord" for="wachtwoord_nieuw">Wachtwoord:</form:label></p>
						<p><form:input path="wachtwoord" type="password" id="wachtwoord_nieuw" title="kies een wachtwoord" /></p>
						<p><form:errors path="wachtwoord" cssClass="fout" /></p>
						<p class="formLabel"><form:label path="wachtwoordBevestig" for="wachtwoord_bevestig">Bevestig uw wachtwoord:</form:label></p>
						<p><form:input path="wachtwoordBevestig" type="password" id="wachtwoord_bevestig" title="bevestig uw wachtwoord" /></p>
						<p><form:errors path="wachtwoordBevestig" cssClass="fout" /></p>
						<p><input type="submit" value="Registreer" title="registreer u" /></p>
					</form:form>
				</div> <!-- END #registreer -->
			</div> <!-- END .content_wrap -->
		</div> <!-- END #main_content -->
		<div class="push"></div>
	</div> <!-- END #wrapper -->
	<jsp:include page="footer.jsp" />
</body>
</html>