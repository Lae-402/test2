<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--taglibディレクティブの記述-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
<c:param name="title">得点管理システム</c:param>

<c:param name="scripts"></c:param>

<c:param name="content">
<section>
	<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報変更</h2>

	<!--actionがないと飛ばせないよね-->
	<form action="StudentUpdateExecute.action" method="get">
		<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">

			<c:forEach var="s" items="${student}">

				<label class="form-label" for="student-f1-select">入学年度</label>
				<input readonly type="text" name="ent_year" value="${s.entYear}" style="border: 0;">

				<label class="form-label">学生番号</label>
				<input readonly type="text" name="no" value="${s.no}" style="border: 0;">

				<label class="form-label">氏名</label>
				<input type="text" name="name" value="${s.name}" required>

				<label class="form-label" for="student-f2-select">クラス</label>
				<select class="form-select" id="student-f2-select" name="class_num">
					<c:forEach var="num" items="${class_num_set}">
						<option value="${num}" <c:if test="${num==s.classNum}">selected</c:if>>${num}</option>
					</c:forEach	>
				</select>

				<div class="col-2 form-check text-center">
					<label class="form-check-label" for="student-f3-check">在学中
						<%-- sが在学中の場合はcheckedを記述 --%>
						<input class="form-check-input" type="checkbox" id="student-f3-check" name="is_attend" value="t"
						<c:if test="${s.isAttend()}">checked</c:if> />
					</label>
				</div>

				<div class="col-2 tect-center">
					<button class="btn btn-secondary" >変更</button>
				</div>

				<div class="mt-2 text-warning">${errors.get("f1")}</div>
			</c:forEach>

		</div>
	</form>
	<a href="StudentList.action">戻る</a>
</section>
</c:param>
</c:import>