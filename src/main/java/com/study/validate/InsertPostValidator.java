package com.study.validate;

import com.study.model.PostDto;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class InsertPostValidator {

    final int NAME_MAX_LENGTH = 10;
    final int NAME_MIN_LENGTH = 1;
    final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
    final int TITLE_CONTENT_MIN_LENGTH = 4;
    final int TITLE_MAX_LENGTH = 100;
    final int CONTENT_MAX_LENGTH = 2000;

    boolean flag = true;

    public boolean validate(PostDto postDto) {
        categoryValidate(postDto.getCategoryId());
        nameValidate(postDto.getName());
        passwordValidate(postDto.getPassword());;
        titleValidate(postDto.getTitle());;
        contentValidate(postDto.getContent());;
        return flag;
    }

    private void categoryValidate(int categoryId){
        try {
            // TODO: db에 있는 카테고리 값이랑 비교?
            if(categoryId == 0){
                flag = false;
                throw new IllegalArgumentException("잘못된 카테고리 값");
            }
        } catch (Exception e){
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void nameValidate(String name){
        try {
            int nameLen = name.length();
            if(nameLen < NAME_MIN_LENGTH || nameLen > NAME_MAX_LENGTH){
                flag = false;
                throw new Exception("이름은 " + NAME_MIN_LENGTH + "글자 이상 " + NAME_MAX_LENGTH + "글자 미만이어야 합니다.");
            }
        } catch (Exception e){
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void passwordValidate(String password){
        try {
            if(!password.matches(PASSWORD_REGEX)){
                flag = false;
                throw new Exception("비밀번호는 4글자 이상, 16글자 미만인 영문/숫자/특수문자를 포함한 문자여야 합니다.");
            }

        } catch (Exception e){
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void titleValidate(String title){
        try {
            int titleLen = title.length();
            if(titleLen < TITLE_CONTENT_MIN_LENGTH || titleLen > TITLE_MAX_LENGTH){
                flag = false;
                throw new Exception("제목은 4글자 이상, 100글자 미만이어야 합니다.");
            }
        } catch (Exception e){
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void contentValidate(String content){
        try {
            int contentLen = content.length();
            if(contentLen < TITLE_CONTENT_MIN_LENGTH || contentLen > CONTENT_MAX_LENGTH){
                flag = false;
                throw new Exception("내용은 4글자 이상, 2000글자 미만이어야 합니다.");
            }
        } catch (Exception e){
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
