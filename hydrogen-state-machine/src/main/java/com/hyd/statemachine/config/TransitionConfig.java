package com.hyd.statemachine.config;

import com.hyd.statemachine.HandlerRule;
import com.hyd.statemachine.TransitionRule;
import java.util.HashSet;
import java.util.Set;

public class TransitionConfig<O, S extends Enum<?>, E, T extends Enum<?>> {

    private Set<TransitionRule<S, T>> transitionRules = new HashSet<>();

    private Set<HandlerRule<O, S, E, T>> handlerRules = new HashSet<>();

    public Set<TransitionRule<S, T>> getTransitionRules() {
        return transitionRules;
    }

    public void setTransitionRules(Set<TransitionRule<S, T>> transitionRules) {
        this.transitionRules = transitionRules;
    }

    public Set<HandlerRule<O, S, E, T>> getHandlerRules() {
        return handlerRules;
    }

    public void setHandlerRules(Set<HandlerRule<O, S, E, T>> handlerRules) {
        this.handlerRules = handlerRules;
    }
}
