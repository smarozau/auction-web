<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="/pages/includes/header.jsp" />

<c:if test="${not empty error}">
	<div class="alert alert-danger">${error}</div>
</c:if>
<div class="container">
	<div class="panel panel-default">
		<div class="panel-heading"><B>${title} ID ${lot.lotId}</B></div>
		<div class="panel-body">
			<B><s:message code="label.region" />:</B> ${lot.stead.steadRegion}
		</div>
		<div class="panel-body">
			<B><s:message code="label.city" />:</B> ${lot.stead.steadCity}
		</div>
		<div class="panel-body">
			<B><s:message code="label.address" />:</B> ${lot.stead.steadAddress}
		</div>
		<div class="panel-body">
			<B><s:message code="label.coordinates" />:</B> ${lot.stead.coordinates}
		</div>
		<div class="panel-body">
			<B><s:message code="label.size" />:</B> ${lot.stead.size}
		</div>
		<div class="panel-body">
			<B><s:message code="label.description" />:</B> ${lot.stead.description}
		</div>
		<div class="panel-body">
			<B><s:message code="label.reservePrice" />:</B> ${lot.stead.reservePrice}
		</div>
		<div class="panel-body">
			<B><s:message code="label.owner" />:</B> ${lot.stead.owner.displayName}
		</div>
	
	
		<c:if
			test="${(lot.auction.statusCode.statusCode != 3) and (lot.stead.owner.userId != user.userId)}">

			<form action="${lot.lotId}" class="form-horizontal" method="POST">
				<input type="submit" value="<s:message code="label.takePart"/>"
					class="btn btn-primary" /> <input type="hidden" name="userId"
					value="${user.userId}" />
			</form>

		</c:if>
		</div>
	</div>
<p/>

<jsp:include page="/pages/includes/footer.jsp" />