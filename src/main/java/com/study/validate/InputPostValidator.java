package com.study.validate;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class InputPostValidator {

    public void validate(String categoryId, String name, String password, String title,String content) {
        categoryValidate(categoryId);
        nameValidate(name);
        passwordValidate(password);
        titleValidate(title);
        contentValidate(content);
    }

    private void categoryValidate(String categoryId){
        try {
            if(categoryId == null || !"".equals(categoryId)){
                throw new IllegalArgumentException("카테고리 값은 필수 입니다.");
            }
        } catch (Exception e){
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void nameValidate(String name){
        try {
            if(name == null || !"".equals(name)){
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
                throw new IllegalArgumentException("내용을 입력해야 합니다.");
            }

        } catch (Exception e){
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
