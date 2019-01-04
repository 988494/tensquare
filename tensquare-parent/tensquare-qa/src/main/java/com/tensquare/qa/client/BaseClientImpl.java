package com.tensquare.qa.client;

import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.StatusCode;
import org.springframework.stereotype.Component;

/**
 * @author 杨郑兴
 * @Date 2019/1/3 22:08
 * @官网 www.weifuwukt.com
 */
@Component
public class BaseClientImpl implements BaseClient{
    @Override
    public Result fandById(String labelId) {
        return new Result(false, StatusCode.OK,"熔断器触发了！");
    }
}
