<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${flashMessage != null}">
    <div class="message">${flashMessage}</div>
</c:if>

<table class="table">
    <tr>
        <td>Game:</td>
        <td>${objective.episode.game.name}</td>
    </tr>
    <tr>
        <td>Episode:</td>
        <td>${objective.episode.title}</td>
    </tr>
    <tr>
        <td>Name:</td>
        <td>${objective.name}</td>
    </tr>
    <tr>
        <td>SeqNum:</td>
        <td>${objective.seqNum}</td>
    </tr>
    <tr>
        <td>MinScorePoints:</td>
        <td>${objective.minScorePoints}</td>
    </tr>
</table>

<div class="botButtons">
    <a href="<c:url value="/objective/edit/${objective.id}" />" class="btn btn-default">Edit</a>
</div>
