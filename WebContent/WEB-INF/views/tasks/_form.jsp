<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<label for="title">タスク名</label><br />
<input type="text" name="title" value="${task.title}" />
<br /><br />

<label for="content">内容</label><br />
<input type="text" name="content" value="${task.content}" />
<br /><br />

<label for="limitday">期日</label><br>
<input type="datetime-local" name="limitday" value="${task.limitday}" />
<br><br>

<input type="hidden" name="_token" value="${_token}" />
<input type="hidden" name="completed" value="${task.completed}" />
<button type="submit">登録</button>