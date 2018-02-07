<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="b" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>${title}</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
	<sec:authentication var="user" property="principal" />
	<nav class="navbar navbar-default">
		<div class="container-fluid">

			<div class="navbar-header">
				<a class="navbar-brand"> ${title}</a>
			</div>
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="<c:url value="#" />">Welcome, <sec:authorize access="!isAuthenticated()">guest!
				</sec:authorize>
				 <sec:authorize	access="isAuthenticated()">${user.displayName}!</sec:authorize></a></li>

				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="<c:url value="/auctions" />"><s:message
							code="label.auctions" /> <span class="caret"></span> </a>
					<ul class="dropdown-menu">
						<li><a href="<c:url value="/auctions/1"/>"><s:message
									code="label.new" /></a></li>
						<li><a href="<c:url value="/auctions/2"/>"><s:message
									code="label.active" /></a></li>
						<li><a href="<c:url value="/auctions/3"/>"><s:message
									code="label.finished" /></a></li>
					</ul></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown"
					href="<c:url value="/user/${user.userId}" />"><s:message
							code="label.myAuctionWeb" /> <span class="caret"></span> </a>
					<ul class="dropdown-menu">
						<li><a href="<c:url value="/user/${user.userId}"/>"><s:message
									code="label.myProfile" /></a></li>
						<li><a href="<c:url value="/steads/${user.userId}"/>"><s:message
									code="label.myLots" /></a></li>
						<li><a href="<c:url value="/bids/${user.userId}"/>"><s:message
									code="label.myBids" /></a></li>
					</ul></li>
				<li><a class="btn btn-default"
					href="<b:url paramName='locale' paramValue='en' />"> <s:message
							code="label.locale.en" /></a></li>
				<li><a class="btn btn-default"
					href="<b:url paramName='locale' paramValue='ru' />"> <s:message
							code="label.locale.ru" />
				</a></li>
				<sec:authorize access="isAuthenticated()">
				<li><a href="<c:url value="/logout"/>"><s:message code="label.logout" />
				</a></li>
				</sec:authorize> 
				<sec:authorize access="!isAuthenticated()">
				<li><a class="btn btn-default" href="<c:url value="/login"/>"><s:message code="label.login" /> </a></li>
				</sec:authorize>
				
			</ul>
		</div>
	</nav>



	<!-- for example 
	<c:forEach items="${paramValues}" var="p">
		Key:<c:out value="${p.key}"/> = 
		<c:forEach items="${p.value}" var="v">
			<c:out value="${v}"/>
		</c:forEach>
		<br/>
	</c:forEach>
	 -->