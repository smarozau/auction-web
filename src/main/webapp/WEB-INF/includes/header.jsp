<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="b" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${title}</title>
	<style type="text/css">
/* 		td { background-color:#ffff00; } */
/*   		tr:hover { background-color:#000000; color: #ffffff; } */
/*   		td.a:hover { background-color:#000000; color: #ffffff; } */
	</style>	
	
	<link rel="stylesheet" href="<c:url value='/resources/css/main.css'/>">
	
	
<!-- 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"> -->
	
	<!-- BootstrapCDN  https://www.bootstrapcdn.com/bootswatch/ -->
<!-- 	<link href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.7/cyborg/bootstrap.min.css" rel="stylesheet" integrity="sha384-D9XILkoivXN+bcvB2kSOowkIvIcBbNdoDQvfBNsxYAIieZbx8/SI4NeUvrRGCpDi" crossorigin="anonymous"> -->
<!-- 	<link href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.7/cosmo/bootstrap.min.css" rel="stylesheet" integrity="sha384-h21C2fcDk/eFsW9sC9h0dhokq5pDinLNklTKoxIZRUn3+hvmgQSffLLQ4G4l2eEr" crossorigin="anonymous"> -->
	<link href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.7/paper/bootstrap.min.css" rel="stylesheet" integrity="sha384-awusxf8AUojygHf2+joICySzB780jVvQaVCAt1clU3QsyAitLGul28Qxb2r1e5g+" crossorigin="anonymous">
<!-- 	<link href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.7/lumen/bootstrap.min.css" rel="stylesheet" integrity="sha384-gv0oNvwnqzF6ULI9TVsSmnULNb3zasNysvWwfT/s4l8k5I+g6oFz9dye0wg3rQ2Q" crossorigin="anonymous"> -->
	
	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
	
	<!-- Latest compiled and minified JavaScript -->
	<script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

</head>
<body>
	<div class="row">
		<div class="col-md-4">
			<div class="page-header">
				<h3>${title}</h3>
			</div>
		</div>
		<div class="col-md-8" style="text-align: right;">
			<div class="page-header">
				<form>
					<h3>
					<a class="btn btn-primary" href="<c:url value="/products" />">
						<s:message code="label.products"/>
					</a>				
					<button class="btn btn-primary" type="submit" formaction="<c:url value='/shopping-cart'/>" formmethod="get">
						<s:message code="label.shoppingCart"/> 
						<span class="badge">${shoppingCart.itemsCount}</span>
					</button>
					<a class="btn btn-default" href="<b:url paramName='locale' paramValue='en' />">
						<s:message code="label.locale.en"/>
					</a>
					<a class="btn btn-default" href="<b:url paramName='locale' paramValue='ru' />">
						<s:message code="label.locale.ru"/>
					</a>
					</h3>
				</form>
			</div>
		</div>
	</div>
	
	<!-- for example 
	<c:forEach items="${paramValues}" var="p">
		Key:<c:out value="${p.key}"/> = 
		<c:forEach items="${p.value}" var="v">
			<c:out value="${v}"/>
		</c:forEach>
		<br/>
	</c:forEach>
	 -->