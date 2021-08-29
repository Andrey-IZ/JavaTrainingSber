package com.sber.javaschool.hometask2.word_count;

import com.sber.javaschool.hometask2.utils.DataHolder;

import java.util.*;
//import java.util.stream.Collectors;

public class WordCounter {
    private Collection<String> lines;

    public WordCounter(Collection<String> lines) {
        setLines(lines);
    }

    /**
     * @return список всех слов
     */
    public Collection<String> getWordList() {
        var wordList = new ArrayList<String>();
        lines.forEach(line -> Collections.addAll(wordList, DataHolder.splitByWords(line)));
        return wordList;
    }

    /**
     * @return список различных слов
     */
    public Collection<String> getDistinctWords() {
        var wordList = getWordList();
        return new HashSet<>(wordList);
    }

    /**
     * @return количество различных слов
     */
    public int calculate() {
        return getDistinctWords().size();
    }

    /**
     * @return список различных слов файла, отсортированный по возрастанию их длины
     */
    public Collection<String> getSortedUniqueWords() {
        Set<String> words = new TreeSet<>(Comparator.comparingInt(String::length));
        var dw = getDistinctWords();
//        return dw.stream().sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList());
        words.addAll(dw);
        return words;
    }

    /**
     * @return сколько раз каждое слово встречается в тексте
     */
    public Map<String, Integer> getWordsCount() {
        var distinctWords = getWordList();
        var wordDict = new HashMap<String, Integer>();
        distinctWords.forEach(word -> {
            if (wordDict.containsKey(word)) {
                wordDict.put(word, wordDict.get(word) + 1);
            } else {
                wordDict.put(word, 1);
            }
        });
        return wordDict;
    }

    /**
     * @return Все строки в обратном порядке.
     */
    public Collection<String> getReversedLines() {
        var rows = new ArrayList<String>();
        lines.forEach(s -> rows.add(reverseString(s)));
        return rows;
    }

    public Collection<String> filterLines(Set<Integer> lineNumbers) {
        var rowsFiltered = new ArrayList<String>();
        for (int number : lineNumbers) {
            if (number > 0 && number <= lines.size())
                rowsFiltered.add(((List<String>) lines).get(number - 1));
        }
        return rowsFiltered;
    }

    private String reverseString(String str) {
//        return new StringBuilder(str).reverse().toString();
        Stack<Character> stack = new Stack<>();
        for (Character character : str.toCharArray()) {
            stack.add(character);
        }
        StringBuilder stringBuffer = new StringBuilder();
        while (!stack.isEmpty()) {
            stringBuffer.append(stack.pop());
        }
        return stringBuffer.toString();
    }

    public Collection<String> getLines() {
        return lines;
    }

    public void setLines(Collection<String> lines) {
        this.lines = Objects.requireNonNullElseGet(lines, ArrayList::new);
    }
}
