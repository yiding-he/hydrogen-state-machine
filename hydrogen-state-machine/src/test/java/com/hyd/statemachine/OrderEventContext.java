package com.hyd.statemachine;

public class OrderEventContext {

    private Order order;

    public OrderEventContext(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
