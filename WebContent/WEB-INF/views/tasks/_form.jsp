<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>
    </div>
</c:if>
<c:choose>
    <c:when test="${task.id !=null }">
        <label for="title">概要</label><br />
        <input type="text" name="title" value="${task.title}" />
        <br /><br />
        <label for="content">詳細</label><br />
        <input type="text" name="content" value="${task.content}" />
        <br /><br />
        <label for="limitday">期日</label><br>
        <input type="date" name="limitday" value="${limitday}" required />
        <input type="time" name="limitTime" value="${limitTime}" required />
        <br><br>
        <c:choose>
            <c:when test="${task.completed == false}">
                <input type="checkbox" name="completed" value="true">完了
            </c:when>
            <c:otherwise>
                <input type="checkbox" name="completed" value="true" checked="checked">完了
            </c:otherwise>
        </c:choose>
    </c:when>
    <c:otherwise>
        <label for="completed"></label>
        <input type="hidden" name="completed" value="false" />
        <label for="title">概要</label><br />
        <input type="text" name="title" value="${task.title}" />
        <br /><br />
        <label for="content">詳細</label><br />
        <input type="text" name="content" value="${task.content}" />
        <br /><br />
        <label for="limitday">期日</label><br>
        <input type="date" name="limitday" value="${limitday}" required />
        <input type="time" name="limitTime" value="${limitTime}" required />
        <br><br>
    </c:otherwise>
</c:choose>
<input type="hidden" name="_token" value="${_token}" />

<p><button type="submit">登録</button></p>