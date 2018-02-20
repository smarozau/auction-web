<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="includes/header.jsp" />

<fmt:formatNumber var="i" value="${previousBid.bid}"  minFractionDigits="0"/>
	<c:if test="${makeBid == 0}">
			<div class="alert alert-danger">${msg}</div>
		</c:if>
		<c:if test="${makeBid < 0}">
			<div class="alert alert-danger">${msg}</div>
		</c:if>
		<c:if test="${makeBid < i}">
			<div class="alert alert-danger">${msg}</div>
		</c:if>
	<div class="container">
	<div class="panel panel-default">
		<div class="panel-heading">
			<B>${title} ID ${auction.id}</B>
		</div>
	<div class="panel-body">
			<B><s:message code="label.startTime" />:</B> ${auction.startTime}
		</div>
	<div class="panel-body">
			<B><s:message code="label.finishTime" />:</B> ${auction.endTime}
		</div>
	<div class="panel-body">
			<B><s:message code="label.status" />:</B>
			<c:choose>
				<c:when test="${auction.statusCode.name=='FINISHED'}">
					<s:message code="label.finished" />
				</c:when>
				<c:when test="${auction.statusCode.name=='NEW'}">
					<s:message code="label.new" />
				</c:when>
				<c:otherwise>
					<s:message code="label.active" />
				</c:otherwise>
			</c:choose>
		</div>
	<div class="panel panel-primary">
			<div class="panel-heading">
				<s:message code="label.lot" />
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
							<c:choose>
								<c:when test="${auction.statusCode.name =='FINISHED'}">
									<th><s:message code="label.finishPrice" /></th>
								</c:when>
								<c:when test="${auction.statusCode.name =='ACTIVE'}">
									<th><s:message code="label.actualPrice" /></th>
									<th></th>
								</c:when>
							</c:choose>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><a href="<c:url value="/lot/${lot.lotId}"/>">${lot.lotId}</a></td>
							<td>${lot.stead.steadRegion}</td>
							<td>${lot.stead.steadCity}</td>
							<td>${lot.stead.size}</td>
							<td>${lot.stead.reservePrice}</td>
							<td>${previousBid.bid}</td>
							<c:choose>
								<c:when test="${auction.statusCode.name =='ACTIVE'}">
									<th><form action="save" method="POST">
											
												<div class="form-group">
													<div class="col-sm-6">
														<input id="bid" type="number" min="0" step="10" name="makeBid"
															value="${makeBid}" placeholder="${previousBid.bid}" class="form-control" />
														<input type="hidden" name="makeBid">
													</div>
	</div>

	<div class="btn-group">
		<input type="submit" value="<s:message code='label.makeBid'/>"
			class="btn btn-success" />
	</div>
	<p />
	</form>
	</th>
	</c:when>
	</c:choose>
	</tbody>
	</table>
	</div>
	</div>
	</div>
	</div>

	<jsp:include page="/pages/includes/footer.jsp" />