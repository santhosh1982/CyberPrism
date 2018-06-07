package com.searchthecheese.codetrees.translators;

import com.searchthecheese.codetranslator.PythonCodeTranslator;
import com.searchthecheese.codetrees.globals.CodeTreeGlobals;
import com.searchthecheese.codetrees.states.Script;
import com.searchthecheese.codetrees.states.State;
import com.searchthecheese.codetrees.states.Word;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.Pack200;

/**
 * Created by Chandramouliswaran on 5/3/2018.
 */

public class StateTranslator {

    public Word[] translateState(String exp, List<State> states) {
        Set<String> translated = new HashSet<String>();

        exp = CodeTreeGlobals.stringUtils.tokenizeOne(exp,"\\[","\\]").trim();

        System.out.println("exp -> "+exp);

        if(exp.startsWith("<")) {
            System.out.println(exp);
        } else {
            for(State state : states) {
                System.out.println("state label -> "+state.getStateLabel());
                if(!state.getStateLabel().equals(""))
                if(exp.contains(CodeTreeGlobals.stringUtils.tokenizeOne(state.getStateLabel(),"\\[","\\]"))) {
                    System.out.println("contains -> "+exp);
                    if(exp.length()==state.getStateLabel().length() || (exp.length()+2)==state.getStateLabel().length()) {
                        System.out.println("exp length equals statelabel");
                        if(state.getScripts().size()>1) {
                            for (Script script : state.getScripts()) {
                                if (script.getScript().startsWith("<")) {
                                    String[] tokens = CodeTreeGlobals.stringUtils.tokenize(script.getScript(), "<", ">");

                                    if (tokens.length > 1) {
                                        for (String token : tokens) {
                                            translated.add(eval(exp, token));
                                        }
                                    } else {
                                        translated.add(eval(exp, tokens[0]));
                                    }
                                }
                            }
                        } else  if(state.getScripts().size()>0 && ((Script)state.getScripts().toArray()[0]).getScriptLabel().equals("<>")){

                            String[] tokens = CodeTreeGlobals.stringUtils.tokenize(exp, "\\[", "\\]");

                            if (tokens.length > 1) {
                               for (Word outcome : state.getStateOutcomes()) {
                                    translated.add(outcome.getWord());
                               }
                            } else {
                                for (Word outcome : state.getStateOutcomes()) {
                                    translated.add(outcome.getWord());
                                }
                            }
                        } else {
                            System.out.println(state.getStateLabel());

                            String[] exp2 = exp.split(CodeTreeGlobals.stringUtils.tokenizeOne(state.getStateLabel(),"\\[","\\]"));

                            System.out.println(Arrays.toString(exp2));

                            if(exp2.length==2) {
                                exp = exp2[1].trim();

                                if(state.getScripts().size()>1) {
                                    for (Script script : state.getScripts()) {
                                        if (script.getScript().startsWith("<")) {
                                            String[] tokens = CodeTreeGlobals.stringUtils.tokenize(script.getScript(), "<", ">");

                                            if (tokens.length > 1) {
                                                for (String token : tokens) {
                                                    translated.add(eval(exp, token));
                                                }
                                            } else {
                                                translated.add(eval(exp, tokens[0]));
                                            }
                                        }
                                    }
                                } else  if(state.getScripts().size()>0 && ((Script)state.getScripts().toArray()[0]).getScriptLabel().equals("<>")){
                                    for (Word outcome : state.getStateOutcomes()) {
                                        translated.add(outcome.getWord());
                                    }
                                } else {
                                    for (Script script : state.getScripts()) {
                                        if (script.getScript().startsWith("<")) {
                                            String[] tokens = CodeTreeGlobals.stringUtils.tokenize(script.getScript(), "<", ">");

                                            if (tokens.length > 1) {
                                                for (String token : tokens) {
                                                    translated.add(eval(exp, token));
                                                }
                                            } else {
                                                translated.add(eval(exp, tokens[0]));
                                            }
                                        }
                                    }
                                }
                            } else {

                            }
                        }
                    } else {
                        System.out.println("exp not equal to statelabel length");
                        System.out.println(state.getStateLabel());

                        String[] exp2 = exp.split(state.getStateLabel());

                        System.out.println(Arrays.toString(exp2));

                        if(exp2.length==1) {
                            exp = exp2[0].trim();

                            if(state.getScripts().size()>1) {
                                for (Script script : state.getScripts()) {
                                    if (script.getScript().startsWith("<")) {
                                        String[] tokens = CodeTreeGlobals.stringUtils.tokenize(script.getScript(), "<", ">");

                                        if (tokens.length > 1) {
                                            for (String token : tokens) {
                                                translated.add(eval(exp, token));
                                            }
                                        } else {
                                           translated.add(eval(exp, tokens[0]));
                                        }
                                    }
                                }
                            } else  if(((Script)state.getScripts().toArray()[0]).getScriptLabel().equals("<>")){
                                for (Word outcome : state.getStateOutcomes()) {
                                    translated.add(outcome.getWord());
                                }
                            } else {

                            }
                        } else {

                        }
                    }

                    break;
                }
            }

            List<Word> trans1 = new ArrayList<Word>();

            for(String trans : translated) {
                trans1.add(new Word(trans));
            }

            Word[] translated2 = new Word[translated.size()];

            return trans1.toArray(translated2);
        }

        return null;
    }

    public String eval(String word,String exp) {
        System.out.println("eval -> "+word+","+exp);
        if(exp.equals("tolower")) {
            return word.toLowerCase();
        } else if(exp.equals("toupper")) {
            return word.toUpperCase();
        } else if(exp.equals("crlf")) {
            return "\r\n";
        }

        return word;
    }

    public static void main(String[] args) {
        String pythonStatesFile = StateTranslator.class.getResource("python.states").getFile();

        try {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(pythonStatesFile)));
            final List<String> lines = new ArrayList<String>();

            String line = "";

            while((line=reader.readLine())!=null) {
                lines.add(line);
            }

            final List<State> states = PythonCodeTranslator.loadStatesFromAssets(lines);

            StateTranslator stateTranslator = new StateTranslator();

            Word[] evals =  stateTranslator.translateState("semi",states);

            System.out.println(evals.length);

            for(Word eval : evals) {
                System.out.println(eval.getWord());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
