package com.tensquare.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 杨郑兴
 * @Date 2019/1/4 19:22
 * @官网 www.weifuwukt.com
 */
@Component
public class WebFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        System.out.println("web 前端过滤器进啦了");
        //得到山下问
        RequestContext currentContext = RequestContext.getCurrentContext();
        //得到request域
        HttpServletRequest request = currentContext.getRequest();
        //获取头信息
            String authorization = request.getHeader("Authorization");
        //判断是否有头信息
        if(!StringUtils.isEmpty(authorization)){
            currentContext.addZuulRequestHeader("Authorization",authorization);
        }
        return null;
    }
}
