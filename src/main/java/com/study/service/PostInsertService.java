package com.study.service;

import com.study.model.PostDao;
import com.study.model.PostDto;
import com.study.validate.InputPostValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PostInsertService implements HttpService{
    private final PostDao postDao = new PostDao();
    private final InputPostValidator inputPostValidator = new InputPostValidator();

    public String doService(HttpServletRequest request, HttpServletResponse response){
        String categoryId = request.getParameter("categoryId");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        String view = "redirect:addPost.jsp";

        try {
            inputPostValidator.validate(categoryId, name, password, title, content); //화면에서 받아온 값 null, 빈값 체크
            PostDto postDto = PostDto.builder()
                    .categoryId(Integer.parseInt(categoryId))
                    .name(name)
                    .password(password)
                    .title(title)
                    .content(content)
                    .build();

            //실패시 0 반환
            int result = postDao.insertPost(postDto);

            log.info(result != 0 ? "게시글 추가" : "게시글 추가 실패");

            if(result != 0) view = "list.do";

        } catch (Exception e) {
            log.info("insert err: " + e.getMessage());
            e.printStackTrace();
        }

        return view;
    }
}
