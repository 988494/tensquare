package com.tensquare.common.entity;

import lombok.Data;

import java.util.List;


/**
 * 分页结果类
 * @author 杨郑兴
 * @Date 2018/12/25 19:46
 * @官网 www.weifuwukt.com
 */
@Data
public class PageResult<T> {

    private long total;
    private List<T> rows;

    public PageResult() {
    }

    public PageResult(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }
}
