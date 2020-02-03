package com.hyd.statemachine;

public class OrderEvent {

    private Order order;

    private OrderEventTypes eventType;

    public OrderEvent(Order order, OrderEventTypes eventType) {
        this.order = order;
        this.eventType = eventType;
    }

    public Order getOrder() {
        return order;
    }

    public OrderEventTypes getEventType() {
        return eventType;
    }
}
