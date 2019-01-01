package com.tensquare.sms.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 杨郑兴
 * @Date 2018/12/30 21:38
 * @官网 www.weifuwukt.com
 */
@Component
@RabbitListener(queues = "sms")
@Slf4j
public class SmsListener {

    @RabbitHandler
    public void executeSms(Map<String,String> map){
        log.info("手机号：{}",map.get("mobile"));
        log.info("验证码：{}",map.get("checkcode"));
    }
}
