<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">得点管理システム</c:param>
	<c:param name="scripts">

	</c:param>
	<c:param name="content">
		<html>
<body>
	<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報変更</h2>
	<form action="SubjectUpdateExecute.action" method="post">
		<div style="position: relative; margin-bottom: 20px;">
			<c:forEach var="s" items="${subject}">
				<label for="cd" style="display: block;">科目コード</label>
				<input type="text" id="cd" name="cd" value="${cd}"
					readonly="readonly" />
		</div>
		<div style="position: relative; margin-bottom: 20px;">
			<label for="name" style="display: block;">科目名</label> <input
				type="text" id="name" name="name" value="${name}" maxlength="20"
				required />
		</div>
		<div class="col-2 text-left">
			<button class="btn btn-secondary"
				style="background-color: #007bff; border-radius: 10px;">変更</button>
		</div>
		<div id="errorMessage"></div>
			</c:forEach>
	</form>
	<a href="SubjectList.action">戻る</a>
</body>
		</html>
	</c:param>
</c:import>
