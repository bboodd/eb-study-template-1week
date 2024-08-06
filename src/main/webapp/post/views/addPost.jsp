<%@ page import="com.study.post.model.PostDto" %><%--
  Created by IntelliJ IDEA.
  User: gimhaghyeon
  Date: 8/6/24
  Time: 09:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div>
    <h1> super app </h1>
</div>

<div>
    <%
        if(request.getAttribute("post") != null){
            PostDto p = (PostDto)request.getAttribute("post");
            out.println("<p>post '" + p + "' added!</p>");
            out.println("<p>title '" + p.getTitle() + "'</p>");
        }
    %>
</div>

<div>
    <h2> add post</h2>
</div>

<form method="post">
    <label>Category:
        <input type="number" name="categoryId" value=1 readonly> <br />
    </label>

    <label>Name:
        <input type="text" name="name"><br />
    </label>

    <label>Password:
        <input type="password" name="password"><br />
    </label>

    <label>Title:
        <input type="text" name="title"><br />
    </label>

    <label>Content:
        <input type="text" name="content"><br />
    </label>
    <button type="submit">Submit</button>
</form>

</body>
</html>
