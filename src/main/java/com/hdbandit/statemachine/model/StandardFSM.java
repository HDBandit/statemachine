package com.hdbandit.statemachine.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class StandardFSM<E, S> implements FSM<E, S>{
    
    private Set<Transition<E, S>> transitions;
    private HashMap<S, List<ActionListener>> actionListeners;
    private S status;
    
    public StandardFSM(Set<Transition<E, S>> transitions) {
        this.transitions = transitions;
    }
    
    public StandardFSM(S status, Set<Transition<E, S>> transitions) {
        this.transitions = transitions;
        this.status = status;
    }
    
    public void registerActionListener(S status, ActionListener actionListener) {
        if (!actionListeners.containsKey(status)) {
            actionListeners.put(status, new ArrayList<>());
        }
        actionListeners.get(status).add(actionListener);
    }

    @Override
    public void sendEvent(E event) {
        for (Transition<E, S> transition : transitions) {
            if (transition.getFrom().equals(status) && transition.getEvent().equals(event)) {
                this.status = transition.getTo();
                actionListeners.get(this.status).stream().forEach(a -> a.action());
                return;
            }
        }
        throw new IllegalArgumentException(String.format("No transition defined from status: %s with event: %s", status, event));
    }

    @Override
    public S getStatus() {
        return status;
    }  

}
