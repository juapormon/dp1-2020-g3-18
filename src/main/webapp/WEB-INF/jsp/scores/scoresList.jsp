<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="scores">
    <h2>Scores</h2>

    <table id="scoresTable" class="table table-striped">
        <thead>
        <tr>
            <th>Value</th>
            <th>Comment</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${scores}" var="score"> 
            <tr>
                <td>
                    <spring:url value="/teachers/{teacherId}/scores/{scoreId}/edit" var="editScoreUrl">
                    <spring:param name="teacherId" value="${score.teacher.id}"/>
                    <spring:param name="scoreId" value="${score.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(editScoreUrl)}"><c:out value="${score.valu}"/></a>
                </td>
                
                <td>
                    <c:out value=" ${score.comment}"/>
                </td>
                <td>
                	<spring:url value="/teachers/{teacherId}/scores/delete/{scoreId}" var="scoreUrl">
                		<spring:param name="teacherId" value ="${score.teacher.id}"/>
                		<spring:param name="scoreId" value ="${score.id}"/>
                	</spring:url>
                    <a href="${fn:escapeXml(scoreUrl)}">Delete</a>
                </td>                
            </tr>
        </c:forEach> 
        </tbody>
    </table>
        <h2>Comments</h2> 

	    <table class="table table-striped">
	
		<thead>
        <tr>
            <th>Comment</th>
            <th>Student who made it</th>
            <c:if test="${teacherAuth}">
            <th>Report comment</th>
            </c:if>
        </tr>
        </thead>
        <tbody>
      		<c:forEach items="${scores}" var="scor"> <!-- -->
				<tr>
					<td><b><c:out value="${scor.comment}"/></b></td>
					<td><b><c:out value="${scor.student.firstName} ${scor.student.lastName}"/></b></td>
					<td>
                    <spring:url value="/reports/new/{scoreId}" var="reportUrl">
                    <spring:param name="scoreId" value="${scor.id}"/>
                    </spring:url>
                    <c:if test="${teacherAuth}">
                    <a href="${fn:escapeXml(reportUrl)}"><c:out value="Report"/></a>
                	</c:if>
                	</td>
				</tr>
			</c:forEach>
        </tbody>
    </table>

</petclinic:layout>
