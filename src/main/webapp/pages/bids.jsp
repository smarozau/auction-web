<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<jsp:include page="includes/header.jsp" />

<div class="panel panel-primary">
	<div class="panel-heading"><s:message code="label.myBids" /></div>
	<div class="panel-body">
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th><s:message code="label.lot" /></th>
					<th><s:message code="label.reservePrice" /></th>
					<th><s:message code="label.bid" /></th>
					<th><s:message code="label.date" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bid" items="${bids}">
					<tr>
						<td><a href="<c:url value="/lot/${bid.lotMember.lot.lotId}"/>">${bid.lotMember.lot.lotId}</a></td>
						<td>${bid.lotMember.lot.stead.reservePrice}</td>
						<td>${bid.bid}</td>
						<td>${bid.crtdTms}</td>						
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<p/>
<jsp:include page="/pages/includes/footer.jsp" />