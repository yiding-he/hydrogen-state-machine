package com.hyd.statemachine;

import static com.hyd.statemachine.util.ObjectValidation.nonNull;
import static java.util.Collections.unmodifiableSet;

import com.hyd.statemachine.config.EventConfig;
import com.hyd.statemachine.config.StateConfig;
import com.hyd.statemachine.config.TransitionConfig;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class StateMachineBuilder<O, S extends Enum<?>, E, T extends Enum<?>> {

    private final StateConfig<O, S> stateConfig = new StateConfig<>();

    private final EventConfig<E, T> eventConfig = new EventConfig<>();

    private final TransitionConfig<O, S, E, T> transitionConfig = new TransitionConfig<>();

    public static <O, S extends Enum<?>, E, T extends Enum<?>>
    StateMachineBuilder<O, S, E, T> newBuilder(
        Class<O> objectClass, Class<S> stateEnum, Class<E> eventClass, Class<T> eventTypeEnum
    ) {
        return new StateMachineBuilder<>(stateEnum, eventTypeEnum);
    }

    public StateMachineBuilder(Class<S> stateEnum, Class<T> eventTypeEnum) {
        Set<S> stateSet = unmodifiableSet(new HashSet<>(Arrays.asList(stateEnum.getEnumConstants())));
        Set<T> eventSet = unmodifiableSet(new HashSet<>(Arrays.asList(eventTypeEnum.getEnumConstants())));

        stateConfig.setStateSet(stateSet);
        eventConfig.setEventSet(eventSet);
    }

    public final StateMachineBuilder<O, S, E, T> setStateExtractor(Function<O, S> extractor) {
        nonNull("State extractor should not be null", extractor);
        this.stateConfig.setStateExtractor(extractor);
        return this;
    }

    public final StateMachineBuilder<O, S, E, T> setEventTypeExtractor(Function<E, T> eventTypeExtractor) {
        nonNull("Event type extractor should not be null", eventTypeExtractor);
        this.eventConfig.setEventTypeExtractor(eventTypeExtractor);
        return this;
    }

    public final StateMachineBuilder<O, S, E, T> addRule(S source, S target, T eventType) {
        nonNull("Source state should not be null", source);
        nonNull("Target state should not be null", target);
        nonNull("Event type should not be null", eventType);
        this.transitionConfig.getTransitionRules().add(new TransitionRule<>(source, target, eventType));
        return this;
    }

    public final StateMachineBuilder<O, S, E, T> addRules(Collection<TransitionRule<S, T>> transitionRules) {
        nonNull("Transition rules should not be null", transitionRules);
        this.transitionConfig.getTransitionRules().addAll(transitionRules);
        return this;
    }

    public final StateMachineBuilder<O, S, E, T> addHandler(
        S source, S target, T eventType, StateChangeHandler<O, S, E, T> handler
    ) {
        nonNull("Handler should not be null", handler);
        this.transitionConfig.getHandlerRules().add(new HandlerRule<>(source, target, eventType, handler));
        return this;
    }

    public final StateMachineBuilder<O, S, E, T> addHandlers(Collection<HandlerRule<O, S, E, T>> handlerRules) {
        nonNull("Handler rules should not be null", handlerRules);
        this.transitionConfig.getHandlerRules().addAll(handlerRules);
        return this;
    }

    public StateMachine<O, S, E, T> build() {
        return new StateMachine<>(stateConfig, eventConfig, transitionConfig);
    }
}
