package com.study.validate;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class InputSearchValidator {
    boolean flag = true;
    String numberRegex = "[+-]?\\d*(\\.\\d+)?";

    public boolean validate(String createDate, String endDate, String categoryId, String keyword) {
        createDateValidate(createDate);
        endDateValidate(endDate);
        categoryIdValidate(categoryId);
        keywordValidate(keyword);

        return flag;
    }

    private void createDateValidate(String createDate){
        try {
            if(createDate == null){
                flag = false;
                throw new IllegalArgumentException("시작 날짜 null");
            }

        } catch (Exception e){
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void endDateValidate(String endDate){
        try {
            if(endDate == null){
                flag = false;
                throw new IllegalArgumentException("끝 날짜 null");
            }

        } catch (Exception e){
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void categoryIdValidate(String categoryId){
        try {
            if(categoryId == null){
                flag = false;
                throw new IllegalArgumentException("카테고리 값 null");
            }
            if(!categoryId.matches(numberRegex)){
                flag = false;
                throw new IllegalArgumentException("잘못된 카테고리 값");
            }
        } catch (Exception e){
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void keywordValidate(String keyword){
        try {
            if(keyword == null){
                flag = false;
                throw new IllegalArgumentException("키워드 null");
            }

        } catch (Exception e){
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
