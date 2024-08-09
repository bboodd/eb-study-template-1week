package com.study.model;

import com.study.config.MybatisConfig;
import com.study.model.PostDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.SQLException;
import java.util.List;

@Slf4j
public class PostDao {
    private SqlSessionFactory factory = MybatisConfig.getSqlSession();

    //게시글 추가
    public int insertPost(PostDto postDto) throws Exception{

        log.trace("insertPost({}) invoked.", postDto);

        SqlSession sqlSession = factory.openSession(true);
        try {
            //삽입된 행의 수 반환
            int result = sqlSession.insert("insertPost", postDto);

            return result;
        } catch(Exception e) {
            throw new SQLException(e);
        } finally {
            sqlSession.close();
        }
    }

    public void insertPostWithFile(PostDto postDto) throws Exception{

    }

    //카테고리 리스트 읽어오기
    public List<CategoryVo> selectCategoryList() throws Exception {
        SqlSession sqlSession = factory.openSession();
        try {
            //카테고리id를 리스트로 반환
            List<CategoryVo> list = sqlSession.selectList("selectCategoryList");

            return list;

        } catch (Exception e){
            throw new SQLException(e);
        } finally {
            sqlSession.close();
        }
    }

    //전체 게시글 목록 리스트 읽어오기
    public List<PostVo> selectPostList() throws Exception {
        SqlSession sqlSession = factory.openSession();
        try {
            //게시글을 리스트로 반환
            List<PostVo> list = sqlSession.selectList("selectPostList");

            return list;
        } catch (Exception e){
            throw new SQLException(e);
        } finally {
            sqlSession.close();
        }
    }

    //검색 게시글 목록 리스트 읽어오기
    public List<PostVo> selectSearchPostList(SearchDto searchDto) throws Exception {
        SqlSession sqlSession = factory.openSession();
        try {
            //검색된 게시글을 리스트로 반환
            List<PostVo> list = sqlSession.selectList("selectSearchPostList", searchDto);

            return list;
        } catch (Exception e){
            throw new SQLException(e);
        } finally {
            sqlSession.close();
        }
    }

    //게시글 Id로 읽어오기
    public PostVo selectPostById(int postId) throws Exception {
        SqlSession sqlSession = factory.openSession();
        try {
            //검색된 게시글을 Vo로 반환
            PostVo postVo = sqlSession.selectOne("selectPostById", postId);

            return postVo;
        } catch (Exception e){
            throw new SQLException(e);
        } finally {
            sqlSession.close();
        }
    }

    //게시글 조회수 1 증가
    public int updatePostViewCount(int postId) throws Exception {
        SqlSession sqlSession = factory.openSession(true);
        try {
            //조회수 증가시 1 반환
            //실패시 0 반환
            int result = sqlSession.update("updatePostViewCount", postId);

            return result;
        } catch (Exception e){
            throw new SQLException(e);
        } finally {
            sqlSession.close();
        }
    }

    //댓글 추가
    public int insertComment(CommentDto commentDto) throws Exception {
        SqlSession sqlSession = factory.openSession(true);
        try {
            //삽입된 행의 수 반환
            int result = sqlSession.insert("insertComment", commentDto);

            return result;

        } catch(Exception e) {
            throw new SQLException(e);
        } finally {
            sqlSession.close();
        }
    }

    //postId에 대한 댓글 리스트 가져오기
    public List<CommentVo> selectCommentList(int postId) throws Exception {
        SqlSession sqlSession = factory.openSession();
        try {
            //검색된 게시글을 리스트로 반환
            List<CommentVo> list = sqlSession.selectList("selectCommentList", postId);

            return list;
        } catch (Exception e){
            throw new SQLException(e);
        } finally {
            sqlSession.close();
        }
    }

    //게시물 삭제
    public int deletePost(int postId) throws Exception {
        SqlSession sqlSession = factory.openSession(true);
        try {
            //삭제 성공시 1 반환
            //실패시 0 반환
            int result = sqlSession.update("deletePost", postId);

            return result;
        } catch (Exception e){
            throw new SQLException(e);
        } finally {
            sqlSession.close();
        }
    }
}
