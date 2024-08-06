package com.study.post.controller;

import com.study.post.model.Post;
import com.study.post.model.PostDao;
import com.study.post.model.PostDto;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "addPost", value = "/post/add")
public class addPost extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/addPost.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PostDao postDao = new PostDao();

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


        System.out.println(postDto.toString());

        try {
            int result = postDao.insertPost(postDto);
            System.out.println(result + "번 게시글 추가");
        } catch (Exception e) {
            System.out.println("Insert err" + e);
        }

        request.setAttribute("post", postDto);

        doGet(request, response);
    }
}
