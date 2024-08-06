package com.study.post.model;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class Post {

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
