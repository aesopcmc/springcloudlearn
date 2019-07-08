package com.chao.cloud.feign;

import com.chao.cloud.entity.User;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

/**
 * FeignClient
 *
 * Hystrix的阈值——Hystrix设计来保护高并发应用的，它要求10秒（可用hystrix.command.default.metrics.rollingStats.timeInMilliseconds 自定义）
 * 以内API错误次数超过20次（用circuitBreaker.requestVolumeThreshold 自定义），此时才可能触发断路器。
 * @author AESOP
 * @date 2019/6/27 10:29
 */
@FeignClient(name = "microservice-provider-user", fallbackFactory = UserFeignClientFallbackFactory.class)
public interface UserFeignClient {
    @GetMapping("/users/{id}")
    User findById(@PathVariable Long id);
}
//方式 1
// 接口注解@FeignClient(name = "microservice-provider-user", fallback = UserFeignClientFallback .class)
//
// @Component
// class UserFeignClientFallback implements UserFeignClient {
//     @Override
//     public User findById(Long id) {
//         return new User(id, "默认用户", "默认用户", 0, new BigDecimal(1));
//     }
// }

//方式 2 ，可以获得错误信息
@Slf4j
@Component
class UserFeignClientFallbackFactory  implements FallbackFactory<UserFeignClient> {
    @Override
    public UserFeignClient create(Throwable throwable) {
        return new UserFeignClient() {
            @Override
            public User findById(Long id) {
                log.error("进入回退逻辑", throwable);
                return new User(id, "默认用户", "默认用户", 0, new BigDecimal(1));
            }
        };
    }
}