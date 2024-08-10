package com.study.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UnknownService implements HttpService{
    public String doService(HttpServletRequest request, HttpServletResponse response){
        //잘못된 서비스 호출 페이지
        String view = "";
        // TODO: 404 호출해야함
        if(request.getQueryString() != null || !request.getQueryString().equals("")){
            view = "dispatch:WEB-INF/jsp/deletePost.jsp";
        } else{
            view = "dispatch:WEB-INF/jsp/wrongService.jsp";
        }
        return view;
    }
}
