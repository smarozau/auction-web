<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<jsp:include page="includes/header.jsp" />

<div class="container">
	<div class="panel panel-default">
		<div class="panel-heading"><B>${title} ID ${stead.steadId}</B></div>
		<div class="panel-body">
			<B><s:message code="label.region" />:</B> ${stead.steadRegion}
		</div>
		<div class="panel-body">
			<B><s:message code="label.city" />:</B> ${stead.steadCity}
		</div>
		<div class="panel-body">
			<B><s:message code="label.address" />:</B> ${stead.steadAddress}
		</div>
		<div class="panel-body">
			<B><s:message code="label.coordinates" />:</B> ${stead.coordinates}
		</div>
		<div class="panel-body">
			<B><s:message code="label.size" />:</B> ${stead.size}
		</div>
		<div class="panel-body">
			<B><s:message code="label.description" />:</B> ${stead.description}
		</div>
		<div class="panel-body">
			<B><s:message code="label.reservePrice" />:</B> ${stead.reservePrice}
		</div>
	</div>
	<a href="<c:url value="/stead/${stead.steadId}/edit"/>" class="btn btn-primary" role="button"><s:message code="label.edit"/></a>
</div>
<p/>

<jsp:include page="/pages/includes/footer.jsp" />