package com.hyd.statemachine;

@FunctionalInterface
public interface StateChangeHandler<S extends Enum<?>, E extends Enum<?>, C> {

    void stateChanged(S source, S target, E event, C context);
}
