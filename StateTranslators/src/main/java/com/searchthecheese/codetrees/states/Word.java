package com.searchthecheese.codetrees.states;

import java.io.Serializable;

/**
 * Created by Chandramouliswaran on 5/3/2018.
 */

public class Word implements Serializable {

    private String word;

    public Word() {
    }

    public Word(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return "Word{" +
                "word='" + word + '\'' +
                '}';
    }
}
