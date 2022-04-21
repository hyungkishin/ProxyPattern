package hello2.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanPostProcessorTest {

    @Test
    void basicConfig() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BasicPostProcessorConfig.class);

        // beanA 이름으로 B 객체가 등록된다.
        B b = applicationContext.getBean("beanA", B.class);
        b.helloB();

        // B 는 빈으로 등록되지 않는다.
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> applicationContext.getBean(A.class));
    }

    @Configuration
    static class BasicPostProcessorConfig {
        @Bean(name = "beanA")
        public A a() { return new A(); }

        @Bean
        public AtoBPostProcessor helloPostProcessor() {
            return new AtoBPostProcessor();
        }
    }

    @Slf4j
    static class A {
        public void helloA() { log.info("helloA"); }
    }

    @Slf4j
    static class B {
        public void helloB() { log.info("hello B"); }
    }

    @Slf4j
    static class AtoBPostProcessor implements BeanPostProcessor {

        @Override
        public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
            log.info("beanName={} bean={}",beanName, bean);
            if (bean instanceof A) {
                return new B();
            }
            return bean;
        }
    }
}
