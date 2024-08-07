package com.study.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetInsertService implements HttpService{
    public String doService(HttpServletRequest request, HttpServletResponse response){

        log.trace("doService(req, res) invoked");

        return "addPost.jsp";
    }
}
