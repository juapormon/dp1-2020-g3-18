<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="scores">
    <h2>Scores</h2>

    <table id="scoresTable" class="table table-striped">
        <thead>
        <tr>
            <th>Point</th>
            <th>Comment</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${scores.scoreList}" var="score">
            <tr>
                <td>
                    <c:out value="${score.point}"/>
                </td>
                <td>
                    <c:out value=" ${score.comment}"/>
                </td>
               <%--  <td>
                    <c:forEach var="specialty" items="${vet.specialties}">
                        <c:out value="${specialty.name} "/>
                    </c:forEach>
                    <c:if test="${vet.nrOfSpecialties == 0}">none</c:if>
                </td> --%>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <table class="table-buttons">
        <tr>
            <td>
                <a href="<spring:url value="/scores.xml" htmlEscape="true" />">View as XML</a>
            </td>            
        </tr>
    </table>
</petclinic:layout>