package com.hyd.statemachine;

public class HandlerRule<S extends Enum<?>, E extends Enum<?>, C> {

    private final S source;

    private final S target;

    private final E event;

    private final StateChangeHandler<S, E, C> handler;

    public HandlerRule(S source, S target, E event, StateChangeHandler<S, E, C> handler) {
        this.source = source;
        this.target = target;
        this.event = event;
        this.handler = handler;
    }

    public S getSource() {
        return source;
    }

    public S getTarget() {
        return target;
    }

    public E getEvent() {
        return event;
    }

    public StateChangeHandler<S, E, C> getHandler() {
        return handler;
    }

    public boolean match(S source, S target, E event) {
        return
            (this.source == source || this.source == null) &&
            (this.target == target || this.target == null) &&
            (this.event == event || this.event == null)
            ;
    }

    @Override
    public String toString() {
        return "HandlerRule{" +
            "source=" + source +
            ", target=" + target +
            ", event=" + event +
            '}';
    }
}
