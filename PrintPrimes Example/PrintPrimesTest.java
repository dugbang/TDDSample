package com.example.shbae.tddsample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by shbae on 2017-11-14.
 */
public class PrintPrimesTest {
    private PrintStream out;

    @Before
    public void setUp() throws Exception {
        out = System.out;
        System.setOut(new PrintStream(new FileOutputStream("D:/lead")));
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(out);
        new File("D:/lead").delete();
    }

    @Test
    public void makeSureMatchesGold() throws IOException {
        new PrintPrimes().main(new String[0]);
        BufferedReader lead = new BufferedReader(new FileReader("D:/lead"));
        BufferedReader gold = new BufferedReader(new FileReader("D:/gold"));
        String line;
        while((line = gold.readLine()) != null)
            assertEquals(line, lead.readLine());
        assertEquals(null, lead.readLine());
    }
}