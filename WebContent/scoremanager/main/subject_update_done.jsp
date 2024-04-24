<%-- 文字コードを指定 --%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- Coreタグを使用 --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
<c:param name="title">得点管理システム</c:param>
	<c:param name="content">
		<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報変更</h2>
		<p class="p mb-3 fw-norma bg-opacity-5 py-1 px-2" style="background-color: darkseagreen; text-align: center">変更が完了しました</p>
		<a href="/test2/scoremanager/main/SubjectList.action">科目一覧</a>
	</c:param>
</c:import>