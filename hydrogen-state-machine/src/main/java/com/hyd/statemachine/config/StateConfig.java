package com.hyd.statemachine.config;

import java.util.Set;

public class StateConfig<S extends Enum<?>> {

    private Set<S> stateSet;

    public Set<S> getStateSet() {
        return stateSet;
    }

    public void setStateSet(Set<S> stateSet) {
        this.stateSet = stateSet;
    }
}
