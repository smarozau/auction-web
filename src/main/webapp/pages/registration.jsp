<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" tagdir="/WEB-INF/tags/form" %>
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
<p align="right">
		<a class="btn btn-default"
			href="<b:url paramName='locale' paramValue='en' />"> <s:message
				code="label.locale.en" />
		</a> <a class="btn btn-default"
			href="<b:url paramName='locale' paramValue='ru' />"> <s:message
				code="label.locale.ru" />
		</a>
	</p>
<div class="panel panel-primary">
	<div class="panel-heading">Registration</div>
	<p/>
	<div class="row">
		<div class="col-md-8">
	<form action="registration" method="POST" class="form-horizontal">
		
		<f:input name="user.firstName" value="${registration.user.firstName}" path="registration.user.firstName" type="text" id="firstName" labelCode="label.firstName"/>
		<f:input name="user.lastName" value="${registration.user.lastName}" path="registration.user.lastName" type="text" id="lastName" labelCode="label.lastName"/>
		<f:input name="user.displayName" value="${registration.user.displayName}" path="registration.user.displayName" type="text" id="displayName" labelCode="label.displayName"/>
		<f:input name="user.country" value="${registration.user.country}" path="registration.user.country" type="text" id="country" labelCode="label.country"/>
		<f:input name="user.city" value="${registration.user.city}" path="registration.user.city" type="text" id="city" labelCode="label.city"/>
		<f:input name="user.address" value="${registration.user.address}" path="registration.user.address" type="text" id="address" labelCode="label.address"/>
		<f:input name="user.email" value="${registration.user.email}" path="registration.user.email" id="email" type="text"  labelCode="label.email"/>
		<f:input name="user.phone" value="${registration.user.phone}" path="registration.user.phone" id="phone" type="text" labelCode="label.phone"/>
		<f:input name="password" value="${registration.password}" path="registration.password" id="password" type="password" labelCode="label.password"/>
		<f:input name="confirmPassword" value="${registration.confirmPassword}" path="registration.confirmPassword" id="confirmPassword" type="password" labelCode="label.confirmPassword"/>
		
		<div class="btn-group">
			<input type="submit" value="<s:message code="label.register"/>" class="btn btn-primary"/>
		</div>
		<p/>
	</form>
		</div>
	</div>
</div>
<jsp:include page="includes/footer.jsp"/>