<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

    <title>Title</title>
</head>
<body>
<div>
    <h1> super app </h1>
</div>

<div>
    <h2> add post</h2>
</div>

<form method="post" action="insert.do" enctype="multipart/form-data">
    <p>
        <label>Category:
            <select type='number' name='categoryId' id='categoryId'>
                <c:if test="${categoryList.size() != 0}">
                    <c:forEach var="category" items="${categoryList}">
                        <option value="${category.categoryId}">${category.categoryName}</option>
                    </c:forEach>
                </c:if>
            </select>
        </label>
    </p>

    <p>
        <label>Name:
            <input type="text" name="name" id="name"><br />
        </label>
    </p>

    <p>
        <label>Password:
            <input type="password" name="password" id="password"><br />
        </label>
    </p>
    <p>
        <label>CheckPassword:
            <input type="password" name="checkPassword" id="checkPassword"><bt />
        </label>
    </p>
    <p>
        <label>Title:
            <input type="text" name="title" id="title"><br />
        </label>
    </p>
    <p>
        <label>Content:
            <input type="text" name="content" id="content"><br />
        </label>
    </p>
    <p>
        <label>File:
            <div id="preview">

            </div>
        <p>
            <input type="file" name="file" id="file-input" multiple />
        </p>
        </label>
    </p>
    <div>
        <p>
            <button type="submit">Submit</button>
        </p>
    </div>
</form>

<c:if test="${postDto != null}">
    <script>
        <c:if test="${postDto.categoryId != 0}">$('#categoryId').val(${postDto.categoryId});</c:if>
        <c:if test="${postDto.name != null}">$('#name').val(${postDto.name});</c:if>
        <c:if test="${postDto.password != null}">$('#password').val(${postDto.password});</c:if>
        <c:if test="${postDto.title != null}">$('#title').val(${postDto.title});</c:if>
        <c:if test="${postDto.content != null}">$('#content').val(${postDto.content});</c:if>
    </script>
</c:if>
<%--파일 추가 제거 jquery--%>
<script>
    const fileInput = $('#file-input');
    const preview = $('#preview');

    //파일 추가
    $(document).on('change', fileInput, (e) => {
        const fileList = e.target.files;
        $(fileList).each((index, file) => {
            // console.log(file);
            const lastModified = file.lastModified;
            const name = file.name;

            const view = '<p id="' + lastModified + '">' + name +
                '<button data-index="' + lastModified +
                '" class = "file-remove">X</button></p>';

            preview.append(view);
        })
    })

    //파일 제거
    $(document).on('click', '.file-remove', (e) => {
        e.preventDefault();

        const removeTargetId = e.target.dataset.index;
        const removeTarget = document.getElementById(removeTargetId);
        const fileList = fileInput[0].files;
        const dataTransfer = new DataTransfer();

        $(fileList).filter((index, file) => file.lastModified != removeTargetId)
            .each((index, file) => {
                dataTransfer.items.add(file);
            })

        fileInput[0].files = dataTransfer.files;

        removeTarget.remove();
    })
</script>

</body>
</html>
