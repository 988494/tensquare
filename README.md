# tensquare
## 问题1：
为什么tensquare中Label类要实现序列化，而tensquare_common模块中的类不需要实现序列化？</br>
tensquare_common模块中的类中的类要嘛说返回给客户端的，或者本模块用，而tensquare中Label类中的类，未来要与各个模块之间调用，所以需要实现序列化
## 问题2:Feign使用知识点和注意事项
* url = "127.0.0.1:8080"表示访问url，如fandById这个方法被调用的时候访问的则是127.0.0.1:8080/label/{label}</br>
 * decode404:当发生http 404错误时，如果该字段位true，会调用decoder进行解码，否则抛出FeignException</br>
 * configuration: Feign配置类，可以自定义Feign的Encoder、Decoder、LogLevel、Contract</br>
 * fallback: 定义容错的处理类，当调用远程接口失败或超时时，会调用对应接口的容错逻辑，fallback指定的类必须实现@FeignClient标记的接口</br>
 * fallbackFactory: 工厂类，用于生成fallback类示例，通过这个属性我们可以实现每个接口通用的容错逻辑，减少重复的代码</br>
 * path: 定义当前FeignClient的统一前缀</br>
 *</br>
 * 注意：</br>
 * 　在使用fallback属性时，需要使用@Component注解，保证fallback类被Spring容器扫描到</br>
 *</br>
 * Feign请求超时问题</br>
 * Hystrix默认的超时时间是1秒，如果超过这个时间尚未响应，将会进入fallback代码。而首次请求往往会比较慢（因为Spring的懒加载机制，要实例化一些类），这个</br>响应时间可能就大于1秒了</br>
 * 解决方案有三种，以feign为例。</br>
 * 方法一</br>
 * hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds: 5000</br>
 * 该配置是让Hystrix的超时时间改为5秒</br>
 * 方法二</br>
 * hystrix.command.default.execution.timeout.enabled: false</br>
 * 该配置，用于禁用Hystrix的超时时间</br>
 * 方法三</br>
 * feign.hystrix.enabled: false</br>
 * 该配置，用于索性禁用feign的hystrix。该做法除非一些特殊场景，不推荐使用。</br>
 */</br>
