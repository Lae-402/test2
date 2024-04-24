<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
<c:param name="title">得点管理システム</c:param>
<c:param name="scripts">
    <style>
        .delete-button {
            background-color: red;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .delete-button:hover {
            background-color: darkred;
        }
    </style>
</c:param>
<c:param name="content">
<html>
<body>
    <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報削除</h2>
    <p id="confirmMessage">「${name}」を排除してもよろしいですか？</p>
    <form action="DeleteSubject.action" method="post">
        <input type="submit" class="delete-button" value="削除" />
    </form>
    <a href="SubjectList.action">戻る</a>
</body>
</html>
</c:param>
</c:import>
