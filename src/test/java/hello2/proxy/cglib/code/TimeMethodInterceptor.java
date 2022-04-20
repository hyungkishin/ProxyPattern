package hello2.proxy.cglib.code;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Slf4j
public class TimeMethodInterceptor implements MethodInterceptor {

    private final Object target;

    public TimeMethodInterceptor(final Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(final Object obj, final Method method, final Object[] args, final MethodProxy methodProxy) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();

        // methodProxy 를 사용하면 좀더 빠르다고 하다.
        Object result = methodProxy.invoke(target, args);
        // Object result = method.invoke(target, args);

        long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;
        log.info("TimeProxy 종료 resultTime={}", resultTime);

        return result;
    }
}
