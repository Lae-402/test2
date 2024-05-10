<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
<c:param name="title">得点管理システム</c:param>
<c:param name="scripts">
    <style>
        .delete-button {
        	width: 60px;
        	height: 30px;
            padding: 0;
            background-color: red;
            border: none;
            border-radius: 5px;
            cursor: pointer;
			text-align: center;
			display: table-cell;
			vertical-align: middle;
        }
        .delete-button:hover {
            background-color: darkred;
        }
    </style>
</c:param>
<c:param name="content">
<html>
<body>
    <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報削除</h2>
    <p id="confirmMessage">「${name}(${cd})」を削除してもよろしいですか？</p>
    	<div class="delete-button" >
    		<a href="SubjectDeleteExecute.action?cd=${cd}"
    		 style="text-decoration: none; color: white;">削除</a>
    	</div>
    <div style="margin-top: 60px;">
		<a href="SubjectList.action">戻る</a>
     </div>
</body>
</html>
</c:param>
</c:import>
