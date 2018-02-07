<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="includes/header.jsp" />

<div class="panel panel-primary">
	<div class="panel-heading"><s:message code="label.auction"/> ID${auctionId}</div>
	<div class="panel-body">
		<div class="row">
			<div class="col-md-8">
				<sf:form commandName="selectLots" method="POST" class="form-horizontal">

				<sf:checkboxes items="${steads}" path="checkbox"/>

					<p/>
					<div class="btn-group">
						<input type="submit" value="<s:message code="label.save"/>"
							class="btn btn-primary" />
					</div>
					<p/>
				</sf:form>
			</div>
		</div>
	</div>
	</div>