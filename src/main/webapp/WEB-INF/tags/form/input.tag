<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<%@ attribute name="id" required="true" type="java.lang.String" %>
<%@ attribute name="name" required="true" type="java.lang.String" %>
<%@ attribute name="type" required="true" type="java.lang.String" %>
<%@ attribute name="value" required="true" type="java.lang.String" %>
<%@ attribute name="path" required="true" type="java.lang.String" %>
<%@ attribute name="labelCode" required="true" type="java.lang.String" %>

<s:bind path="${path}">
	<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">
		<label class="col-sm-2 control-label" for="${name}">
			<s:message code="${labelCode}" />
		</label>
		<div class="col-sm-4">
			<input id="${id}" type="${type}" name="${name}"
				value="${value}" class="form-control" />
			<c:if test="${status.error}">
				<span class="glyphicon glyphicon-remove form-control-feedback"></span>
				<span class="help-block"> <sf:errors path="${path}" />
				</span>
			</c:if>
		</div>
	</div>
</s:bind>