<%@ page import="com.study.model.PostVo" %>
<%@ page import="com.study.model.CommentVo" %>
<%@ page import="java.util.List" %>
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

<%
    //vo받아서 화면에 출력
    if(request.getAttribute("postVo") != null){
        PostVo postVo = (PostVo) request.getAttribute("postVo");

        out.println("<div><p>" + postVo.getName() + "</p>");
        out.println("<p>등록일시 " + postVo.getCreateDate() + "</p>");
        out.println("<p>수정일시 " + postVo.getUpdateDate() + "</p></div>");

        out.println("<div><h3><p>[" + postVo.getCategory() + "]</p>");
        out.println("<p>" + postVo.getTitle() + "</p>");
        out.println("<p>" + postVo.getViewCount() + "</p></h3></div>");

        out.println("<div>" + postVo.getContent() + "</div>");
    }
%>

<div>
<%--    댓글 받아오기   --%>
    <%
        //vo받아서 출력
        if(request.getAttribute("commentList") != null){
            List<CommentVo> commentList = (List<CommentVo>) request.getAttribute("commentList");

            for(CommentVo comment : commentList){
                out.println("<div><p>" + comment.getCreateDate() + "</p>");
                out.println("<p>" + comment.getContent() + "</p></div>");
            }
        }

    %>
    <form method="post" action="comment.do">
        <input type="text" name="comment" placeholder="댓글을 입력해 주세요.">
        <button type="submit">등록</button>
<%--        댓글 추가하고 postId값 넘기면서 리다이렉트   --%>
        <%
            //postId
            out.println("<input type='hidden' name='postId' value='" + request.getParameter("postId") + "'>");
        %>
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
    <form method="get" action="delete.do">
        <p>*비밀번호 확인*</p>

        <input type="inputPassword" name="inputPassword" placeholder="비밀번호를 입력하세요.">
        <%
            //postId
            out.println("<input type='hidden' name='postId' value='" + request.getParameter("postId") + "'>");

            PostVo postVo = (PostVo) request.getAttribute("postVo");
            String password = postVo.getPassword();
            //password
            out.println("<input type='hidden' name='password' value='" + password +"'>");
        %>
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
