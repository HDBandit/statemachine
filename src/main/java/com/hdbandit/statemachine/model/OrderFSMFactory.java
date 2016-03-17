package com.hdbandit.statemachine.model;

import java.util.HashSet;
import java.util.Set;

public class OrderFSMFactory {
    
    public static FSM<OrderEvent, OrderStatus> getInstance() {  
       return getInstance(OrderStatus.PENDING);
    }
    
    public static FSM<OrderEvent, OrderStatus> getInstance(OrderStatus initialStatus) {
        Set<Transition<OrderEvent, OrderStatus>> transitions = new HashSet<>();
        // define transitions
        transitions.add(new Transition<OrderEvent, OrderStatus>(OrderStatus.PENDING, OrderEvent.CONFIRM, OrderStatus.IN_PROGRESS));
        transitions.add(new Transition<OrderEvent, OrderStatus>(OrderStatus.IN_PROGRESS, OrderEvent.DELIVERED, OrderStatus.COMPLETED));
        transitions.add(new Transition<OrderEvent, OrderStatus>(OrderStatus.REJECTED, OrderEvent.CONFIRM, OrderStatus.PENDING));
        transitions.add(new Transition<OrderEvent, OrderStatus>(OrderStatus.IN_PROGRESS, OrderEvent.CANCELED, OrderStatus.REJECTED));
        transitions.add(new Transition<OrderEvent, OrderStatus>(OrderStatus.REJECTED, OrderEvent.CANCELED, OrderStatus.REJECTED));
        transitions.add(new Transition<OrderEvent, OrderStatus>(OrderStatus.PENDING, OrderEvent.CANCELED, OrderStatus.REJECTED));
       
        StandardFSM<OrderEvent, OrderStatus> machine = new StandardFSM<>(initialStatus, transitions);
        // define action listeners
        machine.registerActionListener(OrderStatus.IN_PROGRESS, ()-> System.out.println("La orden esta pendiente!!"));
        machine.registerActionListener(OrderStatus.COMPLETED, ()-> System.out.println("La orden ha sido completada!!"));
        machine.registerActionListener(OrderStatus.REJECTED, ()-> System.out.println("La orden ha sido rejectada!!"));
        machine.registerActionListener(OrderStatus.PENDING, ()-> System.out.println("La orden esta pendiente!!"));
        
        return machine;
     }

}
