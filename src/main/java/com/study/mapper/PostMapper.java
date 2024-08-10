package com.study.mapper;

import com.study.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    // TODO: mybatis-spring 없이 사용하려면 구현체에 sql session 주입해야함 (있으면 세션팩토리 bean, 세션 템플릿 때문에 가능)

    int insertPost(PostVo postVo);

    List<CategoryVo> selectCategoryList();

    List<PostVo> selectPostList(SearchVo searchVo);

    PostVo selectPostById(int postId);

    void updatePostViewCount(int postId);

    int insertComment(CommentVo commentVo);

    List<CommentVo> selectCommentList(int postId);

    void deletePost(int postId);
}
