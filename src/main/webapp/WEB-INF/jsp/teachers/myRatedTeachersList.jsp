<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="teachers">
    <h2>My rated teachers</h2>
 
   <table id="teachersTable" class="table table-striped">
        <thead>
        <tr>
            <th>Full name</th>
            <td><b><c:out value="${teacher.firstName} ${teacher.lastName} "></c:out></b></td>
            <th>Subjects</th>
			<td><b>
                <c:forEach items="${teacher.subjects}" var="subject">
                
	                <c:out value="${subject.name} "/>
	                   
	                    
	                    
	            </c:forEach>
			</b></td>
        </tr>
        </thead>
    </table>
</petclinic:layout>

