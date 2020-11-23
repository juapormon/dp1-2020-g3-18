<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="teachers">
    <jsp:body>
        <h2>
            New Score
        </h2>
         <form:form modelAttribute="score"
                   class="form-horizontal">
            <input type="hidden" name="id" value="${score.id}"/>
            <div class="form-group has-feedback">
                <div class="form-group">
                    <label class="col-sm-2 control-label">Teacher</label>
                    <div class="col-sm-10">
                        <c:out value="${score.teacher.firstName} ${score.teacher.lastName}"/>
                    </div>
                </div>
                <petclinic:inputField label="Point" name="point"/>
                <petclinic:inputField label="Comment" name="comment"/>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                	<button class="btn btn-default" type="submit">Add Pet</button>
                </div>
            </div>
        </form:form>
	</jsp:body>
</petclinic:layout>