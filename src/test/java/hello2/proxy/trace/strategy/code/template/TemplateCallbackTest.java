package hello2.proxy.trace.strategy.code.template;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateCallbackTest {

    /**
     * 템플릿 콜백 패턴 - 익명 클래스
     */
    @Test
    void  callBackV1() {
        TimeLogTemplate template = new TimeLogTemplate();
        template.excute(() -> log.info("비즈니스 로직 1 실행"));
        template.excute(() -> log.info("비즈니스 로직 2 실행"));
    }
}
