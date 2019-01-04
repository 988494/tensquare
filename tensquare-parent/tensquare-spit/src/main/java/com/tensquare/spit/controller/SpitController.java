package com.tensquare.spit.controller;

import com.tensquare.spit.service.SpitService;
import com.tensquare.spit.pojo.Spit;
import com.tensquare.common.entity.PageResult;
import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 杨郑兴
 * @Date 2018/12/27 21:23
 * @官网 www.weifuwukt.com
 */
@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {
    @Autowired
    private SpitService spitService;
    @Autowired
    private RedisTemplate redisTemplate;

    //查询全部列表
    @GetMapping
    public Result findAll(){
        List<Spit> list = spitService.findAll();
        return new Result(true, StatusCode.OK,"查询成功",list);
    }

    //根据ID查询吐槽
    @GetMapping("/{spitId}")
    public Result findById(@PathVariable("spitId") String spitId){
        Spit spit = spitService.findById(spitId);
        return new Result(true, StatusCode.OK,"查询成功",spit);
    }

    //增加吐槽
    @PostMapping
    public Result save(@RequestBody Spit spit){
        spitService.save(spit);
        return new Result(true, StatusCode.OK,"保存成功");
    }

    //修改吐槽
    @PutMapping("/{spitId}")
    public Result update(@PathVariable("spitId") String spitId,@RequestBody Spit spit){
        spit.set_id(spitId);
        spitService.update(spit);
        return new Result(true, StatusCode.OK,"修改成功");
    }

    //删除吐槽
    @DeleteMapping("/{spitId}")
    public Result delete(@PathVariable("spitId") String spitId){
        spitService.delete(spitId);
        return new Result(true, StatusCode.OK,"删除成功");
    }

    //根据上级ID查询吐槽数据（分页）
    @GetMapping("/comment/{parentid}/{page}/{size}")
    public Result delete(@PathVariable("parentid") String parentid,@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        Page<Spit> spitPage = spitService.findByParentid(parentid, page, size);
        return new Result(true, StatusCode.OK,"查询成功",new PageResult<Spit>(spitPage.getTotalElements(),spitPage.getContent()));
    }

    //吐槽点赞
    @PutMapping("thumbup/{spitId}")
    public Result thumbup(@PathVariable("spitId") String spitId){
        //判断当前用户是否已经点赞，但是现在我们没有做认证，暂时先把userid写死
        String userid ="111";
        if ((redisTemplate.opsForValue().get("thumbup_"+userid))!=null){
            return new Result(false, StatusCode.REPE_ERROR,"不能重复点赞");
        }
        spitService.thumbup(spitId);
        redisTemplate.opsForValue().set("thumbup_"+userid,1);
        return new Result(true, StatusCode.OK,"吐槽点赞成功");
    }
}
