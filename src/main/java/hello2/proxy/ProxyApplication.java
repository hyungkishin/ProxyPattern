package hello2.proxy;

import hello2.proxy.config.v1_proxy.ConcreteProxyConfig;
import hello2.proxy.config.v2_dynamicproxy.DynamicProxyBasicConfig;
import hello2.proxy.config.v2_dynamicproxy.DynamicProxyFilterConfig;
import hello2.proxy.config.v3_proxyFactory.ProxyFactoryConfigV1;
import hello2.proxy.config.v3_proxyFactory.ProxyFactoryConfigV2;
import hello2.proxy.config.v4_postprocessor.BeanPostProcessorConfig;
import hello2.proxy.config.v5_autoProxy.AutoProxyConfig;
import hello2.proxy.config.v6_aop.AopConfig;
import hello2.proxy.trace.logtrace.LogTrace;
import hello2.proxy.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

//@Import(AppV1Config.class)
//@Import({AppV1Config.class,AppV2Config.class})
//@Import(InterfaceProxyConfig.class)
//@Import(ConcreteProxyConfig.class)
//@Import(DynamicProxyBasicConfig.class)
//@Import(DynamicProxyFilterConfig.class)
//@Import(ProxyFactoryConfigV1.class)
//@Import(ProxyFactoryConfigV2.class)
//@Import(BeanPostProcessorConfig.class)
//@Import(AutoProxyConfig.class)
@Import(AopConfig.class)
@SpringBootApplication(scanBasePackages = "hello2.proxy.app")
public class ProxyApplication {

    AopAutoConfiguration aopAutoConfiguration = new AopAutoConfiguration();
    public static void main(String[] args) {
        SpringApplication.run(ProxyApplication.class, args);
    }

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }
}