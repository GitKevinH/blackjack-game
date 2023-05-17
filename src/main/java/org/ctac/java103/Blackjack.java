package org.ctac.java103;

import java.util.Scanner;

public class Blackjack {

    public static void main(String[] args) {

        System.out.println("Welcome to Blackjack!");


        // Create the playing deck
        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffleDeck();






        // Create hands for the player and the dealer - hands are created from methods that are made in the deck class
        Deck playerHand = new Deck();
        Deck dealerHand = new Deck();


        double playerBal = 5000.00;
        Scanner userInput = new Scanner(System.in);
        
        // Game loops 
       while(playerBal > 0){

           System.out.println("Money you have to play with: " +playerBal+", What would you like to wager?");
           double playerBet = userInput.nextDouble();
           if(playerBet > playerBal){
               System.out.println("You dont enough money to bet that. You have a problem. Leave. NOW");
               break;
           }

           //Dealing hands
           playerHand.draw(playingDeck);
           playerHand.draw(playingDeck);

           dealerHand.draw(playingDeck);
           dealerHand.draw(playingDeck);

           boolean endRound = false;

           while (true){

               // Print player hand
               System.out.println("Your hand: ");
               System.out.println(playerHand.toString());
               System.out.println("Your hand's value is: "+playerHand.cardsValue());

               // Print dealer hand
               System.out.println("Dealer Hand: " + dealerHand.getCard(0).toString() + " and [Hidden from player]");

               //Player action
               System.out.println("What would you like to do? Enter 1 to Hit or 2 to Stand.");
               int input = userInput.nextInt();

               if(input == 1){
                   playerHand.draw(playingDeck);
                   System.out.println("You drew : " + playerHand.getCard(playerHand.deckSize()-1).toString());

                   //bust condition
                   if (playerHand.cardsValue() > 21){
                       System.out.println("Your hand's value is now: " + playerHand.cardsValue() + ". You got busted buddy.");
                       playerBal -= playerBet;
                       endRound = true;
                       break;
                   }
               }
               if (input == 2){
                   break;
               }
           }

           //Showing dealer hand
           System.out.println("Dealer Cards: " + dealerHand.toString());

           //Dealer win condition
           if((dealerHand.cardsValue() > playerHand.cardsValue()) && endRound == false){
               System.out.println("You lost to the dealer.");
               playerBal -= playerBet;
               endRound = true;
           }
            //Dealer condition at 16, stand at 17
           while ((dealerHand.cardsValue() < 17) && endRound == false){
               dealerHand.draw(playingDeck);
               System.out.println("Dealer Drew: " + dealerHand.getCard(dealerHand.deckSize()-1).toString());
           }

           //Print dealer's total
           System.out.println("The value of the Dealer's hand is now: " + dealerHand.cardsValue());

           //dealer bust condition
           if((dealerHand.cardsValue() > 21) && endRound == false){
               System.out.println("Dealer busts! You win.");
               playerBal += playerBet;
               endRound = true;
           }

           //push condition
           if((playerHand.cardsValue() == dealerHand.cardsValue()) && endRound == false){
               System.out.println("Push/Tie");
               endRound = true;
           }

           //player win condition
           if((playerHand.cardsValue() > dealerHand.cardsValue()) && endRound == false){
               System.out.println("You won!");
               playerBal += playerBet;
           }

           playerHand.moveAllToDeck(playingDeck);
           dealerHand.moveAllToDeck(playingDeck);
           System.out.println("End of hand.");

       }

        System.out.println("You're broke! You can't sit with us! Gameover.");
        
    }
}
