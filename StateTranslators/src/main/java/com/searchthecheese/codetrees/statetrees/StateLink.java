package com.searchthecheese.codetrees.statetrees;

import java.io.Serializable;

/**
 * Created by Chandramouliswaran on 5/3/2018.
 */

public class StateLink implements Serializable {
    private StateNode from,to;

    public StateLink(StateNode from, StateNode to) {
        this.from = from;
        this.to = to;
    }

    public StateLink() {
    }

    public StateNode getFrom() {
        return from;
    }

    public void setFrom(StateNode from) {
        this.from = from;
    }

    public StateNode getTo() {
        return to;
    }

    public void setTo(StateNode to) {
        this.to = to;
    }
}
