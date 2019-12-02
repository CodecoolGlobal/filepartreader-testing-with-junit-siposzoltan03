package com.codecool.testclasses;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class FileWordAnalyzerTest {
    private String filePath = "src/main/resources/data/data.txt";
    private FilePartReader filePartReader;
    private FileWordAnalyzer fileWordAnalyzer;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public FilePartReader getFilePartReader() {
        return filePartReader;
    }

    public void setFilePartReader(FilePartReader filePartReader) {
        this.filePartReader = filePartReader;
    }

    public FileWordAnalyzer getFileWordAnalyzer() {
        return fileWordAnalyzer;
    }

    public void setFileWordAnalyzer(FileWordAnalyzer fileWordAnalyzer) {
        this.fileWordAnalyzer = fileWordAnalyzer;
    }

    @BeforeEach
    public void beforeEach() {
        setFilePartReader(new FilePartReader());
        this.getFilePartReader().setup(this.getFilePath(), 1, 10);
        setFileWordAnalyzer(new FileWordAnalyzer(this.getFilePartReader()));
    }

    @Test
    public void testWordsAlphabetically1_2() {
        this.getFilePartReader().setup(getFilePath(), 1, 2);
        assertEquals(new ArrayList<>(Arrays.asList("test", "words")), this.getFileWordAnalyzer().getWordsOrderedAlphabetically());
    }

    @Test
    public void testWordsAlphabetically2_4() {
        this.getFilePartReader().setup(getFilePath(), 2, 4);
        assertEquals(new ArrayList<>(Arrays.asList("java", "junit", "test")), this.getFileWordAnalyzer().getWordsOrderedAlphabetically());
    }

    @Test
    public void testWordsAlphabeticallyAll() {
        this.getFilePartReader().setup(getFilePath(), 1, 10);
        assertEquals(new ArrayList<>(Arrays.asList("class", "function", "java", "javascript",
                "jetty", "junit", "oop", "python", "test", "words")), this.getFileWordAnalyzer().getWordsOrderedAlphabetically());
    }

    @Test
    public void testWordsContainingSubString() {
        this.getFilePartReader().setup(getFilePath(), 1, 10);
        assertAll("Tests words containing given substring",
                () -> assertEquals(new ArrayList<>(Collections.singletonList("words")), this.getFileWordAnalyzer().getWordsContainingSubString("ord")),
                () -> assertEquals(new ArrayList<>(Arrays.asList("java", "javascript")), this.getFileWordAnalyzer().getWordsContainingSubString("java")),
                () -> assertEquals(new ArrayList<>(Arrays.asList("words", "function", "oop", "python")), this.getFileWordAnalyzer().getWordsContainingSubString("o"))
        );
    }

    @Test
    public void testWordsContainingSubStringEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.getFileWordAnalyzer().getWordsContainingSubString("");
        });
    }

    @Test
    public void testWordsArePalindrome() {
        assertTrue(this.getFileWordAnalyzer().getStringWhichPalindromes().isEmpty());
    }


}