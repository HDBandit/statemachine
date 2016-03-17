package com.hdbandit.statemachine.model;

public class Transition<E, S> {
    
    private S from;
    private S to;
    private E event;

    public Transition(S from, E event, S to) {
       this.from = from;
       this.to = to;
       this.event = event;
    }

    public S getFrom() {
        return from;
    }

    public S getTo() {
        return to;
    }

    public E getEvent() {
        return event;
    }

}
