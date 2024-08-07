package com.study.controller;

import com.study.service.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;

@Slf4j
@WebServlet("*.do")
public class BoardControllerServlet extends HttpServlet {

    HashMap<String, HttpService> commandMap = new HashMap<>();

    public void init(){
        commandMap.put("GET:list", new ListService() );
        commandMap.put("GET:read", new WriteService() );
        commandMap.put("GET:insert", new GetInsertService() );
        commandMap.put("POST:insert", new PostInsertService() );
        commandMap.put("GET:update", new WriteService() );
        commandMap.put("PUT:update", new WriteService() );
        commandMap.put("DELETE:delete", new WriteService() );
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpService targetService = findTargetService(request);
        String view = targetService.doService(request, response);
        //TODO: view 문자열 분석해서 dispatch 혹은 redirect

        if(true){
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(view);
            requestDispatcher.forward(request, response);
        } else{
            response.sendRedirect(view);
        }
    }

    private HttpService findTargetService(HttpServletRequest  request){
        //TODO: method 및 requestUri 를 분석해서 담당서비스를 찾아서 리턴

        String method = request.getMethod();
        String requestUri = request.getRequestURI();

        log.info(requestUri);
        log.info(method);

        HttpService service =

        switch (method) {
            case "GET" -> switch (requestUri) {
                    case "/list.do" -> commandMap.get("GET:list");
                    case "/read.do" -> commandMap.get("GET:read");
                    case "/insert.do" -> commandMap.get("GET:insert");
                    case "/update.do" -> commandMap.get("GET:update");
                default -> throw new IllegalArgumentException("잘못된 uri " + requestUri);
                };
            case "POST" ->
                switch (requestUri) {
                    case "/insert.do"->
                        commandMap.get("POST:insert");
                    default -> throw new IllegalArgumentException("잘못된 uri " + requestUri);
                };
            case "PUT"->
                switch (requestUri) {
                    case "/update.do"->
                        commandMap.get("PUT:update");
                    default -> throw new IllegalArgumentException("잘못된 uri " + requestUri);
                };
            case "DELETE"->
                switch (requestUri) {
                    case "/delete.do"->
                        commandMap.get("DELETE:delete");
                    default -> throw new IllegalArgumentException("잘못된 uri " + requestUri);
                };
            default -> throw new IllegalArgumentException("잘못된 메서드 " + method);
        };

        return service;
    }
}