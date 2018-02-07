<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="includes/header.jsp" />

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
							<c:choose>
								<c:when test="${status=='FINISHED'}">
									<th><s:message code="label.finishPrice" /></th>
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
								<c:choose>
									<c:when test="${status=='FINISHED'}">
										<td>${results[lot]}</td>
									</c:when>
									<c:when test="${status=='ACTIVE'}">
										<th>${results[size-1]}</th>
										<th><s:bind path="bid.bid">
												<div>
													<form action="/${auction.id}/makeBid" method="POST">
														<div class="input-group">
														<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">
															<input type="number" name="bid" min="0" step="10" value="${bid.bid}"
																class="form-control" placeholder="${results[size-1]}">
																<input type="hidden" name="steadId" value="${lot.stead.steadId}">
															<c:if test="${status.error}">
																<span class="glyphicon glyphicon-remove form-control-feedback"></span>
																<span class="help-block"> <sf:errors path="${bid.bid}" /></span>
															</c:if>
															</div>
															<span class="input-group-btn">
																<button class="btn btn-success" type="button">
																	<s:message code="label.makeBid" />
																</button>
															</span>
														</div>
													</form>
												</div>
											</s:bind></th>
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