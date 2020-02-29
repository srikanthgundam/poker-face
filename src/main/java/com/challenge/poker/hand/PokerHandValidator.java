package com.challenge.poker.hand;

import com.challenge.poker.exceptions.PokerHandFormatException;

import java.util.Arrays;

public class PokerHandValidator {
    public void validatePokerHand(String pokerHandString) {

        String[] cards = pokerHandString.split(" ");

        if (cards.length != 5)
            throw new PokerHandFormatException("Poker hand should have 5 cards entered with a space separated ");
        else if (pokerHandString.length() != 14) {
            throw new PokerHandFormatException("Poker hand should have string length of 14");
        } else if (isDuplicateCard(cards)) {
            throw new PokerHandFormatException("Duplicate Cards have been entered ");
        }

    }

    private boolean isDuplicateCard(String[] cards) {

        return Arrays.stream(cards).distinct().count() != cards.length;

    }
}
