package com.study.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UnknownService implements HttpService{
    public String doService(HttpServletRequest request, HttpServletResponse response){
        //잘못된 서비스 호출 페이지
        return "redirect:/views/wrongService.jsp";
    }
}
