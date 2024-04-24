<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">

<c:param name="title">得点管理システム</c:param>

<c:param name="content">
<section class="me-4">
    <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>

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
                        <option value="${num}" <c:if test="${student==param.f3}">selected</c:if>>${num}</option>
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
             <div class="col-2 tect-center">
				<button class="btn btn-secondary" id="filter-button">検索</button>
			</div>
            <div class="mt-2 text-warning">${errors.get("f1")}</div>
        </div>
    </form>

</section>
</c:param>
</c:import>