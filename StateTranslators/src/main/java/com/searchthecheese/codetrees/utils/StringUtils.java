package com.searchthecheese.codetrees.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Created by Chandramouliswaran on 5/5/2018.
 */

public class StringUtils {

    private StringTokenizer tokenizer;

    public String[] tokenize(String exp,String... delimiters) {
        final Set<String> tokens = new HashSet<String>();

        for(String delim : delimiters) {
            tokenizer = new StringTokenizer(exp, delim);

            while(tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();

                for(String t : delimiters) {
                    token = token.replaceAll(t, "");
                }

                System.out.println(token);

                tokens.add(token);
            }
        }



        final String[] tokens2 = new String[tokens.size()];

        return tokens.toArray(tokens2);
    }

    public String tokenizeOne(String exp,String... delimiters) {
        final Set<String> tokens = new HashSet<String>();

        for(String delim : delimiters) {
            tokenizer = new StringTokenizer(exp, delim);

            while(tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();

                for(String t : delimiters) {
                    token = token.replaceAll(t, "");
                }
                System.out.println(token);

                tokens.add(token);
            }
        }



        final String[] tokens2 = new String[tokens.size()];

        if(tokens2.length>0) {
            return tokens.toArray(tokens2)[0];
        } else {
            return "";
        }
    }



}
