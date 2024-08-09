package com.study.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentDto {

    int commentId;

    int postId;

    String content;

    String createDate;
}
