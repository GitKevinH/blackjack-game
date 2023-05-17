package org.ctac.java103;

import java.util.Random;

public class Card {

    //Variables needed
    private Suits suit;
    private Values value;

    // Create arrays for values and suits - need these to generate random cards
    private Values[] values = Values.values();
    private Random randomValues = new Random();
    private Suits[] suits = Suits.values();
    private Random randomSuits = new Random();


    //Constructors
    public Card(Suits suit, Values value) {
        this.value = value;
        this.suit = suit;
    }

    // random card generated
    public Card() {
        this.suit = getRandomSuit();
        this.value = getRandomValue();
    }

    //Returns suit and value (by string)
    public String toString() {
        return this.suit.toString() + "-" + this.value.toString();
    }

    public Values getValue() {
        return this.value;
    }

    public Values getRandomValue() {
        return values[randomValues.nextInt(values.length)];
    }

    public Suits getRandomSuit() {
        return suits[randomSuits.nextInt(values.length)];
    }
}