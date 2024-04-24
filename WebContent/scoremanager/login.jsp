<%-- 文字コードを指定 --%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- Coreタグを使用 --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
<c:param name="title">得点管理システム</c:param>
	<c:param name="content">
	<div style="display: flex; justify-content: center;">
		<div
		 style="border: 1px solid rgba(33,37,41, 0.2); padding: 0; width: 70%;">
			<h2
			 style="padding: 10px 0; background: rgba(33,37,41, 0.07); text-align: center;">ログイン</h2>
			<form action="LoginExecute.action" method="post">
				<div
				 style="margin: 10px 0; display: flex; justify-content: center;">${error.get("error")}</div>
				<div
				 style="display: flex; justify-content: center;">
					<input type="text" name="id" placeholder="ID" required
					 style="margin: 10px; width: 85%; height: 40px; background: rgba(13, 110, 253, 0.1); border: 1px solid rgba(33,37,41, 0.2); border-radius: 5px;">
				</div>
				<div
				 style="display: flex; justify-content: center;">
					<input type="password" id="password" name="password" placeholder="パスワード" required
					 style="margin: 10px; width: 85%; height: 40px; background: rgba(13, 110, 253, 0.1); border: 1px solid rgba(33,37,41, 0.2); border-radius: 5px;">
				</div>
				<div
				 style="margin: 10px 0; display: flex; justify-content: center;">
					<input type="checkbox" id="showPassword" onchange="togglePasswordVisibility()" />パスワードを表示
					<script>
						function togglePasswordVisibility() {
							let passwordInput = document.getElementById("password");
							let showPasswordCheckbox = document.getElementById("showPassword");

							if (showPasswordCheckbox.checked) {
								passwordInput.type = "text";
							} else {
								passwordInput.type = "password";
							}
						}
					</script>
				</div>
				<div
					 style="margin: 15px 0; display: flex; justify-content: center;">
						<input type="submit" name="login" value="ログイン"
						 style="padding: 5px 40px; background: #0d6efd; color: white; border: none; border-radius: 5px;"
						 onMouseOut="this.style.background='#0d6efd'"
						 onMouseOver="this.style.background='rgba(0, 50, 253, 0.6)'">
				</div>
			</form>
		</div>
	</div>
	</c:param>
</c:import>