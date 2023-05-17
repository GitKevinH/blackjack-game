package org.ctac.java103;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    //class variables
    private ArrayList<Card> deck;

    //constructor
    public Deck() {
        this.deck = new ArrayList<Card>();
    }


    //loops through to create deck using two for loops, one for the suits, and then one for the values
    public void createFullDeck() {

        for(Suits cardSuit : Suits.values()){
            for (Values cardValue : Values.values()){
                this.deck.add(new Card(cardSuit, cardValue));
            }

        }
       
    }

    public String toString() {
        String cardOutput = "";
        for (Card card : this.deck) {
            cardOutput += "\n-" + card.toString();
        }
        return cardOutput;
    }

    public void shuffleDeck(){
        Collections.shuffle(deck);


    }



    public Card getCard(int i){
        return this.deck.get(i);
    }

    public void removeCard(int i){
        this.deck.remove(i);
    }

    public void addCard(Card addCard) {
        this.deck.add(addCard);
    }

    // Get the size of the deck
    public int deckSize() {
        return this.deck.size();
    }

    // Draws from the deck
    public void draw(Deck comingFrom) {
       this.deck.add(comingFrom.getCard(0));
       comingFrom.removeCard(0);
    }

    // This will move cards back into the deck to continue playing
    public void moveAllToDeck(Deck moveTo) {

        int currentDeckSize = this.deck.size();

        //moving used cards back into deck
        for(int i = 0; i < currentDeckSize; i++){
            moveTo.addCard((this.getCard(i)));
        }

        //removing cards from used cards pile
        for(int i = 0; i < currentDeckSize; i++){
            this.removeCard(0);
        }
      
    }

    //Return total value of cards in deck
    public int cardsValue() {
        int totalValue = 0;
        int aces = 0;

        for (Card singleCard : this.deck) {
            switch (singleCard.getValue()) {
                case TWO -> totalValue += 2;
                case THREE -> totalValue += 3;
                case FOUR -> totalValue += 4;
                case FIVE -> totalValue += 5;
                case SIX -> totalValue += 6;
                case SEVEN -> totalValue += 7;
                case EIGHT -> totalValue += 8;
                case NINE -> totalValue += 9;
                case TEN, JACK, QUEEN, KING -> totalValue += 10;
                case ACE -> aces += 1;

            }
        }

        for (int i = 0; i < aces; i++) {

            if (totalValue > 10) {
                totalValue += 1;
            } else {
                totalValue += 11;
            }
        }


        return totalValue;
    }
}