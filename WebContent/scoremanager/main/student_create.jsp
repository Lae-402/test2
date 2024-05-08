<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--taglibディレクティブの記述-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
<c:param name="title">得点管理システム</c:param>

<c:param name="scripts"></c:param>

<c:param name="content">
<section>
	<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報登録</h2>

	<!--actionがないと飛ばせないよね-->
	<div style="padding: 0 20px 0 20px;">
		<form action="StudentCreateExecute.action" method="get">

			<div class="col-4" style="width: 100%;">
				<label class="form-label" for="student-f1-select">入学年度</label>
				<select class="form-select" id="student-f1-select" name="ent_year"
				 style="width: 100%;">
					<option value="0">--------</option>
					<c:forEach var="year" items="${ent_year_set}">
						<option value="${year}" <c:if test="${year==ent_year}">selected</c:if>>${year}</option>
					</c:forEach>
				</select>
				<div class="mt-2 text-warning">${errors.get("error1")}</div>
			</div>

			<div class="col-4" style="width: 100%;">
				<label class="form-label">学生番号</label>
				<input class="form-select" type="text" name="no" placeholder="学生番号を入力してください"  value="${no}" required
				 style="background-image: none;">
				<div class="mt-2 text-warning">${errors.get("error2")}</div>
			</div>

			<div class="col-4" style="width: 100%;">
				<label class="form-label">氏名</label>
				<input class="form-select"  type="text" name="name" placeholder="氏名を入力してください"  value="${name}" required
				 style="background-image: none;">
			</div>

			<div class="col-4" style="width: 100%;">
				<label class="form-label" for="student-f2-select">クラス</label>
				<select class="form-select" id="student-f2-select" name="class_num"
				 style="width: 100%;">
					<c:forEach var="num" items="${class_num_set}">
						<option value="${num}" <c:if test="${num==class_num}">selected</c:if>>${num}</option>
					</c:forEach	>
				</select>
			</div>
			<div style="margin-top: 20px;">
				<button class="btn btn-secondary" >登録して終了</button>
			</div>
			<div class="mt-2 text-warning">${errors.get("f1")}</div>

		</form>
		<div style="margin-top: 20px;">
			<a href="StudentList.action">戻る</a>
		</div>
	</div>
</section>
</c:param>
</c:import>