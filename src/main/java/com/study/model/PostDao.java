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

        try {
            SqlSession sqlSession = factory.openSession();

            //삽입된 행의 수 반환
            int result = sqlSession.insert("insertPost", postDto);

            if(result == 0){
                sqlSession.rollback();
            } else{
                sqlSession.commit();
            }

            sqlSession.close();

            return result;

        } catch(Exception e) {
            throw new SQLException(e);
        }
    }

    public void insertPostWithFile(PostDto postDto) throws Exception{

    }

    //카테고리 리스트 읽어오기
    public List<CategoryVo> selectCategoryList() throws Exception {

        log.trace("selectCategoryList() invoked");

        try {
            SqlSession sqlSession = factory.openSession();

            //카테고리id를 리스트로 반환
            List<CategoryVo> list = sqlSession.selectList("selectCategoryList");

            sqlSession.close();

            return list;

        } catch (Exception e){
            throw new SQLException(e);
        }
    }

    //전체 게시글 목록 리스트 읽어오기
    public List<PostVo> selectPostList() throws Exception {
        log.trace("selectPostList() invoked");

        try {
            SqlSession sqlSession = factory.openSession();

            //게시글을 리스트로 반환
            List<PostVo> list = sqlSession.selectList("selectPostList");

            sqlSession.close();

            return list;
        } catch (Exception e){
            throw new SQLException(e);
        }
    }

    //검색 게시글 목록 리스트 읽어오기
    public List<PostVo> selectSearchPostList(SearchDto searchDto) throws Exception {
        log.trace("selectSearchPostList({}) invoked", searchDto);

        try {
            SqlSession sqlSession = factory.openSession();

            //검색된 게시글을 리스트로 반환
            List<PostVo> list = sqlSession.selectList("selectSearchPostList", searchDto);

            sqlSession.close();

            return list;
        } catch (Exception e){
            throw new SQLException(e);
        }
    }
}
