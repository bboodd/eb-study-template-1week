package com.study.model;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Value
public class PostVo {

    private int postId;

    private int categoryId;

    private String name;

    private String password;

    private String title;

    private String content;

    private int viewCount;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}
