package com.tensquare.search.base.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 标签实体类
 * @author 杨郑兴
 * @Date 2018/12/26 0:10
 * @官网 www.weifuwukt.com
 */
@Entity
@Table(name = "tb_label")
@Data//一定要加序列化，才能在不同微服务间使用
public class Label implements Serializable {

    @Id//标注为主键
    private String id;
    private String labelname;//标签名称
    private String state;//状态
    private Long count;//使用数量
    private Long fans;//关注数
    private String recommend;//是否推荐
}
