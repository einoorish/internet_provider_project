<!DOCTYPE html>
<html lang="en">
<head>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ page contentType="text/html;charset=UTF-8" %>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Home</title>
	<link href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round" rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<style>
			<%@include file="/WEB-INF/styles/home.css"%>
	</style>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script>
		$(function(){
		    $('.list-group li').click(function(e) {
		        e.preventDefault()
		        
		        $that = $(this);
		        
		        $(".list-group").find('li').removeClass('list-group-item-success');
		        $that.addClass('list-group-item-success');
		    });
		})
	</script>
</head>
<body>

<header>
	<nav class="navbar navbar-light bg-dark">
		<a href="${pageContext.request.contextPath}/home" class="navbar-brand text-light">Internet Provider</a>
		<form class="form-inline">
			<a class="mr-sm-2">EN</a>
            <a class="mr-sm-2">RU</a>
            <a class="mr-sm-2">UA</a>
            
			<a class="btn btn-dark mr-sm-2" href="${pageContext.request.contextPath}/register">Sign Up</a>
			<a class="btn btn-success mr-sm-2" href="${pageContext.request.contextPath}/register">Sign In</a>
  		</form>
	</nav>
</header>
<div class="container-fluid">
    
    
    <div class="row">
        <div class="col-2 px-1 bg-secondary position-fixed" id="sticky-sidebar">
            <ul class="nav flex-column flex-nowrap py-4 vh-100 overflow-auto text-white p-2 list-group list-group-flush">
			  <li class="list-group-item list-group-item-action ">Phone</li>
			  <li class="list-group-item list-group-item-action ">Internet</li>
			  <li class="list-group-item list-group-item-action ">Cable TV</li>
			  <li class="list-group-item list-group-item-action ">IP-TV</li>
			</ul>
        </div>
        
        <div class="col-10 offset-2 bg-light"  id="main">
            <div class="content">
		        <div class="container">
		            <div class="row py-4">
		            <c:forEach items="${requestScope.data}" var="tariff">
			           <div class="col-md-3">
			                   <div class="card py-2 px-2">
			                       <div class="card-block">
			                           <h3 class="card-title align-items-center d-flex justify-content-center">${tariff.title}</h3>
			                           <small class="card-subtitle text-muted align-items-top d-flex justify-content-center">${tariff.price} uah</small> 
			                       		<div class="card-block">
				                           <div class="card-text my-2">${tariff.description}</div>
				                           <a href="#" class="card-link productItem btn btn-primary align-items-center d-flex justify-content-center">Subscribe</a> 
				                  		</div>
		                  			</div>
			               		</div>
			               		
		               </div>
			        </c:forEach>
		    	</div>
	        </div>
	    </div>

	     </div>
    </div>
</div>



    </body>
</html>

