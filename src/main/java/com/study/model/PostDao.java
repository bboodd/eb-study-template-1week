package com.study.model;

import com.study.config.MybatisConfig;
import com.study.mapper.PostMapper;
import com.study.model.PostDto;
import com.study.validate.InsertPostValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PostDao {
    private SqlSessionFactory factory = MybatisConfig.getSqlSession();
    private InsertPostValidator insertPostValidator = new InsertPostValidator();

    //게시글 추가
    public int insertPost(PostDto postDto) throws Exception{
        SqlSession sqlSession = factory.openSession(true);
        int resultId = 0;

        try {
            if(insertPostValidator.validate(postDto)){
                PostVo postVo = PostVo.builder()
                        .categoryId(postDto.getCategoryId())
                        .name(postDto.getName())
                        .password(postDto.getPassword())
                        .title(postDto.getTitle())
                        .content(postDto.getContent())
                        .build();

                //삽입된 게시글의 id반환
                sqlSession.insert("insertPost", postVo);
                resultId = postVo.getPostId();
            }

        } catch(Exception e) {
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();
        } finally {
            sqlSession.close();
            return resultId;
        }
    }

    //카테고리 리스트 읽어오기
    public List<CategoryVo> selectCategoryList() throws Exception {
        SqlSession sqlSession = factory.openSession();
        List<CategoryVo> voList = new ArrayList<>();

        // TODO: db에서 나오고 유효성 검증?
        try {
            //카테고리를 vo리스트로 반환
            voList = sqlSession.selectList("selectCategoryList");

        } catch(Exception e) {
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();
        } finally {
            sqlSession.close();
            return voList;
        }
    }

    //전체 게시글 목록 리스트 읽어오기
    public List<PostVo> selectPostList(SearchDto searchDto) throws Exception {
        SqlSession sqlSession = factory.openSession();
        List<PostVo> voList = new ArrayList<>();

        // TODO: 검색조건 유효성검증?
        try {
            //dto to vo
            SearchVo searchVo = SearchVo.builder()
                    .startDate(searchDto.getStartDate())
                    .endDate(searchDto.getEndDate())
                    .categoryId(searchDto.getCategoryId())
                    .keyword(searchDto.getKeyword())
                    .build();

            //게시글을 vo리스트로 반환
            voList = sqlSession.selectList("selectPostList", searchVo);

        } catch(Exception e) {
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();
        } finally {
            sqlSession.close();
            return voList;
        }
    }

    //게시글 Id로 읽어오기
    public PostVo selectPostById(int postId) throws Exception {
        SqlSession sqlSession = factory.openSession();
        PostVo postVo = new PostVo();
        // TODO: 유효성검증
        try {
            //검색된 게시글을 Vo로 반환
            postVo = sqlSession.selectOne("selectPostById", postId);

        } catch(Exception e) {
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();

        } finally {
            sqlSession.close();
            return postVo;
        }
    }

    //게시글 조회수 1 증가
    public int updatePostViewCount(int postId) throws Exception {
        SqlSession sqlSession = factory.openSession(true);
        int result = 0;
        try {
            // TODO: 유효성검증
            //조회수 증가시 1 반환
            //실패시 0 반환
            result = sqlSession.update("updatePostViewCount", postId);

        } catch(Exception e) {
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();

        } finally {
            sqlSession.close();
            return result;
        }
    }

    //댓글 추가
    public int insertComment(CommentDto commentDto) throws Exception {
        SqlSession sqlSession = factory.openSession(true);
        int result = 0;

        try {
            // TODO: 유효성 검증

            //dto to vo
            CommentVo commentVo = CommentVo.builder()
                    .postId(commentDto.getPostId())
                    .content(commentDto.getContent())
                    .build();

            //삽입된 행의 수 반환
            result = sqlSession.insert("insertComment", commentVo);

        } catch(Exception e) {
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();

        } finally {
            sqlSession.close();
            return result;
        }
    }

    //postId에 대한 댓글 리스트 가져오기
    public List<CommentVo> selectCommentList(int postId) throws Exception {
        SqlSession sqlSession = factory.openSession();
        List<CommentVo> list = new ArrayList<>();

        try {
            //검색된 게시글을 리스트로 반환
            list = sqlSession.selectList("selectCommentList", postId);

        } catch(Exception e) {
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();

        } finally {
            sqlSession.close();
            return list;
        }
    }

    //게시물 삭제
    public int deletePost(int postId) throws Exception {
        SqlSession sqlSession = factory.openSession(true);
        int result = 0;

        try {
            //삭제 성공시 1 반환
            //실패시 0 반환
            result = sqlSession.update("deletePost", postId);

        } catch(Exception e) {
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();

        } finally {
            sqlSession.close();
            return result;
        }
    }

    //파일 추가
    public int insertFileList(List<FileDto> fileList) throws Exception {
        SqlSession sqlSession = factory.openSession(true);
        int result = 0;

        try {
            for(FileDto fileDto : fileList){
                FileVo fileVo = FileVo.builder()
                        .postId(fileDto.getPostId())
                        .fileOriginalName(fileDto.getFileOriginalName())
                        .fileName(fileDto.getFileName())
                        .filePath(fileDto.getFilePath())
                        .fileSize(fileDto.getFileSize())
                        .build();
                if(fileVo.getPostId() != 0){
                    result += sqlSession.insert("insertFile", fileVo);
                }
            }
        } catch(Exception e) {
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();

        } finally {
            sqlSession.close();
            return result;
        }
    }

    //postId에 대한 파일 리스트 가져오기
    public List<FileVo> selectFileList(int postId) throws Exception {
        SqlSession sqlSession = factory.openSession();
        List<FileVo> list = new ArrayList<>();

        try {
            //검색된 게시글을 리스트로 반환
            list = sqlSession.selectList("selectFileList", postId);

        } catch(Exception e) {
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();

        } finally {
            sqlSession.close();
            return list;
        }
    }

    //fileId에 대한 파일 가져오기
    public FileVo selectFile(int fileId) throws Exception {
        SqlSession sqlSession = factory.openSession();
        FileVo fileVo = new FileVo();

        try {
            fileVo = sqlSession.selectOne("selectFile", fileId);

        } catch(Exception e) {
            log.info("에러메세지: " + e.getMessage());
            e.printStackTrace();

        } finally {
            sqlSession.close();
            return fileVo;
        }
    }
}
