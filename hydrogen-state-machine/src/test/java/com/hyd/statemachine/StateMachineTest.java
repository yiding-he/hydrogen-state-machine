package com.hyd.statemachine;

import org.junit.Test;

public class StateMachineTest {

    @Test
    public void testBuild() throws Exception {

        StateChangeHandler<OrderStates, OrderEventTypes, OrderEventContext> orderPaidHandler =
            (source, target, event, context) ->
                System.out.println("订单 " + context.getOrder().getOrderId() + " 支付成功。");

        StateChangeHandler<OrderStates, OrderEventTypes, OrderEventContext> orderCancelledHandler =
            (source, target, event, context) ->
                System.out.println("订单 " + context.getOrder().getOrderId() + " 已取消支付。");

        StateMachineBuilder<OrderStates, OrderEventTypes, OrderEventContext>
            builder = StateMachineBuilder
            .newBuilder(OrderStates.class, OrderEventTypes.class, OrderEventContext.class)

            .addRule(OrderStates.Init, OrderStates.Paid, OrderEventTypes.OrderPaid)
            .addRule(OrderStates.Paid, OrderStates.Fulfilled, OrderEventTypes.OrderFulfilled)
            .addRule(OrderStates.Init, OrderStates.Cancelled, OrderEventTypes.OrderCancelled)
            .addRule(OrderStates.Paid, OrderStates.Cancelled, OrderEventTypes.OrderCancelled)

            .addHandler(null, OrderStates.Paid, null, orderPaidHandler)
            .addHandler(null, OrderStates.Cancelled, null, orderCancelledHandler);

        StateMachine<OrderStates, OrderEventTypes, OrderEventContext>
            stateMachine = builder.build();

        System.out.println(stateMachine.getEventSet());
        System.out.println(stateMachine.getStateSet());
        System.out.println(stateMachine.getTransitionRules());
        System.out.println(stateMachine.getHandlerRules());
        System.out.println();

        stateMachine.process(OrderStates.Init, OrderEventTypes.OrderPaid, new OrderEventContext(new Order(1)));
        stateMachine.process(OrderStates.Init, OrderEventTypes.OrderCancelled, new OrderEventContext(new Order(2)));

        Order order3 = new Order(3, OrderStates.Cancelled);
        stateMachine.process(order3.getOrderState(), OrderEventTypes.OrderPaid, new OrderEventContext(order3));
    }

}
