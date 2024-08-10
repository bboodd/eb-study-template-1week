package com.study.service;

import com.study.model.*;
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
        List<CommentVo> commentVoList = new ArrayList<>();
        String view = "dispatch:WEB-INF/jsp/post.jsp";

        try {
            if(postId != null && !"".equals(postId)){
                viewUpdateResult = postDao.updatePostViewCount(Integer.parseInt(postId));
                postVo = postDao.selectPostById(Integer.parseInt(postId));
                commentVoList = postDao.selectCommentList(Integer.parseInt(postId));
            }

            //post vo객체를 dto로 변환
            PostDto postDto = PostDto.builder()
                    .postId(postVo.getPostId())
                    .categoryName(postVo.getCategoryName())
                    .name(postVo.getName())
                    .password(postVo.getPassword())
                    .title(postVo.getTitle())
                    .viewCount(postVo.getViewCount())
                    .createDate(postVo.getCreateDate())
                    .updateDate(postVo.getUpdateDate())
                    .state(postVo.getState())
                    .build();

            //수정일자 처리
            if(postVo.getCreateDate().equals(postVo.getUpdateDate())){
                postDto.setUpdateDate("-");
            }

            //댓글 vo객체 리스트를 dto로 변환
            List<CommentDto> commentDtoList = new ArrayList<>();
            for(CommentVo commentVo : commentVoList){
                CommentDto commentDto = CommentDto.builder()
                        .content(commentVo.getContent())
                        .createDate(commentVo.getCreateDate())
                        .build();

                commentDtoList.add(commentDto);
            }

            if(postDto.getState() == 0){ //삭제된 페이지를 불러올 경우
                // TODO: 404 호출해야함
                view = "redirect:unknown.do?postId="+postDto.getPostId();
            } else{
                request.setAttribute("postDto", postDto);
                request.setAttribute("commentList", commentDtoList);
            }

            //불러온 댓글 리스트 체크
            //댓글이 없을수도있음
            log.info(commentDtoList.size() != 0 ? "댓글 리스트 불러오기 성공" : "댓글 리스트 불러오기 실패");

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
