package com.study.service;

import com.study.model.CategoryDto;
import com.study.model.CategoryVo;
import com.study.model.PostDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class GetInsertService implements HttpService{
    private final PostDao postDao = new PostDao();

    public String doService(HttpServletRequest request, HttpServletResponse response){

        try {
            // TODO: 화면에 뿌리기 전에 유효성 검증?
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

        } catch (Exception e) {
            log.info("select err: " + e.getMessage());
            e.printStackTrace();
        }

        return "dispatch:WEB-INF/jsp/addPost.jsp";
    }
}
