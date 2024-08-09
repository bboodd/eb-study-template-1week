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
        commandMap.put("GET:list", new GetListService() );
        commandMap.put("GET:read", new GetReadService() );
        commandMap.put("GET:insert", new GetInsertService() );
        commandMap.put("POST:insert", new PostInsertService() );
        commandMap.put("GET:update", new WriteService() );
        commandMap.put("PUT:update", new WriteService() );
        commandMap.put("DELETE:delete", new WriteService() );
        commandMap.put("POST:search", new PostSearchService() );
        commandMap.put("POST:comment", new PostCommentService() );
        commandMap.put("Unknown", new UnknownService() );
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.trace("service(req, res) invoked.");

        HttpService targetService = findTargetService(request);

        //비즈니스 로직 수행 결과 데이터를 공유데이터영역인 "Application Scope"(accessed by ServletContext)에 바인딩하는 방법도 있음.
        String view = targetService.doService(request, response);

        //TODO: view 문자열 분석해서 dispatch 혹은 redirect

        String end = view.substring(view.length()-4, view.length());

        log.info("넘어온 view 문자열은: {}", end);

        if(end.equals(".jsp")){
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
        String contextPath = request.getContextPath();
        String requestUrl = request.getRequestURL().toString();
        String queryString = request.getQueryString();

        log.info("\t + 0. contextPath : {}", contextPath); // /maven01 or /myapp

        // + contextPath가 /(루트)일 경우 빈 문자열이라 substring 필요없음

        log.info("\t + 1. requestUri : {}", requestUri); // /*.do
        log.info("\t + 2. requestUrl : {}", requestUrl); // http://localhost:8080/*.do
        log.info("\t + 3. method : {}", method); // GET

        // substring으로 uri에서 컨텍스트 패스를 제외한 문자열을 돌려준다.
        // String command = requestUri.substring(contextPath.length());

        String command = requestUri;
        log.info("\t + 4. command : {}", command); // /*.do

        // command는 contextPath가 그냥 루트일 경우 URI와 동일하게 나온다.

        log.info("\t + 5. queryString : {}", queryString); //

        HttpService service =

        switch (method) {
            case "GET" -> switch (requestUri) {
                    case "/list.do" -> commandMap.get("GET:list");
                    case "/read.do" -> commandMap.get("GET:read");
                    case "/insert.do" -> commandMap.get("GET:insert");
                    case "/update.do" -> commandMap.get("GET:update");
                    default -> commandMap.get("Unknown"); //오류페이지 오출
                };
            case "POST" ->
                switch (requestUri) {
                    case "/insert.do"-> commandMap.get("POST:insert");
                    case "/search.do" -> commandMap.get("POST:search");
                    case "/comment.do" -> commandMap.get("POST:comment");
                    default -> commandMap.get("Unknown"); //오류페이지 오출
                };
            case "PUT"->
                switch (requestUri) {
                    case "/update.do"-> commandMap.get("PUT:update");
                    default -> commandMap.get("Unknown"); //오류페이지 오출
                };
            case "DELETE"->
                switch (requestUri) {
                    case "/delete.do"-> commandMap.get("DELETE:delete");
                    default -> commandMap.get("Unknown"); //오류페이지 오출
                };
            default -> commandMap.get("Unknown"); //오류페이지 오출
        };

        return service;
    }
}
