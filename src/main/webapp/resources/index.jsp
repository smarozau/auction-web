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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Добро пожаловать на Auction Web!</title>
<!-- Bootstrap core CSS -->
    <link href="<c:url value="/pages/css/bootstrap.css" />" rel="stylesheet">
 
    <!-- Custom styles for this template -->
    <link href="<c:url value="/pages/css/jumbotron-narrow.css" />" rel="stylesheet">
 
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
</head>

<body>
	<div class="container">
		<div class="jumbotron text-center" style="margin-top: 20px;">
			<h1>Auction Web</h1>
			<p class="lead">Лучший аукцион земельных участков!</p>

			<sec:authorize access="!isAuthenticated()">
				<p>
					<a class="btn btn-lg btn-success" href="<c:url value="/login" />"
						role="button">Войти</a>
				</p>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				<p>
					Ваш логин:
					<sec:authentication property="principal.username" />
				</p>
				<p>
					<a class="btn btn-lg btn-danger" href="<c:url value="/logout" />"
						role="button">Выйти</a>
				</p>
			</sec:authorize>
		</div>
		<div class="footer">
			<p>© Auction Web 2017</p>
		</div>

	</div>