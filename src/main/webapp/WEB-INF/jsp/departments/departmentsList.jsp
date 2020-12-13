
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="departments">
    <h2>Departments</h2>

    <table id="departmentsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Related Subjects</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${departments}" var="department">
             <tr>
                <td>
                    <spring:url value="/departments/{departmentId}" var="studentUrl">
                    <spring:param name="departmentId" value="${department.id}"/>
                    </spring:url>
                    <c:out value="${department.name}"/>
                </td>
                <td>
                     <spring:url value="/departments/{departmentId}/relatedSubjects" var="departmentUrl">
                     <spring:param name="departmentId" value="${department.id}"/>
                     </spring:url>
                    <a href="${fn:escapeXml(departmentUrl)}"><c:out value="Related Subjects"/></a>
                </td>

               
            </tr> 
        </c:forEach>
        </tbody>
    </table> 
    
</petclinic:layout>

