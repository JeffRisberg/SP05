<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${flashMessage != null}">
    <div class="message">${flashMessage}</div>
</c:if>

<div class="row" style="padding-top: 8px">
    <div class="col-md-6" style="font-size: 16px; font-weight: bold">
        Find, edit, and create your games
    </div>
    <div class="col-md-6">
        <a href="<c:url value="/game/create" />" class="pull-right btn btn-default" style="padding: 0px 10px">
            Create New Game &raquo
        </a>
    </div>
</div>
<div id="gameResults">
    <div class="list">
        <table class="table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Custom CSS</th>
                <th>Date Created</th>
                <th>Last Updated</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="game" items="${gameList}" varStatus="rowCounter">
                <tr class="${rowCounter.count % 2 == 0 ? 'even' : 'odd'}">
                    <td>
                        <a href="<c:url value="/game/show/${game.id}" />">${game.name}</a>
                    </td>
                    <td>${game.customCSS}</td>
                    <td>${game.dateCreated}</td>
                    <td>${game.lastUpdated}</td>
                    <td>
                        <a class="btn btn-default" style="padding: 0px 10px"
                           href="<c:url value="/page/${game.id}" />">View</a>
                        <a class="btn btn-default" style="padding: 0px 10px"
                           href="<c:url value="/game/edit/${game.id}" />">Edit</a>
                        <a class="btn btn-default" style="padding: 0px 10px"
                           href="<c:url value="/game/delete/${game.id}" />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty gameList}">
                <tr>
                    <td colspan="999">No games found</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</div>
