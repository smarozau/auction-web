<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
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
											<s:bind path="bid.bid">
												<div
													class="form-group ${status.error ? 'has-error has-feedback' : ''}">
													<div class="col-sm-6">
														<input id="bid" type="number" min="0" step="10" name="bid"
															value="${bid.bid}" placeholder="${previousBid.bid}" class="form-control" />
														<c:if test="${status.error}">
															<span
																class="glyphicon glyphicon-remove form-control-feedback"></span>
															<span class="help-block"> <sf:errors
																	path="${bid.bid}" />
															</span>
														</c:if>
													</div>
												</div>
											</s:bind>
											<s:bind path="bid.lotMember">
											<input type="hidden" name="lotMember" value="${bid.lotMember}"/>
														<c:if test="${status.error}">
															<span
																class="glyphicon glyphicon-remove form-control-feedback"></span>
															<span class="help-block"> <sf:errors
																	path="${bid.lotMember}" />
															</span>
														</c:if>
											</s:bind>
											<div class="btn-group">
												<input type="submit"
													value="<s:message code='label.makeBid'/>"
													class="btn btn-success" />
											</div>
											<p />
										</form></th>
								</c:when>
							</c:choose>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<jsp:include page="/pages/includes/footer.jsp" />