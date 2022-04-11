package hello2.proxy.proxyfactory;

import hello2.proxy.common.advice.TimeAdvice;
import hello2.proxy.common.service.ServiceImpl;
import hello2.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ProxyFactoryTest {

    @Test
    @DisplayName("인터페이스가 있으면 JDK 동적 프록시가 사용됩니다.")
    void interfaceProxy() {
        ServiceImpl target = new ServiceImpl(); // save, find 의 구현체
        ProxyFactory proxyFactory = new ProxyFactory(target); // 프록시 factory 에 구현체를 등록 ( 기능 )
        proxyFactory.addAdvice(new TimeAdvice()); // advice 를 등록 ( 부가 기능 )
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy(); // 프록시를 가져옴
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());

        proxy.save();
        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue();
        assertThat(AopUtils.isCglibProxy(proxy)).isFalse();
    }
}
