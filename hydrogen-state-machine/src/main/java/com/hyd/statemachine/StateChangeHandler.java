package com.hyd.statemachine;

@FunctionalInterface
public interface StateChangeHandler<S extends Enum<?>, E extends Enum<?>, C> {

    /**
     * 处理状态机输出
     *
     * @param source  处理前的状态
     * @param target  处理后的状态
     * @param event   事件类型
     * @param context 相关上下文
     */
    void stateChanged(S source, S target, E event, C context);
}
