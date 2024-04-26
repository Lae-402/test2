<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
<c:param name="title">得点管理システム</c:param>
<c:param name="content">
    <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
    <p class="p mb-3 fw-norma bg-opacity-5 py-1 px-2" style="background-color: darkseagreen; text-align: center">登録が完了しました</p>
    <div style="display: flex; justify-content: space-between; align-items: center;">
        <a href="/test2/scoremanager/main/Menu.action" style="margin-right: 10px;">戻る</a>
        <a href="/test2/scoremanager/main/TestList.action" style="margin-right: 800px;">成績参照</a>
    </div>
</c:param>
</c:import>
