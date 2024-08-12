package com.study.service;

import com.study.model.CategoryDto;
import com.study.model.CategoryVo;
import com.study.model.FileVo;
import com.study.model.PostDao;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DownloadService implements HttpService{
    private final PostDao postDao = new PostDao();

    public String doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String fileId = request.getParameter("fileId");
        String postId = request.getParameter("postId");
        String view = "redirect:read.do?postId=" + postId;
        FileVo fileVo = new FileVo();

        try {
            if(fileId != null && !fileId.isBlank()){
                fileVo = postDao.selectFile(Integer.parseInt(fileId));
            }

            String fileOriginalName = fileVo.getFileOriginalName();
            String fileName = fileVo.getFileName(); //uuid 저장명
            String filePath = fileVo.getFilePath();

            String path = filePath + fileName; //절대경로 획득
            @Cleanup
            FileInputStream fis = new FileInputStream(path);

            //mime type 획득
            ServletContext sc = request.getServletContext();
            String mimeType = sc.getMimeType(fileOriginalName);
            if(mimeType == null){ //mime type이 없을경우 기본값으로 설정
                mimeType = "application/octet-stream";
            }
            //HTTP 응답 컨텐츠타입 설정
            response.setContentType(mimeType);

            //파일 이름을 UTF-8 형식으로 인코딩
            String encodedFileName = new String(fileOriginalName.getBytes("UTF-8"), "ISO-8859-1");
            //HTTP 응답 헤더 설정
            response.setHeader("Content-Disposition", "attachment; filename=" + encodedFileName);

            byte[] buffer = new byte[4096];
            int readBytes = 0;

            @Cleanup
            ServletOutputStream sos = response.getOutputStream();

            //파일을 읽어서 버퍼에 저장하고 버퍼의 내용을 출력 스트림으로 전송
            while((readBytes = fis.read(buffer)) != -1){
                sos.write(buffer, 0, readBytes);
            }

            sos.flush();



        } catch (Exception e) {
            log.info("select err: " + e.getMessage());
            e.printStackTrace();
        }

        return view;
    }
}
