package com.searchthecheese.codetrees.states;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Chandramouliswaran on 5/3/2018.
 */

public class State implements Serializable {

    private String stateLabel;
    private Set<Word> stateOutcomes;
    private Set<Script> scripts = new HashSet<Script>();

    public State(String stateLabel) {
        this.stateLabel = stateLabel;
    }

    public String getStateLabel() {
        return stateLabel;
    }

    public void setStateLabel(String stateLabel) {
        this.stateLabel = stateLabel;
    }

    public State() {
    }

    public Set<Word> getStateOutcomes() {
        return stateOutcomes;
    }

    public State addOutcome(Word word) {
        if(this.getStateOutcomes()==null) {
            this.setStateOutcomes(new HashSet<Word>());
        }

        this.getStateOutcomes().add(word);

        return this;
    }

    public void setStateOutcomes(Set<Word> stateOutcomes) {
        this.stateOutcomes = stateOutcomes;
    }

    public Set<Script> getScripts() {
        return scripts;
    }

    public State addScript(Script script) {
        if(this.getScripts()==null) {
            this.setScripts(new HashSet<Script>());
        }

        this.getScripts().add(script);

        return this;
    }

    public void setScripts(Set<Script> scripts) {
        this.scripts = scripts;
    }

    @Override
    public String toString() {
        return "State{" +
                "stateLabel='" + stateLabel + '\'' +
                ", stateOutcomes=" + stateOutcomes +
                ", scripts=" + scripts +
                '}';
    }
}
