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
                    <c:out value="${teacher.firstName}"/>
                </td>
                <td>
                    <c:out value=" ${teacher.lastName}"/>
                </td>
               
            </tr> 
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
