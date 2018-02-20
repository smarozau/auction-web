<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="includes/header.jsp" />

<div class="panel panel-primary">
	<div class="panel-heading"></div>
	<div class="panel-body">
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>#</th>
					<th><s:message code="label.startTime" /></th>
					<th><s:message code="label.finishTime" /></th>
					<th><s:message code="label.status" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="a" items="${auctions}">
					<tr>
						<td><a href="<c:url value="/auction/${a.id}"/>">${a.id}</a></td>
						<td> <fmt:formatDate value="${a.startTime}" type="both" timeStyle="medium" /></td>
						<td><fmt:formatDate value="${a.endTime}" type="both" timeStyle="medium" /></td>
						<td>${a.statusCode.name}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<a href="<c:url value="/createAuction"/>" type="button"
				class="btn btn-primary"><s:message code="label.createAuction" /></a>
		</sec:authorize>
	</div>
</div>
<jsp:include page="/pages/includes/footer.jsp" />