package cloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 其中通过@Value注解，注入了key为didispace.title的配置（默认为空字符串）
 * 比较重要的注解@RefreshScope，主要用来让这个类下的配置内容支持动态刷新，也就是当我们的应用启动之后，修改了Nacos中的配置内容之后，这里也会马上生效。
 * @author AESOP
 * @date 2019/6/29 11:34
 */
@RefreshScope
@RestController
public class ConfigClientController {
    @Value("${didispace.title:}")
    private String title;

    @GetMapping("/title")
    public String hello() {
        System.out.println(this.title);
        return this.title;
    }
}