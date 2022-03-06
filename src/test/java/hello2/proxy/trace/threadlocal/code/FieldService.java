package hello2.proxy.trace.threadlocal.code;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;

@Slf4j
public class FieldService {

    private String nameStore;

    public String logic(String name) {
        log.info("저장 name={} -> nameStore={}" , name, nameStore);
        nameStore = name;
        sleep(1000);
        log.info("조회 nameStroe={}", nameStore);
        return nameStore;
    }

    public void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
