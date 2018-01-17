<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<jsp:include page="includes/header.jsp" />

<div class="container">
	<div class="panel panel-default">
		<div class="panel-heading">${title} ID ${auction.id}</div>
		<div class="panel-body">
			<s:message code="label.startTime" />
			: ${startTime}
		</div>
		<div class="panel-body">
			<s:message code="label.finishTime" />
			: ${endTime}
		</div>
		<div class="panel-body">
			<c:choose>
				<c:when test="${status=='FINISHED'}">
					<s:message code="label.status" />: <s:message code="label.finished" />
				</c:when>
				<c:when test="${status=='NEW'}">
					<s:message code="label.status" />: <s:message code="label.new" />
				</c:when>
				<c:otherwise>
					<s:message code="label.status" />: <s:message code="label.active" />
				</c:otherwise>
			</c:choose>
		</div>
		<div class="panel-body">
			<s:message code="label.countMembers" />
			: ${size}
		</div>
	</div>
</div>

<div class="panel panel-primary">
	<div class="panel-heading">
		<s:message code="label.lots" />
	</div>
	<div class="panel-body">
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>#</th>
					<th><s:message code="label.region" /></th>
					<th><s:message code="label.city" /></th>
					<th><s:message code="label.size" /><sup><small>2</small></sup></th>
					<th><s:message code="label.reservePrice" /></th>
					<th><s:message code="label.finishPrice" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="r" items="${results}">
					<tr>
						<td><a href="<c:url value="/lot/${r.key.lotId}"/>">${r.key.lotId}</a></td>
						<td>${r.key.stead.steadRegion}</td>
						<td>${r.key.stead.steadCity}</td>
						<td>${r.key.stead.size}</td>
						<td>${r.key.stead.reservePrice}</td>
						<td>${r.value.bid}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<jsp:include page="/pages/includes/footer.jsp" />