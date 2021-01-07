
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="subjects">
    <h2>Subjects</h2>

    <table id="subjectsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Curso</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${subjects.subjectList}" var="subject">
             <tr>
                <td>
                    <spring:url value="/subjects/{subjectId}" var="subjectUrl">
                    <spring:param name="subjectId" value="${subject.id}"/>
                    </spring:url>
                    <c:out value="${subject.name}"/>
                </td>
                <td>
                    <c:out value=" ${subject.curso}"/>
                </td>

               
            </tr> 
        </c:forEach>
        
             
        </tbody>
    </table> 
    <h3>
         <spring:url value="/subjects/mySubjects/{studentId}" var="mySubjectsUrl">
                     <spring:param name="studentId" value="${student.id}"/>
                     </spring:url>
                    <a href="${fn:escapeXml(mySubjectsUrl)}"><c:out value="My Subjects"/></a>
                </h3>
    
                    
    
</petclinic:layout>

