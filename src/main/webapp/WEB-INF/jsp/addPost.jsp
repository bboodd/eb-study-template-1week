<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
    <h2> add post</h2>
</div>

<form method="post" action="insert.do">
    <label>Category:
        <select type='number' name='categoryId'>
            <c:if test="${categoryList.size() != 0}">
                <c:forEach var="category" items="${categoryList}">
                    <option value="${category.categoryId}">${category.categoryName}</option>
                </c:forEach>
            </c:if>
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
