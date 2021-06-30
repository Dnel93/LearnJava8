package com.epam.cdp.m2.hw2.aggregator;

import javafx.util.Pair;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Java8ParallelAggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        return numbers.parallelStream().mapToInt(Integer::intValue).sum();
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        List<Pair<String, Long>> result = new ArrayList<>();

        words.parallelStream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .forEach((x, y) -> result.add(new Pair<>(x, y)));


        return result.stream().limit(limit).collect(Collectors.toList());
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        Set<String> uniqueValues = new HashSet<>();

        return words
                .parallelStream()
                .map(String::toUpperCase)
                .sorted(Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder()))
                .sequential()
                .filter(word -> !uniqueValues.add(word))
                .limit(limit)
                .collect(Collectors.toList());
    }
}
