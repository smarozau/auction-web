<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="paramName" required="true" type="java.lang.String" %>
<%@ attribute name="paramValue" required="true" type="java.lang.String" %>

<c:url value="">

    <%-- 
      replaces or adds a param to a URL
      if $name in query then replace its value with $value. 
      copies existing 
    --%>

    <c:forEach items="${paramValues}" var="p">
        <c:choose>
            <c:when test="${p.key == paramName}">
                <c:param name="${paramName}" value="${paramValue}"/>
            </c:when>
            <c:otherwise>
                <c:forEach items="${p.value}" var="val">
                    <c:param name="${p.key}" value="${val}"/>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <%-- if $name not in query, then add --%>
    <c:if test="${empty param[paramName] }">
        <c:param name="${paramName}" value="${paramValue}"/>
    </c:if>

</c:url>
