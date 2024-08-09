package com.study.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

//db용 객체
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Value
public class CommentVo {

    private int commentId;

    private int postId;

    private String content;

    private String createDate;
}
