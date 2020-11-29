<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="error">

    <spring:url value="/resources/images/error.png" var="errorImage"/>
    <img src="${errorImage}"/>

    <h2>It seems you cannot do this or we are having technical problems...</h2>
    <h2>We are working hard to prevent unexpected errors    :D</h2>

    <p>${exception.message}</p>

</petclinic:layout>
