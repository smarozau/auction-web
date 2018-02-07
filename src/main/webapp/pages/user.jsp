<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<jsp:include page="/pages/includes/header.jsp"/>

<div class="panel panel-primary">
	<div class="panel-heading"><s:message code="title.userDetails"/></div>
	<p/>
	<div class="row">
		<div class="col-md-8">
	<form action="${user.userId}/edit" class="form-horizontal">
		<div class="form-group">
   			<label class="control-label col-sm-2" for="firstName">
   				<s:message code="label.firstName"/>
   			</label>
    		<div class="col-sm-4">
      			<p class="form-control-static">${user.firstName}</p>
    		</div>
  		</div>
  		<div class="form-group">
   			<label class="control-label col-sm-2" for="lastName">
   				<s:message code="label.lastName"/>
   			</label>
    		<div class="col-sm-4">
      			<p class="form-control-static">${user.lastName}</p>
    		</div>
  		</div>
  		<div class="form-group">
   			<label class="control-label col-sm-2" for="displayName">
   				<s:message code="label.displayName"/>
   			</label>
    		<div class="col-sm-4">
      			<p class="form-control-static">${user.displayName}</p>
    		</div>
  		</div>
  		<div class="form-group">
   			<label class="control-label col-sm-2" for="email">
				<s:message code="label.email"/>
			</label>
    		<div class="col-sm-4">
      			<p class="form-control-static">${user.email}</p>
    		</div>
  		</div>
  		<div class="form-group">
   			<label class="control-label col-sm-2" for="phone">
				<s:message code="label.phone"/>
			</label>
    		<div class="col-sm-4">
      			<p class="form-control-static">${user.phone}</p>
    		</div>
  		</div>  		
  		
				<input type="submit" value="<s:message code="label.edit"/>" class="btn btn-primary"/>				
	</form>	
		</div>
	</div>
<!-- 		<div class="col-md-4"> -->
<%-- 			<a href="<c:url value='/orders'/>?userId=${user.id}">See all orders</a> --%>
<!-- 		</div> -->
	<p/>
	
</div>
<jsp:include page="/pages/includes/footer.jsp"/>