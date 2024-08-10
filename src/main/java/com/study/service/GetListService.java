package com.study.service;

import com.study.model.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class GetListService implements HttpService {
    private final PostDao postDao = new PostDao();

    public String doService(HttpServletRequest request, HttpServletResponse response){
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String categoryId = request.getParameter("categoryId");
        String keyword = request.getParameter("keyword");

        // TODO:new dto를 하면 기본값은 null인데 null 체크를 해야하나? >> builder 쓰면 될듯

        log.info("startDate: " + startDate + " endDate: " + endDate + "categoryId: " + categoryId + " keyword: " + keyword);

        try {
            SearchDto searchDto = new SearchDto();

            if(categoryId == null){
                //인덱스페이지 요청
                SearchDto firstSearchDto = SearchDto.builder()
                        .startDate("")
                        .endDate("")
                        .categoryId(0)
                        .keyword("")
                        .build();
                searchDto = firstSearchDto;
            } else{
                // TODO: 검증해야할듯
                SearchDto inputSearchDto = SearchDto.builder()
                        .startDate(startDate)
                        .endDate(endDate)
                        .categoryId(Integer.parseInt(categoryId))
                        .keyword(keyword)
                        .build();
                searchDto = inputSearchDto;
            }

            List<PostVo> postVoList = postDao.selectPostList(searchDto);

            //vo리스트를 dto리스트로 변환
            List<PostDto> postDtoList = new ArrayList<>();
            for(PostVo postVo : postVoList){
                PostDto postDto = PostDto.builder()
                        .postId(postVo.getPostId())
                        .categoryName(postVo.getCategoryName())
                        .name(postVo.getName())
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

                postDtoList.add(postDto);
            }

            //카테고리 목록 불러오기
            List<CategoryVo> categoryVoList = postDao.selectCategoryList();

            //뽑은 vo리스트를 dto리스트로 다시 변환
            List<CategoryDto> categoryDtoList = new ArrayList<>();
            for(CategoryVo categoryVo : categoryVoList){
                CategoryDto categoryDto = CategoryDto.builder()
                        .categoryId(categoryVo.getCategoryId())
                        .categoryName(categoryVo.getCategoryName())
                        .build();

                categoryDtoList.add(categoryDto);
            }

            request.setAttribute("categoryList", categoryDtoList);
            request.setAttribute("postList", postDtoList);
            request.setAttribute("searchDto", searchDto);

            log.info(postDtoList != null ? "게시글 목록 불러오기 성공" : "게시글 목록 불러오기 실패");

        } catch (Exception e) {
            log.info("select err: " + e.getMessage());
            e.printStackTrace();
        }

        return "dispatch:WEB-INF/jsp/board.jsp";
    }
}
