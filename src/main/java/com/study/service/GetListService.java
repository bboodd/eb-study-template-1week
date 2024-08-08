package com.study.service;

import com.study.model.PostDao;
import com.study.model.PostVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class GetListService implements HttpService {
    private final PostDao postDao = new PostDao();

    public String doService(HttpServletRequest request, HttpServletResponse response){

        log.trace("doService(req, res) invoked");

        try {

            List<PostVo> postList = postDao.selectPostList();

            request.setAttribute("postList", postList);

            if(postList != null) {
                log.info("게시글 목록 불러오기 성공");
            } else {
                log.info("게시글 목록 불러오기 실패");
            }
        } catch (Exception e) {
            log.info("select err: " + e.getMessage());
            e.printStackTrace();
        }

        return "board.jsp";
    }
}
