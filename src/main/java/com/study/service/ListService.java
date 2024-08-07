package com.study.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ListService implements HttpService {
    public String doService(HttpServletRequest request, HttpServletResponse response){

        log.trace("doService(req, res) invoked");

        return "board.jsp";
    }
    //TODO: 요청 처리
    //후속 조치에 필요한 문자열 리턴 "dispatch:some.jsp" or "redirect:/some/do" 등등
}
