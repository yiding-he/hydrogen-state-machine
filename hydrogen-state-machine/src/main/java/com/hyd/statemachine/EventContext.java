package com.hyd.statemachine;

public class EventContext<S extends Enum<?>, E extends Enum<?>, C> {

    private S sourceState;

    private S targetState;

    private E event;

    private C context;

    public S getSourceState() {
        return sourceState;
    }

    public void setSourceState(S sourceState) {
        this.sourceState = sourceState;
    }

    public S getTargetState() {
        return targetState;
    }

    public void setTargetState(S targetState) {
        this.targetState = targetState;
    }

    public E getEvent() {
        return event;
    }

    public void setEvent(E event) {
        this.event = event;
    }

    public C getContext() {
        return context;
    }

    public void setContext(C context) {
        this.context = context;
    }
}
