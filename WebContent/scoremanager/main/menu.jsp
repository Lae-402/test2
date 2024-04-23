<%-- 文字コードを指定 --%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- Coreタグを使用 --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
<c:param name="title">得点管理システム</c:param>
	<c:param name="content">

		<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">メニュー</h2>

		<div
		 style="display: flex; justify-content: center; padding: 10px;">
			<div class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
				style="height: 10rem; width: 27%; background-color: #dbb;">
				<p><a href="StudentList.action" style="font-size: 170%;">
					学生管理
				</a></p>
			</div>

			<div class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
				style="height: 10rem; width: 27%; background-color: #bdb;">
				<div style="margin: 0; padding: 15px 0 0 0; font-size: 170%; box-sizing: border-box; text-align: center;">
					<p style="margin-bottom: -40px;">成績管理</p><br>
					<p style="margin-bottom: -40px;"><a href="TestRegist.action" style="font-size: 100%;">成績登録</a></p><br>
					<p><a href="TestList.action" style="font-size: 100%;">成績参照</a></p>
				</div>
			</div>

			<div class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
				style="height: 10rem;width: 27%;  background-color: #bbd;">
				<p><a href="SubjectList.action" style="font-size: 170%;">科目管理</a></p>
			</div>
		</div>
	</c:param>
</c:import>