package com.study.post.controller;

import com.study.post.model.Post;
import com.study.post.model.PostDao;
import com.study.post.model.PostDto;
import com.study.post.model.PostService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet(name = "addPost", value = "/post/add")
public class addPost extends HttpServlet {

    private final PostService postService = new PostService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/addPost.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        PostDto postDto = new PostDto();
        postDto.setCategoryId(categoryId);
        postDto.setName(name);
        postDto.setPassword(password);
        postDto.setTitle(title);
        postDto.setContent(content);

        //게시물 등록
        try {
            int result = postService.insertPostAndValidate(postDto);
            System.out.println(result + "번 게시글 추가");
        } catch (Exception e) {
            System.out.println("Insert err" + e);
        }

        request.setAttribute("post", postDto);

        doGet(request, response);
    }
}
