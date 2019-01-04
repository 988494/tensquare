package com.tensquare.search.controller;

import com.tensquare.search.pojo.Article;
import com.tensquare.search.service.ArticleSearchService;
import com.tensquare.common.entity.PageResult;
import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author 杨郑兴
 * @Date 2018/12/29 11:12
 * @官网 www.weifuwukt.com
 */
@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleSearchController {
    @Autowired
    private ArticleSearchService articleSearchService;

    //添加文章
    @PostMapping
    public Result save(@RequestBody Article article){
        articleSearchService.save(article);
        return new Result(true, StatusCode.OK,"添加文章成功");
    }

    //关键字查询文章
    @GetMapping("/{key}/{page}/{size}")
    public Result findByKey(@PathVariable("key") String key,@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        Page<Article> pageData = articleSearchService.findByKey(key, page, size);
        return new Result(true, StatusCode.OK,"查询文章成功",new PageResult<>(pageData.getTotalElements(),pageData.getContent()));
    }
}
