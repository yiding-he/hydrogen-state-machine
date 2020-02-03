package com.hyd.statemachine;

import com.hyd.statemachine.config.EventConfig;
import com.hyd.statemachine.config.StateConfig;
import com.hyd.statemachine.config.TransitionConfig;
import java.util.Collections;
import java.util.Set;

/**
 * 状态机的默认实现
 *
 * @param <O> Object 带状态的对象类型
 * @param <S> State 状态类型
 * @param <E> Event 事件对象类型
 * @param <T> Type 事件类型
 */
public class StateMachine<O, S extends Enum<?>, E, T extends Enum<?>> {

    /**
     * 状态配置
     */
    private final StateConfig<O, S> stateConfig;

    /**
     * 事件配置T
     */
    private final EventConfig<E, T> eventConfig;

    /**
     * 状态转换规则配置
     */
    private final TransitionConfig<O, S, E, T> transitionConfig;

    /**
     * 构造方法
     *
     * @param stateConfig      有哪些状态
     * @param eventConfig      有哪些事件
     * @param transitionConfig 有哪些规则
     */
    public StateMachine(
        StateConfig<O, S> stateConfig,
        EventConfig<E, T> eventConfig,
        TransitionConfig<O, S, E, T> transitionConfig
    ) {
        this.stateConfig = stateConfig;
        this.eventConfig = eventConfig;
        this.transitionConfig = transitionConfig;
    }

    public Set<S> getStateSet() {
        return this.stateConfig.getStateSet();
    }

    public Set<T> getEventSet() {
        return this.eventConfig.getEventSet();
    }

    public Set<TransitionRule<S, T>> getTransitionRules() {
        return Collections.unmodifiableSet(this.transitionConfig.getTransitionRules());
    }

    public Set<HandlerRule<O, S, E, T>> getHandlerRules() {
        return Collections.unmodifiableSet(this.transitionConfig.getHandlerRules());
    }

    /**
     * 根据事件进行状态处理
     *
     * @param object 带状态的对象
     * @param event  事件
     */
    public void process(O object, E event) {

        T eventType = this.eventConfig.getEventTypeExtractor().apply(event);
        S sourceState = this.stateConfig.getStateExtractor().apply(object);

        TransitionRule<S, T> rule = this.transitionConfig.getTransitionRules()
            .stream()
            .filter(r -> r.getSource() == sourceState && r.getEventType() == eventType)
            .findFirst()
            .orElseThrow(() -> new UnsupportedOperationException(
                "Unable to match transition rule, object=" + object + ", event=" + event
            ));

        this.transitionConfig.getHandlerRules()
            .stream()
            .filter(r -> r.match(sourceState, rule.getTarget(), eventType))
            .findFirst()
            .ifPresent(handlerRule ->
                handlerRule.getHandler().stateChanged(sourceState, rule.getTarget(), object, event)
            );
    }
}
