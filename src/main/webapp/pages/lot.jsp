<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="/pages/includes/header.jsp" />

<div class="panel panel-primary">
	<div class="panel-heading">
		<s:message code="title.lot" />
		ID: ${lot.lotId}
	</div>
	<p />
	<div class="row">
		<div class="col-md-8">
			<form action="lot/${lot.lotId}" class="form-horizontal">
				<s:bind path="lotMember">
					<div class="form-group">
						<label class="control-label col-sm-2" for="auction.id"> <s:message
								code="label.auction" />
						</label>
						<div class="col-sm-4">
							<p class="form-control-static">
								<a href="<c:url value="/auction/${lot.auction.id}"/>">${lot.auction.id}</a>
							</p>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-2" for="stead.steadCountry">
							<s:message code="label.country" />
						</label>
						<div class="col-sm-4">
							<p class="form-control-static">${lot.stead.steadCountry}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="stead.steadRegion">
							<s:message code="label.region" />
						</label>
						<div class="col-sm-4">
							<p class="form-control-static">${lot.stead.steadRegion}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="stead.steadCity">
							<s:message code="label.city" />
						</label>
						<div class="col-sm-4">
							<p class="form-control-static">${lot.stead.steadCity}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="stead.steadAddress">
							<s:message code="label.address" />
						</label>
						<div class="col-sm-4">
							<p class="form-control-static">${lot.stead.steadAddress}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="stead.coordinates">
							<s:message code="label.coordinates" />
						</label>
						<div class="col-sm-4">
							<p class="form-control-static">${lot.stead.coordinates}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="stead.description">
							<s:message code="label.description" />
						</label>
						<div class="col-sm-4">
							<p class="form-control-static">${lot.stead.description}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="stead.reservePrice">
							<s:message code="label.reservePrice" />
						</label>
						<div class="col-sm-4">
							<p class="form-control-static">${lot.stead.reservePrice}</p>
						</div>
					</div>
					<c:if test="${lot.auction.statusCode.statusCode =='1'}">
						<input type="submit" value="<s:message code="label.takePart"/>"
							class="btn btn-primary" />
					</c:if>
					<c:if test="${status.error}">
					<span class="help-block"> <sf:errors path="${lotMember}" /> </span>
				</c:if>
					</s:bind>
			</form>
		</div>
	</div>

</div>
<jsp:include page="/pages/includes/footer.jsp" />