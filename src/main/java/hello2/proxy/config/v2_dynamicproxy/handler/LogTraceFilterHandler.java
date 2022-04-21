package hello2.proxy.config.v2_dynamicproxy.handler;

import hello2.proxy.trace.TraceStatus;
import hello2.proxy.trace.logtrace.LogTrace;
import org.springframework.util.PatternMatchUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogTraceFilterHandler implements InvocationHandler {

    private final Object target;
    private final LogTrace logTrace;
    private final String[] pattern;

    public LogTraceFilterHandler(final Object target, final LogTrace logTrace, final String[] pattern) {
        this.target = target;
        this.logTrace = logTrace;
        this.pattern = pattern;
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {

        // 메서드 이름 필터
        String methodName = method.getName();

        // save, request, reque*, *est
        if (!PatternMatchUtils.simpleMatch(pattern, methodName)) {
            return method.invoke(target, args);
        }

        TraceStatus status = null;
        try {
            String message = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
            status = logTrace.begin(message);

            // 로직 호출
            Object result = method.invoke(target, args);
            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
