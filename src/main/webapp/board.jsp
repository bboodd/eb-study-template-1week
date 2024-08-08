<%@ page import="com.study.model.PostDto" %>
<%@ page import="com.study.model.PostVo" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>자유게시판</title>
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
            <div class="date">

            </div>

            <div class="category">
                <!-- 옵션 -->
                <%--        카테고리 테이블에서 출력              --%>
                <select>
                    <option value="1">JAVA</option>
                    <option value="2">JavaScript</option>
                    <option value="3">Database</option>
                    <option value="4">React</option>
                </select>
            </div>

            <div class="search">
                <!-- 검색창 -->
                <input type="text" placeholder="검색어를 입력해 주세요. (제목+작성자+내용)" id="searchKeyword">

                <!-- 검색 버튼 -->
                <button type="button" id="searchBtn">검색</button>
            </div>
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
            총 <span></span> 건
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
                            out.println("<td>" + postVo.getUpdateDate() + "</td>");
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
