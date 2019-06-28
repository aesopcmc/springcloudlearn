package com.chao.cloud;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 访问http://localhost:8888即可打开图形查看hystrix状态
 *
 * 原默认的访问地址是：http://localhost:8888/hystrix
 * @author AESOP
 * @date 2019/6/28 11:39
 */
@Controller
public class HystrixIndexController {
    @GetMapping("")
    public String index() {
        return "forward:/hystrix";
    }
}