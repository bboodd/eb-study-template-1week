package com.study.post.model;

import com.study.post.validate.PostValidator;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class PostService {

    private final PostDao postDao = new PostDao();

    private final PostValidator postValidator = new PostValidator();

    public int insertPostAndValidate(PostDto postDto) throws Exception{
        try {
            postValidator.validate(postDto);
            postDao.insertPost(postDto);
        } catch (Exception e) {
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();
        } finally {
            int postId = postDto.getPostId();
            return postId;
        }
    }


}
