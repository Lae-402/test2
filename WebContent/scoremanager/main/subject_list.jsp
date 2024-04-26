<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
<c:param name="title">得点管理システム</c:param>
<c:param name="content">
    <section class="me-4">
        <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
        <form action="SubjectList.action" method="get">
            <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
                <div class="col-4">
                    <label class="form-label" for="subject-f1-select">入学年度</label>
                    <select class="form-select" id="subject-f1-select" name="f1">
                        <option value="0">--</option>
                        <c:forEach var="code" items="${subject_code_set}">
                            <option value="${code}" <c:if test="${code==param.f1}">selected</c:if>>${code}</option>
                        </c:forEach>
                    </select>
                    </div>
                    <div class="row">
            <div class="col-4">
                <label class="form-label" for="subject-f2-select">クラス</label>
                <select class="form-select" id="subject-f2-select" name="f2">
                    <option value="0">--</option>
                    <c:forEach var="num" items="${subject_name_set}">
                        <option value="${num}" <c:if test="${num==param.f2}">selected</c:if>>${num}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-4">
                <label class="form-label" for="subject-f3-select">科目</label>
                <select class="form-select" id="subject-f3-select" name="f3">
                    <option value="0">--</option>
                    <c:forEach var="num" items="${subject_name_set}">
                        <option value="${num}" <c:if test="${num==param.f3}">selected</c:if>>${num}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-4">
                <label class="form-label" for="subject-f4-select">回数</label>
                <select class="form-select" id="subject-f4-select" name="f4">
                    <option value="0">--</option>
                    <c:forEach var="num" items="${subject_name_set}">
                        <option value="${num}" <c:if test="${num==param.f4}">selected</c:if>>${num}</option>
                    </c:forEach>
                </select>
            </div>

                </div>
            </div>
        </form>
        <div>科目:${subjectname}</div>
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">入学年度</th>
                    <th scope="col">クラス</th>
                    <th scope="col">学生番号</th>
                    <th scope="col">氏名</th>
                    <th scope="col">点数</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="student" items="${students}">
                    <tr>
                        <th scope="row">${student.entYear}</th>
                        <td>${student.classNum}</td>
                        <td>${student.no}</td>
                        <td>${student.name}</td>
                        <td>
                            <input type="text" class="form-control" name="point_${student.studentNumber}" value="${student.score}" placeholder="点数を入力" oninput="validateScore(this)">
                            <div class="validation-message" style="display: none; color: red;">0~100の間隔で入力してください</div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="text-left">
            <button class="btn btn-secondary">登録して終了</button>
        </div>

            <div class="col-2 text-center">
                <button class="btn btn-secondary" id="filter-button">検索</button>
            </div>

    </section>
</c:param>
</c:import> 