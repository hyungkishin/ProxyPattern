package hello2.proxy.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * 전략을 파라미터로 전달하는 방식
 * 전략을 필드로 가지지 않고, 전략을 excute 가 호출될 때 마다 항상 파라미터로 전달 받는다.
 */
@Slf4j
public class ContextV2 {

    public void excute(Strategy strategy) {
        long startTime = System.currentTimeMillis();
        // 비즈니스 로직 실행
        strategy.call(); // 위임
        // 비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}
