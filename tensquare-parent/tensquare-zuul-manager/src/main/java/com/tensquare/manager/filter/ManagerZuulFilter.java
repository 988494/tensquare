package com.tensquare.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.tensquare.common.util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 杨郑兴
 * @Date 2019/1/4 16:45
 * @官网 www.weifuwukt.com
 */
@Component
public class ManagerZuulFilter extends ZuulFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String filterType() {
        //在请求前执行pre,请求后执行post
        return "pre";
    }

    @Override
    public int filterOrder() {
        //多个过滤器的执行顺序，数字越小则越先执行
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //当前过滤器是否开启
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //过滤器内执行的操作，返回任何object的值表示继续执行
        //setsendzullResponse(false)表示不在继续执行
        System.out.println("经过manager后台过滤器了");
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        System.out.println(request.getRequestURL());

        //zuul每次请求都有两次转发，第一次请求是是OPTIONS，是用于内容分发的请求，第二个请求才是真实的请求，
        //所以要把每次请求中，zuul的第一次内容分发请求放行
        if(request.getMethod().equals("OPTIONS")){
            return null;
        }

//        // 跨域 要嘛在zuul中设置如下，要嘛在每一个微服务中设置@CrossOrigin(origins = "*")注解，千万要在zuul中设置了以后又在每个微服务中设置@CrossOrigin(origins = "*")，这样适得其反
//        HttpServletResponse response = currentContext.getResponse();
//        response.addHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", "*");

        //登陆需要放行
        if(request.getRequestURI().indexOf("login")>0){
            return null;
        }
        String authorization = request.getHeader("Authorization");
        if(!StringUtils.isEmpty(authorization)){
            if(authorization.startsWith("Bearer ")){
                String token = authorization.substring(7);
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    String roles = (String) claims.get("roles");
                    if(roles!=null&&roles.equals("admin")){//管理员
                        //把头信息转发下去，并且放行
                        currentContext.addZuulRequestHeader("Authorization",authorization);
                        return null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    currentContext.setSendZuulResponse(false);//中止运行
                }
            }
        }
        currentContext.setSendZuulResponse(false);//中止运行
        currentContext.setResponseStatusCode(403);
        currentContext.setResponseBody("权限不足");
        currentContext.getResponse().setContentType("text/html;charset=utf-8");
        return null;
    }
}
