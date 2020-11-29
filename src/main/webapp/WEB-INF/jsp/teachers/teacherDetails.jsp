<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="teachers">

    <h2>Teacher Details</h2>

    <table class="table table-striped">
        <tr>
            <th>Name</th>
            <td><b><c:out value="${teacher.firstName} ${teacher.lastName}"/></b></td>
        </tr>
        <tr>
            <th>Username</th>
            <td><b><c:out value="${teacher.user.username} "/></b></td>
        </tr>

    </table>

    <spring:url value="{teacherId}/scores/new" var="addUrl">
        <spring:param name="teacherId" value="${teacher.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(addUrl)}" class="btn btn-default">Add New Score</a>

	<br/>
    <br/>
    <h2>Scores</h2>

 <!-- 	
    <table class="table table-striped">
	 	 <tr>
		<c:forEach items="${scores}" var="scor">
		<th>Point</th>
		<th><b><c:out value="${scor.point}"/></b></th>
		<td>Comment</td>
		<td><b><c:out value="${scor.comment}"/></b></td>
		</c:forEach>
	</tr>
    </table>
         -->
   
    <spring:url value="{teacherId}/scores" var="showScoresUrl">
        <spring:param name="teacherId" value="${teacher.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(showScoresUrl)}" class="btn btn-default">Show Score and Comment List</a>


</petclinic:layout>