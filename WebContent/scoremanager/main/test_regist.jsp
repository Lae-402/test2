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
									<c:if test="${ent==f1}">selected</c:if>>${ent}</option>
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
									<c:if test="${subject.cd==f3.cd}">selected</c:if>>${subject.name}</option>
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
									<c:if test="${no==f4}">selected</c:if>>${no}</option>
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
					<div>科目：${f3.name} (${f4}回)</div>
					<form action="TestRegistExecute.action" method="get">
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
									<td style="padding: 0 0 0 10px;"><p style="margin:0; padding:0; line-height: 57px;">${t.getStudent().getEntYear()}</p></td>
									<td style="padding: 0 0 0  5px;"><p style="margin:0; padding:0; line-height: 57px;">${t.getStudent().getClassNum()}</p></td>
									<td style="padding: 0 0 0 10px;"><p style="margin:0; padding:0; line-height: 57px;">${t.getStudent().getNo()}</p></td>
									<td style="padding: 0 0 0 10px;"><p style="margin:0; padding:0; line-height: 57px;">${t.getStudent().getName()}</p></td>
							        <td>
							        	<input name="p${t.getStudent().getNo()}"
							        	 type="number" max="100" min="0"
							        	 class="form-select" style="background-image: none; border-color: black; "

								            <c:choose>
								                <c:when test="${t.point==666}">
								                	<%-- 空白 --%>
								                </c:when>
								                <c:otherwise>
								                    value="${t.point}"
								                </c:otherwise>
								            </c:choose>
								            >
										<div class="mt-2 text-warning">${errors.get("p"+t.getStudent().getNo())}</div>
							        </td>
								</tr>
							</c:forEach>
						</table>
						<input type="hidden" name="f1" value="${f1}">
						<input type="hidden" name="f2" value="${f2}">
						<input type="hidden" name="f3" value="${f3.cd}">
						<input type="hidden" name="f4" value="${f4}">
						<div class="col-3" style="width: 130px;">
							<button class="btn btn-secondary" id="filter-button">登録して終了</button>
						</div>
					</form>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>

		</section>
	</c:param>
</c:import>
