package com.study.controller;

import com.study.service.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;

@Slf4j
@WebServlet("*.do")
@MultipartConfig(
        location="/Users/gimhaghyeon/Desktop/upload",
        maxFileSize = 1* 1024 * 1024 * 20, 		// 최대 파일크기 : 20MB
        maxRequestSize = 1 * 1024 * 1024 * 20	// 한 요청 당 최대 크기 : 20MB
)
public class BoardControllerServlet extends HttpServlet {

    HashMap<String, HttpService> commandMap = new HashMap<>();

    public void init(){
        commandMap.put("GET/list.do", new GetListService() );
        commandMap.put("GET/read.do", new GetReadService() );
        commandMap.put("GET/insert.do", new GetInsertService() );
        commandMap.put("POST/insert.do", new PostInsertService() );
//        commandMap.put("GET/update.do", new WriteService() );
//        commandMap.put("PUT/update.do", new WriteService() );
        commandMap.put("POST/delete.do", new DeleteService() );
        commandMap.put("POST/comment.do", new PostCommentService() );
        commandMap.put("GET/unknown.do", new UnknownService() );
        commandMap.put("GET/download.do", new DownloadService() );
        commandMap.put("POST/unknown.do", new UnknownService() );
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpService targetService = findTargetService(request);

        //비즈니스 로직 수행 결과 데이터를 공유데이터영역인 "Application Scope"(accessed by ServletContext)에 바인딩하는 방법도 있음.
        String view = targetService.doService(request, response);

        String pathHead = view.substring(0, 9);
        String viewPath = view.substring(9, view.length());

        log.info("넘어온 view 문자열은: {}", view);
        log.info("flagWord: {}", pathHead);
        log.info("viewWord: {}", viewPath);

        if(pathHead.equals("dispatch:")){
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewPath);
            requestDispatcher.forward(request, response);
        } else{
            response.sendRedirect(viewPath);
        }
    }

    private HttpService findTargetService(HttpServletRequest  request){
        //method 및 uri분석
        String method = request.getMethod();
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String requestUrl = request.getRequestURL().toString();
        String queryString = request.getQueryString();

        /**
         * String command = requestUri.substring(contextPath.length());
         *   + command는 contextPath가 그냥 루트일 경우 URI와 동일하게 나온다.
         *   + substring으로 uri에서 컨텍스트 패스를 제외한 문자열을 돌려준다.
         *   + contextPath가 /(루트)일 경우 빈 문자열이라 substring 필요없음
         */

        log.info("\t + 0. contextPath : {}", contextPath); // /maven01 or /myapp
        log.info("\t + 1. requestUri : {}", requestUri); // /*.do
        log.info("\t + 2. requestUrl : {}", requestUrl); // http://localhost:8080/*.do
        log.info("\t + 3. method : {}", method); // GET
        log.info("\t + 4. queryString : {}", queryString); // ?...&..

        HttpService service = commandMap.get(method+requestUri);

        if(service == null){
            service = commandMap.get("Unknown");
        }

        return service;
    }
}
