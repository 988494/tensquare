package com.tensquare.user.interceptor;

import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.tensquare.common.util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 杨郑兴
 * @Date 2018/12/31 23:07
 * @官网 www.weifuwukt.com
 */
@Component
public class UserJwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    //前置处理
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       //无论如何都放行。具体能不能操作还是在具体的操作中去判断
       //拦截器知识负责把头请求中包含的token的令牌进行一个解析校验
        System.out.println("拦截器进来了");
        System.out.println(request.getRequestURL());
        String header = request.getHeader("Authorization");


        if(!StringUtils.isEmpty(header)){
            //如果有包含Authorization头信息，就对其进行解析
            if(header.startsWith("Bearer ")){
                //得到token
                String token = header.substring(7);
                //对令牌进行验证
                //如果过期会报错，所以要try
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    String roles = (String) claims.get("roles");
                    if(roles!=null&&roles.equals("admin")){
                        //以后要用的时候在request域中拿claims_admin变量，
                        // 如果有值，则表示当前角色是admin
                        request.setAttribute("claims_admin",token);
                    }
                } catch (Exception e) {
                    throw new RuntimeException("令牌不正确");
                }
            }
        }
        return true;//true表示放行
    }
}
