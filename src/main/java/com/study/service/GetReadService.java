package com.study.service;

import com.study.model.CommentVo;
import com.study.model.PostDao;
import com.study.model.PostVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class GetReadService implements HttpService{
    private final PostDao postDao = new PostDao();

    public String doService(HttpServletRequest request, HttpServletResponse response){

        log.trace("doService(req, res) invoked");


        String postId = request.getParameter("postId");

        PostVo postVo = new PostVo();
        int viewUpdateResult = 0;
        List<CommentVo> commentList = new ArrayList<>();

        String view;


        try {

            if(postId != null && !"".equals(postId)){
                viewUpdateResult = postDao.updatePostViewCount(Integer.parseInt(postId));
                postVo = postDao.selectPostById(Integer.parseInt(postId));
                commentList = postDao.selectCommentList(Integer.parseInt(postId));
            }

            if(postVo.getState() == 0){ //삭제된 페이지를 불러올 경우
                view = "views/deletePage.jsp";
            } else{
                view = "post.jsp";

                request.setAttribute("postVo", postVo);
                request.setAttribute("commentList", commentList);
            }

            //불러온 댓글 리스트 체크
            //댓글이 없을수도있음
            if(commentList != null){
                log.info("댓글 리스트 불러오기 성공");
            } else{
                log.info("댓글 리스트 불러오기 실패");
            }

            //불러온 게시글의 id체크
            if(postVo.getPostId() != 0) {
                log.info("게시글 불러오기 성공");
            } else {
                log.info("게시글 불러오기 실패");
            }

            //조회수 증가시 1 아닐시 0
            if(viewUpdateResult != 0){
                log.info("조회수 1 증가");
            } else {
                log.info("조회수 증가 실패");
            }

        }catch (Exception e) {
            log.info("select err: " + e.getMessage());
            e.printStackTrace();
        }

        return "dispatch:post.jsp";
    }
}
