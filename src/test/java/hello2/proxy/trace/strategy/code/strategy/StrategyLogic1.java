package hello2.proxy.trace.strategy.code.strategy;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StrategyLogic1 implements Strategy {

    @Override
    public void call() {
        System.out.println("비즈니스 로직 1 실행");
    }
}
