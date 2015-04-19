<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${flashMessage != null}">
    <div class="message">${flashMessage}</div>
</c:if>

<table class="table">
    <tr>
        <td>Site:</td>
        <td>${episode.game.name}</td>
    </tr>
    <tr>
        <td>Title:</td>
        <td>${episode.title}</td>
    </tr>
    <tr>
        <td>SeqNum:</td>
        <td>${episode.seqNum}</td>
    </tr>
    <tr>
        <td>MinScorePoints:</td>
        <td>${episode.minScorePoints}</td>
    </tr>
</table>

<div class="botButtons">
    <a href="<c:url value="/episode/edit/${episode.id}" />" class="btn btn-default">Edit</a>
</div>
