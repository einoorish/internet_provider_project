<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
	
	<title><fmt:message key="action.edit"/></title>
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
            
			<a class="btn btn-dark text-muted mr-sm-2" href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message key="nav.sign_out"/></a>
			<a class="btn btn-dark mr-sm-2" href="${pageContext.request.contextPath}/controller?command=profile"><fmt:message key="nav.profile"/></a>

  		</form>
	</nav>
</header>


<div class="container">
        <div class="col-lg-12 text-lg-center">
        	<c:choose>
		        <c:when test="${requestScope.tariff != null}">
           			<h4 class="m-4 p-2"><fmt:message key="action.edit"/></h4>
		        </c:when>
		        <c:otherwise>
           			<h4 class="m-4 p-2"><fmt:message key="action.add"/></h4>
		        </c:otherwise>
        	</c:choose>
        </div> 
          
        <form enctype='multipart/form-data' method="post" action="${pageContext.request.contextPath}/save?">
		                
            <c:if test="${requestScope.tariff != null}">
            	<input readonly type="hidden" name="id" value="${requestScope.tariff.id}" /> 
            </c:if>
		                
            <div class="form-group row">
	            <label class="col-lg-3 col-form-label form-control-label"><fmt:message key="tariff.title"/></label>                
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
	            <label class="col-lg-3 col-form-label form-control-label"><fmt:message key="tariff.title_uk"/></label>                
	      		<div class=" col-lg-9">
		  		   	<c:choose>
				        <c:when test="${requestScope.tariff != null}">
			                   <input class="form-control" name="title_uk" type="text" value="${requestScope.tariff.title_uk}" />
						</c:when>
						<c:otherwise>
			                    <input required class="form-control" name="title_uk" type="text"/>
			            </c:otherwise>
					</c:choose>   
				</div>
			</div>            

            <div class="form-group row">
                <label class="col-lg-3 col-form-label form-control-label"><fmt:message key="tariff.type"/></label>
                <div class="col-lg-9">
    				<c:set var="type" value="${requestScope.tariff.type}" scope="page"/>
 	       			<c:choose>
					    <c:when test="${type eq 'PHONE'}">
						  	<input type="radio" name="type" value="PHONE" checked> <fmt:message key="menu.type.phone"/>
						 	    <input class="ml-4" type="radio" name="type" value="INTERNET"> <fmt:message key="menu.type.internet"/>
					 	    <input class="ml-4" type="radio" name="type" value="CABLE"> <fmt:message key="menu.type.cable"/>
					 	    <input class="ml-4" type="radio" name="type" value="IP_TV"> IP-TV
					    </c:when>
					    <c:when test="${type eq 'INTERNET'}">
						  	<input type="radio" name="type" value="PHONE"> <fmt:message key="menu.type.phone"/>
						 	    <input class="ml-4" type="radio" name="type" value="INTERNET" checked> <fmt:message key="menu.type.internet"/>
					 	    <input class="ml-4" type="radio" name="type" value="CABLE"> <fmt:message key="menu.type.cable"/>
					 	    <input class="ml-4" type="radio" name="type" value="IP_TV"> IP-TV
					    </c:when>
					    <c:when test="${type eq 'CABLE'}">
						  	<input type="radio" name="type" value="PHONE"> <fmt:message key="menu.type.phone"/>
						 	    <input class="ml-4" type="radio" name="type" value="INTERNET"> <fmt:message key="menu.type.internet"/>
					 	    <input class="ml-4" type="radio" name="type" value="CABLE" checked> <fmt:message key="menu.type.cable"/>
					 	    <input class="ml-4" type="radio" name="type" value="IP_TV"> IP-TV
					    </c:when>
					    <c:otherwise>
						  	<input type="radio" name="type" value="PHONE"> <fmt:message key="menu.type.phone"/>
						 	    <input class="ml-4" type="radio" name="type" value="INTERNET"> <fmt:message key="menu.type.internet"/>
					 	    <input class="ml-4" type="radio" name="type" value="CABLE"> <fmt:message key="menu.type.cable"/>
					 	    <input class="ml-4" type="radio" name="type" value="IP_TV" checked> IP-TV
					    </c:otherwise>
					</c:choose>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-lg-3 col-form-label form-control-label"><fmt:message key="tariff.description"/></label>
       			<div class="col-lg-9">
		      	<c:choose>
		        	<c:when test="${requestScope.tariff != null}">  
	                     <input class="form-control"  name="description" type="text" maxlength="1024" value="${requestScope.tariff.description}" />
	                </c:when>
	    		    <c:otherwise>
	                     <input required class="form-control"  maxlength="1024" name="description" type="text"/>
		        	</c:otherwise>
		        </c:choose>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-lg-3 col-form-label form-control-label"><fmt:message key="tariff.description_uk"/></label>
       			<div class="col-lg-9">
		      	<c:choose>
		        	<c:when test="${requestScope.tariff != null}">  
	                     <input class="form-control"  name="description_uk" type="text" maxlength="1024" value="${requestScope.tariff.description_uk}" />
	                </c:when>
	    		    <c:otherwise>
	                     <input required class="form-control"  maxlength="1024" name="description_uk" type="text"/>
		        	</c:otherwise>
		        </c:choose>
                </div>
            </div>
           	<div class="form-group row">
                <label class="col-lg-3 col-form-label form-control-label"><fmt:message key="tariff.price"/></label>
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
                	<c:if test="${requestScope.tariff != null}">
	                 	<c:url value="/save" var="url">									
							<c:param name="remove" value="${requestScope.tariff.id}" />	
						</c:url>
	                    <a href="${url}" class="btn btn-danger">Delete</a>
                	</c:if>
                    <input type="reset" class="btn btn-secondary" value="Reset"/>
                    <input type="submit" class="btn btn-primary" value="Save"/>
                </div>
            </div>
        </form>
</div>

</body>
</html>

