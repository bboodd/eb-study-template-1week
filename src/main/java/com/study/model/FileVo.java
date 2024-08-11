package com.study.model;

import lombok.*;

//db용 객체
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
@Value
public class FileVo {

    private int fileId;

    private int postId;

    private String fileOriginalName;

    private String fileName;

    private String filePath;

    private long fileSize;
}
