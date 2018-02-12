<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="includes/header.jsp" />

<div class="panel panel-primary">
	<div class="panel-heading"><s:message code="label.auction"/> ID${auctionId}</div>
	<div class="panel-body">
		<div class="row">
			<div class="col-md-8">
			<c:if test="${empty steads}">
			<div class="alert alert-danger">${error}</div>
		</c:if>
				<form action="selectLots" method="POST" class="form-horizontal">
<%-- 				<s:bind path="checkbox.steads"> --%>
					<c:forEach items="${steads}" var="lot">
					<div class="checkbox">
						<label><input type="checkbox" name="steads" value="${lot.steadId}"><a href=<c:url value="/stead/${lot.steadId}"/>>${lot.steadId}</a>, ${lot.steadCountry},
						 ${lot.steadRegion}, ${lot.steadCity}, ${lot.steadAddress}, ${lot.size}, ${lot.reservePrice}</label>
						 <c:if test="${status.error}">
				<span class="glyphicon glyphicon-remove form-control-feedback"></span>
				<span class="help-block"> <sf:errors path="${checkbox.steads}" />
				</span>
			</c:if>
					</div>
					</c:forEach>
<%-- 					</s:bind> --%>
					<p/>
					<div class="btn-group">
						<input type="submit" value="<s:message code="label.save"/>"
							class="btn btn-primary" />
					</div>
					<p/>
				</form>
			</div>
		</div>
	</div>
	</div>