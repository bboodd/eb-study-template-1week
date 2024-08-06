package com.study.post.model;

import com.study.config.MybatisConfig;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.ArrayList;
import java.util.List;

public class PostDao {
    private SqlSessionFactory factory = MybatisConfig.getSqlSession();

    //게시글 추가
    public int insertPost(PostDto postDto) throws Exception{
        SqlSession sqlSession = factory.openSession();
        int result = sqlSession.insert("insertPost", postDto);
        if(result == 0){
            sqlSession.rollback();
        } else{
            sqlSession.commit();
        }
        sqlSession.close();
        return result;
    }

    public void insertPostWithFile(PostDto postDto) throws Exception{

    }

    //카테고리id 읽어오기
    public List<Integer> selectCategoryIdList(){
        SqlSession sqlSession = factory.openSession();
        List<Integer> list = sqlSession.selectList("selectCategoriesId");
        sqlSession.close();
        return list;
    }
}
