
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="teachers">
    <h2>Teachers</h2>

   <table id="teachersTable" class="table table-striped">
        <thead>
        <tr>
            <th>First name</th>
            <th>Last name</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${teachers.teachersList}" var="teacher">
             <tr>
                <td>
                    <spring:url value="/teachers/{teacherId}" var="teacherUrl">
                    <spring:param name="teacherId" value="${teacher.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(teacherUrl)}"><c:out value="${teacher.firstName}"/></a>
                </td>
                <td>
                    <c:out value=" ${teacher.lastName}"/>
                </td>
               
            </tr> 
        </c:forEach>
        </tbody>
    </table>
        
	
    <spring:url value="/teachersWithScore" var="addUrl">
    </spring:url>
    <sec:authorize access="hasAuthority('admin')">
    <a href="${fn:escapeXml(addUrl)}" class="btn btn-default">Teachers With Score</a>
    </sec:authorize>
    
    <br>
    <br>
    
    <table id="teachersTable" class="table table-striped">
        <thead>
        <tr>
            <th>Departments</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${departments}" var="department">
             <tr>
                <td>
                    <spring:url value="/teachers/teachersByDepartment/{departmentName}" var="teacherUrl">
                    <spring:param name="departmentName" value="${department.name}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(teacherUrl)}"><c:out value="${department.name}"/></a>
                </td>
            </tr> 
        </c:forEach>
        </tbody>
    </table>
    
</petclinic:layout>

