<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${flashMessage != null}">
    <div class="message">${flashMessage}</div>
</c:if>

<div class="row" style="padding-top: 8px">
    <div class="col-md-6" style="font-size: 16px; font-weight: bold">
        Find, edit, and create your objectives
    </div>
    <div class="col-md-6">
        <a href="<c:url value="/objective/create" />" class="pull-right btn btn-default" style="padding: 0px 10px">
            Create New Objective &raquo
        </a>
    </div>
</div>
<div id="objectiveResults">
    <div class="list">
        <table class="table">
            <thead>
            <tr>
                <th>Game</th>
                <th>Episode</th>
                <th>Name</th>
                <th>Seq #</th>
                <th>MinScorePoints</th>
                <th>Date Created</th>
                <th>Last Updated</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="objective" items="${objectiveList}" varStatus="rowCounter">
                <tr class="${rowCounter.count % 2 == 0 ? 'even' : 'odd'}">
                    <td>${objective.episode.game.name}</td>
                    <td>${objective.episode.title}</td>
                    <td>
                        <a href="<c:url value="/objective/show/${objective.id}" />">${objective.name}</a>
                    </td>
                    <td>${objective.seqNum}</td>
                    <td>${objective.minScorePoints}</td>
                    <td>${objective.dateCreated}</td>
                    <td>${objective.lastUpdated}</td>
                    <td>
                        <a class="btn btn-default" style="padding: 0px 10px"
                           href="<c:url value="/objective/edit/${objective.id}" />">Edit</a>
                        <a class="btn btn-default" style="padding: 0px 10px"
                           href="<c:url value="/objective/delete/${objective.id}" />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty objectiveList}">
                <tr>
                    <td colspan="999">No objectives found</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</div>
