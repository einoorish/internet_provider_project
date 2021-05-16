<!DOCTYPE html>
<html lang="en" xmlns:c="http://java.sun.com/jsp/jstl/core">
<head>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ page contentType="text/html;charset=UTF-8" %>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Edit</title>
	<link href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round" rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
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
            
			<a class="btn btn-dark text-muted mr-sm-2" href="${pageContext.request.contextPath}/controller?command=logout">Log out</a>
			<a class="btn btn-dark mr-sm-2" href="${pageContext.request.contextPath}/controller?command=profile">My profile</a>

  		</form>
	</nav>
</header>


<div class="container">
        <!-- edit form column -->
        <div class="col-lg-12 text-lg-center">
        	<c:choose>
		        <c:when test="${requestScope.tariff != null}">
           			<h4 class="m-4 p-2">Edit</h4>
		        </c:when>
		        <c:otherwise>
           			<h4 class="m-4 p-2">Add</h4>
		        </c:otherwise>
        	</c:choose>
        </div> 
          
             <form enctype='multipart/form-data' method="post" action="${pageContext.request.contextPath}/save?">
		                
                <c:if test="${requestScope.tariff != null}">
                	<input readonly type="hidden" name="id" value="${requestScope.tariff.id}" /> 
                </c:if>
		                
                <div class="form-group row">
                <label class="col-lg-3 col-form-label form-control-label">Title</label>                
          		<div class=" col-lg-9">
		       	<c:choose>
			        <c:when test="${requestScope.tariff != null}">  
		                    <input class="form-control" name="title" type="text" value="${requestScope.tariff.title}" />
					</c:when>
					<c:otherwise>
		                    <input required class="form-control" name="title" type="text"/>
		            </c:otherwise>
				</c:choose>   
				</div>
				</div>             

                <div class="form-group row">
                    <label class="col-lg-3 col-form-label form-control-label">Type</label>
                    <div class="col-lg-9">
     	       			  <c:set var="type" value="${requestScope.tariff.type}" scope="page"/>
     	       			  <c:choose>
						    <c:when test="${type eq 'PHONE'}">
							  	<input type="radio" name="type" value="PHONE" checked> Phone
	 					 	    <input class="ml-4" type="radio" name="type" value="INTERNET"> Internet
						 	    <input class="ml-4" type="radio" name="type" value="CABLE"> Cable
						 	    <input class="ml-4" type="radio" name="type" value="IP_TV"> IP-TV
						    </c:when>
						    <c:when test="${type eq 'INTERNET'}">
							  	<input type="radio" name="type" value="PHONE"> Phone
	 					 	    <input class="ml-4" type="radio" name="type" value="INTERNET" checked> Internet
						 	    <input class="ml-4" type="radio" name="type" value="CABLE"> Cable
						 	    <input class="ml-4" type="radio" name="type" value="IP_TV"> IP-TV
						    </c:when>
						    <c:when test="${type eq 'CABLE'}">
							  	<input type="radio" name="type" value="PHONE"> Phone
	 					 	    <input class="ml-4" type="radio" name="type" value="INTERNET"> Internet
						 	    <input class="ml-4" type="radio" name="type" value="CABLE" checked> Cable
						 	    <input class="ml-4" type="radio" name="type" value="IP_TV"> IP-TV
						    </c:when>
						    <c:otherwise>
							  	<input type="radio" name="type" value="PHONE"> Phone
	 					 	    <input class="ml-4" type="radio" name="type" value="INTERNET"> Internet
						 	    <input class="ml-4" type="radio" name="type" value="CABLE"> Cable
						 	    <input class="ml-4" type="radio" name="type" value="IP_TV" checked> IP-TV
						    </c:otherwise>
						</c:choose>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-lg-3 col-form-label form-control-label">Description</label>
			        <div class="col-lg-9">
			       	<c:choose>
				        <c:when test="${requestScope.tariff != null}">  
	                        <input class="form-control"  name="description" type="text" value="${requestScope.tariff.description}" />
	                    </c:when>
				        <c:otherwise>
	                        <input required class="form-control"  name="description" type="text"/>
				        </c:otherwise>
			        </c:choose>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-lg-3 col-form-label form-control-label">Price</label>
                    <div class="col-lg-9">
                        <c:choose>
				        <c:when test="${requestScope.tariff != null}">  
	                        <input class="form-control"  name="price" type="number" value="${requestScope.tariff.price}" min=0 step="0.01"/>
	                    </c:when>
				        <c:otherwise>
	                        <input required class="form-control"  name="price" type="number" min=0 step="0.01"/>
				        </c:otherwise>
			        </c:choose>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-lg-3 col-form-label form-control-label"></label>
                    <div class="col-lg-9">
                        <input type="button" class="btn btn-danger" value="Delete"/>
                        <input type="reset" class="btn btn-secondary" value="Reset"/>
                        <input type="submit" class="btn btn-primary" value="Save"/>
                    </div>
                </div>
            </form>
</div>

</body>
</html>

