package com.hdbandit.statemachine.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.Transient;

@Entity
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private OrderStatus orderStatus;

    //  La marcamos como transient para no persistirla.
    //  Únicamente se persistirá la column orderStatus
    @Transient
    private FSM<OrderEvent, OrderStatus> fsm;
    
    public Order() {
        this.fsm = OrderFSMFactory.getInstance();
    }
    
    @PostLoad
    public void updateFSM() {
        this.fsm = OrderFSMFactory.getInstance(orderStatus);
    }

    public OrderStatus getStatus() {
        return fsm.getStatus();
    }

    public void sendEvent(OrderEvent orderEvent) {
        this.fsm.sendEvent(orderEvent);
    }
    
}
