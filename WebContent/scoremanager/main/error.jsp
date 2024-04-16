<%-- 文字コードを指定 --%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- Coreタグを使用 --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
<c:param name="title">得点管理システム</c:param>
	<c:param name="content">
		<p class="p mb-3 fw-norma bg-opacity-5 py-1 px-2" style="background-color: rosybrown; text-align: center">なにかしら・エラー</p>
		<a href="/test2/scoremanager/main/StudentList.action">学生一覧</a>
	</c:param>
</c:import>