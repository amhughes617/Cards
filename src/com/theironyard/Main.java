package com.theironyard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {

    static HashSet<Card> createDeck() {
        HashSet<Card> deck = new HashSet<>();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                Card c = new Card(suit, rank);
                deck.add(c);
            }
        }
        return deck;
    }

    static HashSet<HashSet<Card>> createHands(HashSet<Card> deck) {
        HashSet<HashSet<Card>> hands = new HashSet<>();
        for (Card c1 : deck) {
            HashSet<Card> deck2 = (HashSet<Card>) deck.clone();
            deck2.remove(c1);
            for (Card c2 : deck2) {
                HashSet<Card> deck3 = (HashSet<Card>) deck2.clone();
                deck3.remove(c2);
                for (Card c3 : deck3) {
                    HashSet<Card> deck4 = (HashSet<Card>) deck3.clone();
                    deck4.remove(c3);
                    for (Card c4 : deck4) {
                        HashSet<Card> hand = new HashSet<>();
                        hand.add(c1);
                        hand.add(c2);
                        hand.add(c3);
                        hand.add(c4);
                        hands.add(hand);
                    }
                }
            }
        }
        return hands;
    }

    static boolean isFlush(HashSet<Card> hand) {
        HashSet<Card.Suit> suits =
                hand.stream()
                .map(card -> {
                    return card.suit;
                })
                .collect(Collectors.toCollection(HashSet<Card.Suit>::new));
        return suits.size() == 1;
    }

    static boolean isStraight(HashSet<Card> hand) {
        ArrayList<Card.Rank> ranks =
                hand.stream()
                .map(card -> {
                    return card.rank;
                })
                .sorted().collect(Collectors.toCollection(ArrayList<Card.Rank>::new));
        HashSet<Card.Rank> rankSet = new HashSet<>(ranks);
        return 3 == ranks.get(3).ordinal() - ranks.get(0).ordinal() && rankSet.size() == 4;
    }
    static boolean isStraightFlush(HashSet<Card> hand) {
        return isStraight(hand) && isFlush(hand);
    }
    static boolean isFourOfAKind(HashSet<Card> hand) {
        HashSet<Card.Rank> ranks =
                hand.stream()
                        .map(card -> {
                            return card.rank;
                        })
                        .collect(Collectors.toCollection(HashSet<Card.Rank>::new));
        return ranks.size() == 1;
    }
    static boolean isThreeOfAKind(HashSet<Card> hand) {
        ArrayList<Card.Rank> ranks =
                hand.stream()
                        .map(card -> {
                            return card.rank;
                        })
                        .collect(Collectors.toCollection(ArrayList<Card.Rank>::new));
        HashSet<Integer> freqs =
                ranks.stream()
                        .map(rank ->
                                Collections.frequency(ranks, rank))
                        .collect(Collectors.toCollection(HashSet<Integer>::new));
        int sum = 0;
        for (int freq : freqs) {
            sum += freq;
        }
        return sum == 4 && freqs.size() != 1;
    }
    static boolean isTwoOfAKind(HashSet<Card> hand) {
        ArrayList<Card.Rank> ranks =
                hand.stream()
                        .map(card -> {
                            return card.rank;
                        })
                        .collect(Collectors.toCollection(ArrayList<Card.Rank>::new));
        HashSet<Integer> freqs =
                ranks.stream()
                        .map(rank ->
                                Collections.frequency(ranks, rank))
                        .collect(Collectors.toCollection(HashSet<Integer>::new));
        return freqs.contains(2) && freqs.size() != 1;
    }
    static boolean isTwoPair(HashSet<Card> hand) {
        ArrayList<Card.Rank> ranks =
                hand.stream()
                        .map(card -> {
                            return card.rank;
                        })
                        .collect(Collectors.toCollection(ArrayList<Card.Rank>::new));
        ArrayList<Integer> freqs =
                ranks.stream()
                        .map(rank ->
                                Collections.frequency(ranks, rank))
                        .collect(Collectors.toCollection(HashSet<Integer>::new))
                        .stream()
                        .collect(Collectors.toCollection(ArrayList<Integer>::new));
        return  freqs.get(0) == 2 && freqs.size() == 1;
    }
    public static void main(String[] args) {
        long beginTime = System.currentTimeMillis();
        HashSet<Card> deck = createDeck();
        HashSet<HashSet<Card>> hands = createHands(deck);
        hands = hands.stream()
                .filter(Main::isStraight)
                .collect(Collectors.toCollection(HashSet<HashSet<Card>>::new));
        System.out.println(hands.size());
        long endTime = System.currentTimeMillis();
        System.out.printf("Elapsed time: %d msecs\n", endTime - beginTime);
    }
}
