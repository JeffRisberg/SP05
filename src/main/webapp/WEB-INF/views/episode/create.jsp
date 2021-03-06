<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url var="saveUrl" value="/episode/save"/>
<form:form method="post" action="${saveUrl}">
    <form:hidden path="id"/>
    <table>
        <tr>
            <td>Game:</td>
            <td>
                <form:select path="game">
                    <form:options items="${gameList}" itemValue="id" itemLabel="name"/>
                </form:select>
            </td>
            <td><form:errors path="title" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Title:</td>
            <td><form:input path="title" size="40"/></td>
            <td><form:errors path="title" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Seq Num:</td>
            <td><form:input path="seqNum" size="40"/></td>
            <td><form:errors path="seqNum" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Min Score Points:</td>
            <td><form:input path="minScorePoints" size="40"/></td>
            <td><form:errors path="minScorePoints" cssClass="error"/></td>
        </tr>
    </table>

    <div class="botButtons">
        <input class="save" type="submit" value="Submit"/>
    </div>
</form:form>
