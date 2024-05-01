<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">

	<c:param name="title">得点管理システム</c:param>

	<c:param name="content">
		<!-- ページのメインコンテンツ -->
		<section class="me-4">
			<!-- ページタイトル -->
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理（科目）</h2>


			<!-- フィルターフォーム -->
			<form action="SubjectList.action" method="get">
				<div class="row border mx-3 mb-3 py-2 align-items-center rounded"
					id="filter">

					<!-- 科目情報フィルター -->
					<div class="col-2">
						<label class="form-label" for="subject-f1-select"
							style="margin-left: 30px;">科目情報</label>
					</div>


					<!-- 入学年度選択 -->
					<div class="col-2">
						<label class="form-label" for="subject-f1-select">入学年度</label>

						<!-- 入学年度の選択肢を表示 -->
						<select class="form-select" id="subject-f1-select" name="f1">

							<option value="0">--</option>
							<!-- 入学年度のセットを反復処理 -->
							<c:forEach var="code" items="${subject_code_set}">

								<!-- 入学年度オプションを設定 -->
								<option value="${code}"
									<c:if test="${code==param.f1}">selected</c:if>>${code}</option>
							</c:forEach>
						</select>
					</div>

					<!-- クラス選択 -->
					<div class="col-2">
						<label class="form-label" for="subject-f2-select">クラス</label>

						<!-- クラスの選択肢を表示 -->
						<select class="form-select" id="subject-f2-select" name="f2">
							<option value="0">--</option>

							<!-- クラスのセットを反復処理 -->
							<c:forEach var="num" items="${subject_name_set}">

								<!-- クラスオプションを設定 -->
								<option value="${num}"
									<c:if test="${num==param.f2}">selected</c:if>>${num}</option>
							</c:forEach>
						</select>
					</div>

					<!-- 科目選択 -->
					<div class="col-4">
						<label class="form-label" for="subject-f3-select">科目</label> <select
							class="form-select" id="subject-f3-select" name="f3">
							<option value="0">--</option>
							<!-- 科目リストから科目を選択 -->
							<c:forEach var="num" items="${subject_name_set}">
								<option value="${num}"
									<c:if test="${student==param.f3}">selected</c:if>>${num}</option>
							</c:forEach>
						</select>
					</div>


					<!-- 検索ボタン -->
					<div class="col-2 text-center">
						<button class="btn btn-secondary" id="filter-button">検索</button>
					</div>

					<!-- エラーメッセージ -->
					<div class="mt-2 text-warning">${errors.get("f1")}</div>
				</div>
			</form>



			<!-- 成績テーブル表示 -->
			<c:choose>
				<!-- 成績情報が存在する場合 -->
				<c:when test="${test_list.size()>0}">
					<!-- テーブルのヘッダー -->
					<table class="table table-hover">
						<thead>
							<tr>
								<th>入学年度</th>
								<th>クラス</th>
								<th>学生番号</th>
								<th>氏名</th>
								<th>１回</th>
								<th>２回</th>
							</tr>
						</thead>
						<tbody>
							<!-- 各学生の成績情報をテーブルに反映 -->
							<c:forEach var="student" items="${test_list}">
								<tr>
									<!-- 各学生の成績情報を列に表示 -->
									<c:forEach var="p" items="${student.points}">
										<!-- 1回目の成績を表示 -->
										<c:choose>
											<c:when test="$[k.key==1]">
												<td>${student.entYear}</td>
												<td>${student.classNum}</td>
												<td>${student.studentNo}</td>
												<td>${student.studentName}</td>
												<td>${p.value}</td>
											</c:when>


											<!-- 2回目の得点を表示 -->
											<c:otherwise>
												<td>${p.value}</td>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</tr>
							</c:forEach>
						</tbody>

					</table>
				</c:when>

				<!-- 学生情報が存在しない場合のメッセージ -->
				<c:otherwise>
					<p>学生情報が存在しませんでした</p>
				</c:otherwise>
			</c:choose>

		</section>

	</c:param>
</c:import>
