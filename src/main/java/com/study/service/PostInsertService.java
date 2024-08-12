package com.study.service;

import com.study.model.FileDto;
import com.study.model.PostDao;
import com.study.model.PostDto;
import com.study.validate.InputPostValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
public class PostInsertService implements HttpService {
    private final PostDao postDao = new PostDao();
    private final InputPostValidator inputPostValidator = new InputPostValidator();

    public String doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryId = request.getParameter("categoryId");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String checkPassword = request.getParameter("checkPassword");
        List<Part> partList = (List<Part>) request.getParts();

        String view = "redirect:insert.do";
        String filePath = "/Users/gimhaghyeon/Desktop/upload/";


        try {
            int resultId = 0;
            PostDto postDto = new PostDto();

            if(inputPostValidator.validate(categoryId, name, password, title, content, checkPassword)){
                PostDto inputPostDto = PostDto.builder()
                        .categoryId(Integer.parseInt(categoryId))
                        .name(name)
                        .password(password)
                        .title(title)
                        .content(content)
                        .build();
                postDto = inputPostDto;

                //실패시 0 반환
                resultId = postDao.insertPost(postDto);   //화면에서 받아온 값 null, 빈값 체크
            }

            //파일
            List<FileDto> fileDtoList = new ArrayList<>();
            for(Part part : partList){
                //TODO: 이름 인코더?

                log.info("======================================================");
                log.info("\t+ 1. part.getName : {}", part.getName()); // 전송파라미터이름(=파트이름)
                log.info("\t+ 2. part.getContentType : {}", part.getContentType());	// Content-Type 헤더의 값
                log.info("\t+ 3. part.getSize : {}", part.getSize()); // Body의 Content Length
                log.info("\t+ 4. part.getSubmittedFileName : {}", part.getSubmittedFileName());	// 파일의 원본 파일명
                log.info("\t+ 5. part.getHeaderNames : {}", part.getHeaderNames());	// 헤더명의 목록

                String fileOriginalName = part.getSubmittedFileName();

                if(fileOriginalName == null || fileOriginalName.isBlank()) continue;

                String uuid = UUID.randomUUID().toString();
                part.write(uuid);
                part.delete();

                FileDto fileDto = FileDto.builder()
                        .postId(resultId)
                        .fileOriginalName(fileOriginalName)
                        .fileName(uuid)
                        .filePath(filePath)
                        .fileSize(part.getSize())
                        .build();
                fileDtoList.add(fileDto);
            }

            int fileResult = postDao.insertFileList(fileDtoList);

            log.info(resultId != 0 ? resultId+"번 게시글 추가" : "게시글 추가 실패");
            log.info("파일 {}개 추가", fileResult);

            if(resultId != 0) view = "redirect:list.do";

        } catch (Exception e) {
            log.info("insert err: " + e.getMessage());
            e.printStackTrace();
        }

        return view;
    }
}
