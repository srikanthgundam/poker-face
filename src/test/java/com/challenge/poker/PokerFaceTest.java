package com.challenge.poker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Unit test for PokerFace.
 */
public class PokerFaceTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;



    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));

    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testFor_InvalidFile_Location()
    {
        PokerFace.main(new String[]{"/src/test/resources/PokerHands"});

        assertTrue(outContent.toString().contains("Error: Something is wrong with File/FilePath"));

    }

    @Test
    public void TestFor_Invalid_input(){
        PokerFace.main(new String[]{"/src/test/resources/PokerHands","9S"});
        assertEquals("Error: Invalid input entered.Enter a file location which contains poker hands"
                ,outContent.toString().trim());
    }

    @Test
    public void TestFor_Invalid_Card(){
        Path resourceDirectory = Paths.get("src","test", "resources","InvalidPokerHand");
        PokerFace.main(new String[]{resourceDirectory.toFile().getAbsolutePath()});
        assertEquals("Error:Invalid number is being entered for the card"
                ,outContent.toString().trim());
    }

    @Test
    public void TestFor_Multiple_PokerHands(){
        Path resourceDirectory = Paths.get("src","test", "resources","PokerHands");
        PokerFace.main(new String[]{resourceDirectory.toFile().getAbsolutePath()});
        assertTrue(outContent.toString().contains("3H JS 3C 7C 5D => One pair"));
        assertTrue(outContent.toString().contains("JH 2C JD 2H 4C => Two pair"));
        assertTrue(outContent.toString().contains("9H 9D 3S 9S 9C => Four of a kind"));
        assertTrue(outContent.toString().contains("TC 3H TS TH 3S => Full house"));
    }

}
