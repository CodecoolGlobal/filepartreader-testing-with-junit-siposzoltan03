package com.codecool.testclasses;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FileWordAnalyzer {
    private FilePartReader filePartReader;

    public FilePartReader getFilePartReader() {
        return filePartReader;
    }

    public void setFilePartReader(FilePartReader filePartReader) {
        this.filePartReader = filePartReader;
    }

    public FileWordAnalyzer(FilePartReader filePartReader) {
        setFilePartReader(filePartReader);
    }

    public List getWordsOrderedAlphabetically() {
        ArrayList<String> output = getContentAsList();
        if (output == null) throw new NullPointerException("List of Words equals null");
        Collections.sort(output);
        return output;
    }

    public List getWordsContainingSubString(String subString) {
        List<String> output = new ArrayList<>();
        if(subString.equals("")) throw new IllegalArgumentException("Substring cannot be empty string");
        for (String word : getContentAsList()) {
            if (word.contains(subString)) {
                output.add(word);
            }
        }
        return output;
    }

    public List getStringWhichPalindromes() {
        List<String> output = new ArrayList<>();
        for (String word : getContentAsList()) {
            if (isPalindrome(word)){
                output.add(word);
            }
        }
        return output;
    }


    private ArrayList<String> getContentAsList() {
        try {
            return new ArrayList<>(Arrays.asList(this.getFilePartReader().readLines().replaceAll("\\n", " ").split(" ")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isPalindrome(String str) {
        return str.equals(new StringBuilder(str).reverse().toString());
    }
}
