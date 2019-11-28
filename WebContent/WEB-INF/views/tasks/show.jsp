<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${task != null}">
                <h2>${task.title} のタスク詳細</h2>
                <div class="show">
                    <c:if test="${task.completed == false}">
                        <h3>このタスクは完了していません！</h3>
                    </c:if>
                    <p><a href="<c:url value='/edit?id=${task.id}' /> ">編集</a></p>

                    <table class="show">
                        <tbody>
                            <tr>
                                <th>概要</th>
                                <td><c:out value="${task.title}" /></td>
                            </tr>
                        </tbody>
                        <tbody>
                            <tr>
                                <th>詳細</th>
                                <td><c:out value="${task.content}" /></td>
                            </tr>
                        </tbody>
                        <tbody>
                            <tr>
                                <th>期日</th>
                                <td><fmt:formatDate value="${task.limitday}" pattern="yyyy-MM-dd HH:mm" /></td>
                            </tr>
                        </tbody>
                        <tbody>
                            <tr>
                                <th>登録日時</th>
                                <td><fmt:formatDate value="${task.created_at}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                            </tr>
                        </tbody>
                        <tbody>
                            <tr>
                                <th>更新日時</th>
                                <td><fmt:formatDate value="${task.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                            </tr>
                        </tbody>
                        <tbody>
                            <tr>
                                <th>ステータス</th>
                                <td>
                                    <c:choose>
                                       <c:when test="${task.completed == false}">
                                           継続中
                                       </c:when>
                                       <c:otherwise>
                                           タスク完了済み
                                       </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <p><a href="<c:url value='/edit?id=${task.id}' /> ">編集</a></p>
                </div>
                <p><a href="<c:url value='/index' /> ">一覧に戻る</a></p>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>
    </c:param>
</c:import>