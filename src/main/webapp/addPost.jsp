<%@ page import="com.study.model.PostDto" %>
<%@ page import="com.study.model.CategoryVo" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: gimhaghyeon
  Date: 8/6/24
  Time: 09:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="postDao" class="com.study.model.PostDao"/>
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

<form method="post" action="insert.do">
    <label>Category:
        <select type='number' name='categoryId'>
            <%
                List<CategoryVo> categoryList = postDao.selectCategoryList();
                if(categoryList == null || categoryList.size() == 0) {
            %>
            <option>카테고리를 찾을 수 없음</option>
            <%  } else {
                    for(CategoryVo vo : categoryList){
                        out.println("<option value='" + vo.getCategoryId() + "'>" + vo.getCategoryName() + "</option>");
                    }
                }
            %>
        </select>
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
