<%@ page import="com.study.model.PostVo" %>
<%@ page import="com.study.model.CommentVo" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: gimhaghyeon
  Date: 8/8/24
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>
    <%= "게시판 - 보기" %>
</h1>

<%
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
            out.println("<input type='hidden' name='postId' value='" + request.getParameter("postId") + "'>");
        %>
    </form>
</div>

<div>
    <button onclick="location.href='list.do'">목록</button>

    <button onclick="location.href='modify.do'">수정</button>

    <button onclick="location.href='delete.do'">삭제</button>
</div>

</body>
</html>
