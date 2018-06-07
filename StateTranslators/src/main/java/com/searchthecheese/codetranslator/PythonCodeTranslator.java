package com.searchthecheese.codetranslator;

import com.searchthecheese.codetrees.states.Script;
import com.searchthecheese.codetrees.states.State;
import com.searchthecheese.codetrees.states.Word;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chandramouliswaran on 5/3/2018.
 */

public class PythonCodeTranslator {
    private static List<State> states = new ArrayList<State>();

    public static final String pythonstates = "python.states";

    public static List<State> getStates() {
        return states;
    }

    public static void setStates(List<State> states) {
        PythonCodeTranslator.states = states;
    }

    public static List<State> loadStatesFromAssets(List<String> lines) {

        for(String line : lines) {
            final String[] split = line.split(":");

            State state = new State(split[0]);

            if(split.length==3) {
                state = state.addOutcome(new Word(split[1])).addScript(new Script(split[2],split[2]));
            } else if(split.length==2) {
                state = state.addScript(new Script(split[1],split[1]));
            }

            states.add(state);
        }

        //System.out.println(states);
        for(State state : states) {
            System.out.println(state);
        }

        return states;
    }

//    public static List<String> readAsset(AssetManager mgr, String path) {
//        List<String> contents = new ArrayList<String>();
//
//        InputStream is = null;
//        BufferedReader reader = null;
//        try {
//            is = mgr.open(path);
//            reader = new BufferedReader(new InputStreamReader(is));
//            String line = reader.readLine();
//            while ((line = reader.readLine()) != null) {
//                contents.add(line);
//            }
//        } catch (final Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (is != null) {
//                try {
//                    is.close();
//                } catch (IOException ignored) {
//                }
//            }
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException ignored) {
//                }
//            }
//        }
//        return contents;
//    }
}
