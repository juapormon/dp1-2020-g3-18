<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="teacher">
   <%--  <h2>
        <c:if test="${teacher['new']}">New </c:if> Teacher
    </h2>
    <form:form modelAttribute="teacher" class="form-horizontal" id="add-teacher-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="First Name" name="firstName"/>
            <petclinic:inputField label="Last Name" name="lastName"/>
            <petclinic:inputField label="Name" name="name"/>
            <petclinic:inputField label="Email" name="email"/>
            <petclinic:inputField label="Username" name="user.username"/>
            <petclinic:inputField label="Password" name="user.password"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${teacher['new']}">
                        <button class="btn btn-default" type="submit">Add Teacher</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Update Teacher</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form> --%>
</petclinic:layout>