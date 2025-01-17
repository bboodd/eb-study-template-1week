package com.study.service;

import com.study.model.PostDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.PrintWriter;

@Slf4j
public class DeleteService implements HttpService{
    private final PostDao postDao = new PostDao();

    public String doService(HttpServletRequest request, HttpServletResponse response){
        String postId = request.getParameter("postId");
        String inputPassword = request.getParameter("inputPassword");
        String password = request.getParameter("password");
        String view = "redirect:read.do?postId="+postId;

        // TODO:유효성 검증 해야함
        if(inputPassword != null && !inputPassword.isBlank() && password != null && !password.isBlank()){

            try {

                int deleteResult = 0;
                if(postId != null && !postId.isBlank() && password.equals(inputPassword)){
                    deleteResult = postDao.deletePost(Integer.parseInt(postId));
                }

                //삭제 성공시 1 아닐시 0
                if(deleteResult != 0){
                    log.info("삭제 성공");
                    view = "redirect:list.do";
                } else {
                    log.info("삭제 실패");
                }

            } catch (Exception e) {
                log.info("update err: " + e.getMessage());
                e.printStackTrace();
            }
        }

        return view;
    }
}
