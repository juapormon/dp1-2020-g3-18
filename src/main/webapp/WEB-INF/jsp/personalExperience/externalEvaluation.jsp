<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


 <petclinic:layout pageName="externalEvaluation">
    <h2><c:if test="${externalEvaluation['new']}">New </c:if> External Evaluation	</h2>
    <form:form modelAttribute="externalEvaluation" class="form-horizontal" id="add-externalEvaluation-form" >
        <div class="form-group has-feedback">
            <petclinic:inputField label="Note" name="note"/>
            <petclinic:inputField label="Evaluation Report" name="evaluationReport"/>
            <petclinic:inputField label="Comment" name="comment"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
    		<button class="btn btn-default" type="submit">Save External Evaluation</button>
    				
            </div>
        </div>
    </form:form>
</petclinic:layout> 