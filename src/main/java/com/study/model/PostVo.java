package com.study.model;

import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;

//db용 객체
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Value
//@Value
public class PostVo {

    private int postId;

    private String category;

    private String name;

    private String password;

    private String title;

    private String content;

    private int viewCount;

    private String createDate;

    private String updateDate;
}
