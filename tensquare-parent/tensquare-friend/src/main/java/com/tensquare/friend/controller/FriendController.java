package com.tensquare.friend.controller;

import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.service.FriendService;
import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 杨郑兴
 * @Date 2019/1/2 16:57
 * @官网 www.weifuwukt.com
 */
@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private FriendService friendService;

    @Autowired
    private UserClient userClient;

    @GetMapping("/index")
    @ResponseBody
    public String index(){
        String name = "ok";
        return name;
    }
    //添加好友或非好友
    @PutMapping("/like/{friendid}/{type}")
    @ResponseBody
    public Result addFriend(@PathVariable("friendid") String friendid,@PathVariable("type") String type){
        //验证是否登陆，并拿到当前登陆的用户id
        Claims claims = (Claims)request.getAttribute("claims_user");
        if(StringUtils.isEmpty(claims)){
            //说明当前你用户没有user角色
            return new Result(false, StatusCode.ERROR,"权限不足或者没有登陆");
        }
        String userid = claims.getId();
        //判断是添加好友还是添加非好友
        if(type!=null){
            if(type.equals("1")){
                //添加好友
                int flag = friendService.addFirend(userid,friendid);
                if(flag==0){
                    return new Result(false, StatusCode.ERROR,"不能重复添加好友");
                }
                if(flag==1){
                    userClient.updateFanscountAndFollowcount(userid,friendid,1);
                    return new Result(true, StatusCode.OK,"添加成功");
                }
            }else if(type.equals("2")){
                //添加非好友
                int flag = friendService.noFriend(userid,friendid);
                if(flag==0){
                    return new Result(false, StatusCode.ERROR,"不能重复添加好友");
                }
                if(flag==1){
                    return new Result(true, StatusCode.OK,"添加成功");
                }
            }
            return new Result(false, StatusCode.ERROR,"参数异常");
        }else{
            return new Result(false, StatusCode.ERROR,"参数异常");
        }
    }

    @DeleteMapping("/{friendid}")
    public Result delete(@PathVariable("friendid") String friendid){
        //验证是否登陆，并拿到当前登陆的用户id
        Claims claims = (Claims)request.getAttribute("claims_user");
        if(StringUtils.isEmpty(claims)){
            //说明当前你用户没有user角色
            return new Result(false, StatusCode.ERROR,"权限不足或者没有登陆");
        }
        String userid = claims.getId();
        friendService.deleteFriend(userid,friendid);
        userClient.updateFanscountAndFollowcount(userid,friendid,-1);
        return new Result(true, StatusCode.OK,"删除成功");
    }
}
