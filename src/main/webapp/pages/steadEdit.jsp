<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="f" tagdir="/WEB-INF/tags/form"%>

<jsp:include page="/pages/includes/header.jsp" />
<div class="panel panel-primary">
	<div class="panel-heading">
		<s:message code="label.editStead" />
	</div>
	<p />
	<div class="row">
		<div class="col-md-8">
			<form action="save" method="POST" class="form-horizontal">

				<f:input name="steadCountry" value="${stead.steadCountry}"
					path="stead.steadCountry" type="text" id="steadCountry"
					labelCode="label.country" />
				<f:input name="steadRegion" value="${stead.steadRegion}"
					path="stead.steadRegion" type="text" id="steadRegion"
					labelCode="label.region" />
				<f:input name="steadCity" value="${stead.steadCity}"
					path="stead.steadCity" type="text" id="steadCity"
					labelCode="label.city" />
				<f:input name="steadAddress" value="${stead.steadAddress}"
					path="stead.steadAddress" type="text" id="steadAddress"
					labelCode="label.address" />
				<f:input name="coordinates" value="${stead.coordinates}"
					path="stead.coordinates" type="text" id="coordinates"
					labelCode="label.coordinates" />

				<s:bind path="stead.size">
					<div
						class="form-group ${status.error ? 'has-error has-feedback' : ''}">
						<label class="col-sm-2 control-label" for="size"> <s:message
								code="label.size" />
						</label>
						<div class="col-sm-4">
							<input id="size" type="number" min="0" step="0.1" name="size"
								value="${stead.size}" class="form-control" />
							<c:if test="${status.error}">
								<span class="glyphicon glyphicon-remove form-control-feedback"></span>
								<span class="help-block"> <sf:errors path="${stead.size}" />
								</span>
							</c:if>
						</div>
					</div>
				</s:bind>

				<s:bind path="stead.description">
					<div
						class="form-group ${status.error ? 'has-error has-feedback' : ''}">
						<label class="col-sm-2 control-label" for="description"><s:message
								code="label.description" /></label>
						<div class="col-sm-6">
							<textarea name="description" rows="5" id="description"
								class="form-control">${stead.description}</textarea>
							<c:if test="${status.error}">
								<span class="glyphicon glyphicon-remove form-control-feedback"></span>
								<span class="help-block"> <sf:errors
										path="${stead.description}" />
								</span>
							</c:if>
						</div>
					</div>
				</s:bind>

				<s:bind path="stead.reservePrice">
					<div
						class="form-group ${status.error ? 'has-error has-feedback' : ''}">
						<label class="col-sm-2 control-label" for="$reservePrice">
							<s:message code="label.reservePrice" />
						</label>
						<div class="col-sm-4">
							<input id="reservePrice" type="number" min="0" max="1000000000" step="0.01"
								name="reservePrice" value="${stead.reservePrice}"
								class="form-control" />
							<c:if test="${status.error}">
								<span class="glyphicon glyphicon-remove form-control-feedback"></span>
								<span class="help-block"> <sf:errors path="${stead.reservePrice}" />
								</span>
							</c:if>
						</div>
					</div>
				</s:bind>

				<div class="btn-group">
					<input type="submit" value="<s:message code='label.save'/>"
						class="btn btn-primary" />
				</div>
				<p />
			</form>
		</div>
	</div>
</div>
<jsp:include page="/pages/includes/footer.jsp" />