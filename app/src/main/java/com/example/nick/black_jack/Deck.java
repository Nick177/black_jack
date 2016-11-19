package com.example.nick.black_jack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Kristine on 11/19/16.
 */

public class Deck {
    private Deque<Card> cards;
    private int size;
    private int initId;


    public Deck(int initId) {
        this.initId = initId;
        int imgId = initId;

        cards = new LinkedList<Card>();

        for(int ix = 1; ix <= 13; ix++) {
            for(int jx = 0; jx < 4; jx++) {
                int value = ix;
                if(value > 10)
                    value = 10;
                Card card = new Card(value, imgId);
                cards.addFirst(card);
                imgId++;
            }
        }
    }

    public void reset() {
        int imgId = initId;

        cards.clear();

        for(int ix = 1; ix <= 13; ix++) {
            for(int jx = 0; jx < 4; jx++) {
                int value = ix;
                if(value > 10)
                    value = 10;
                Card card = new Card(value, imgId);
                cards.addFirst(card);
                imgId++;
            }
        }
    }


























    public void shuffle() {
        Random rand = new Random();
        int value;
        Card[] temp = new Card[52];
        Card card;
        while(!cards.isEmpty()) {
            card = cards.removeFirst();
            value = rand.nextInt(51);
            while (temp[value].getValue() > 0) {
                value = rand.nextInt(51);
            }
            temp[value] = card;
        }
        for (int i = 0; i < 52; i++) {
            cards.addFirst(temp[i]);
        }
    }
}
