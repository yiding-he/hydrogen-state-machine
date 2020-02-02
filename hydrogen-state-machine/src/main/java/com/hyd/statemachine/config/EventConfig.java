package com.hyd.statemachine.config;

import java.util.Set;

public class EventConfig<E extends Enum<?>> {

    private Set<E> eventSet;

    public Set<E> getEventSet() {
        return eventSet;
    }

    public void setEventSet(Set<E> eventSet) {
        this.eventSet = eventSet;
    }
}
