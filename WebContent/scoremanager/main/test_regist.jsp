<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">

	<c:param name="title">得点管理システム</c:param>

	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>

			<!-- フィルター部分のコード -->
			<form action="TestRegist.action" method="get">
				<div class="row border mx-3 mb-3 py-2 align-items-center rounded"
					id="filter">
					<!-- 入学年度 -->
					<div class="col-3"  style="width: 150px;">
						<label class="form-label" for="subject-f1-select">入学年度</label>
						<select class="form-select" id="subject-f1-select" name="f1">
							<option value="0">--------</option>
							<c:forEach var="ent" items="${ent_year_list}">
								<option value="${ent}"
									<c:if test="${ent==in_ey}">selected</c:if>>${ent}</option>
							</c:forEach>
						</select>
					</div>
					<!-- クラス -->
					<div class="col-3" style="width: 150px;">
						<label class="form-label" for="subject-f2-select">クラス</label>
						<select class="form-select" id="subject-f2-select" name="f2">
							<option value="0">--------</option>
							<c:forEach var="class_num" items="${class_list}">
								<option value="${class_num}"
								<c:if test="${class_num==f2}">selected</c:if>>${class_num}</option>
							</c:forEach>
						</select>
					</div>
					<!-- 科目 -->
					<div class="col-3" style="width: 320px;">
						<label class="form-label" for="subject-f3-select">科目</label>
						<select class="form-select" id="subject-f3-select" name="f3">
							<option value="0">--------</option>
							<c:forEach var="subject" items="${subject_list}">
								<option value="${subject.cd}"
									<c:if test="${subject.cd==in_s}">selected</c:if>>${subject.name}</option>
							</c:forEach>
						</select>
					</div>

					<!-- 回数 -->
					<div class="col-3" style="width: 150px;">
						<label class="form-label" for="subject-f4-select">回数</label>
						<select class="form-select" id="subject-f4-select" name="f4">
							<option value="0">--------</option>
							<c:forEach var="no" items="${no_list}">
								<option value="${no}"
									<c:if test="${no==in_n}">selected</c:if>>${no}</option>
							</c:forEach>
						</select>
					</div>
					<!-- 検索ボタン -->
					<div class="col-3" style="width: 100px; margin-left: 20px;">
						<button class="btn btn-secondary" id="filter-button">検索</button>
					</div>
				</div>
			</form>

			<div class="mt-2 text-warning">${error}</div>

			<c:choose>
				<c:when test="${tests.size()>0}">
					<div>科目：${subject.name}(${in_n})</div>
					<table class="table table-hover">
						<tr>
							<th>入学年度</th>
							<th>クラス</th>
							<th>学生番号</th>
							<th>氏名</th>
							<th>点数</th>
						</tr>
						<c:forEach var="t" items="${tests}">
							<tr>
								<td>${t.getStudent().getEntYear()}</td>
								<td>${t.getStudent().getClassNum()}</td>
								<td>${t.getStudent().getNo()}</td>
								<td>${t.getStudent().getName()}</td>
								<td>${t.point}</td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>

		</section>
	</c:param>
</c:import>
