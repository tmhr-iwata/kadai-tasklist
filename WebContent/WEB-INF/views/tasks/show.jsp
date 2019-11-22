<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${task != null}">
                <h2>id : ${task.id} のタスク詳細</h2>

                <p>タスク：<c:out value="${task.title}" /></p>
                <p>詳細：<c:out value="${task.content}" /></p>
                <p>期日：<fmt:formatDate value="${task.limitday}" pattern="yyyy-MM-dd HH:mm" /></p>
                <p>作成日時：<fmt:formatDate value="${task.created_at}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
                <p>更新日時：<fmt:formatDate value="${task.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
                <c:choose>
                   <c:when test="${task.completed == false}">
                       <p>タスク未了</p>
                   </c:when>
                   <c:otherwise>
                       <p>タスク完了</p>
                   </c:otherwise>
                </c:choose>
                <p><a href="<c:url value='/edit?id=${task.id}' /> ">編集</a></p>

                <p><a href="<c:url value='/index' /> ">一覧に戻る</a></p>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>
    </c:param>
</c:import>