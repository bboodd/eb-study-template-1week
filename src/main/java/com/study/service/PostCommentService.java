package com.study.service;

import com.study.model.CommentDto;
import com.study.model.CommentVo;
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
        // TODO: 지금 빈 값도 들어감 유효성 검증 해야함

        try {
            //dto to vo
            CommentDto commentDto = CommentDto.builder()
                    .postId(Integer.parseInt(postId))
                    .content(comment)
                    .build();

            //실패시 0 반환
            int result = postDao.insertComment(commentDto);

            log.info(result != 0 ? "댓글 추가" : "댓글 추가 실패");

        } catch (Exception e) {
            log.info("insert err: " + e.getMessage());
            e.printStackTrace();
        }

//        여기서 문제 발생 >> get메서드가 아니라 post메서드로 보내짐 원래 post여서 그런가봄 >> redirect로 해결
        return "redirect:read.do?postId="+postId;
    }
}
