<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="text"/>

<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round" rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

	<title><fmt:message key="nav.profile"/></title>
</head>
<body>

<header>
	<nav class="navbar navbar-light bg-dark">
		<a href="${pageContext.request.contextPath}/controller?command=home" class="navbar-brand text-light"><fmt:message key="nav.project_name"/></a>
		<form class="form-inline">
			<c:choose>
	            <c:when test="${not fn:contains(pageContext.request.queryString, 'lang')}">
	                <a class="mr-sm-2" href="?${pageContext.request.queryString}&lang=uk">Укр</a>
	                <a class="mr-sm-2" href="?${pageContext.request.queryString}&lang=en">Eng</a>
	            </c:when>
	            <c:otherwise>
	                <a class="mr-sm-2" href="?${fn:replace(pageContext.request.queryString,'lang=en', 'lang=uk')}">Укр</a>
	                <a class="mr-sm-2" href="?${fn:replace(pageContext.request.queryString, 'lang=uk', 'lang=en')}">Eng</a>
	            </c:otherwise>
	         </c:choose>
            
			<!-- SignIn/SignUp -->            
	        <c:set var="username" value="${sessionScope.user.login}" scope="page"/>
	
			<a class="btn btn-dark text-muted mr-sm-2" href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message key="nav.sign_out"/></a>
			<a class="btn btn-dark mr-sm-2" href="${pageContext.request.contextPath}/controller?command=profile"><fmt:message key="nav.profile"/></a>
            <!-- Add log out -->
           
  		</form>
	</nav>
</header>



<div class="container-fluid vh-100 bg-light pt-2">
	<div class="card">
	
	  <div class="card-body">
	    <h5 class="card-title">${sessionScope.user.login}</h5>
	    <p class="card-text"><fmt:message key="profile.funds"/>: ${sessionScope.user.funds} uah</p>
	  </div>
	  <hr>
	  <div class="card-body">
	    <h5 class="card-title"><fmt:message key="profile.subscriptions"/></h5>
    		<div class="row">
		        <c:forEach items="${requestScope.subscriptions}" var="tariff">
	    		<div class="col-md-3">
		    		<c:choose>
					    <c:when test="${tariff.type eq 'PHONE'}">
							<p class="card-text"><fmt:message key="menu.type.phone"/></p>
					    </c:when>
					    <c:when test="${tariff.type eq 'INTERNET'}">
							<p class="card-text"><fmt:message key="menu.type.internet"/></p>
					    </c:when>
					    <c:when test="${tariff.type eq 'CABLE'}">
							<p class="card-text"><fmt:message key="menu.type.cable"/></p>
					    </c:when>
					    <c:otherwise>
							<p class="card-text"><fmt:message key="menu.type.ip_tv"/></p>
					    </c:otherwise>
				    </c:choose>
				 	<div class="card py-2 px-2">
				    	<div class="card-block">
				        	<h3 class="card-title align-items-center d-flex justify-content-center mb-1">${tariff.title}</h3>
				            <small class="card-subtitle text-muted align-items-top d-flex justify-content-center">${tariff.price} uah</small> 
				           	<div class="card-block">
				            	<div class="card-text my-2">${tariff.description}</div>
			            			<c:url value="/controller?command=unsubscribe" var="url">
									<c:param name="tariffId" value="${tariff.id}" />
									</c:url>
					            
									<a href="${url}" class="col-12 card-link btn btn-outline-danger align-items-center justify-content-center"><fmt:message key="action.unsubscribe"/></a> 
				         		</div>
				   		</div>
        			</div>
	  			</div>
	  			</c:forEach>
			</div>
		</div>
	</div>
</div>
</body>
</html>

