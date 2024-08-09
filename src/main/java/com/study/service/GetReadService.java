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
        String postId = request.getParameter("postId");
        PostVo postVo = new PostVo();
        int viewUpdateResult = 0;
        List<CommentVo> commentList = new ArrayList<>();
        String view = "dispatch:post.jsp";

        try {
            if(postId != null && !"".equals(postId)){
                viewUpdateResult = postDao.updatePostViewCount(Integer.parseInt(postId));
                postVo = postDao.selectPostById(Integer.parseInt(postId));
                commentList = postDao.selectCommentList(Integer.parseInt(postId));
            }

            if(postVo.getState() == 0){ //삭제된 페이지를 불러올 경우
                view = "redirect:views/deletePost.jsp";
            } else{
                request.setAttribute("postVo", postVo);
                request.setAttribute("commentList", commentList);
            }

            //불러온 댓글 리스트 체크
            //댓글이 없을수도있음
            log.info(commentList.size() != 0 ? "댓글 리스트 불러오기 성공" : "댓글 리스트 불러오기 실패");

            //불러온 게시글의 id체크
            log.info(postVo.getPostId() != 0 ? "게시글 불러오기 성공" : "게시글 불러오기 실패");

            //조회수 증가시 1 아닐시 0
            log.info(viewUpdateResult != 0 ? "조회수 1 증가" : "조회수 증가 실패");

        }catch (Exception e) {
            log.info("select err: " + e.getMessage());
            e.printStackTrace();
        }

        return view;
    }
}
