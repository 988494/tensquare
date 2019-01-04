package com.tensquare.common.entity;

import lombok.Data;

/**
 * 返回结果实体类
 * @author 杨郑兴
 * @Date 2018/12/25 19:34
 * @官网 www.weifuwukt.com
 */

@Data
public class Result {
    private boolean flag;
    private Integer code;
    private String message;
    private Object object;

    public Result() {
    }

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result(boolean flag, Integer code, String message, Object object) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.object = object;
    }
}
