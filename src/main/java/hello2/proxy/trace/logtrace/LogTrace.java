package hello2.proxy.trace.logtrace;

import hello2.proxy.trace.TraceStatus;

public interface LogTrace {

    TraceStatus begin(String message);

    void end(TraceStatus trace);

    void exception(TraceStatus status, Exception e);
}
