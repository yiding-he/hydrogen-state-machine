package com.hyd.statemachine;

import com.hyd.statemachine.config.EventConfig;
import com.hyd.statemachine.config.StateConfig;
import com.hyd.statemachine.config.TransitionConfig;

import java.util.Collections;
import java.util.Set;

/**
 * 状态机的默认实现
 *
 * <ul>
 *     <li>状态机是线程安全的</li>
 *     <li>状态机的处理是同步的，在当前线程中进行</li>
 * </ul>
 *
 * @param <S> 状态类型
 * @param <E> 事件类型
 * @param <C> 事件上下文对象类型
 */
public class StateMachine<S extends Enum<?>, E extends Enum<?>, C> {

    /**
     * 状态配置
     */
    private final StateConfig<S> stateConfig;

    /**
     * 事件配置
     */
    private final EventConfig<E> eventConfig;

    /**
     * 状态转换规则配置
     */
    private final TransitionConfig<S, E, C> transitionConfig;

    /**
     * 构造方法
     *
     * @param stateConfig      有哪些状态
     * @param eventConfig      有哪些事件
     * @param transitionConfig 有哪些规则
     */
    public StateMachine(
        StateConfig<S> stateConfig,
        EventConfig<E> eventConfig,
        TransitionConfig<S, E, C> transitionConfig
    ) {
        this.stateConfig = stateConfig;
        this.eventConfig = eventConfig;
        this.transitionConfig = transitionConfig;
    }

    public Set<S> getStateSet() {
        return this.stateConfig.getStateSet();
    }

    public Set<E> getEventSet() {
        return this.eventConfig.getEventSet();
    }

    public Set<TransitionRule<S, E>> getTransitionRules() {
        return Collections.unmodifiableSet(this.transitionConfig.getTransitionRules());
    }

    public Set<HandlerRule<S, E, C>> getHandlerRules() {
        return Collections.unmodifiableSet(this.transitionConfig.getHandlerRules());
    }

    /**
     * 执行处理
     *
     * @param state   当前状态
     * @param event   当前事件类型
     * @param context 本次处理的上下文信息，将会传递给 {@link StateChangeHandler}
     */
    public void process(S state, E event, C context) {
        TransitionRule<S, E> rule = this.transitionConfig.getTransitionRules()
            .stream()
            .filter(r -> r.getSource() == state && r.getEvent() == event)
            .findFirst()
            .orElseThrow(() -> new UnsupportedOperationException(
                "Unable to match transition rule, state=" + state + ", event=" + event
            ));

        this.transitionConfig.getHandlerRules()
            .stream()
            .filter(r -> r.match(state, rule.getTarget(), event))
            .findFirst()
            .ifPresent(handlerRule ->
                handlerRule.getHandler().stateChanged(state, rule.getTarget(), event, context)
            );
    }
}
