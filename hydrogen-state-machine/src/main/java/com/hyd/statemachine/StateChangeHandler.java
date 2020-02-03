package com.hyd.statemachine;

@FunctionalInterface
public interface StateChangeHandler<O, S extends Enum<?>, E, T extends Enum<?>> {

    /**
     * 处理状态机输出
     *
     * @param source 处理前的状态
     * @param target 处理后的状态
     * @param object 状态变化的对象
     * @param event  事件对象
     */
    void stateChanged(S source, S target, O object, E event);
}
