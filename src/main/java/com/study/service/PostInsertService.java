package com.study.service;

import com.study.model.PostDao;
import com.study.model.PostDto;
import com.study.validate.PostValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PostInsertService implements HttpService{
    private final PostDao postDao = new PostDao();
    private final PostValidator postValidator = new PostValidator();

    public String doService(HttpServletRequest request, HttpServletResponse response){

        String categoryId = request.getParameter("categoryId");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        PostDto postDto = new PostDto();

        if(categoryId != null && !"".equals(categoryId)) {postDto.setCategoryId(Integer.parseInt(categoryId));}
        if(name != null && !"".equals(name)) {postDto.setName(name);}
        if(password != null && !"".equals(password)) {postDto.setPassword(password);}
        if(title != null && !"".equals(title)) {postDto.setTitle(title);}
        if(content != null && !"".equals(content)) {postDto.setContent(content);}

        try {
            postValidator.validate(postDto);
            postDao.insertPost(postDto);

            int resultId= postDto.getPostId();
            if(resultId != 0) {
                log.info(resultId + "번 게시글 추가");
            } else {
                log.info("게시글 추가 실패");
            }
        } catch (Exception e) {
            log.info("insert err: " + e.getMessage());
            e.printStackTrace();
        }

        request.setAttribute("post", postDto);


        return "addPost.jsp";
    }
}
