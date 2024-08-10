package com.study.validate;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class InputPostValidator {
    boolean flag = true;
    String numberRegex = "[+-]?\\d*(\\.\\d+)?";

    public boolean validate(String categoryId, String name, String password, String title,String content) {
        categoryIdValidate(categoryId);
        nameValidate(name);
        passwordValidate(password);
        titleValidate(title);
        contentValidate(content);
        return flag;
    }

    private void categoryIdValidate(String categoryId){
        try {
            if(categoryId == null || !"".equals(categoryId)){
                flag = false;
                throw new IllegalArgumentException("카테고리 값은 필수 입니다.");
            }
            if(!categoryId.matches(numberRegex)){
                flag = false;
                throw new IllegalArgumentException("카테고리 오류");
            }
        } catch (Exception e){
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void nameValidate(String name){
        try {
            if(name == null || !"".equals(name)){
                flag = false;
                throw new IllegalArgumentException("이름을 입력해야 합니다.");
            }

        } catch (Exception e){
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void passwordValidate(String password){
        try {
            if(password == null || !"".equals(password)){
                flag = false;
                throw new IllegalArgumentException("비밀번호를 입력해야 합니다.");
            }

        } catch (Exception e){
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void titleValidate(String title){
        try {
            if(title == null || !"".equals(title)){
                flag = false;
                throw new IllegalArgumentException("제목을 입력해야 합니다.");
            }

        } catch (Exception e){
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void contentValidate(String content){
        try {
            if(content == null || !"".equals(content)){
                flag = false;
                throw new IllegalArgumentException("내용을 입력해야 합니다.");
            }

        } catch (Exception e){
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
