package com.searchthecheese.codetrees.states;

import java.io.Serializable;

/**
 * Created by Chandramouliswaran on 5/3/2018.
 */

public class Script implements Serializable {
    private String scriptLabel;
    private String script;

    public Script(String scriptLabel, String script) {
        this.scriptLabel = scriptLabel;
        this.script = script;
    }

    public Script() {
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getScriptLabel() {
        return scriptLabel;
    }

    public void setScriptLabel(String scriptLabel) {
        this.scriptLabel = scriptLabel;
    }

    public static Word applyScript(Script script,Word word) {
        return word;
    }

    @Override
    public String toString() {
        return "Script{" +
                "scriptLabel='" + scriptLabel + '\'' +
                ", script='" + script + '\'' +
                '}';
    }
}
