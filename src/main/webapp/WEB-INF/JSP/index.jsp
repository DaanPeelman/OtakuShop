<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Index - OtakuShop</title>
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
				<h2><spring:message code="indexHeader" /></h2>
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. In eleifend <a href="#" title="lorem ipsum dolor sit amet">nunc a volutpat</a> fringilla. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac mattis neque. Aenean ipsum arcu, sagittis at porttitor et, condimentum sagittis lectus. Nam lacinia lacus vel libero convallis suscipit. Nam hendrerit non lacus et elementum. Sed egestas, lacus at congue faucibus, elit diam aliquet orci, ac sodales quam nibh sit amet nibh. Quisque eu vulputate lectus. Mauris fringilla cursus mauris, mattis tristique enim varius nec. Ut felis sapien, lobortis a dui id, ultricies suscipit urna. Vestibulum non scelerisque nulla, a imperdiet neque.</p>
				<p>Donec at vehicula lacus. Ut vitae erat ut tellus euismod posuere. Pellentesque ultrices libero ut lectus semper vestibulum. Morbi ullamcorper pharetra facilisis. Phasellus malesuada aliquam iaculis. Nulla bibendum pulvinar velit, at malesuada lacus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam at facilisis tellus. Vestibulum tincidunt ipsum in quam aliquet vulputate. Donec porta sapien sit amet lectus pretium porttitor. Sed nulla lacus, sodales et dapibus nec, adipiscing a sapien. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Quisque cursus turpis lacinia odio sodales, nec rhoncus libero feugiat. Interdum et malesuada fames ac ante ipsum primis in faucibus.</p>
				<h2>Nieuwste producten</h2>
				<div id="laatste_producten">
					<div class="product_rij clearfix">
						<div class="foto">
							<img src="${pageContext.servletContext.contextPath}/images/producten/1.jpg" alt="Nendoroid Nadeko"/>
						</div> <!-- END .foto -->
						<div class="info">
							<h3><a href="#" title="meer details over Nendoroid Nadeko Sengoku">Nendoroid Nadeko Sengoku</a></h3>
							<p>From the popular anime series 'Bakemonogatari' comes a Nendoroid of the girl with a snake wrapped around her - Nadeko Sengoku! She comes with three expressions including her standard expression, an unsure embarrassed expression and a bright smiling expression! Her trademark large hat as well as the coat she wears on her shoulders are both included and can be attached and removed whenever you like, allowing you to recreate all sorts of different scenes from the series! She also comes with other optional parts including a swimsuit and sitting parts! Be sure to display her with the other characters from the series to fully bring out the Bakemonogatari universe!</p>
							<span class="prijs">&euro;43.77</span>
							<form class="bestelform" action="#" method="post">
								<p><label>Aantal:
								<input type="text" value="1" title="voer het aantal in dat u wil bestellen" /></label>
								<input type="submit" value="Voeg toe aan mandje" title="voeg dit product toe aan uw mandje" /></p>
							</form>
						</div> <!-- END .info -->
					</div> <!-- END .product_rij -->
					<div class="product_rij clearfix">
						<div class="foto">
							<img src="${pageContext.servletContext.contextPath}/images/producten/2.jpg" alt="Nendoroid Shimakaze"/>
						</div> <!-- END .foto -->
						<div class="info">
							<h3><a href="#" title="meer details over Nendoroid Shimakaze">Nendoroid Shimakaze</a></h3>
							<p>From 'Kantai Collection ~KanColle~' comes a Nendoroid of Shimakaze, one of the cutest and fastest battleships you'll ever see! Shimakaze includes various optional parts including three of the cute 'Rensouhou-chan' naval artillery cannons! Other optional parts include an alternate body to pose her in her 'half damage' form, torpedo parts to fire on her enemy and even shooting effect parts for her cute little cannons. Recreate the world of KanColle in cute Nendoroid form!</p>
							<span class="prijs">&euro;49.77</span>
							<form class="bestelform" action="#" method="post">
								<p><label>Aantal:
								<input type="text" value="1" title="voer het aantal in dat u wil bestellen" /></label>
								<input type="submit" value="Voeg toe aan mandje" title="voeg dit product toe aan uw mandje" /></p>
							</form>
						</div> <!-- END .info -->
					</div> <!-- END .product_rij -->
					<div class="product_rij clearfix">
						<div class="foto">
							<img src="${pageContext.servletContext.contextPath}/images/producten/3.jpg" alt="Nendoroid Misaka Mikoto"/>
						</div> <!-- END .foto -->
						<div class="info">
							<h3><a href="#" title="meer details over Nendoroid Misaka Mikoto">Nendoroid Misaka Mikoto</a></h3>
							<p>From the popular anime series 'Toaru Kagaku no Railgun S' comes a Nendoroid of Tokiwadai Middle School's 'Railgun', also known as Mikoto Misaka! She comes complete with a number of cute optional parts which feature her beloved 'Gekota' character - including her schoolbag with a Gekota strap as well as her Gekota themed cellphone! She also comes with electric effect parts for both her head and the front of her arm, allowing you to display her in her element. The coin used for her trademark 'Railgun' attack is also included! All these optional parts can be used to show off the 'Electric Princess' in a range of poses from epic combat scenes to cute embarrassed scenes, allowing fans to decide which they prefer! </p>
							<span class="prijs">&euro;39.81</span>
							<form class="bestelform" action="#" method="post">
								<p><label>Aantal:
								<input type="text" value="1" title="voer het aantal in dat u wil bestellen" /></label>
								<input type="submit" value="Voeg toe aan mandje" title="voeg dit product toe aan uw mandje" /></p>
							</form>
						</div> <!-- END .foto -->
					</div> <!-- END .product_rij -->
				</div> <!-- END #laatste_producten -->
			</div> <!-- END .content_wrap -->
		</div> <!-- END #main_content -->
		<div class="push"></div>
	</div> <!-- END #wrapper -->
	<jsp:include page="footer.jsp" />
</body>
</html>