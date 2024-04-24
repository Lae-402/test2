<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--taglibディレクティブの記述-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">得点管理システム</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section>
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報登録</h2>
			<form action="SubjectCreateExecute.action" methood="post">
				<div style="position: relative; margin-bottom: 20px;">
					<label for="code" style="display: block;">科目コード</label>
					 <input type="text" name="subjectcd" placeholder = "科目コードを入力してください" value="${subjectcd}" maxlength="20"
						required />

				</div>
				<div style="position: relative; margin-bottom: 20px;">
					<label for="code" style="display: block;">科目名</label>
					<input type="text" name="subjectname" placeholder = "科目名を入力してください" value="${subjectname}" maxlength="20"
						required />
				</div>
				<div class="col-2 text-center">
					<button class="btn btn-secondary"
						style="background-color: #007bff; border-radius: 10px;">登録</button>
				</div>
			</form>
			<a href="SubjectList.action">戻る</a>
		</section>
	</c:param>
</c:import>
