package com.epam.cdp.m2.hw2.aggregator;

import javafx.util.Pair;

import java.util.*;

public class Java7Aggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        int result = 0;
        for(int number: numbers) {
            result += number;
        }
        return result;
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        List<Pair<String, Long>> result = new ArrayList<>();
        HashMap<String, Long> validatedWords = new HashMap<>();

        for (String word : words) {
            if(validatedWords.containsKey(word)) {
                long counter = validatedWords.get(word);
                validatedWords.put(word, ++counter);
            } else {
                validatedWords.put(word, 1L);
            }
        }

        for(Map.Entry<String, Long> entry: validatedWords.entrySet()) {
            result.add(new Pair<>(entry.getKey(), entry.getValue()));
        }

        System.out.println(result);

        if(limit >= result.size()) {
            return result;
        } else {
            return result.subList(0, (int) limit);
        }

    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        List<String> upperWords = new ArrayList<>();

        for(String word : words) {
            upperWords.add(word.toUpperCase());
        }

        Collections.sort(upperWords, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                int inputLength, input = a.compareTo(b);

                if(a.length() > b.length()){
                    inputLength = 1;
                } else if (a.length() < b.length()) {
                    inputLength = -1;
                } else {
                    inputLength = 0;
                }

                if(inputLength == 0) {
                    return input == 0 ? inputLength : input;
                }

                return inputLength;
            }
        });

        Set<String> result = new HashSet<>();
        List<String> duplicates = new ArrayList<>();

        for(String wordToEval : upperWords) {
            if(!result.add(wordToEval)) {
                duplicates.add(wordToEval);
            }
        }
        return duplicates.size() >= limit ? duplicates.subList(0, (int) limit) : duplicates.subList(0, duplicates.size());

    }
}
