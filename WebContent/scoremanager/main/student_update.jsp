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
		<div
		 style="padding: 20px;">

			<c:forEach var="s" items="${student}">

				<div style="margin-bottom: 15px;">
					<label class="form-label" for="student-f1-select"
					 style="margin-bottom: 0;">入学年度</label><br>
					　<input readonly type="text" name="ent_year" value="${s.entYear}" style="border: 0;">
				</div>

				<div style="margin-bottom: 15px;">
					<label class="form-label"
					 style="margin-bottom: 0;">学生番号</label><br>
					　<input readonly type="text" name="no" value="${s.no}" style="border: 0;">
				</div>

				<div>
					<label class="form-label">氏名</label>
					<input class="form-select"  type="text" name="name" value="${s.name}" required
				 style="background-image: none;">
				 </div>

				<div>
					<label class="form-label" for="student-f2-select">クラス</label>
					<select class="form-select" id="student-f2-select" name="class_num">
						<c:forEach var="num" items="${class_num_set}">
							<option value="${num}" <c:if test="${num==s.classNum}">selected</c:if>>${num}</option>
						</c:forEach	>
					</select>
				</div>

				<div style="margin: 15px 0 20px 0;">
					<label class="form-check-label" for="student-f3-check">在学中</label>
					<%-- sが在学中の場合はcheckedを記述 --%>
					<input class="form-check-input" type="checkbox" id="student-f3-check" name="is_attend" value="t"
					<c:if test="${s.isAttend()}">checked</c:if> />
				</div>

				<div class="col-2 tect-center">
					<button class="btn btn-secondary"
					 style="background: #0d6efd; color: white; border: none; border-radius: 5px;"
					 onMouseOut="this.style.background='#0d6efd'"
					 onMouseOver="this.style.background='rgba(0, 50, 253, 0.6)'">変更</button>
				</div>

				<div class="mt-2 text-warning">${errors.get("f1")}</div>
			</c:forEach>
			<a href="StudentList.action">戻る</a>
		</div>
	</form>
</section>
</c:param>
</c:import>