<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" tagdir="/WEB-INF/tags/form" %>
<%@ taglib prefix="b" tagdir="/WEB-INF/tags"%>

<jsp:include page="includes/header.jsp" />

<div class="panel panel-primary">
	<div class="panel-heading">${title}</div>
	<p/>
	<div class="row">
		<div class="col-md-8">
	<form action="createAuction" method="POST" class="form-horizontal">
		
		<s:bind path="auction.startTime">
		<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">		
			<label class="col-sm-2 control-label" for="startTime"><s:message code="label.startTime"/></label>
			<div class="col-sm-3">
				<input id="startTime" type="datetime-local" name="startTime" value="${auction.startTime}" class="form-control"/>
				<c:if test="${status.error}">
					<span class="glyphicon glyphicon-remove form-control-feedback"></span>
					<span class="help-block">
						<sf:errors path="auction.startTime"/>
					</span>
				</c:if>
			 </div>	
		</div>
		</s:bind>
		
		<s:bind path="auction.endTime">
		<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">		
			<label class="col-sm-2 control-label" for="endTime"><s:message code="label.finishTime"/></label>
			<div class="col-sm-3">
				<input id="finishTime" type="datetime-local" name="endTime" value="${auction.endTime}" class="form-control"/>
				<c:if test="${status.error}">
					<span class="glyphicon glyphicon-remove form-control-feedback"></span>
					<span class="help-block">
						<sf:errors path="auction.endTime"/>
					</span>
				</c:if>
			 </div>	
		</div>
		</s:bind>
		
		<div class="btn-group">
			<input type="submit" value="<s:message code="label.save"/>" class="btn btn-primary"/>
		</div>
		<p/>
	</form>
		</div>
	</div>
</div>
<jsp:include page="includes/footer.jsp"/>