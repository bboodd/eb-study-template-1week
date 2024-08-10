<%@ page import="com.study.model.PostVo" %>
<%@ page import="com.study.model.CommentVo" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <title>Title</title>
</head>
<body>
<h1>
    <%= "게시판 - 보기" %>
</h1>
<div>
    <%--게시글--%>
    <c:if test="${postDto != null}">
        <div>
            <p>${postDto.name}</p>
            <p>${postDto.createDate}</p>
            <p>${postDto.updateDate}</p>
        </div>
        <div>
            <h3>
                <p>[${postDto.categoryName}]</p>
                <p>${postDto.title}</p>
                <p>${postDto.viewCount}</p>
            </h3>
        </div>
        <div>
            <p>${postDto.content}</p>
        </div>
    </c:if>
</div>
<div>
    <%--댓글--%>
    <c:if test="${commentList != null}">
        <c:forEach var="comment" items="${commentList}">
            <div>
                <p>${comment.createDate}</p>
                <p>${comment.content}</p>
            </div>
        </c:forEach>
    </c:if>
    <%--        댓글 추가하고 postId값 넘기면서 리다이렉트   --%>
    <form method="post" action="comment.do">
        <input type="text" name="comment" placeholder="댓글을 입력해 주세요.">
        <button type="submit">등록</button>
        <input type="hidden" name="postId" value="${param.postId}">
    </form>
</div>

<div>
    <button onclick="location.href='list.do'">목록</button>

    <button onclick="location.href='modify.do'">수정</button>

<%--    비밀번호 확인 폼 만들어서 삭제 구현하기   --%>
    <button onclick="deleteBtnClick()">삭제</button>
</div>

<div class="checkDelete" style="display: none">
<%--    비밀번호 확인 폼   --%>
    <form method="post" action="delete.do">
        <p>*비밀번호 확인*</p>

        <input type="inputPassword" name="inputPassword" placeholder="비밀번호를 입력하세요.">
        <input type="hidden" name="postId" value="${param.postId}">
        <c:if test="${postDto != null}">
            <input type="hidden" name="password" value="${postDto.password}">
        </c:if>

        <button type="submit">확인</button>
    </form>
</div>

<script>
    //비밀번호 삭제 폼 토글 함수
    const deleteBtnClick = () => {
        $('.checkDelete').toggle();
    }
</script>
</body>
</html>
