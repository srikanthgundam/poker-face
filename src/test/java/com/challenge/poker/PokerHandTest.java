package com.challenge.poker;

import com.challenge.poker.domain.Card;
import com.challenge.poker.domain.Suit;
import com.challenge.poker.domain.Value;
import com.challenge.poker.exceptions.InvalidCardException;
import com.challenge.poker.hand.PokerHand;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class PokerHandTest {
    private List<Card> cards;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testRankFor_High_Card(){

        cards.add(new Card(Value.ACE, Suit.SPADES));
        cards.add(new Card(Value.TEN, Suit.DIAMONDS));
        cards.add(new Card(Value.TWO, Suit.HEARTS));
        cards.add(new Card(Value.QUEEN,Suit.CLUBS));
        cards.add(new Card(Value.NINE, Suit.CLUBS));
        assertEquals("High card",new PokerHand(cards).rank());
    }

    @Test
    public void testRankFor_One_Pair(){
        cards.add(new Card(Value.ACE, Suit.SPADES));
        cards.add(new Card(Value.ACE, Suit.DIAMONDS));
        cards.add(new Card(Value.TWO, Suit.HEARTS));
        cards.add(new Card(Value.QUEEN,Suit.CLUBS));
        cards.add(new Card(Value.NINE, Suit.CLUBS));
        assertEquals("One pair",new PokerHand(cards).rank());

    }

    @Test
    public void testRankFor_Two_Pair(){
        cards.add(new Card(Value.ACE, Suit.SPADES));
        cards.add(new Card(Value.ACE, Suit.DIAMONDS));
        cards.add(new Card(Value.TWO, Suit.HEARTS));
        cards.add(new Card(Value.TWO,Suit.CLUBS));
        cards.add(new Card(Value.NINE, Suit.CLUBS));
        assertEquals("Two pair",new PokerHand(cards).rank());
    }

    @Test
    public void testRankFor_Three_Of_Kind(){
        cards.add(new Card(Value.KING, Suit.SPADES));
        cards.add(new Card(Value.ACE, Suit.DIAMONDS));
        cards.add(new Card(Value.KING, Suit.HEARTS));
        cards.add(new Card(Value.QUEEN,Suit.CLUBS));
        cards.add(new Card(Value.KING, Suit.CLUBS));
        assertEquals("Three of a kind",new PokerHand(cards).rank());

    }

    @Test
    public void testRankFor_Four_a_Kind(){
        cards.add(new Card(Value.TEN, Suit.SPADES));
        cards.add(new Card(Value.TEN, Suit.DIAMONDS));
        cards.add(new Card(Value.TEN, Suit.HEARTS));
        cards.add(new Card(Value.QUEEN,Suit.CLUBS));
        cards.add(new Card(Value.TEN, Suit.CLUBS));
        assertEquals("Four of a kind",new PokerHand(cards).rank());
    }

    @Test
    public void testRankFor_Straight(){
        cards.add(new Card(Value.FIVE, Suit.SPADES));
        cards.add(new Card(Value.ACE, Suit.DIAMONDS));
        cards.add(new Card(Value.TWO, Suit.HEARTS));
        cards.add(new Card(Value.FOUR,Suit.CLUBS));
        cards.add(new Card(Value.THREE, Suit.CLUBS));
        assertEquals("Straight",new PokerHand(cards).rank());
    }

    @Test
    public void testRankFor_Flush(){
        cards.add(new Card(Value.TEN, Suit.SPADES));
        cards.add(new Card(Value.ACE, Suit.SPADES));
        cards.add(new Card(Value.TWO, Suit.SPADES));
        cards.add(new Card(Value.FOUR,Suit.SPADES));
        cards.add(new Card(Value.THREE, Suit.SPADES));
        assertEquals("Flush",new PokerHand(cards).rank());
    }

    @Test
    public void testRankFor_FullHouse(){
        cards.add(new Card(Value.TEN, Suit.SPADES));
        cards.add(new Card(Value.TEN, Suit.CLUBS));
        cards.add(new Card(Value.ACE, Suit.CLUBS));
        cards.add(new Card(Value.ACE,Suit.SPADES));
        cards.add(new Card(Value.TEN, Suit.DIAMONDS));
        assertEquals("Full house",new PokerHand(cards).rank());
    }

    @Test
    public void testRankFor_Straight_Flush(){
        cards.add(new Card(Value.FIVE, Suit.SPADES));
        cards.add(new Card(Value.ACE, Suit.SPADES));
        cards.add(new Card(Value.TWO, Suit.SPADES));
        cards.add(new Card(Value.FOUR,Suit.SPADES));
        cards.add(new Card(Value.THREE, Suit.SPADES));
        assertEquals("Straight flush",new PokerHand(cards).rank());
    }

    @Test
    public void testRankFor_Straight_Flush_with_Hearts(){
        cards.add(new Card(Value.TEN, Suit.HEARTS));
        cards.add(new Card(Value.SIX, Suit.HEARTS));
        cards.add(new Card(Value.EIGHT, Suit.HEARTS));
        cards.add(new Card(Value.SEVEN,Suit.HEARTS));
        cards.add(new Card(Value.NINE, Suit.HEARTS));
        assertEquals("Straight flush",new PokerHand(cards).rank());
    }


    @Test
    public void testRankFor_Royal_Flush(){
        cards.add(new Card(Value.KING, Suit.CLUBS));
        cards.add(new Card(Value.TEN, Suit.CLUBS));
        cards.add(new Card(Value.ACE, Suit.CLUBS));
        cards.add(new Card(Value.QUEEN,Suit.CLUBS));
        cards.add(new Card(Value.JACK, Suit.CLUBS));
        assertEquals("Royal Flush",new PokerHand(cards).rank());
    }

    @Test
    public void testFor_Build_Pokerhand(){
        String pokerHandString= "9H 9D 3S 9S 9C";
        PokerHand pokerHand=PokerHand.buildPokerHand(pokerHandString);
        assertEquals(pokerHand.getCards().size(),5);
    }

    @Test
    public void testFor_Build_PokerHand_Royal_Flush(){
        String pokerHandString= "AH JH TH KH QH";
        PokerHand pokerHand=PokerHand.buildPokerHand(pokerHandString);
        long suitCount = pokerHand.getCards().stream()
                .filter(card->card.getSuit().equals(Suit.HEARTS))
                .count();
        assertTrue(pokerHand.getCards().stream().anyMatch(card->card.getValue().equals(Value.KING)));
        assertTrue(pokerHand.getCards().stream().anyMatch(card->card.getValue().equals(Value.JACK)));
        assertTrue(pokerHand.getCards().stream().anyMatch(card->card.getValue().equals(Value.TEN)));
        assertTrue(pokerHand.getCards().stream().anyMatch(card->card.getValue().equals(Value.ACE)));
        assertTrue(pokerHand.getCards().stream().anyMatch(card->card.getValue().equals(Value.QUEEN)));
        assertEquals(suitCount,5);

    }

    @Test
    public void testFor_Invalid_value(){
        String pokerHandString= "0H 9D 3S 9S 9C";
        exceptionRule.expect(InvalidCardException.class);
        exceptionRule.expectMessage("Invalid number is being entered for the card");
        PokerHand.buildPokerHand(pokerHandString);
    }

    @Test
    public void testFor_Invalid_Suit(){
        String pokerHandString= "7H 9D 3N 9S 9C";
        exceptionRule.expect(InvalidCardException.class);
        exceptionRule.expectMessage("Invalid suit entered as input");
        PokerHand.buildPokerHand(pokerHandString);

    }

    @Before
    public void setUp_List(){
        cards = new ArrayList<>();

    }

}
