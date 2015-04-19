<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${flashMessage != null}">
    <div class="message">${flashMessage}</div>
</c:if>

<div class="row" style="padding-top: 8px">
    <div class="col-md-6" style="font-size: 16px; font-weight: bold">
        Find, edit, and create your episodes
    </div>
    <div class="col-md-6">
        <a href="<c:url value="/episode/create" />" class="pull-right btn btn-default" style="padding: 0px 10px">
            Create New Episode &raquo
        </a>
    </div>
</div>
<div id="episodeResults">
    <div class="list">
        <table class="table">
            <thead>
            <tr>
                <th>Game</th>
                <th>Title</th>
                <th>Seq #</th>
                <th>MinScorePoints</th>
                <th>Date Created</th>
                <th>Last Updated</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="episode" items="${episodeList}" varStatus="rowCounter">
                <tr class="${rowCounter.count % 2 == 0 ? 'even' : 'odd'}">
                    <td>${episode.game.name}</td>
                    <td>
                        <a href="<c:url value="/episode/show/${episode.id}" />">${episode.title}</a>
                    </td>
                    <td>${episode.seqNum}</td>
                    <td>${episode.minScorePoints}</td>
                    <td>${episode.dateCreated}</td>
                    <td>${episode.lastUpdated}</td>
                    <td>
                        <a class="btn btn-default" style="padding: 0px 10px"
                           href="<c:url value="/episode/edit/${episode.id}" />">Edit</a>
                        <a class="btn btn-default" style="padding: 0px 10px"
                           href="<c:url value="/episode/delete/${episode.id}" />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty episodeList}">
                <tr>
                    <td colspan="999">No episodes found</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</div>
