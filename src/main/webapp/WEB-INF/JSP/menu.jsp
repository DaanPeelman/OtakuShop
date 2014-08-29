<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<div id="menu" class="clearfix">
			<div class="content_wrap">
				<ul>
					<li><a href="<c:url value='/' />" title="terug naar start pagina">Home</a></li>
					<li><a href="<c:url value='/producten'/>" title="bekijk onze producten">Producten</a></li>
					<li><a href="<c:url value='/about' />" title="meer informatie over ons">About us</a></li>
					<security:authorize access="isAuthenticated()"><li><a href="#" title="bekijk en wijzig uw gegevens">Gegevens</a></li></security:authorize>
					<security:authorize access="hasRole('admin')"><li><a href="#" title="beheer de producten">Beheer</a></li></security:authorize>
					<security:authorize access="isAnonymous()"><li class="right"><a href="<c:url value='/login' />" title="meld u aan">Aanmelden</a></li></security:authorize>
					<security:authorize access="isAuthenticated()"><li class="right"><form method="post" action="<c:url value='/logout' />" id="logoutform"><input type="submit" value="Afmelden" id="logoutbutton" title="meld u af"/><security:csrfInput /></form></li></security:authorize>
					<li class="right"><a href="<c:url value='/mandje' />" title="bekijk uw mandje">Mandje&nbsp;<span class="small">(${aantalInMandje})</span></a></li>
				</ul>
			</div> <!-- END .content_wrap -->
		</div> <!-- END #menu -->
		