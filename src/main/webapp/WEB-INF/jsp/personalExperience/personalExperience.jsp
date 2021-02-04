<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>


<petclinic:layout pageName="personalExperience">

    <h2>Personal Experience</h2>

            <br/>
		    <br/>
		    <h2>Research Experience</h2>
		<c:if test="${personalExperience.researchExperience  == null}">
   		<spring:url value="/personalExperience/{personalExperienceId}/newResearchExperience" var="personalExperienceUrl">
                <spring:param name="personalExperienceId" value ="${personalExperience.id}"/>
        </spring:url>
        <a href="${fn:escapeXml(personalExperienceUrl)}">Add Research Experience</a>
		</c:if>
		
		<c:if test="${personalExperience.researchExperience  == null}">
   			<c:out value="${personalExperience.researchExperience.research}"/>
		</c:if>

            
            <br/>
		    <br/>
		    <h2>Teaching Experience</h2>
		<c:if test="${personalExperience.teachingExperience  == null}">
   		<spring:url value="/personalExperience/{personalExperienceId}/newTeachingExperience" var="personalExperienceUrl">
                <spring:param name="personalExperienceId" value ="${personalExperience.id}"/>
        </spring:url>
        <a href="${fn:escapeXml(personalExperienceUrl)}">Add Teaching Experience</a>
		</c:if>
		
		<c:if test="${personalExperience.researchExperience  == null}">
   			<c:out value="${personalExperience.teachingExperience.titulation}"/>
		</c:if>
		
			<br/>
		    <br/>
		    <h2>Professional Experience</h2>
		<c:if test="${personalExperience.professionalExperience  == null}">
   		<spring:url value="/personalExperience/{personalExperienceId}/newTeachingExperience" var="personalExperienceUrl">
                <spring:param name="personalExperienceId" value ="${personalExperience.id}"/>
        </spring:url>
        <a href="${fn:escapeXml(personalExperienceUrl)}">Add Professional Experience</a>
		</c:if>
		
		<c:if test="${personalExperience.professionalExperience  == null}">
   			<c:out value="${personalExperience.professionalExperience.university}"/>
		</c:if>
		
			<br/>
		    <br/>
		    <h2>External Evaluation</h2>
		<c:if test="${personalExperience.externalEvaluation  == null}">
   		<spring:url value="/personalExperience/{personalExperienceId}/newExternalEvaluation" var="personalExperienceUrl">
                <spring:param name="personalExperienceId" value ="${personalExperience.id}"/>
        </spring:url>
        <a href="${fn:escapeXml(personalExperienceUrl)}">Add External Experience</a>
		</c:if>
		
		<c:if test="${personalExperience.professionalExperience  == null}">
   			<c:out value="${personalExperience.externalEvaluation.evaluationReport}"/>
		</c:if>

</petclinic:layout>