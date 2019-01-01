package com.tensquare.search.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.search.article.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{

    //文章审核 state 1：审核通过，0：审核不通过
    @Modifying
    @Query(value = "UPDATE tb_article SET state=? WHERE id =?",nativeQuery = true)
    public void updateState(String state,String id);

    //更新文章点赞数
    @Modifying
    @Query(value = "UPDATE tb_article SET thumbup=thumbup+1 WHERE id = ?",nativeQuery = true)
    public void addThumbup(String id);
}
