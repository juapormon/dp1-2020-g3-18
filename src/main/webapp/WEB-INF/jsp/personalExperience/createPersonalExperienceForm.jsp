<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="personalExperiences">
    <jsp:body>
        <h2>
          <c:choose><c:when test="${personalExperience['new']}">New </c:when><c:otherwise>Update </c:otherwise></c:choose>Score
        </h2>
         <form:form modelAttribute="personalExperience"
                   class="form-horizontal">
            <input type="hidden" name="id" value="${personalExperience.id}"/>
            <div class="form-group has-feedback">
                <div class="form-group">
                    <label class="col-sm-2 control-label">Teacher</label>
                    <div class="col-sm-10">
                        <c:out value="${teacher.firstName} ${teacher.lastName}"/>
                    </div>
                </div>
                <h3>Continua</h3>
               <%--  <petclinic:inputField label="Value" name="valu"/>
                <petclinic:inputField label="Comment" name="comment"/> --%>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <c:choose>
                        <c:when test="${personalExperience['new']}">
                            <button class="btn btn-default" type="submit">Add PersonalExperience</button>
                            <h2>
                            *Personal Experience already created, you can't create a new one.
                            </h2>
                        </c:when>
                        <c:otherwise>
                            <button class="btn btn-default" type="submit">Update Personal Experience</button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </form:form>
	</jsp:body>
</petclinic:layout>