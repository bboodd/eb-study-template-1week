<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>자유게시판</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</head>
<body>

<h1>
    <%= "자유게시판 - 목록" %>
</h1>
<br/>
<section id="post-board">
    <div class="inner">

        <div class="box1">
            <form method="get" action="list.do">
                <div class="date">
                    <label>등록일
                        <input type="date" name="startDate" id="startDate">
                        ~
                        <input type="date" name="endDate" id="endDate">

                    </label>

                </div>

                <div class="category">
                    <%--        카테고리 테이블에서 출력              --%>
                    <select name="categoryId" id="categoryId">
                        <option value="0">전체 카테고리</option>
                            <c:if test="${categoryList.size() != 0}">
                                <c:forEach var="category" items="${categoryList}">
                                    <option value="${category.categoryId}">${category.categoryName}</option>
                                </c:forEach>
                            </c:if>
                    </select>
                </div>

                <div class="search">
                    <!-- 검색창 -->
                    <input type="text" placeholder="검색어를 입력해 주세요. (제목+작성자+내용)" name="keyword" id="keyword">
                    <!-- 검색 버튼 -->
                    <button type="submit">검색</button>
                </div>
<%--                    검색 후 리다이렉트 시 검색어 가지고 있기    --%>
                <c:if test="${searchDto != null}">
                    <script>
                        $('#startDate').val(${searchDto.startDate});
                        $('#endDate').val(${searchDto.endDate});
                        $('#categoryId').val(${searchDto.categoryId});
                        $('#keyword').val(${searchDto.keyword});
                    </script>
                </c:if>
            </form>
        </div>

        <%--가져온 list.size         --%>
        <div class="box2">
            <c:if test="${postList != null}">
                <p>총 ${postList.size()}건</p>
            </c:if>
        </div>

        <div class="box3">

            <table class="post-list">
                <thead>
                <tr>
                    <th scope="col">카테고리</th>
                    <th scope="col"></th>
                    <th scope="col">제목</th>
                    <th scope="col">작성자</th>
                    <th scope="col">조회수</th>
                    <th scope="col">등록 일시</th>
                    <th scope="col">수정 일시</th>
                </tr>
                </thead>
                <tbody>
                <%--            post list 출력         --%>
                <c:if test="${postList != null}">
                    <c:forEach var="post" items="${postList}">
                        <tr>
                            <td>${post.categoryName}</td>
                            <c:choose>
                                <c:when test="${post.fileCount != 0}">
                                    <td>파일o</td>
                                </c:when>
                                <c:when test="${post.fileCount == 0}">
                                    <td></td>
                                </c:when>
                            </c:choose>
                            <td onclick="location.href='read.do?postId=${post.postId}'">${post.title}</td>
                            <td>${post.name}</td>
                            <td>${post.viewCount}</td>
                            <td>${post.createDate}</td>
                            <td>${post.updateDate}</td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
        </div>
    </div>
</section>

<button onclick="location.href='insert.do'">등록</button>
</body>
</html>
