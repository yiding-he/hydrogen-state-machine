package com.hyd.statemachine.config;

import java.util.Set;
import java.util.function.Function;

public class StateConfig<O, S extends Enum<?>> {

    private Set<S> stateSet;

    private Function<O, S> stateExtractor;

    public Function<O, S> getStateExtractor() {
        return stateExtractor;
    }

    public void setStateExtractor(Function<O, S> stateExtractor) {
        this.stateExtractor = stateExtractor;
    }

    public Set<S> getStateSet() {
        return stateSet;
    }

    public void setStateSet(Set<S> stateSet) {
        this.stateSet = stateSet;
    }
}
