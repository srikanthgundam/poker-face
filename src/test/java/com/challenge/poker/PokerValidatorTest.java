package com.challenge.poker;

import com.challenge.poker.exceptions.PokerHandFormatException;
import com.challenge.poker.hand.PokerHandValidator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PokerValidatorTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    PokerHandValidator pokerHandValidator=new PokerHandValidator();

    @Test
    public void testFor_invalid_size(){
        exceptionRule.expect(PokerHandFormatException.class);
        exceptionRule.expectMessage("Poker hand should have 5 cards entered with a space separated ");
        pokerHandValidator.validatePokerHand( "9S T4 A5 7S 8S 9S");
    }


    @Test
    public void testFor_invalid_pokerHand_length(){
        exceptionRule.expect(PokerHandFormatException.class);
        exceptionRule.expectMessage("Poker hand should have string length of 14");
        pokerHandValidator.validatePokerHand("2S 3H TS JD 8D ");
    }

    @Test
    public void testFor_duplicateCard_entry(){
        exceptionRule.expect(PokerHandFormatException.class);
        exceptionRule.expectMessage("Duplicate Cards have been entered ");
        pokerHandValidator.validatePokerHand("2S 3H TS 8D 8D");

    }

}
