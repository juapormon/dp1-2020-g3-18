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
 
    <table class="table table-striped">
		<c:forEach items="${teacher.scores}" var="scor">
		<tr>
		<th>Point</th>
		<th><b><c:out value="${scor.point}"/></b></th>
		<td>Comment</td>
		<td><b><c:out value="${scor.comment}"/></b></td>
		</tr>
		</c:forEach>
    </table>
    <spring:url value="{teacherId}/scores" var="showScoresUrl">
        <spring:param name="teacherId" value="${teacher.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(showScoresUrl)}" class="btn btn-default">Show Scores</a>
    
    <br/>
    <br/>
    <br/>
    <h2>Comments</h2>
    <table class="table table-striped">
	
	<c:forEach items="${teacher.scores}" var="scor">
		<tr>
		<td><b><c:out value="${scor.comment}"/></b></td>
		</tr>
		</c:forEach>
    </table>

<!--   <table class="table table-striped"> 
	 <tr>
   	 	<th><b>Ejemplo de comentario:</b></th>
		<td><b><c:out value="${teacher.scores[0].comment}"/></b></td>
	</tr>
    </table>
    <br/>
    <spring:url value="{teacherId}/comments" var="showCommentsUrl">
        <spring:param name="teacherId" value="${teacher.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(showCommentsUrl)}" class="btn btn-default">Show Comments</a>
    -->  
    <h2>Personal experience</h2>
    
    <!--  Dentro de personal experience pondremos un boton o varios, hay que decidirlo -->

</petclinic:layout>