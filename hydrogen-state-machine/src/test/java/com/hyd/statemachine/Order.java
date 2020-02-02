package com.hyd.statemachine;

public class Order {

    private long orderId = System.currentTimeMillis();

    private OrderStates orderState = OrderStates.Init;

    public Order() {
    }

    public Order(long orderId) {
        this.orderId = orderId;
    }

    public Order(long orderId, OrderStates orderState) {
        this.orderId = orderId;
        this.orderState = orderState;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public OrderStates getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderStates orderState) {
        this.orderState = orderState;
    }
}
