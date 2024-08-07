package com.study.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ListService implements HttpService {
    public String doService(HttpServletRequest request, HttpServletResponse response){

        return "board.jsp";
    }
    //TODO: 요청 처리
    //후속 조치에 필요한 문자열 리턴 "dispatch:some.jsp" or "redirect:/some/do" 등등
}
