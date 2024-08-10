package com.study.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UnknownService implements HttpService{
    public String doService(HttpServletRequest request, HttpServletResponse response){
        //잘못된 서비스 호출 페이지
        // TODO: 404 호출해야함
        return "dispatch:WEB-INF/jsp/wrongService.jsp";
    }
}
