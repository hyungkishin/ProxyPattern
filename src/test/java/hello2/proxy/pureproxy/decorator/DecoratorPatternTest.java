package hello2.proxy.pureproxy.decorator;

import hello2.proxy.pureproxy.decorator.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class DecoratorPatternTest {

    @Test
    void noDecorator(){
        Component realComponent = new RealComponent();
        DecoratorPatternClient client = new DecoratorPatternClient(realComponent);
        client.excute();
    }

    @Test
    void decorator1(){
        Component realComponent = new RealComponent();
        Component messageDecorator = new MessageDecorator(realComponent); // 기능
        DecoratorPatternClient client = new DecoratorPatternClient(messageDecorator); // View
        client.excute();
    }

    @Test
    void decorator2(){
        Component realComponent = new RealComponent();
        Component messageDecorator = new MessageDecorator(realComponent); // 기능
        Component timeDecorator = new TimeDecorator(messageDecorator);
        DecoratorPatternClient client = new DecoratorPatternClient(timeDecorator); // View
        client.excute();
    }
}
