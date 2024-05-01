<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">

	<c:param name="title">得点管理システム</c:param>

	<c:param name="content">
		<!-- ページコンテンツのセクション -->
		<section class="me-4">
			<!-- セクションの見出し -->
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理（学生）</h2>

			<!-- フィルターフォーム（科目情報） -->
			<div class="border" style="margin-bottom: 20px;">
				<!-- 科目情報のフィルターフォーム -->
				<form action="TestListSubjectExecute.action" method="get">
					<!-- フォームのレイアウト -->
					<div class="row mx-3 mb-3 py-2 align-items-center" id="filter"
						style="border-bottom: 1px solid #dee2e6">
						<div class="col-2">
							<!-- ラベル -->
							<label class="form-label" for="subject-f1-select"
								style="margin-left: 30px;">科目情報</label>
						</div>
						<div class="col-2">
							<!-- 入学年度の選択 -->
							<label class="form-label" for="subject-f1-select">入学年度</label> <select
								class="form-select" id="subject-f1-select" name="ent_year">
								<option value="0">--------</option>
								<!-- 入学年度の選択肢 -->
								<c:forEach var="code" items="${ent_year_set}">
									<option value="${code}"
										<c:if test="${code==param.f1}">selected</c:if>>${code}</option>
								</c:forEach>
							</select>
						</div>
						<!-- クラスの選択 -->
						<div class="col-2">
							<label class="form-label" for="subject-f2-select">クラス</label> <select
								class="form-select" id="subject-f2-select" name="class_num">
								<option value="0">--------</option>
								<c:forEach var="num" items="${classList}">
									<option value="${num}"
										<c:if test="${num==param.f2}">selected</c:if>>${num}</option>
								</c:forEach>
							</select>
						</div>
						<!-- 科目の選択 -->
						<div class="col-4">
							<label class="form-label" for="subject-f3-select">科目</label> <select
								class="form-select" id="subject-f3-select" name="subject_cd">
								<option value="0">--------</option>
								<c:forEach var="num" items="${subjecList}">
									<option value="${num}"
										<c:if test="${num==param.f3}">selected</c:if>>${num}</option>
								</c:forEach>
							</select>
						</div>
						<!-- 検索ボタン -->
						<div class="col-2 text-center">
							<button class="btn btn-secondary" id="filter-button">検索</button>
						</div>
						<!-- エラーメッセージ -->
						<div class="mt-2 text-warning">${errors.get("su")}</div>
					</div>
				</form>

				<!-- フィルターフォーム（学生情報） -->
				<form action="TestListStudentExecute.action" method="get">
					<!-- 学生情報のフィルターフォーム -->
					<div class="row mx-3 align-items-center rounded" id="filter">
						<div class="col-2">
							<!-- ラベル -->
							<label class="form-label" for="subject-f1-select"
								style="margin-left: 30px;">学生情報</label>
						</div>
						<div class="col-4">
							<!-- 学生番号の入力 -->
							<label class="form-label" for="subject-f4-select">学生番号</label> <input
								class="form-select" type="text" name="student_no"
								placeholder="学生番号を入力してください" style="background-image: none;">
						</div>
						<!-- 検索ボタン -->
						<div class="col-2 text-center">
							<button class="btn btn-secondary" id="filter-button">検索</button>
						</div>
						<!-- エラーメッセージ -->
						<div class="mt-2 text-warning">${errors.get("st")}</div>
					</div>
				</form>
			</div>

			<!-- 成績テーブル表示 -->
			<c:choose>
				<!-- 学生情報が存在する場合 -->
				<c:when test="${test_list.size()>0}">
					<!-- テーブルの作成 -->
					<table class="table table-hover">
						<!-- テーブルのヘッダー -->
						<thead>
							<tr>
								<th>科目名</th>
								<th>科目コード</th>
								<th>回数</th>
								<th>点数</th>
							</tr>
						</thead>
						<!-- テーブルのボディ -->
						<tbody>
							<!-- 各学生の成績情報を表示 -->
							<c:forEach var="student" items="${test_list}">
								<tr>
									<!-- 科目名の表示 -->
									<td>${student.subjectName}</td>
									<!-- 科目コードの表示 -->
									<td>${student.subjectCd}</td>
									<!-- 回数の表示 -->
									<td>${student.num}</td>
									<!-- 点数の表示 -->
									<td>${student.point}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:when>
				<!-- 学生情報が存在しない場合 -->
				<c:otherwise>
					<!-- メッセージの表示 -->
					<p>学生情報が存在しませんでした</p>
				</c:otherwise>
			</c:choose>


		</section>

	</c:param>
</c:import>
