<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">

	<c:param name="title">得点管理システム</c:param>

	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理（学生）</h2>

			<div class="border" style="margin-bottom: 20px;">
				<form action="TestListSubjectExecute.action" method="get">
					<div class="row mx-3 mb-3 py-2 align-items-center" id="filter"
						style="border-bottom: 1px solid #dee2e6">
						<div class="col-2">
							<label class="form-label" for="subject-f1-select"
								style="margin-left: 30px;">科目情報</label>
						</div>

						<div class="col-2">
							<label class="form-label" for="subject-f1-select">入学年度</label> <select
								class="form-select" id="subject-f1-select" name="f1">
								<option value="0">--------</option>
								<c:forEach var="year" items="${ent_year_set}">
									<option value="${year}"
										<c:if test="${year==param.f1}">selected</c:if>>${year}</option>
								</c:forEach>
							</select>
						</div>

						<div class="col-2">
							<label class="form-label" for="subject-f2-select">クラス</label> <select
								class="form-select" id="subject-f2-select" name="f2">
								<option value="0">--------</option>
								<c:forEach var="classli" items="${classList}">
									<option value="${classli}"
										<c:if test="${classli==param.f2}">selected</c:if>>${classli}</option>
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

					</div>
				</form>

				<form action="TestListStudentExecute.action" method="get">
					<div class="row mx-3 align-items-center rounded" id="filter">
						<div class="col-2">
							<label class="form-label" for="subject-f1-select"
								style="margin-left: 30px;">学生情報</label>
						</div>

						<div class="col-4">
							<label class="form-label" for="subject-f4-select">学生番号</label> <input
								class="form-select" type="text" name="student_no"
								placeholder="学生番号を入力してください" style="background-image: none;"
								required>
						</div>
						<div class="col-2 text-center">
							<button class="btn btn-secondary" id="filter-button">検索</button>
						</div>

					</div>
				</form>
			</div>

			<p>
				<c:choose>
					<c:when test="${not empty student_name}">
						氏名: ${student_name}
					</c:when>
				</c:choose>
				( ${studentno} )
			</p>

			<c:choose>

				<c:when test="${test_list.size()>0}">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>科目名</th>
								<th>科目コード</th>
								<th>回数</th>
								<th>点数</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach var="testli" items="${test_list}">
								<tr>
									<td>${testli.subjectName}</td>
									<td>${testli.subjectCd}</td>
									<td>${testli.num}</td>
									<td>
										<c:choose>
											<c:when test="${testli.point==666}">
												-
											</c:when>
											<c:otherwise>
												${testli.point}
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:when>

				<c:otherwise>
					<p>成績情報が存在しませんでした</p>
				</c:otherwise>
			</c:choose>

		</section>

	</c:param>
</c:import>