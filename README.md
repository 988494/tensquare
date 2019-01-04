# tensquare
## 问题：
为什么tensquare中Label类要实现序列化，而tensquare_common模块中的类不需要实现序列化？</br>
tensquare_common模块中的类中的类要嘛说返回给客户端的，或者本模块用，而tensquare中Label类中的类，未来要与各个模块之间调用，所以需要实现序列化
## 问题：eureka
eureka:</br>
  client:</br>
    register-with-eureka: false #是否将自己注册到Eureka服务中，本身就是所有无需</br>
    fetch-registry: false #是否从Eureka中获取注册信息</br>
 eureka:</br>
  instance:</br>
    #    ip-address: 表示鼠：鼠标放到微服务实例名上，右下角出现的ip地址是什么，这里写什么哪里就显示什么</br>
    instance-id: ${spring.application.name}:${server.port}</br>
    prefer-ip-address: true #模块之间可以跨域访问</br>
## 问题:Feign使用知识点和注意事项
url = "127.0.0.1:8080"表示访问url，如fandById这个方法被调用的时候访问的则是127.0.0.1:8080/label/{label}</br>
 decode404:当发生http 404错误时，如果该字段位true，会调用decoder进行解码，否则抛出FeignException</br>
 configuration: Feign配置类，可以自定义Feign的Encoder、Decoder、LogLevel、Contract</br>
 fallback: 定义容错的处理类，当调用远程接口失败或超时时，会调用对应接口的容错逻辑，fallback指定的类必须实现@FeignClient标记的接口</br>
 fallbackFactory: 工厂类，用于生成fallback类示例，通过这个属性我们可以实现每个接口通用的容错逻辑，减少重复的代码</br>
 path: 定义当前FeignClient的统一前缀</br>
</br>
  注意：</br>
　在使用fallback属性时，需要使用@Component注解，保证fallback类被Spring容器扫描到</br>
</br>
  Feign请求超时问题</br>
  Hystrix默认的超时时间是1秒，如果超过这个时间尚未响应，将会进入fallback代码。而首次请求往往会比较慢（因为Spring的懒加载机制，要实例化一些类），这个</br>响应时间可能就大于1秒了</br>
  解决方案有三种，以feign为例。</br>
 方法一</br>
 hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds: 5000</br>
 该配置是让Hystrix的超时时间改为5秒</br>
 方法二</br>
 hystrix.command.default.execution.timeout.enabled: false</br>
 该配置，用于禁用Hystrix的超时时间</br>
 方法三</br>
 feign.hystrix.enabled: false</br>
 该配置，用于索性禁用feign的hystrix。该做法除非一些特殊场景，不推荐使用。</br>
 </br>
 ## 问题:Feign技巧
 1、在使用feign时候，如果feign接口写在消费方，则@EnableFeignClients注解的basePackages熟悉可以不用写，如果feign接口放在生产者这边，</br>
 则@EnableFeignClients(basePackages = "com.tensquare.friend")这个注解一定要有basePackages，否则feign接口动态代理生成的类无法注入到容器中</br>
 2、我个人思考了个问题，最好把feign接口写在生产者这边，因为随着服务的增多，也会有多个调用同一个feign接口方法，如果写在生产者这边，</br>那么就会少写代码，如果写在消费者那边，则每个消费者都要写相同的代码</br>
 ##  问题:springboot jpa使用注意事项
 1、springboot使用jpa，需要在service层添加@@Transactional注解（就是事务）</br>
 否则会出现异常：org.springframework.dao.InvalidDataAccessApiUsageException:</br>
 2、dao层修改、删除、更新需要如果使用sql语句，则需要在方法上添加@Modifying注解</br>
 如：</br>
 	@Modifying</br>
	@Query(value = "UPDATE tb_user SET fanscount=fanscount+? WHERE id=?",nativeQuery = true)</br>
    void updateFanscount(Integer x, String friendid);</br>
 ## 问题:zuul网关是非常坑的（这里springcloud Finchley.SR2,springboot 2.0.7.RELEASE其他版本或许不是这样，根据版本查看源代码分析即可）
 主要的问题是：Spring Cloud系列之客户端请求带“Authorization”请求头,经过zuul转发后丢失了？</br>
 解决问题参考连接：http://www.cnblogs.com/liaojie970/p/9158991.html</br>
 zuul进行转发的时候，发现默认过滤掉的请求头有：authorization、set-cookie、cookie、host、connection、content-length、</br>content-encoding、server、transfer-encoding、x-application-context</br>
 由于我这个项目中使用了jwt,其中恰巧header中传了名为Authorization的参数，所以使用zuul网关到其他微服务时候，</br>在其他微服务中Authorization字段将被丢失，通过分析源码可知，zuulProperties这个配置类中的，</br>
  private Set<String> sensitiveHeaders = new LinkedHashSet(Arrays.asList("Cookie", "Set-Cookie", "Authorization"));</br>这个字段就是包换忽略的字段，</br>
### 第一个解决办法：可以在application.yml配置文件中把sensitiveHeaders默认给覆盖掉即可，这里项目中设置为空
zuul:</br>
 sensitive-headers:  </br>
但是如果开启	zuul retry上面的解决方法就要慎用了</br>
### 第二个解决方法：</br>
zuul.routes.<routeName>.sensitive-headers=</br>
zuul.routes.<routeName>.custom-sensitive-headers=true</br>
		
 
