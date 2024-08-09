package com.study.service;

import com.study.model.CommentDto;
import com.study.model.PostDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PostCommentService implements HttpService{
    private final PostDao postDao = new PostDao();

//    TODO: 비동기로 구현
    public String doService(HttpServletRequest request, HttpServletResponse response){
        String postId = request.getParameter("postId");
        String comment = request.getParameter("comment");
        CommentDto commentDto = new CommentDto();

        if(postId != null && !"".equals(postId)){
            commentDto.setPostId(Integer.parseInt(postId));
        }
        if(comment != null && !"".equals(comment)){
            commentDto.setContent(comment);
        }

        try {
            // TODO: 검증하기
            postDao.insertComment(commentDto);

            // 자동으로 값이 들어간 Id를 추출한다.
            int resultId= commentDto.getCommentId();

            log.info(resultId != 0 ? resultId + "번 댓글 추가" : "댓글 추가 실패");

        } catch (Exception e) {
            log.info("insert err: " + e.getMessage());
            e.printStackTrace();
        }

//        여기서 문제 발생 >> get메서드가 아니라 post메서드로 보내짐 원래 post여서 그런가봄 >> redirect로 해결
        return "redirect:read.do?postId="+postId;
    }
}
