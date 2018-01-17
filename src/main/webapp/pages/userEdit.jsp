<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="/pages/includes/header.jsp"/>
<div class="panel panel-primary">
	<div class="panel-heading">Edit user details</div>
	<p/>
	<div class="row">
		<div class="col-md-8">
	<form action="save" method="POST" class="form-horizontal">
		
		<s:bind path="user.firstName">
		<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">		
			<label class="col-sm-2 control-label" for="firstName"><s:message code="label.firstName"/></label>
			<div class="col-sm-4">
				<input id="firstName" type="text" name="firstName" value="${user.firstName}" class="form-control"/>
				<c:if test="${status.error}">
					<span class="glyphicon glyphicon-remove form-control-feedback"></span>
					<span class="help-block">
						<sf:errors path="user.firstName"/>
					</span>
				</c:if>
			 </div>	
		</div>
		</s:bind>
		
		<s:bind path="user.lastName">
		<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">
			<label class="col-sm-2 control-label" for="lastName"><s:message code="label.lastName"/></label>
			<div class="col-sm-4">
				<input type="text" name="lastName" value="${user.lastName}" class="form-control">
				<c:if test="${status.error}">
					<span class="glyphicon glyphicon-remove form-control-feedback"></span>
					<span class="help-block">
						<sf:errors path="user.lastName"/>
					</span>
				</c:if>
			</div>
		</div>
		</s:bind>
		
		<s:bind path="user.email">
		<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">
			<label class="col-sm-2 control-label" for="email"><s:message code="label.email"/></label>			
			<div class="col-sm-4">				
				<input type="text" name="email" value="${user.email}" class="form-control">
				<c:if test="${status.error}">
					<span class="glyphicon glyphicon-remove form-control-feedback"></span>					
				</c:if>
			</div>
		</div>
		</s:bind>
		
		<s:bind path="user.phone">
		<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">
			<label class="col-sm-2 control-label" for="phone"><s:message code="label.phone"/></label>
			<div class="col-sm-4">
				<input type="text" name="phone" value="${user.phone}" class="form-control">
				<c:if test="${status.error}">
					<span class="glyphicon glyphicon-remove form-control-feedback"></span>
					<span class="help-block">
						<sf:errors path="user.phone"/>
					</span>
				</c:if>
			</div>
		</div>
		</s:bind>
		
		<input type="hidden" name="id" value="${user.id}"/>
		
		<div class="btn-group">
<!-- 			<div class="col-sm-4"> -->
				<input type="submit" value="<s:message code='label.save'/>" class="btn btn-primary"/>
<!-- 			</div> -->
		</div>
		<p/>
	</form>
		</div>
	</div>
</div>
<jsp:include page="/pages/includes/footer.jsp"/>