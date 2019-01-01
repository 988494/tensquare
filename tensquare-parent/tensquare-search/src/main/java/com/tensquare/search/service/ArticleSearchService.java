package com.tensquare.search.service;

import com.tensquare.search.dao.ArticleSearchDao;
import com.tensquare.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author 杨郑兴
 * @Date 2018/12/29 11:10
 * @官网 www.weifuwukt.com
 */
@Service
public class ArticleSearchService {
    @Autowired
    private ArticleSearchDao articleSearchDao;

    //添加文章
    public void save(Article article){
        articleSearchDao.save(article);
    }

    //模糊查询
    public Page<Article> findByKey(String key, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page-1,size);
        return articleSearchDao.findByTitleOrContentLike(key,key,pageable);
    }
}
