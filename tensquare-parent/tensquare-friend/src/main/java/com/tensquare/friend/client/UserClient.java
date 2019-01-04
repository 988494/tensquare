package com.tensquare.friend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * @author 杨郑兴
 * @Date 2019/1/2 1:03
 * @官网 www.weifuwukt.com
 */

/**
 * url = "127.0.0.1:8080"表示访问url，如fandById这个方法被调用的时候访问的则是127.0.0.1:8080/label/{label}
 * decode404:当发生http 404错误时，如果该字段位true，会调用decoder进行解码，否则抛出FeignException
 * configuration: Feign配置类，可以自定义Feign的Encoder、Decoder、LogLevel、Contract
 * fallback: 定义容错的处理类，当调用远程接口失败或超时时，会调用对应接口的容错逻辑，fallback指定的类必须实现@FeignClient标记的接口
 * fallbackFactory: 工厂类，用于生成fallback类示例，通过这个属性我们可以实现每个接口通用的容错逻辑，减少重复的代码
 * path: 定义当前FeignClient的统一前缀
 *
 * 注意：
 * 　在使用fallback属性时，需要使用@Component注解，保证fallback类被Spring容器扫描到
 *
 * Feign请求超时问题
 * Hystrix默认的超时时间是1秒，如果超过这个时间尚未响应，将会进入fallback代码。而首次请求往往会比较慢（因为Spring的懒加载机制，要实例化一些类），这个响应时间可能就大于1秒了
 * 解决方案有三种，以feign为例。
 * 方法一
 * hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds: 5000
 * 该配置是让Hystrix的超时时间改为5秒
 * 方法二
 * hystrix.command.default.execution.timeout.enabled: false
 * 该配置，用于禁用Hystrix的超时时间
 * 方法三
 * feign.hystrix.enabled: false
 * 该配置，用于索性禁用feign的hystrix。该做法除非一些特殊场景，不推荐使用。
 */
@Component
@FeignClient(serviceId = "tensquare-user",path = "/user")
public interface UserClient {
    //更新好友粉丝数和用户关注数
    @PutMapping("/{userid}/{friendid}/{x}")
    public void updateFanscountAndFollowcount(@PathVariable("userid") String userid,@PathVariable("friendid") String friendid,@PathVariable("x") Integer x);


}

