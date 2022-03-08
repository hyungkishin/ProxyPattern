package hello2.proxy;

import hello2.proxy.config.AppV1Config;
import hello2.proxy.config.AppV2Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({AppV1Config.class,AppV2Config.class})
@SpringBootApplication(scanBasePackages = "hello2.proxy.app")
public class ProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProxyApplication.class, args);
    }

}