package com.hyd.statemachine.config;

import com.hyd.statemachine.HandlerRule;
import com.hyd.statemachine.TransitionRule;

import java.util.HashSet;
import java.util.Set;

public class TransitionConfig<S extends Enum<?>, E extends Enum<?>, C> {

    private Set<TransitionRule<S, E>> transitionRules = new HashSet<>();

    private Set<HandlerRule<S, E, C>> handlerRules = new HashSet<>();

    public Set<HandlerRule<S, E, C>> getHandlerRules() {
        return handlerRules;
    }

    public void setHandlerRules(Set<HandlerRule<S, E, C>> handlerRules) {
        this.handlerRules = handlerRules;
    }

    public Set<TransitionRule<S, E>> getTransitionRules() {
        return transitionRules;
    }

    public void setTransitionRules(Set<TransitionRule<S, E>> transitionRules) {
        this.transitionRules = transitionRules;
    }
}
