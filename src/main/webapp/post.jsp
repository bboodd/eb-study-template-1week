<%@ page import="com.study.model.PostVo" %><%--
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
    <%
        if(request.getAttribute("postVo") != null){
            PostVo postVo = (PostVo) request.getAttribute("postVo");
            out.println(postVo.getTitle());
            out.println(postVo.getCreateDate());
            out.println(postVo.getUpdateDate());
        }
    %>
</h1>

</body>
</html>
