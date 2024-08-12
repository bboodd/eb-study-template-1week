package com.study.model;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
@Builder
public class PostDto {

    int postId;

    int categoryId;

    String categoryName;

    String name;

    String password;

    String title;

    String content;

    int viewCount;

    String createDate;

    String updateDate;

    int state;

    int fileCount;
}
