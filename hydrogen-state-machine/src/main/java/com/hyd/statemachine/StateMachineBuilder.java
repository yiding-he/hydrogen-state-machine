package com.hyd.statemachine;

import com.hyd.statemachine.config.EventConfig;
import com.hyd.statemachine.config.StateConfig;
import com.hyd.statemachine.config.TransitionConfig;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static com.hyd.statemachine.util.ObjectValidation.nonNull;
import static java.util.Collections.unmodifiableSet;

public class StateMachineBuilder<S extends Enum<?>, E extends Enum<?>, C> {

    private final StateConfig<S> stateConfig = new StateConfig<>();

    private final EventConfig<E> eventConfig = new EventConfig<>();

    private final TransitionConfig<S, E, C> transitionConfig = new TransitionConfig<>();

    public static <S extends Enum<?>, E extends Enum<?>, C>
    StateMachineBuilder<S, E, C> newBuilder(
        Class<S> stateType, Class<E> eventType, Class<C> contextType
    ) {
        return new StateMachineBuilder<>(stateType, eventType, contextType);
    }

    public StateMachineBuilder(Class<S> stateType, Class<E> eventType, Class<C> contextType) {
        Set<S> stateSet = unmodifiableSet(new HashSet<>(Arrays.asList(stateType.getEnumConstants())));
        Set<E> eventSet = unmodifiableSet(new HashSet<>(Arrays.asList(eventType.getEnumConstants())));

        stateConfig.setStateSet(stateSet);
        eventConfig.setEventSet(eventSet);
    }

    public final StateMachineBuilder<S, E, C> addRule(S source, S target, E event) {
        nonNull("Source state should not be null", source);
        nonNull("Target state should not be null", target);
        nonNull("Event should not be null", event);
        this.transitionConfig.getTransitionRules().add(new TransitionRule<>(source, target, event));
        return this;
    }

    public final StateMachineBuilder<S, E, C> addRules(Collection<TransitionRule<S, E>> transitionRules) {
        nonNull("Transition rules should not be null", transitionRules);
        this.transitionConfig.getTransitionRules().addAll(transitionRules);
        return this;
    }

    public final StateMachineBuilder<S, E, C> addHandler(
        S source, S target, E event, StateChangeHandler<S, E, C> handler
    ) {
        nonNull("Handler should not be null", handler);
        this.transitionConfig.getHandlerRules().add(new HandlerRule<>(source, target, event, handler));
        return this;
    }

    public final StateMachineBuilder<S, E, C> addHandlers(Collection<HandlerRule<S, E, C>> handlerRules) {
        nonNull("Handler rules should not be null", handlerRules);
        this.transitionConfig.getHandlerRules().addAll(handlerRules);
        return this;
    }

    public StateMachine<S, E, C> build() {
        return new StateMachine<>(stateConfig, eventConfig, transitionConfig);
    }
}
