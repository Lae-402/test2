<%-- 文字コードを指定 --%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- Coreタグを使用 --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
<c:param name="title">得点管理システム</c:param>
	<c:param name="content">
		<!-- メイン -->
		<div id="main">

			<h2>ログイン</h2>
			<form action="LoginExecute.action" method="post">
				<input type="text" name="id" placeholder="ID" required>
				<input type="text" name="password" placeholder="パスワード" required><br>
				<label>
					<input type="checkbox" name="chk_d_ps">パスワードを表示
				</label>
				<input type="submit" name="login" value="ログイン">
			</form>
		</div>
	</c:param>
</c:import>