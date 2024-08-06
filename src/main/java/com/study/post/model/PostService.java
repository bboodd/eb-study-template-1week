package com.study.post.model;

import com.study.post.validate.PostValidator;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class PostService {

    final int NAME_MAX_LENGTH = 5;
    final int NAME_MIN_LENGTH = 1;
    final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
    final int TITLE_CONTENT_MIN_LENGTH = 4;
    final int TITLE_MAX_LENGTH = 100;
    final int CONTENT_MAX_LENGTH = 2000;

    private final PostDao postDao = new PostDao();

    private final PostValidator postValidator = new PostValidator();

    public int insertPostAndValidate(PostDto postDto) throws Exception{
        postValidator.validate(postDto);
        int result = postDao.insertPost(postDto);
        return result;
    }


}
