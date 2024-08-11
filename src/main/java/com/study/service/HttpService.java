package com.study.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public interface HttpService {
    String doService(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
