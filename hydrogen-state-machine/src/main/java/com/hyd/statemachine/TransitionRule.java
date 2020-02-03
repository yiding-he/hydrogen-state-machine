package com.hyd.statemachine;

public class TransitionRule<S extends Enum<?>, T extends Enum<?>> {

    private final S source;

    private final S target;

    private final T eventType;

    public TransitionRule(S source, S target, T eventType) {
        this.source = source;
        this.target = target;
        this.eventType = eventType;
    }

    public S getSource() {
        return source;
    }

    public S getTarget() {
        return target;
    }

    public T getEventType() {
        return eventType;
    }

    @Override
    public String toString() {
        return "TransitionRule{" +
            "source=" + source +
            ", target=" + target +
            ", eventType=" + eventType +
            '}';
    }
}
