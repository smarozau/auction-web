<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" tagdir="/WEB-INF/tags/form" %>

<jsp:include page="includes/header.jsp"/>
<div class="panel panel-primary">
	<div class="panel-heading">${title}</div>
	<p/>
	<div class="row">
		<div class="col-md-8">
			<b>${message}</b>
		</div>
	</div>
</div>
<jsp:include page="includes/footer.jsp"/>