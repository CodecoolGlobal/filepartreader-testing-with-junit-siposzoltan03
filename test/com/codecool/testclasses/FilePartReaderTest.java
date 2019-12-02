package com.codecool.testclasses;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class FilePartReaderTest {
    private final String filePath = "src/main/resources/data/data.txt";
    private FilePartReader filePartReader;


    public FilePartReader getFilePartReader() {
        return filePartReader;
    }

    public void setFilePartReader(FilePartReader filePartReader) {
        this.filePartReader = filePartReader;
    }

    public String getFilePath() {
        return filePath;
    }

    @BeforeEach
    void beforeEach() {
        setFilePartReader(new FilePartReader());
    }

    @Test
    public void testReadLinesBeforeSetup() {
        assertThrows(FileNotFoundException.class, () -> {
            this.getFilePartReader().readLines();
        });
    }

    @Test
    public void testSetupFromLineLessThan1() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.getFilePartReader().setup(getFilePath(), 0, 1);
        });
    }

    @Test
    public void testSetupToLineLessThanFromLine() {
        assertThrows(IllegalArgumentException.class, () ->{
            this.getFilePartReader().setup(getFilePath(), 2, 1);
        });
    }

    @Test
    public void testLines1_2() throws Exception {
        this.getFilePartReader().setup(getFilePath(), 1, 2);
        assertEquals("words\ntest", this.getFilePartReader().readLines());
    }

    @Test
    public void testLines2_4() throws Exception {
        this.getFilePartReader().setup(getFilePath(), 2, 4);
        assertEquals("test\njunit\njava", this.getFilePartReader().readLines());
    }

    @Test
    public void testReadAllLines() throws Exception {
        this.getFilePartReader().setup(getFilePath(), 1, 10);
        assertEquals("words\n" +
                "test\n" +
                "junit\n" +
                "java\n" +
                "class\n" +
                "function\n" +
                "oop\n" +
                "python\n" +
                "javascript\n" +
                "jetty", this.getFilePartReader().readLines());
    }

    @Test
    public void testReadLinesPastEof() throws Exception {
        this.getFilePartReader().setup(getFilePath(), 1, 12);
        assertEquals("words\n" +
                "test\n" +
                "junit\n" +
                "java\n" +
                "class\n" +
                "function\n" +
                "oop\n" +
                "python\n" +
                "javascript\n" +
                "jetty", this.getFilePartReader().readLines());
    }
}