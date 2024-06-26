<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">

<c:param name="title">得点管理システム</c:param>

<c:param name="content">
	<section class="me-4">
		<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>

		<div class="border" style="margin-bottom: 20px;">
			<form action="TestListSubjectExecute.action" method="get">
				<div class="row mx-3 mb-3 py-2 align-items-center" id="filter"
				 style="border-bottom: 1px solid #dee2e6">
					<div class="col-2">
						<label class="form-label" for="subject-f1-select"
							style="margin-left: 30px;">科目情報</label>
					</div>

					<div class="col-2">
						<label class="form-label" for="subject-f1-select">入学年度</label>
						<select class="form-select" id="subject-f1-select" name="f1">
							<option value="0">--------</option>
							<c:forEach var="code" items="${ent_year_set}">
								<option value="${code}"
									<c:if test="${code==param.f1}">selected</c:if>>${code}</option>
							</c:forEach>
						</select>
					</div>

					<div class="col-2">
						<label class="form-label" for="subject-f2-select">クラス</label> <select
							class="form-select" id="subject-f2-select" name="f2">
							<option value="0">--------</option>
							<c:forEach var="num" items="${classList}">
								<option value="${num}"
									<c:if test="${num==param.f2}">selected</c:if>>${num}</option>
							</c:forEach>
						</select>
					</div>

					<div class="col-4">
						<label class="form-label" for="subject-f3-select">科目</label> <select
							class="form-select" id="subject-f3-select" name="f3">
							<option value="0">--------</option>
							<c:forEach var="subject" items="${subjectList}">
								<option value="${subject.cd}"
									<c:if test="${subject.cd==param.f3}">selected</c:if>>${subject.name}</option>
							</c:forEach>
						</select>
					</div>

					<div class="col-2 text-center">
						<button class="btn btn-secondary" id="filter-button">検索</button>
					</div>
					<div class="mt-2 text-warning">${errors.get("error")}</div>
				</div>
			</form>

			<form action="TestListStudentExecute.action" method="get">
				<div class="row mx-3 align-items-center rounded"
					id="filter">
					<div class="col-2">
						<label class="form-label" for="subject-f1-select"
							style="margin-left: 30px;">学生情報</label>
					</div>

					<div class="col-4">
						<label class="form-label" for="subject-f4-select">学生番号</label>
						<input class="form-select"  type="text" name="student_no" placeholder="学生番号を入力してください" style="background-image: none;" required>
					</div>

					<div class="col-2 text-center">
						<button class="btn btn-secondary" id="filter-button">検索</button>
					</div>

				<%-- 	<div class="mt-2 text-warning">${errors.get("st")}</div> --%>
				</div>
			</form>
		</div>
		<p
		 style="color: #00dfff;">科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</p>
	</section>

</c:param>
</c:import>
