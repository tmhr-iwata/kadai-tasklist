<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>タスク一覧</h2>
        <ul>
            <c:forEach var="task" items="${tasks}">
                <li>
                    <a href="<c:url value="/show?id=${task.id}" />">
                        <c:out value="${task.id}" />
                    </a>
                    ：<c:out value="${task.title}"></c:out> &gt; <c:out value="${task.content}" />
                </li>
            </c:forEach>
        </ul>

        <p><a href="<c:url value="/new" />">タスクを登録する</a></p>

    </c:param>
</c:import>