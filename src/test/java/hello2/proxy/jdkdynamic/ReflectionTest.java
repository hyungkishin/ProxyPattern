package hello2.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection0() {
        Hello target = new Hello();

        // 공통 로직 1 시작
        log.info("start");
        String result1 = target.callA(); // 호출하는 메서드가 다름
        log.info("result={}", result1);
        // 공통 로직1 종료

        // 공통 로직 2 시작
        log.info("start");
        String result2 = target.callB(); // 호출하는 메서드가 다름
        log.info("result={}", result2);
        // 공통 로직2 종료

    }

    @Test
    void reflection1() throws Exception {
        // 클래스 정보 -> 클래스 메타정보를 획득한다. 내부 클래스는 구분을 위해 $ (달라) 를 사용.
        Class classHello = Class.forName("hello2.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        // callA 메서드 정보 -> getMethod ( 메소드 메타정보를 획득 ) methodCallA 는 Method 자체의 메타라 보면 된다.
        Method methodCallA = classHello.getMethod("callA");
        // 획득한 메타정보로 실제 인스턴스의 메서드를 호출한다.
        Object result1 = methodCallA.invoke(target);

        log.info("result1={}", result1);

        // callA 메서드 정보
        Method methodCallB = classHello.getMethod("callB");
        Object result2 = methodCallB.invoke(target);
        log.info("result1={}", result2);
    }

    @Test
    void reflection2() throws Exception {
        // 클래스 정보
        Class classHello = Class.forName("hello2.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        // callA 메서드 정보
        Method methodCallA = classHello.getMethod("callA");
        dynamicCall(methodCallA, target);

        // callA 메서드 정보
        Method methodCallB = classHello.getMethod("callB");
        dynamicCall(methodCallB, target);
    }

    /**
     *
     * @param method
     * @param target
     * @throws Exception
     *
     * 공통로직 1,2 를 한번에 처리할 수 있는 통합 공통 로직.
     * 기존엔 메서드 이름을 직접 호출했지만, 이제는 Method 라는 메타정보를 통해서 호출할 메서드 정보가 동적으로 제공된다.
     */
    private void dynamicCall(Method method, Object target) throws Exception {
        log.info("start");
        Object result = method.invoke(target);
        log.info("result={}", result);
    }

    @Slf4j
    static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B";
        }
    }
}
