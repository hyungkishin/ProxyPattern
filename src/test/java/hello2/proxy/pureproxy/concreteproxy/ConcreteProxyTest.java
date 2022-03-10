package hello2.proxy.pureproxy.concreteproxy;

import hello2.proxy.pureproxy.concreteproxy.code.ConcreteClient;
import hello2.proxy.pureproxy.concreteproxy.code.ConcreteLogic;
import hello2.proxy.pureproxy.concreteproxy.code.TimeProxy;
import org.junit.jupiter.api.Test;

public class ConcreteProxyTest {

    @Test
    void noProxy() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        ConcreteClient client = new ConcreteClient(concreteLogic);
        client.excute();
    }

    @Test
    void addProxy() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        TimeProxy timeProxy = new TimeProxy(concreteLogic);
        ConcreteClient client = new ConcreteClient(timeProxy);
        client.excute();
    }
}
