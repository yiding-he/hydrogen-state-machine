package com.hyd.statemachine;

import org.junit.Test;

public class StateMachineTest {

    @Test
    public void testBuild() throws Exception {

        // 当订单支付时的处理
        StateChangeHandler<Order, OrderStates, OrderEvent, OrderEventTypes> orderPaidHandler =
            (source, target, event, context) ->
                System.out.println("订单 " + context.getOrder().getOrderId() + " 支付成功。");

        // 当订单取消时的处理
        StateChangeHandler<Order, OrderStates, OrderEvent, OrderEventTypes> orderCancelledHandler =
            (source, target, event, context) ->
                System.out.println("订单 " + context.getOrder().getOrderId() + " 已取消支付。");

        // 构建状态机对象
        StateMachineBuilder<Order, OrderStates, OrderEvent, OrderEventTypes>
            builder = StateMachineBuilder
            .newBuilder(Order.class, OrderStates.class, OrderEvent.class, OrderEventTypes.class)

            .setStateExtractor(Order::getOrderState)
            .setEventTypeExtractor(OrderEvent::getEventType)

            .addRule(OrderStates.Init, OrderStates.Paid, OrderEventTypes.OrderPaid)
            .addRule(OrderStates.Paid, OrderStates.Fulfilled, OrderEventTypes.OrderFulfilled)
            .addRule(OrderStates.Init, OrderStates.Cancelled, OrderEventTypes.OrderCancelled)
            .addRule(OrderStates.Paid, OrderStates.Cancelled, OrderEventTypes.OrderCancelled)

            .addHandler(null, OrderStates.Paid, null, orderPaidHandler)
            .addHandler(null, OrderStates.Cancelled, null, orderCancelledHandler);

        StateMachine<Order, OrderStates, OrderEvent, OrderEventTypes>
            stateMachine = builder.build();

        // 查看状态机的属性
        System.out.println(stateMachine.getEventSet());
        System.out.println(stateMachine.getStateSet());
        System.out.println(stateMachine.getTransitionRules());
        System.out.println(stateMachine.getHandlerRules());
        System.out.println();

        // 状态机根据已有规则进行计算
        Order order1 = new Order(1, OrderStates.Init);
        OrderEvent order1Paid = new OrderEvent(order1, OrderEventTypes.OrderPaid);
        stateMachine.process(order1, order1Paid);

        Order order2 = new Order(2, OrderStates.Paid);
        OrderEvent order2Cancelled = new OrderEvent(order2, OrderEventTypes.OrderCancelled);
        stateMachine.process(order2, order2Cancelled);

        // 如果状态机没有对应的规则，则会抛出异常
        try {
            Order order3 = new Order(3, OrderStates.Cancelled);
            OrderEvent order3Cancelled = new OrderEvent(order3, OrderEventTypes.OrderCancelled);
            stateMachine.process(order3, order3Cancelled);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
