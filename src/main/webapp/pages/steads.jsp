<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<jsp:include page="includes/header.jsp" />

<div class="panel panel-primary">
	<div class="panel-heading"></div>
	<div class="panel-body">
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>#</th>
					<th><s:message code="label.region" /></th>
					<th><s:message code="label.city" /></th>
					<th><s:message code="label.size" /></th>
					<th><s:message code="label.reservePrice" /></th>
					<th><s:message code="label.owner" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="s" items="${steads}">
					<tr>
						<td><a href="<c:url value="/stead/${s.steadId}"/>">${s.steadId}</a></td>
						<td>${s.steadRegion}</td>
						<td>${s.steadCity}</td>
						<td>${s.size}</td>
						<td>${s.reservePrice}</td>
						<td>${s.owner.displayName}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<sec:authorize access="isAuthenticated()">
<p/>
<div>
<a href="<c:url value="/steadAddition"/>"
	class="btn btn-primary" role="button"><s:message code="label.addStead" /></a>
</div>
</sec:authorize>
<p/>
<jsp:include page="/pages/includes/footer.jsp" />