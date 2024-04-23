<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp" />
<!-- タイトルとスクリプトを設定 -->
<c:param name="title" value="得点管理システム" />
<c:param name="scripts" value="" />
<c:param name="content">
	<!-- 科目管理セクション -->
	<section class="me-4">
		<!-- セクションの見出し -->
		<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目管理</h2>
		<!-- 新規登録リンク -->
		<div class="my-2 text-end px-4">
			<a href="SubjectCreate.action">新規登録</a>
		</div>
		<!-- 検索フォーム -->
		<form action="SubjectList.action" method="get"></form>
		<!-- 科目一覧テーブル -->
		<table class="table table-hover">
			<thead>
				<!-- テーブルヘッダー -->
				<tr>
					<th>科目コード</th>
					<th>科目名</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<!-- 科目一覧を表示 -->
				<c:forEach var="subject" items="${subject_list}">
					<tr>
						<td>${subject.code}</td>
						<td>${subject.name}</td>
						<td class="text-center"></td>
						<!-- 変更リンク -->
						<td><a href="SubjectUpdate.action?name=${subject.name}">変更</a></td>
						<!-- 削除リンク -->
						<td><a href="SubjectDelete.action?name=${subject.name}">削除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>
</c:param>
</c:import>