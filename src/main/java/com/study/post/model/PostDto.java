package com.study.post.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class PostDto {

    int postId;

    int categoryId;

    String name;

    String password;

    String title;

    String content;

    int viewCount;

    LocalDateTime createdDate;

    LocalDateTime updatedDate;
}
