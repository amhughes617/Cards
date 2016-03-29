package com.theironyard;

import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * Created by alexanderhughes on 3/28/16.
 */
public class MainTest {
    @Test
    public void testCreateDeck() {
        HashSet<Card> deck = Main.createDeck();
        assertTrue(deck.size() == 52);
    }
    @Test
    public void testCreateHands() {
        HashSet<HashSet<Card>> hands = Main.createHands(Main.createDeck());
        assertTrue(hands.size() == 270725);//value obtained by doing maths
    }
    @Test
    public void testIsFlush() {
        HashSet<Card> hand = new HashSet<>();
        hand.add(new Card(Card.Suit.CLUBS, Card.Rank.EIGHT));
        hand.add(new Card(Card.Suit.CLUBS, Card.Rank.FIVE));
        hand.add(new Card(Card.Suit.CLUBS, Card.Rank.FOUR));
        hand.add(new Card(Card.Suit.CLUBS, Card.Rank.ACE));
        boolean value = Main.isFlush(hand);
        assertTrue(value);
    }
    @Test
    public void testIsStraight() {
        HashSet<Card> hand = new HashSet<>();
        hand.add(new Card(Card.Suit.CLUBS, Card.Rank.SEVEN));
        hand.add(new Card(Card.Suit.DIAMONDS, Card.Rank.FIVE));
        hand.add(new Card(Card.Suit.CLUBS, Card.Rank.FOUR));
        hand.add(new Card(Card.Suit.CLUBS, Card.Rank.SIX));
        boolean value = Main.isStraight(hand);
        assertTrue(value);
    }
    @Test
    public void testIsStraightFlush() {
        HashSet<Card> hand = new HashSet<>();
        hand.add(new Card(Card.Suit.CLUBS, Card.Rank.SEVEN));
        hand.add(new Card(Card.Suit.CLUBS, Card.Rank.FIVE));
        hand.add(new Card(Card.Suit.CLUBS, Card.Rank.FOUR));
        hand.add(new Card(Card.Suit.CLUBS, Card.Rank.SIX));
        boolean value = Main.isStraightFlush(hand);
        assertTrue(value);
    }
    @Test
    public void testIsFourOfAKind() {
        HashSet<Card> hand = new HashSet<>();
        hand.add(new Card(Card.Suit.CLUBS, Card.Rank.ACE));
        hand.add(new Card(Card.Suit.DIAMONDS, Card.Rank.ACE));
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.ACE));
        hand.add(new Card(Card.Suit.HEARTS, Card.Rank.ACE));
        boolean value = Main.isFourOfAKind(hand);
        assertTrue(value);
    }
    @Test
    public void testIsThreeOfAKind() {
        HashSet<Card> hand = new HashSet<>();
        hand.add(new Card(Card.Suit.CLUBS, Card.Rank.JACK));
        hand.add(new Card(Card.Suit.DIAMONDS, Card.Rank.ACE));
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.ACE));
        hand.add(new Card(Card.Suit.HEARTS, Card.Rank.ACE));
        boolean value = Main.isThreeOfAKind(hand);
        assertTrue(value);
    }
    @Test
    public void testIsTwoOfAKind() {
        HashSet<Card> hand = new HashSet<>();
        hand.add(new Card(Card.Suit.CLUBS, Card.Rank.JACK));
        hand.add(new Card(Card.Suit.DIAMONDS, Card.Rank.QUEEN));
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.ACE));
        hand.add(new Card(Card.Suit.HEARTS, Card.Rank.ACE));
        boolean value = Main.isTwoOfAKind(hand);
        assertTrue(value);
    }
    @Test
    public void testIsTwoPair() {
        HashSet<Card> hand = new HashSet<>();
        hand.add(new Card(Card.Suit.CLUBS, Card.Rank.JACK));
        hand.add(new Card(Card.Suit.DIAMONDS, Card.Rank.JACK));
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.ACE));
        hand.add(new Card(Card.Suit.HEARTS, Card.Rank.ACE));
        boolean value = Main.isTwoPair(hand);
        assertTrue(value);
    }
}