package com.hyd.statemachine;

public class TransitionRule<S extends Enum<?>, E extends Enum<?>> {

    private final S source;

    private final S target;

    private final E event;

    public TransitionRule(S source, S target, E event) {
        this.source = source;
        this.target = target;
        this.event = event;
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

    @Override
    public String toString() {
        return "TransitionRule{" +
            "source=" + source +
            ", target=" + target +
            ", event=" + event +
            '}';
    }
}
