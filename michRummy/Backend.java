// MADE BY JONAHTAN MCCANN Fall 2017 ALL ORIGINAL WORK

package michRummy;

import java.util.concurrent.ThreadLocalRandom;

import generic.Card;
import generic.Deck;
import generic.Hand;
import generic.Player;

public class Backend {

	Deck deal, discard;
	Player user, ai1, ai2,ai3;
	int userScore, ai1Score, ai2Score, ai3Score, dealer,hand;
	
	void newGame(){
		ai1 = new Player("AI West");
		ai2 = new Player("AI North");
		ai3 = new Player("Ai East");
		user = new Player("You");
		userScore = 0;
		ai1Score = 0;
		ai2Score = 0;
		ai3Score = 0;
		dealer = ThreadLocalRandom.current().nextInt(0, 4);		
		hand = 6;
	}
	
	void playHand(){
		boolean complete = false;
		if(hand >12){
			return;
		}
		deal = new Deck('m');
		deal.shuffle();
		user.setHand(new Hand(hand,deal));
		ai1.setHand(new Hand(hand,deal));
		ai2.setHand(new Hand(hand,deal));
		ai3.setHand(new Hand(hand,deal));
		int currentPlayer = dealer;
		while(!complete) {
			if(currentPlayer == 0) {
				System.out.println("Would you like the " + discard.topCardToString() + "?\n");
				
			}
			else {
				discard.addCardTop(aiTurn(currentPlayer));
			}
			currentPlayer=(currentPlayer+1)%4;
		}
	}
	Card aiTurn(int ai) {
		return null;
	}
}
