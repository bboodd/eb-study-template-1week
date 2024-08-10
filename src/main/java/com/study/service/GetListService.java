package com.study.service;

import com.study.model.PostDao;
import com.study.model.PostVo;
import com.study.model.SearchDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class GetListService implements HttpService {
    private final PostDao postDao = new PostDao();

    public String doService(HttpServletRequest request, HttpServletResponse response){
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String categoryId = request.getParameter("categoryId");
        String keyword = request.getParameter("keyword");

        SearchDto searchDto = new SearchDto();

        // TODO:new dto를 하면 기본값은 null인데 null 체크를 해야하나? >> builder 쓰면 될듯
        if(startDate != null){searchDto.setStartDate(startDate);}
        if(endDate != null){searchDto.setEndDate(endDate);}
        if(categoryId != null){searchDto.setCategoryId(Integer.parseInt(categoryId));}
        if(keyword != null){searchDto.setKeyword(keyword);}

        log.info("startDate: " + startDate + " endDate: " + endDate + "categoryId: " + categoryId + " keyword: " + keyword);
        log.info(String.valueOf(searchDto));

        try {
            List<PostVo> postList = postDao.selectPostList(searchDto);

            request.setAttribute("postList", postList);
            request.setAttribute("searchDto", searchDto);

            log.info(postList != null ? "게시글 목록 불러오기 성공" : "게시글 목록 불러오기 실패");

        } catch (Exception e) {
            log.info("select err: " + e.getMessage());
            e.printStackTrace();
        }

        return "dispatch:WEB-INF/jsp/board.jsp";
    }
}
