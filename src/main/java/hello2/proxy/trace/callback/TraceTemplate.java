package hello2.proxy.trace.callback;

import hello2.proxy.trace.TraceStatus;
import hello2.proxy.trace.logtrace.LogTrace;

public class TraceTemplate {

    private final LogTrace trace;

    public TraceTemplate(LogTrace trace) {
        this.trace = trace;
    }

    public <T> T excute(String message, TraceCallback<T> callback) {
        TraceStatus status = null;

        try {
            status = trace.begin(message);
            // 로직 호출
            T result = callback.call();

            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

}
