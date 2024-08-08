package com.study.validate;

import com.study.model.PostDto;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class PostValidator {

    final int NAME_MAX_LENGTH = 5;
    final int NAME_MIN_LENGTH = 1;
    final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
    final int TITLE_CONTENT_MIN_LENGTH = 4;
    final int TITLE_MAX_LENGTH = 100;
    final int CONTENT_MAX_LENGTH = 2000;

//    값을 받아오지 않는 필드에 대한 유효성?
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
        try {
            if(name == null){
                throw new NullPointerException("이름을 입력해야 합니다.");
            }

            int nameLen = name.length();
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
            if(password == null){
                throw new NullPointerException("비밀번호를 입력해야 합니다.");
            }

            if(!password.matches(PASSWORD_REGEX)){
                throw new Exception("비밀번호는 4글자 이상, 16글자 미만인 영문/숫자/특수문자를 포함한 문자여야 합니다.");
            }

        } catch (Exception e){
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void titleValidate(String title){
        try {
            if(title == null){
                throw new NullPointerException("제목을 입력해야 합니다.");
            }

            int titleLen = title.length();
            if(titleLen < TITLE_CONTENT_MIN_LENGTH || titleLen > TITLE_MAX_LENGTH){
                throw new Exception("제목은 4글자 이상, 100글자 미만이어야 합니다.");
            }
        } catch (Exception e){
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void contentValidate(String content){
        try {
            if(content == null){
                throw new NullPointerException("내용을 입력해야 합니다.");
            }

            int contentLen = content.length();
            if(contentLen < TITLE_CONTENT_MIN_LENGTH || contentLen > CONTENT_MAX_LENGTH){
                throw new Exception("내용은 4글자 이상, 2000글자 미만이어야 합니다.");
            }
        } catch (Exception e){
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
