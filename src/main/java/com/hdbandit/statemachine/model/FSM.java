package com.hdbandit.statemachine.model;

// Finite State Machine contract
public interface FSM <E, S> {
    
    void sendEvent(E event);
    
    S getStatus();

}
