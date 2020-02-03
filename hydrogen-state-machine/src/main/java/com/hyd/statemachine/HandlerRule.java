package com.hyd.statemachine;

public class HandlerRule<O, S extends Enum<?>, E, T extends Enum<?>> {

    private final S source;

    private final S target;

    private final T eventType;

    private final StateChangeHandler<O, S, E, T> handler;

    public HandlerRule(S source, S target, T eventType, StateChangeHandler<O, S, E, T> handler) {
        this.source = source;
        this.target = target;
        this.eventType = eventType;
        this.handler = handler;
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

    public StateChangeHandler<O, S, E, T> getHandler() {
        return handler;
    }

    public boolean match(S source, S target, T eventType) {
        return
            (this.source == source || this.source == null) &&
                (this.target == target || this.target == null) &&
                (this.eventType == eventType || this.eventType == null)
            ;
    }

    @Override
    public String toString() {
        return "HandlerRule{" +
            "source=" + source +
            ", target=" + target +
            ", eventType=" + eventType +
            '}';
    }
}
