<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page trimDirectiveWhitespaces="true"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:if test="${flush != null}">
                <div id="flush_success">
                    <c:out value="${flush}"></c:out>
                </div>
            </c:if>
            <h2>タスク一覧</h2>
            <div id="menu">
                <ul>
                    <li><a href='<c:url value="?completed=all" />' >全件表示</a></li>
                    <li><a href='<c:url value="?completed=false" />' >未完了タスク</a></li>
                    <li><a href='<c:url value="?completed=true" />' >完了済タスク</a></li>
                </ul>
            </div>
            <table>
                <tbody>
                    <tr>
                        <th class="index">期日</th>
                        <td class="title">概要</td>
                        <td class="updated">更新日時</td>
                        <td class="complete">進捗</td>
                    </tr>
                    <c:forEach var="task" items="${tasks}" varStatus="status">
                        <tr>
                            <th class="index"><fmt:formatDate value="${task.limitday}" pattern="yyyy-MM-dd HH:mm" /></th>
                            <td class="title"><a href="<c:url value="/show?id=${task.id}" />"><c:out value="${task.title}" /></a></td>
                            <td class="updated"><fmt:formatDate value="${task.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                            <td class="complete">
                                <c:choose>
                                    <c:when test="${task.completed == true}">
                                        完了
                                    </c:when>
                                    <c:otherwise>
                                        未完了
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div id="pagination">
                (全 ${tasks_count} 件)<br>
                <c:forEach var="i" begin="1" end="${((tasks_count -1) / 10) + 1}" step="1">
                    <c:choose>
                        <c:when test="${i == page}">
                            <c:out value="${i}" />&nbsp;
                        </c:when>
                        <c:otherwise>
                            <a href='<c:url value="/index?completed=${completed}&page=${i}"/>'><c:out value="${i}" /></a>&nbsp;
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>
            <p><a href="<c:url value="/new" />">タスクを登録する</a></p>
    </c:choose>
    </c:param>
</c:import>