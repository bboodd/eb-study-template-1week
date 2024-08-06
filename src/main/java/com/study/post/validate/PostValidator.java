package com.study.post.validate;

import com.study.post.model.PostDto;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class PostValidator {

    final int NAME_MAX_LENGTH = 5;
    final int NAME_MIN_LENGTH = 1;
    final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
    final int TITLE_CONTENT_MIN_LENGTH = 4;
    final int TITLE_MAX_LENGTH = 100;
    final int CONTENT_MAX_LENGTH = 2000;

    public void validate(PostDto postDto){
        idValidate(postDto.getPostId());
        categoryValidate(postDto.getCategoryId());
        nameValidate(postDto.getName());
        passwordValidate(postDto.getPassword());
        titleValidate(postDto.getTitle());
        contentValidate(postDto.getContent());
    }

    private void idValidate(int postId){
        try {
            if(postId != 0){
                throw new Exception("id값은 들어갈 수 없습니다.");
            }
        } catch (Exception e){
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void categoryValidate(int categoryId){
        try {
            if(categoryId == 0){
                throw new Exception("카테고리 값은 필수 입니다.");
            }
        } catch (Exception e){
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void nameValidate(String name){
        int nameLen = name.length();
        try {
            if(nameLen < NAME_MIN_LENGTH || nameLen > NAME_MAX_LENGTH){
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
                throw new Exception("비밀번호는 4글자 이상, 16글자 미만인 영문/숫자/특수문자를 포함한 문자여야 합니다.");
            }

        } catch (Exception e){
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void titleValidate(String title){
        int titleLen = title.length();
        try {
            if(titleLen < TITLE_CONTENT_MIN_LENGTH || titleLen > TITLE_MAX_LENGTH){
                throw new Exception("제목은 4글자 이상, 100글자 미만이어야 합니다.");
            }
        } catch (Exception e){
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void contentValidate(String content){
        int contentLen = content.length();
        try {
            if(contentLen < TITLE_CONTENT_MIN_LENGTH || contentLen > CONTENT_MAX_LENGTH){
                throw new Exception("내용은 4글자 이상, 2000글자 미만이어야 합니다.");
            }
        } catch (Exception e){
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
