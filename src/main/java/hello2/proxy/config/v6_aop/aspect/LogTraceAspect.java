package hello2.proxy.config.v6_aop.aspect;

import hello2.proxy.trace.TraceStatus;
import hello2.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.lang.reflect.Method;

@Slf4j
@Aspect // @Aspect -> Advice, Advisor, pointcut 를 편리하게 생성해준다 보면된다.
public class LogTraceAspect {

    private final LogTrace logTrace;

    public LogTraceAspect(final LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    /**
     * Advice 로직이 내부에 들어가고, @Around 부분은 포인트 컷이다. 이 둘은 어드바이저(조언자) 가 된다.
     * 지금까지 어드바이스 클래스 만들고, 포인트컷 만들어 줬던것을 @Aspect 가 편하게 해준다 보면 된다.
     * @Aspect : 애노테이션 기반 프록시를 적용할 때 필요하다.
         * @Around("execution(* hello.proxy.app..*(..))")
         * @Around 의 값에 포인트컷 표현식을 넣는다. 표현식은 AspectJ 표현식을 사용한다.
         * @Around 의 메서드는 어드바이스( Advice )가 된다.
     * ProceedingJoinPoint joinPoint : 어드바이스에서 살펴본 MethodInvocation invocation 과 유사한 기능이다.
     * 내부에 실제 호출 대상, 전달 인자, 그리고 어떤 객체와 어떤 메서드가 호출되었는지 정보가 포함되어 있다.
     * joinPoint.proceed() : 실제 호출 대상( target )을 호출한다.
     */
    @Around("execution(* hello2.proxy.app..*(..))")
    public Object excute(ProceedingJoinPoint joinPoint) throws Throwable {

        TraceStatus status = null;

//        log.info("target={}", joinPoint.getTarget()); // 실제 호출 대상
//        log.info("getArgs={}", joinPoint.getArgs()); // 전달인자
//        log.info("getSignature={}", joinPoint.getSignature()); // join Point 시그니처

        try {
            String message = joinPoint.getSignature().toShortString();
            status = logTrace.begin(message);

            // 로직 호출
            Object result = joinPoint.proceed();

            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
