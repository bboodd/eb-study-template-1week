package com.study.service;

import com.study.model.PostDao;
import com.study.model.PostVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetReadService implements HttpService{
    private final PostDao postDao = new PostDao();

    public String doService(HttpServletRequest request, HttpServletResponse response){

        log.trace("doService(req, res) invoked");

        /**
         * 비즈니스 로직
         */

        try {

            String postId = request.getParameter("postId");

            PostVo postVo = new PostVo();
            int result = 0;

            if(postId != null && !"".equals(postId)){
                result = postDao.updatePostViewCount(Integer.parseInt(postId));
                postVo = postDao.selectPostById(Integer.parseInt(postId));
            }

            request.setAttribute("postVo", postVo);

            //불러온 게시글의 id체크
            if(postVo.getPostId() != 0) {
                log.info("게시글 불러오기 성공");
            } else {
                log.info("게시글 불러오기 실패");
            }

            //조회수 증가시 1 아닐시 0
            if(result != 0){
                log.info("조회수 1 증가");
            } else {
                log.info("조회수 증가 실패");
            }

        }catch (Exception e) {
            log.info("select err: " + e.getMessage());
            e.printStackTrace();
        }

        return "post.jsp";
    }
}
