<%-- 文字コードを指定 --%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- Coreタグを使用 --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
<c:param name="title">得点管理システム</c:param>
	<c:param name="content">

		<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">メニュー</h2>

		<div class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
			style="height: 10rem; background-color: #dbb;">
			<a href="StudentList.action">学生管理</a>
		</div>

		<div class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
			style="height: 10rem; background-color: #bdb;">
			<a href="StudentList.action">（成績管理）</a>
		</div>

		<div class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
			style="height: 10rem; background-color: #bbd;">
			<a href="StudentList.action">（科目管理）</a>
		</div>
	</c:param>
</c:import>