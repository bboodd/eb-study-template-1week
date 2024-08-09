package com.study.service;

import com.study.model.CategoryVo;
import com.study.model.PostDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class GetInsertService implements HttpService{
    private final PostDao postDao = new PostDao();

    public String doService(HttpServletRequest request, HttpServletResponse response){
        return "redirect:addPost.jsp";
    }
}
