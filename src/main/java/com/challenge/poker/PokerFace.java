package com.challenge.poker;

import com.challenge.poker.hand.PokerHand;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * PokerFace
 *
 */
public class PokerFace {
    public static void main(String[] cards) {
        if (cards.length == 1) {
            try (Stream<String> stream = Files.lines(Paths.get(cards[0]))) {

                stream.forEach(stringOfCards -> {
                    try {

                        System.out.println(stringOfCards + " => " + PokerHand.buildPokerHand(stringOfCards.toUpperCase()).rank());

                    } catch (Exception ex) {
                        System.out.println("Error:" + ex.getMessage());
                    }
                });

            } catch (IOException ex) {
                System.out.println("Error: Something is wrong with File/FilePath: " + ex.getMessage());
            }
        } else {

            System.out.println("Error: Invalid input entered.Enter a file location which contains poker hands ");

        }
    }
}