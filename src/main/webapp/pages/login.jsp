<%@page import="java.util.Enumeration"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="b" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	<p align="right">
		<a class="btn btn-default"
			href="<b:url paramName='locale' paramValue='en' />"> <s:message
				code="label.locale.en" />
		</a> <a class="btn btn-default"
			href="<b:url paramName='locale' paramValue='ru' />"> <s:message
				code="label.locale.ru" />
		</a>
	</p>

	<div class="panel panel-primary" align="center">
		<div class="panel-heading">
			<s:message code="label.login" />
		</div>
		<p />

		<c:if test="${not empty error}">
			<div class="alert alert-danger">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="alert alert-info">${msg}</div>
		</c:if>

		<form name='loginForm' action="<c:url value='login' />" method='POST'>

			<table>
				<tr>
					<td><s:message code="label.email" /></td>
					<td><input type='text' name='email' value=''
						autocomplete="off" /> <!-- pattern="[^@]+@[^@]+\.[a-zA-Z]{2,4}"--></td>
				</tr>
				<tr>
					<td><s:message code="label.password" /></td>
					<td><input type='password' name='password' autocomplete="off"
						title=<s:message code="title.password"/> /> <!-- pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{7,}" --></td>
				</tr>
				<tr>
					<td></td>
					<td><input name="submit" type="submit"
						value="<s:message code="label.submit"/>" /></td>
				</tr>
			</table>
		</form>
	</div>
	<p align="center">
		<a href="<c:url value='registration' />"><s:message
				code="label.register" /></a>
	</p>

	<jsp:include page="includes/footer.jsp" />