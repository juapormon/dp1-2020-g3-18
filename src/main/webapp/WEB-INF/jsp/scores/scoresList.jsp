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
            <th>Point</th>
            <th>Comment</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${scores.scoreList}" var="score">
            <tr>
                <td>
                    <c:out value="${score.valu}"/>
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
            <!-- <th>Actions</th> -->
        </tr>
        </thead>
        <tbody>
      		<c:forEach items="${scores.scoreList}" var="scor">
				<tr>
					<td><b><c:out value="${scor.comment}"/></b></td>
					<td><b><c:out value="${scor.student.firstName} ${scor.student.lastName}"/></b></td>
				</tr>
				<%-- <td>
                	<spring:url value="scores/delete/{scoreId}" var="scoreUrl">
                		<spring:param name="scoreId" value ="${score.id}"/>
                	</spring:url>
                    <a href="${fn:escapeXml(scoreUrl)}">Delete</a>
                </td> --%>
			</c:forEach>
        </tbody>
    </table>
    
    <h2>Total amount of scores:</h2>
    <c:out value="${scores.totalScores}"> </c:out> 

     <!--
    <table class="table table-striped">
      	 <tr>
   	 	<th><b>Ejemplo de comentario:</b></th>
		<td><b><c:out value="${teacher.scores[0].comment}"/></b></td>
	</tr>

    </table>
    <br/>
     
    <spring:url value="comments" var="showCommentsUrl">
        <spring:param name="teacherId" value="${teacher.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(showCommentsUrl)}" class="btn btn-default">Show Comments</a>
     -->
</petclinic:layout>
