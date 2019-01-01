package com.tensquare.search.dao;

import com.tensquare.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author 杨郑兴
 * @Date 2018/12/29 11:08
 * @官网 www.weifuwukt.com
 */
public interface ArticleSearchDao extends ElasticsearchRepository<Article,String> {

    public Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);
}
