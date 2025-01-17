package com.study.validate;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class InputPostValidator {
    // TODO: 먹통됨 왜그러지?? --> !"".equals(something) < 안됨
    boolean flag = true;
    String numberRegex = "[+-]?\\d*(\\.\\d+)?";

    public boolean validate(String categoryId, String name, String password, String title,String content, String checkPassword) {
        categoryIdValidate(categoryId);
        nameValidate(name);
        passwordValidate(password, checkPassword);
        titleValidate(title);
        contentValidate(content);
        return flag;
    }

    private void categoryIdValidate(String categoryId){
        try {
            if(categoryId == null || categoryId.isBlank()){
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
            if(name == null || name.isBlank()){
                flag = false;
                throw new IllegalArgumentException("이름을 입력해야 합니다.");
            }

        } catch (Exception e){
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void passwordValidate(String password, String checkPassword){
        try {
            if(password == null || password.isBlank()){
                flag = false;
                throw new IllegalArgumentException("비밀번호를 입력해야 합니다.");
            }
            if(!password.equals(checkPassword)){
                flag = false;
                throw new IllegalArgumentException("두 비밀번호가 다릅니다.");
            }

        } catch (Exception e){
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void titleValidate(String title){
        try {
            if(title == null || title.isBlank()){
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
            if(content == null || content.isBlank()){
                flag = false;
                throw new IllegalArgumentException("내용을 입력해야 합니다.");
            }

        } catch (Exception e){
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
