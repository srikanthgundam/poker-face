package com.challenge.poker.domain;

public enum Suit {
    SPADES("S"),
    CLUBS("C"),
    DIAMONDS("D"),
    HEARTS("H");

    private final String symbol;

    Suit(String symbol){
        this.symbol = symbol;
    }

    public String getSymbol(){
        return symbol;
    }
}

