<!DOCTYPE html>
<html lang="en" xmlns:c="http://java.sun.com/jsp/jstl/core">
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
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>

<header>
	<nav class="navbar navbar-light bg-dark">
		<a href="${pageContext.request.contextPath}/controller?command=home" class="navbar-brand text-light">Internet Provider</a>
		<form class="form-inline">
			<a class="mr-sm-2">EN</a>
            <a class="mr-sm-2">RU</a>
            <a class="mr-sm-2">UA</a>
            
			<!-- SignIn/SignUp -->            
	        <c:set var="username" value="${sessionScope.user.login}" scope="page"/>
	
	        <c:choose>
	            <c:when test="${empty username}">
					<a class="btn btn-dark mr-sm-2" data-toggle="modal" data-target="#modalRegister" href="#">Sign Up</a>
					<a class="btn btn-success mr-sm-2"  data-toggle="modal" data-target="#modalLogin">Sign In</a>
	            </c:when>
	            <c:otherwise>
					<a class="btn btn-dark text-muted mr-sm-2" href="${pageContext.request.contextPath}/controller?command=logout">Log out</a>
					<a class="btn btn-dark mr-sm-2" href="${pageContext.request.contextPath}/controller?command=profile">My profile</a>
	                <!-- Add log out -->
	            </c:otherwise>
	        </c:choose>
            
  		</form>
	</nav>
</header>



<div class="container-fluid">
    
    <div class="row">
        <div class="col-2  m-0 p-0 bg-secondary position-fixed">
          <a class="btn btn-dark btn-block m-0" href="${pageContext.request.contextPath}/controller?menutype=PHONE">Phone</a>  
		  <a class="btn btn-dark btn-block m-0" href="${pageContext.request.contextPath}/controller?menutype=INTERNET">Internet</a>
		  <a class="btn btn-dark btn-block m-0" href="${pageContext.request.contextPath}/controller?menutype=CABLE">CableTV</a>
		  <a class="btn btn-dark btn-block m-0" href="${pageContext.request.contextPath}/controller?menutype=IP_TV">IP-TV</a>
    </div>
    
   <div class="col-10 min-vh-100 offset-2 bg-light"  id="main">
   		<div class="content">
    		<div class="container">
        		<div class="row py-4">
			        <c:forEach items="${requestScope.data}" var="tariff">
			        <div class="col-md-3">
		                <div class="card py-2 px-2">
		                    <div class="card-block">
		                        <h3 class="card-title align-items-center d-flex justify-content-center mb-1">${tariff.title}</h3>
		                        <small class="card-subtitle text-muted align-items-top d-flex justify-content-center">${tariff.price} uah</small> 
                    			<div class="card-block">
		                        <div class="card-text my-2">${tariff.description}</div>
		            
		               	    	<a href="#" class="col-12 card-link btn btn-outline-primary align-items-center justify-content-center">Subscribe</a> 
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


<!-- Modals -->
<div class="modal fade login" id="modalLogin" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Sign In</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
			<form class="form-horizontal" action="${pageContext.request.contextPath}/controller?command=login" method="post">
				<div class="form-group">
					<i class="fa fa-user"></i>
					<input name="login" type="text" class="form-control" placeholder="Username" required="required">
				</div>
				<div class="form-group">
					<i class="fa fa-lock"></i>
					<input name="password" type="password" class="form-control" placeholder="Password" required="required">					
				</div>
	            <button class="btn btn-success">Continue</button>
			</form>								
		</div>
    </div>
  </div>
</div>

<div class="modal fade" id="modalRegister" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Sign Up</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
			<form class="form-horizontal" action="${pageContext.request.contextPath}/controller?command=registration" method="post">
				<div class="form-group">
					<i class="fa fa-user"></i>
					<input name="login" type="text" class="form-control" placeholder="Username" required="required">
				</div>
				<div class="form-group">
					<i class="fa fa-lock"></i>
					<input name="password" type="password" class="form-control" placeholder="Password" required="required">					
				</div>
	            <button class="btn btn-success">Continue</button>
			</form>								
	  </div>
    </div>
  </div>
</div>

</body>
</html>

