<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">得点管理システム</c:param>
	<c:param name="content">


		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>
			<form action="SubjectList.action" method="get">
				<!-- 科目情報 -->
				<div
					class="d-flex flex-row justify-content-between border mx-3 mb-3 py-2 align-items-center rounded"
					id="filter">
					<!-- 科目情報 -->
					<div class="flex-fill mx-2">
						<label class="form-label"><strong>科目情報</strong></label>
					</div>
					<!-- 入学年度 -->
					<div class="flex-fill mx-2">
						<label class="form-label" for="subject-f1-select">入学年度</label> <select
							class="form-select" id="subject-f1-select" name="f1">
							<option value="0">--</option>
							<c:forEach var="code" items="${subjectcd}">
								<option value="${code}" ${code == param.f1 ? 'selected' : ''}>${code}</option>
							</c:forEach>
						</select>
					"WebContent/scoremanager/main/test_reference.jsp"</div>
					<!-- クラス -->
					<div class="flex-fill mx-2">
						<label class="form-label" for="subject-f2-select">クラス</label> <select
							class="form-select" id="subject-f2-select" name="f2">
							<option value="0">--</option>
							<c:forEach var="num" items="${subjectname}">
								<option value="${num}" ${num == param.f2 ? 'selected' : ''}>${num}</option>
							</c:forEach>
						</select>
					</div>
					<!-- 科目 -->
					<div class="flex-fill mx-2">
						<label class="form-label" for="subject-f3-select">科目</label> <select
							class="form-select" id="subject-f3-select" name="f3">
							<option value="0">--</option>
							<c:forEach var="num" items="${subjectname}">
								<option value="${num}" ${num == param.f3 ? 'selected' : ''}>${num}</option>
							</c:forEach>
						</select>
					</div>

					<!-- 検索ボタン -->
					<div class="flex-fill mx-2 text-center">
						<button class="btn btn-secondary" id="filter-button">検索</button>
					</div>
				</div>
			</form>

			<!-- 学生情報 -->
			<div
				class="d-flex flex-row justify-content-between border mx-3 mb-3 py-2 align-items-center rounded"
				id="student-info">
				<div class="flex-fill mx-2">
					<label class="form-label"><strong>学生情報</strong></label>
				</div>
				<div class="flex-fill mx-2">
					<div style="position: relative; margin-bottom: 20px; left: -180px">
						<label for="name" style="display: block;">学生番号</label> <input
							type="text" id="name" name="name" value="${name}"
							placeholder="学生番号を入力してください" maxlength="20" required />
						<button class="btn btn-secondary" id="filter-button">検索</button>
					</div>

				</div>
			</div>
		</section>
	</c:param>
</c:import>
