<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<jsp:include page="includes/header.jsp" />

<div class="panel panel-primary">
	<div class="panel-heading">${title}</div>
	<div class="panel-body">
		<table class="table table-striped table-hover">
			<thead>
				<tr>
				    <th>#</th>
					<th><s:message code="label.startTime"/></th>
					<th><s:message code="label.finishTime"/></th>
					<th><s:message code="label.status"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="a" items="${auctions}">
					<tr>
						<td><a href="<c:url value="/auction/${a.id}"/>">${a.id}</a></td>
						<td>${a.startTime}</td>
						<td>${a.endTime}</td>
						<td>${a.statusCode.name}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<jsp:include page="/pages/includes/footer.jsp" />