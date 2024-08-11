package com.study.model;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
@Builder
public class FileDto {

    int fileId;

    int postId;

    String fileOriginalName;

    String fileName;

    String filePath;

    long fileSize;
}
