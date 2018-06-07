package com.searchthecheese.codetrees.statetrees;

import com.searchthecheese.codetrees.states.State;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Chandramouliswaran on 5/3/2018.
 */

public class StateNode implements Serializable {
    private StateNode parent;
    private State state;

    public StateNode(StateNode parent, State state) {
        this.parent = parent;
        this.state = state;
    }

    public StateNode() {
    }


    public StateNode getParent() {
        return parent;
    }

    public void setParent(StateNode parent) {
        this.parent = parent;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

}
