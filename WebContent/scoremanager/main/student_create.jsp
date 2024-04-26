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
	<form action="StudentCreateExecute.action" method="get">
		<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">

			<div class="col-4">
				<label class="form-label" for="student-f1-select">入学年度</label>
				<select class="form-select" id="student-f1-select" name="ent_year">
					<option value="0">--------</option>
					<c:forEach var="year" items="${ent_year_set}">
						<option value="${year}" <c:if test="${year==ent_year}">selected</c:if>>${year}</option>
					</c:forEach>
				</select>
				<div class="mt-2 text-warning">${errors.get("error1")}</div>
			</div>

			<label class="form-label">学生番号</label>
			<input type="text" name="no" placeholder="学生番号を入力してください"  value="${no}" required>
			<div class="mt-2 text-warning">${errors.get("error2")}</div>

			<label class="form-label">氏名</label>
			<input type="text" name="name" placeholder="氏名を入力してください"  value="${name}" required>

			<div class="col-4">
				<label class="form-label" for="student-f2-select">クラス</label>
				<select class="form-select" id="student-f2-select" name="class_num">
					<c:forEach var="num" items="${class_num_set}">
						<option value="${num}" <c:if test="${num==class_num}">selected</c:if>>${num}</option>
					</c:forEach	>
				</select>
			</div>

			<div class="mt-2 text-warning">${errors.get("f1")}</div>
			<div class="col-2 text-center">
				<button class="btn btn-secondary" >登録して終了</button>
			</div>
		</div>
	</form>
	<a href="StudentList.action">戻る</a>
</section>
</c:param>
</c:import>