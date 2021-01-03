<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="reports">
    <jsp:body>
         <form:form modelAttribute="report"
                   class="form-horizontal">
            <input type="hidden" name="id" value="${report.id}"/>
            <div class="form-group has-feedback">
                <div class="form-group">
                    <label class="col-sm-2 control-label">Reported comment:</label>
                    <div class="col-sm-10">
                        <c:out value="${report.score.comment}"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                </div>
            </div>
            <div>
            <petclinic:inputField label="Reason:" name="reason"/>
            </div>
            <button class="btn btn-default" type="submit">Create report</button>
        </form:form>
	</jsp:body>
</petclinic:layout>