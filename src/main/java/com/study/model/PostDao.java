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

        log.trace("insert({}) invoked.", postDto);

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

    //카테고리id list 읽어오기
    public List<Integer> selectCategoryIdList() throws Exception {

        log.trace("select() invoked");

        try {
            SqlSession sqlSession = factory.openSession();

            //카테고리id를 리스트로 반환
            List<Integer> list = sqlSession.selectList("selectCategoryIdList");

            sqlSession.close();

            return list;

        } catch (Exception e){
            throw new SQLException(e);
        }
    }

    //전체 게시글 목록 list 읽어오기
    public List<PostVo> selectPostList() throws Exception {
        log.trace("select() invoked");

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
}
