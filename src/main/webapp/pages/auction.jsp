<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="includes/header.jsp" />

<div class="container">
	<div class="panel panel-default">
		<div class="panel-heading">
			<B>${title} ID ${auction.id}</B>
		</div>
		<div class="panel-body">
			<B><s:message code="label.startTime" />:</B> <fmt:formatDate value="${auction.startTime}" type="both" timeStyle="medium" />
		</div>
		<div class="panel-body">
			<B><s:message code="label.finishTime" />:</B> <fmt:formatDate value="${auction.endTime}" type="both" timeStyle="medium" />
		</div>
		<div class="panel-body">
			<c:choose>
				<c:when test="${status=='FINISHED'}">
					<B><s:message code="label.status" />:</B>
					<s:message code="label.finished" />
				</c:when>
				<c:when test="${status=='NEW'}">
					<B><s:message code="label.status" />:</B>
					<s:message code="label.new" />
				</c:when>
				<c:otherwise>
					<B><s:message code="label.status" />:</B>
					<s:message code="label.active" />
				</c:otherwise>
			</c:choose>
		</div>
		<div class="panel-body">
			<B><s:message code="label.countLots" />:</B> ${size}
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
							<th><s:message code="label.size" /></th>
							<th><s:message code="label.reservePrice" /></th>
							<th><s:message code="label.countMembers" /></th>
							<c:choose>
								<c:when test="${status=='FINISHED'}">
									<th><s:message code="label.finishPrice" /></th>
									<th><s:message code="label.winner" /></th>
								</c:when>
								<c:when test="${status=='ACTIVE'}">
									<th><s:message code="label.actualPrice" /></th>
									<th></th>
								</c:when>
							</c:choose>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="lot" items="${lots}">
							<tr>
								<td><a href="<c:url value="/lot/${lot.lotId}"/>">${lot.lotId}</a></td>
								<td>${lot.stead.steadRegion}</td>
								<td>${lot.stead.steadCity}</td>
								<td>${lot.stead.size}</td>
								<td>${lot.stead.reservePrice}</td>
								<td>${fn:length(members[lot])}</td>
								<c:choose>
									<c:when test="${status=='FINISHED'}">
										<td>${results[lot.lotId]}</td>
										<td>${winners[lot.lotId]}</td>
									</c:when>
									<c:when test="${status=='ACTIVE'}">
										<th>${results[size-1]}</th>
										<th><a href="<c:url value="/lot/${lot.lotId}/makeBid"/>"
				type="button" class="btn btn-primary"><s:message code="label.makeBid" /></a></th>
									</c:when>
								</c:choose>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<c:if test="${auction.statusCode.statusCode != 3}">
			<a href="<c:url value="/auction/${auction.id}/selectLots"/>"
				type="button" class="btn btn-primary"><s:message code="label.addLots" /></a>
		</c:if>
	</div>
</div>

<jsp:include page="/pages/includes/footer.jsp" />