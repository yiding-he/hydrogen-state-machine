package com.hyd.statemachine.config;

import java.util.Set;
import java.util.function.Function;

public class EventConfig<E, T extends Enum<?>> {

    private Set<T> eventSet;

    private Function<E, T> eventTypeExtractor;

    public Set<T> getEventSet() {
        return eventSet;
    }

    public void setEventSet(Set<T> eventSet) {
        this.eventSet = eventSet;
    }

    public Function<E, T> getEventTypeExtractor() {
        return eventTypeExtractor;
    }

    public void setEventTypeExtractor(Function<E, T> eventTypeExtractor) {
        this.eventTypeExtractor = eventTypeExtractor;
    }
}
