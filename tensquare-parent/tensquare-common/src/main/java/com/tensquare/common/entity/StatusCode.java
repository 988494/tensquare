package com.tensquare.common.entity;

/**
 * 状态码实体类
 * @author 杨郑兴
 * @Date 2018/12/25 19:54
 * @官网 www.weifuwukt.com
 */
public class StatusCode {
    public static final int OK = 20000;//成功
    public static final int ERROR = 20001;//失败
    public static final int LOGIN_EROR = 20002;//用户名或密码错误
    public static final int ACCESS_ERROR = 20003;//权限不足
    public static final int REMOTE_ERROR = 20004;//远程调用失败
    public static final int REPE_ERROR = 20003;//重复操作
}
