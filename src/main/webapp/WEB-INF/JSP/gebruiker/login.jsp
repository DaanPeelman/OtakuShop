<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
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
				<div id="aanmelden">
					<form action="login" method="post">
						<h2>Ik ben al geregistreerd</h2>
						<p class="formLabel"><label for="emailadres">Emailadres:</label></p>
						<p><input type="text" id="emailadres" title="voer uw emailadres in" /></p>
						<p class="formLabel"><label for="wachtwoord">Wachtwoord:</label></p>
						<p><input type="password" id="wachtwoord" title="voer uw wachtwoord in" /></p>
						<p><input type="submit" value="Aanmelden" title="meld u aan" /></p>
					</form>
				</div> <!-- END #aanmelden -->
				<div id="registreer">
					<form action="login" method="post">
						<h2>Ik ben nieuw hier</h2>
						<p class="formLabel"><label for="voornaam">Voornaam:</label></p>
						<p><input type="text" id="voornaam" title="voer uw voornaam in" /></p>
						<p class="formLabel"><label for="familienaam">Familienaam:</label></p>
						<p><input type="text" id="familienaam" title="voer uw familienaam in" /></p>
						<p class="formLabel"><label for="emailadres_nieuw">Emailadres:</label></p>
						<p><input type="text" id="emailadres_nieuw" title="voer uw emailadres in" /></p>
						<p class="formLabel"><label for="straat">Straat:</label></p>
						<p><input type="text" id="straat" title="voer uw straat in" /></p>
						<p class="formLabel"><label for="huisnr">Huisnummer:</label></p>
						<p><input type="text" id="huisnr" title="voer uw huisnummer in" /></p>
						<p class="formLabel"><label for="postcode">Postcode:</label></p>
						<p><input type="text" id="postcode" title="voer uw postcode in" /></p>
						<p class="formLabel"><label for="gemeente">Gemeente:</label></p>
						<p><input type="text" id="gemeente" title="voer uw gemeente in" /></p>
						<p class="formLabel"><label for="wachtwoord_nieuw">Wachtwoord:</label></p>
						<p><input type="password" id="wachtwoord_nieuw" title="kies een wachtwoord" /></p>
						<p class="formLabel"><label for="wachtwoord_bevestig">Bevestig uw wachtwoord:</label></p>
						<p><input type="password" id="wachtwoord_bevestig" title="bevestig uw wachtwoord" /></p>
						<p><input type="submit" value="Registreer" title="registreer u" /></p>
					</form>
				</div> <!-- END #registreer -->
			</div> <!-- END .content_wrap -->
		</div> <!-- END #main_content -->
		<div class="push"></div>
	</div> <!-- END #wrapper -->
	<jsp:include page="../footer.jsp" />
</body>
</html>