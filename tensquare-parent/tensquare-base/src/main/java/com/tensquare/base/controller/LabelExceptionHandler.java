package com.tensquare.base.controller;

import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 杨郑兴
 * @Date 2019/1/2 1:06
 * @官网 www.weifuwukt.com
 */
@ControllerAdvice
public class LabelExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public Result exception(Exception e){
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR,e.getMessage());
    }
}
