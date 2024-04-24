<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報</h2> <!-- 科目見出し -->
        <table class="table"> <!-- 成績一覧テーブル -->
            <thead>
                <tr>
                    <th>入学年度</th>
                    <th>クラス</th>
                    <th>科目</th>
                    <th>回数</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="student" items="${students}"> <!-- 成績情報をリストで表示 -->
                    <tr>
                        <td>${student.enrollmentYear}</td> <!-- 成績情報(入学年度) -->
                        <td>${student.classNumber}</td> <!-- 成績情報(クラス) -->
                        <td>${student.studentNumber}</td> <!-- 成績情報(学生番号) -->
                        <td>${student.name}</td> <!-- 成績情報(氏名) -->
                        <td>
                            <input type="text" name="point_${student.studentNumber}" size="5" value="${student.score}" /> <!-- 成績情報(点数) -->
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <form action="Testlist.action" method="post"> <!-- 登録して終了ボタンのフォーム -->
            <input type="submit" value="登録して終了" class="btn btn-primary" />
        </form>
    </c:param>
</c:import>
