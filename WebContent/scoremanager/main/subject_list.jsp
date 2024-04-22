<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">

<c:param name="title">得点管理システム</c:param>

<c:param name="content">
<section class="me-4">
    <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目管理</h2>
    <div class="my-2 text-end px-4">
        <a href="StudentCreate.action">新規登録</a>
    </div>

    <form action="SubjectList.action" method="get">
        <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
            <div class="col-4">
                <label class="form-label" for="subject-f1-select">科目コード</label>
                <select class="form-select" id="subject-f1-select" name="f1">
                    <option value="0">--</option>
                    <c:forEach var="code" items="${subject_code_set}">
                        <option value="${code}" <c:if test="${code==param.f1}">selected</c:if>>${code}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-4">
                <label class="form-label" for="subject-f2-select">科目名</label>
                <select class="form-select" id="subject-f2-select" name="f2">
                    <option value="0">--</option>
                    <c:forEach var="num" items="${subject_name_set}">
                        <option value="${num}" <c:if test="${num==param.f2}">selected</c:if>>${num}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="mt-2 text-warning">${errors.get("f1")}</div>
        </div>
    </form>
    <table class="table table-hover">
        <tr>
            <th>科目コード</th>
            <th>科目名</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach var="subject" items="${subject}">
            <tr>
                <td>${subject.code}</td>
                <td>${subject.name}</td>
                <td class="text-center"></td>
                <td><a href="SubjectUpdate.action?name=${subject.name}">変更</a></td>
                <td><a href="SubjectDelete.action?name=${subject.name}">削除</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</c:param>
</c:import>
