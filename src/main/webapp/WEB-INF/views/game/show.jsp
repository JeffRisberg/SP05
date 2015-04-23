<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${flashMessage != null}">
    <div class="message">${flashMessage}</div>
</c:if>

<table class="table">
    <tr>
        <td>Name:</td>
        <td>${game.name}</td>
    </tr>
    <tr>
        <td>Active:</td>
        <td>${game.active}</td>
    </tr>
    <tr>
        <td>Download Price:</td>
        <td>${game.downloadPrice}</td>
    </tr>
    <tr>
        <td>Date Created:</td>
        <td>${game.dateCreated}</td>
    </tr>
    <tr>
        <td>Last Updated:</td>
        <td>${game.lastUpdated}</td>
    </tr>
</table>

<c:if test="${not empty game.episodes}">
    <h4>Contains episodes:</h4>

    <div>
        <c:forEach var="episode" items="${game.episodes}" varStatus="rowCounter">
            <span>${episode.title}</span>
        </c:forEach>
    </div>
</c:if>

<div class="botButtons">
    <a href="<c:url value="/game/edit/${game.id}" />" class="btn btn-default">Edit</a>
</div>
