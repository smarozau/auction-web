<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<jsp:include page="includes/header.jsp" />

<!--  <div class="row"> -->
<!-- 	<div class="col-md-6"> -->
<!-- 	<div class="panel-group" id="usersGroup" role="tablist" aria-multiselectable="true"> -->
<!-- 		<div class="panel panel-default"> -->
<!-- 			<div class="panel-heading"> -->
<!-- 			<h4 class="panel-title"> -->
<!-- 				<a role="button" data-toggle="collapse" data-parent="#usersGroup" href="#searchUsers" aria-expanded="false" aria-controls="searchUsers"> -->
<!--           			Search user -->
<!--         		</a> -->
<!--         	</h4> -->
<!-- 			</div> -->
<!-- 			<div id="searchUsers" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne"> -->
<!-- 			<div class="panel-body"> -->
<!-- 				<form action="users" method="GET" class="form-horizontal"> -->
<!-- 					<div class="form-group"> -->
<!-- 						<label class="col-sm-2 control-label" for="lastName"> -->
<%-- 							<s:message code="label.lastName" /> --%>
<!-- 						</label> -->
<!-- 						<div class="col-sm-4"> -->
<!-- 							<input type="text" name="lastName" class="form-control" -->
<%-- 								value="${usersPage.filter.lastName}"> --%>
<!-- 						</div> -->
<!-- 						<label class="col-sm-2 control-label" for="email"> -->
<%-- 							<s:message code="label.email" /> --%>
<!-- 						</label> -->
<!-- 						<div class="col-sm-4"> -->
<!-- 							<input type="text" name="email" class="form-control" -->
<%-- 								value="${usersPage.filter.email}"> --%>
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="btn-group"> -->
<%-- 						<input type="submit" value="<s:message code='label.search'/>" --%>
<!-- 							class="btn btn-primary" /> -->
<%-- 						<input type="reset" value="<s:message code='label.reset'/>" --%>
<!-- 							class="btn btn-secondary" /> -->
<!-- 					</div> -->
<!-- 					<p /> -->
<!-- 				</form> -->
<!-- 			</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- 	</div> -->
<!-- </div>  -->

<div class="panel panel-primary">
	<div class="panel-heading">${title}</div>
	<div class="panel-body">
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>#</th>
					<th><s:message code="label.firstName" /></th>
					<th><s:message code="label.lastName" /></th>
					<th><s:message code="label.email" /></th>
					<th><s:message code="label.phone" /></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="u" items="${users}">
					<tr>
						<td><a href="user/${u.userId}">${u.userId}</a></td>
						<td>${u.firstName}</td>
						<td>${u.lastName}</td>
						<td>${u.email}</td>
						<td>${u.phone}</td>
						<td>
							<form action="user/${u.userId}/edit">
								<input type="submit" value="<s:message code="label.edit"/>" />
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<!--<c:set var="page" value="${usersPage}"/>
		
	<nav aria-label="Page navigation">
  <ul class="pagination">
    <li class="${page.filter.start == 0 ? 'disabled' : ''}">
      <a 
      	onclick="return ${page.filter.start != 0};" 
      	href="users?start=${page.filter.start - page.filter.count}&count=${page.filter.count}${page.filter.params}" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <c:forEach begin="1" end="${page.totalPagesCount}" varStatus="loop">
    	<li class="${page.filter.count * (loop.index-1) == page.filter.start ? 'active' : ''}">
    		<a href="users?start=${page.filter.count * (loop.index-1)}&count=${page.filter.count}${page.filter.params}">
    			${loop.index}
    		</a>
    	</li>
    </c:forEach>
    <li class="${page.filter.start + page.filter.count >= page.totalItemsCount ? 'disabled' : ''}">
      <a onclick="javascript: return ${page.filter.start + page.filter.count < page.totalItemsCount};" 
      	href="users?start=${page.filter.start + page.filter.count}&count=${page.filter.count}${page.filter.params}" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>	-->
	</div>
</div>
<jsp:include page="/pages/includes/footer.jsp" />