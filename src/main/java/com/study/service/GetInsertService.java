package com.study.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetInsertService implements HttpService{

    public String doService(HttpServletRequest request, HttpServletResponse response){

        return "addPost.jsp";
    }
}
