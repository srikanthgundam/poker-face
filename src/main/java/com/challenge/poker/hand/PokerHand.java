package com.challenge.poker.hand;

import com.challenge.poker.domain.Card;
import com.challenge.poker.domain.Suit;
import com.challenge.poker.domain.Value;
import com.challenge.poker.exceptions.InvalidCardException;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PokerHand {
    private List<Card> cards;

    public PokerHand(List<Card> cards) {
        this.cards = cards;
    }

    enum RANK {

        HIGH_CARD("High card"),
        ONE_PAIR("One pair"),
        TWO_PAIR("Two pair"),
        THREE_A_KIND("Three of a kind"),
        STRAIGHT("Straight"),
        FLUSH("Flush"),
        FULL_HOUSE("Full house"),
        FOUR_A_KIND("Four of a kind"),
        STRAIGHT_FLUSH("Straight flush"),
        ROYAL_FLUSH("Royal Flush");

        private final String rank;

        RANK(String rank) {
            this.rank = rank;
        }

        public String getRank() {
            return rank;
        }
    }

    /**
     * Ranks the poker hand for the calling PokerHand Object
     *
     * @return rank
     */

    public String rank() {

        Map<Value, List<Card>> valueListMap = cards.stream()
                .collect(Collectors.groupingBy(Card::getValue));

        switch (valueListMap.size()) {

            case 4:
                return RANK.ONE_PAIR.getRank();
            case 3:
                if (valueListMap.entrySet().stream()
                        .anyMatch(c -> c.getValue().size() == 3)) {
                    return RANK.THREE_A_KIND.getRank();
                } else {
                    return RANK.TWO_PAIR.getRank();
                }

            case 2:
                if (valueListMap.entrySet().stream()
                        .anyMatch(c -> c.getValue().size() == 4)) {
                    return RANK.FOUR_A_KIND.getRank();
                } else {
                    return RANK.FULL_HOUSE.getRank();
                }

            case 5: return checkForStraightAndFlush(cards);

            default: throw new IllegalStateException("Unable to rank the pokerhand, please provide valid input");


        }
    }

    private String checkForStraightAndFlush(List<Card> cards) {
        if (isItFlush(cards)) {
            if (isItStraight(cards))
                return cards.stream()
                        .anyMatch(card -> card.getValue().equals(Value.KING)) ? RANK.ROYAL_FLUSH.getRank() : RANK.STRAIGHT_FLUSH.getRank();

            return RANK.FLUSH.getRank();
        }
        if (isItStraight(cards)) {
            return RANK.STRAIGHT.getRank();
        }
        return RANK.HIGH_CARD.getRank();
    }

    private boolean isItStraight(List<Card> cards) {

        List<Integer> valueList = cards.stream()
                .map(card -> card.getValue().getNumber())
                .collect(Collectors.toList());

        valueList.sort(Comparator.comparing(Integer::intValue));

        for (int i = valueList.contains(1) ? 1 : 0; i < valueList.size() - 1; i++)
            if (valueList.get(i) + 1 != valueList.get(i + 1))
                return false;

        return true;
    }

    private boolean isItFlush(List<Card> cards) {

        return cards.stream().collect(Collectors.groupingBy(Card::getSuit)).size() == 1;

    }


    public static PokerHand buildPokerHand(String cardsString){

        new PokerHandValidator().validatePokerHand(cardsString);

        String[] cards = cardsString.split(" ");

        List<Card> cardsList = Arrays.stream(cards)
                .map(card -> new Card(valueFrom(card.codePointAt(0)), suitFrom(card.charAt(1))))
                .collect(Collectors.toList());

        return new PokerHand(cardsList);

    }

    /**
     * Unicode code point values for 2...9 : 50...57
     * Unicode code point values for A,J,Q,K,T : 65,74,81,75,84
     *
     * @param codePoint CodePoint value for a given digit/letter
     * @return Value
     */
    private static Value valueFrom(int codePoint){

        switch (codePoint) {
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
                return buildValue(codePoint - 48);
            case 65:
                return buildValue(codePoint - 64);
            case 74:
                return buildValue(codePoint - 63);
            case 75:
                return buildValue(codePoint - 62);
            case 81:
                return buildValue(codePoint - 69);
            case 84:
                return buildValue(codePoint - 74);
            default:
                throw new InvalidCardException("Invalid number is being entered for the card");

        }

    }

    private static Value buildValue(int val) {
        return Arrays.stream(Value.values())
                .filter(v -> v.getNumber() == val)
                .findFirst().orElseThrow(() -> new InvalidCardException("Invalid number is being entered for the card"));
    }


    private static Suit suitFrom(char symbol) {
        return Arrays.stream(Suit.values())
                .filter(s -> s.getSymbol().equalsIgnoreCase(Character.toString(symbol)))
                .findFirst().orElseThrow(() -> new InvalidCardException("Invalid suit entered as input"));
    }

    public List<Card> getCards() {
        return cards;
    }
}
