### 리플렉션

자바의 리플렉션 기술을 이용하면, 클래스나 메서드의 메타정보를 동적으로 획득하고,
코드도 동적으로 호출 할 수 있다.


### 아래 코드
- 공통로직 1과 로직 2 는 호출하는 메서드만 다르고 전체 코드 흐름이 완전히 같다.
  - 먼저 start 로그를 출력한다.
  - 어떤 메서드를 호출한다.
  - 메서드의 호출 결과를 로그로 출력한다.
- 여기서 공통 로직1 과 공통 로직2 를 하나의 메서드로 뽑아서 합칠 수 있다.
- target.callA() 와 target.callB() 부분만 동적으로 처리 할 수 있다면 ...?
```java
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
```


```java
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
```
### 쉽게 이해하기 위해...
- 필요한 재료 : 클래스의 메타정보, 클래스의 메서드 정보 (Hello 라는 클래스 안에 callA 메서드가 있다고 가정.)
  - STEP1 클래스의 메타정보를 가져온다. Class classHello = Class.forName("package 주소 따라라라라.$해당클래스")
  - STEP2 Hello target = new Hello(); 생성자 호출로 인스턴스를 만들어 놓는다.
  - STEP3 메서드 정보를 얻어온다 (문자로) -> Method methodCallA = classHello.getMethod("callA")
  - STEP4 동적으로 Call 한다. -> methodCallA.invoke(target); // 만들어둔 인스턴스에 있는 callA 메서드를 호출한다.
---
- 여기서 제일 중요한점은 클래스나 메서드 정보를 동적으로 변경할 수 있다는 점.
- 영한님이 말씀하시기를, 리플렉션을 사용하면, 클래스와 메서드의 메타정보를
- 사용해서 어플리케이션을 동적으로 유연하게 만들 수 있다고 하셨다.
- 그러나 리플렉션 기술은 런타임에 동작하기 때문에, 컴파일 시점에 오류를 잡을 수 없다.
- 예를 들어 지금까지 살펴본 코드에서 getMethod("callA") 안에 들어가는 문자를 실수로 작성해도 
- 컴파일 오류가 발생한다.
- 가장 좋은 오류는 개발자가 즉시 확인할 수 있는 컴파일 오류이고, 가장 무서운 오류는 사용자가 직접 실행할ㄷ 때 발생하는 런타임 오류다.
- 따라서 리플렉션은 일반적으로 사용하면 안된다. 프레임워크를 역행하는 꼴.

리플렉션은 프레임워크 개발이나 또는 매우 일반적인 공통 처리가 필요할 때 부분적으로 주의해서 사용해야 한다.