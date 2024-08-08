<%@ page import="com.study.model.PostDto" %>
<%@ page import="com.study.model.PostVo" %>
<%@ page import="java.util.List" %>
<%@ page import="com.study.model.SearchDto" %>
<%@ page import="com.study.model.CategoryVo" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="postDao" class="com.study.model.PostDao"/>
<!DOCTYPE html>
<html>
<head>
    <title>자유게시판</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</head>
<body>
<%



%>
<h1>
    <%= "자유게시판 - 목록" %>
</h1>
<br/>
<section id="post-board">
    <div class="inner">

        <div class="box1">
            <form method="post" action="search.do">
                <div class="date">
                    <label>등록일
                        <input type="date" name="startDate" id="startDate">
                        ~
                        <input type="date" name="endDate" id="endDate">

                    </label>

                </div>

                <div class="category">
                    <!-- 옵션 -->
                    <%--        카테고리 테이블에서 출력              --%>
                    <select name="categoryId" id="categoryId">
                        <option value="0">전체 카테고리</option>
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
                </div>

                <div class="search">
                    <!-- 검색창 -->
                    <input type="text" placeholder="검색어를 입력해 주세요. (제목+작성자+내용)" name="keyword" id="keyword">

                    <!-- 검색 버튼 -->
                    <button type="submit">검색</button>
                </div>
                <script>
                <%
                    if(request.getAttribute("searchDto") != null){
                        SearchDto searchDto = (SearchDto) request.getAttribute("searchDto");

                        out.println("$('#startDate').val(" + searchDto.getStartDate() + ");");
                        out.println("$('#endtDate').val(" + searchDto.getEndDate() + ");");
                        out.println("$('#categoryId').val(" + searchDto.getCategoryId() + ");");
                        out.println("$('#keyword').val(" + searchDto.getKeyword() + ");");
                    }
                %>
                </script>
            </form>
        </div>

        <%--가져온 list.size         --%>
        <div class="box2">
            <%
                if(request.getAttribute("postList") != null){
                    List<PostVo> list = (List<PostVo>)request.getAttribute("postList");
                    out.println("<p>총 " + list.size() + "건</p>");
                } else{
                    out.println("<p>총 0건 </p>");
                }
            %>
        </div>

        <div class="box3">

            <table class="post-list">
                <thead>
                <tr>
                    <th scope="col">카테고리</th>
                    <th scope="col">제목</th>
                    <th scope="col">작성자</th>
                    <th scope="col">조회수</th>
                    <th scope="col">등록 일시</th>
                    <th scope="col">수정 일시</th>
                </tr>
                </thead>
                <tbody>
                <%--            post list 출력         --%>
                <%
                    if(request.getAttribute("postList") != null){
                        for(PostVo postVo : (List<PostVo>)request.getAttribute("postList")){
                            out.println("<tr>");
                            out.println("<td>" + postVo.getCategory() + "</td>");
                            out.println("<td>" + postVo.getTitle() + "</td>");
                            out.println("<td>" + postVo.getName() + "</td>");
                            out.println("<td>" + postVo.getViewCount() + "</td>");
                            out.println("<td>" + postVo.getCreateDate() + "</td>");
                            if (postVo.getCreateDate().equals(postVo.getUpdateDate())){
                                out.println("<td>-</td>");
                            } else {
                                out.println("<td>" + postVo.getUpdateDate() + "</td>");
                            }
                            out.println("</tr>");
                        }
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</section>

<button onclick="location.href='insert.do'">등록</button>
</body>
</html>
