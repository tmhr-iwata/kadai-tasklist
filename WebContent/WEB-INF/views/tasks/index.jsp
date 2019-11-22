<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${tasks != null}">
                <h2>タスク一覧</h2>
                <table>
                    <tbody>
                        <tr>
                            <th>期日</th>
                            <td>概要</td>
                            <td>更新日時</td>
                            <td>進捗</td>
                        </tr>
                        <c:forEach var="task" items="${tasks}">
                            <tr>
                            <th><fmt:formatDate value="${task.limitday}" pattern="yyyy-MM-dd HH:mm" /></th>
                            <td><a href="<c:url value="/show?id=${task.id}" />"><c:out value="${task.title}" /></a></td>
                            <td><fmt:formatDate value="${task.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                            <td>
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

                <p><a href="<c:url value="/new" />">タスクを登録する</a></p>
            </c:when>
            <c:otherwise>
               <h2>タスクの登録がありません</h2>
               <p>タスクを登録してください。 <a href="<c:url value="/new" /> ">登録</a>
            </c:otherwise>
        </c:choose>
    </c:param>
</c:import>