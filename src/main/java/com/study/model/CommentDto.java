package com.study.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
@Builder
public class CommentDto {

    int commentId;

    int postId;

    String content;

    String createDate;
}
