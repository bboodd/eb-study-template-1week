package com.study.model;

import lombok.*;

//db용 객체
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
@Value
public class PostVo {

    private int postId;

    private int categoryId;

    private String categoryName;

    private String name;

    private String password;

    private String title;

    private String content;

    private int viewCount;

    private String createDate;

    private String updateDate;

    private int state;

    private int fileCount;
}
