<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--taglibディレクティブの記述-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
<c:param name="title">得点管理システム</c:param>

<c:param name="scripts"></c:param>

<c:param name="content">
<section class="me-4">
	<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">教師一覧</h2>
	<div class="my-2 text-end px-4">
		<!-- <a href="StudentCreate.action">新規登録</a>教師削除 -->
	</div>

<c:choose>
    <c:when test="${not empty teacher}">
        <table class="table table-hover">
            <tr>
                <th>ID</th>
                <th>氏名</th>
   <!--              <th>学校</th> -->
            </tr>
            <c:forEach var="tea" items="${teacher}">
                <tr>
                    <td>${tea.id}</td>
                    <td>${tea.name}</td>
                    <%-- <td>${tea.school}</td> --%>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <div>教師情報が存在しませんでした</div>
    </c:otherwise>
</c:choose>

</section>
</c:param>
</c:import>