package com.tensquare.search.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * @author 杨郑兴
 * @Date 2018/12/29 10:51
 * @官网 www.weifuwukt.com
 */
@Data
@Document(indexName = "tensquare_article",type = "article")
public class Article implements Serializable {

    @Id
    private String id;
    //index 表示是否索引，就是看改域是否能被索引
    //是否分词，就是表示搜索的时候是整体匹配还是单词匹配，索引的才能添加分词
    //是否存储，就是是否在页面显示，Article类写出来的字段就是要存储的字段
    @Field(index = true,analyzer = "ik_max_word",searchAnalyzer ="ik_max_word" )
    private String title;//标题
    @Field(index = true,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String content;//文章正文
    private String state;//审核状态
}
