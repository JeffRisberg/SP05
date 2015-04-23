<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style>
    .error {
        color: #ff0000;
    }
</style>

<c:if test="${flashMessage != null}">
    <div class="message">${flashMessage}</div>
</c:if>

<c:url var="saveUrl" value="/game/save"/>
<form:form method="post" action="${saveUrl}">
    <form:hidden path="id"/>

    <table>
        <tr>
            <td>Name:</td>
            <td><form:input path="name" size="40"/></td>
            <td><form:errors path="name" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Active:</td>
            <td><form:checkbox path="active" size="40"/></td>
            <td><form:errors path="active" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Download Price:</td>
            <td><form:input path="downloadPrice" size="40"/></td>
            <td><form:errors path="downloadPrice" cssClass="error"/></td>
        </tr>
    </table>

    <div class="botButtons">
        <input class="save" type="submit" value="Submit"/>
    </div>

    <form:hidden path="dateCreated"/>
</form:form>
