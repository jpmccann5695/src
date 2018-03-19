package michRummy;

import java.util.concurrent.ThreadLocalRandom;

import generic.Deck;
import generic.Hand;
import generic.Player;

public class Backend {

	Deck deal, discard;
	Player user, ai1, ai2,ai3;
	int userScore, ai1Score, ai2Score, ai3Score, dealer,level;
	boolean gameOver;
	
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
		level = 6;
		gameOver = false;
	}
	
	void playHand(){
		boolean complete = false;
		if(level >12){
			gameOver = true;
			return;
		}
		deal = new Deck('m');
		deal.shuffle();
		user.setHand(new Hand(level,deal));
		ai1.setHand(new Hand(level,deal));
		ai2.setHand(new Hand(level,deal));
		ai3.setHand(new Hand(level,deal));
		
//		while(1) {
//			
//		}
	}

	int bookCount(Hand check) {
		int count = 0;
		int[] valueCount = check.valueCount();
		if(valueCount[0]>0) {
			int tempJoker = valueCount[0];
			for(int i = 1; i< valueCount.length; i++) {
				if(valueCount[i]>2) {
					count++;
				}
				else if(valueCount[i]>1) {
					if(tempJoker>0) {
						tempJoker--;
						count++;
					}
				}
			}
		}
		else {
			for(int i = 1; i< valueCount.length; i++) {
				if(valueCount[i]>2)
					count++;
			}
		}
		return count;
	}
	int runCount(Hand check) {
		int count = 0;
		int[] valueCount = check.valueCount();
		if(valueCount[0]>0) {
			
		}
		else {
			for(int i = 1; i< 12; i++) { //on the ace search if there is up to 5
				boolean fourFlag = false;
				boolean fiveFlag = false;
				if(i==1) {
					for(int j = 1; j<5; j++) {
						if(valueCount[i+j]==0)
							break;
						else if(j == 3)
							
					}
				}
				else if(valueCount[i] >1 && i!=11 ){
					
				}
				else if(valueCount[11] >1) {
					
				}
				
			}
		}
		return count;
	}
	boolean layableHand(Hand check) {
		
		
		if(level == 6) {
			
		}
		else if(level ==7) {
			
		}
		else if(level == 8) {
			
		}
		else if(level ==9) {
			
		}
		else if(level == 10) {
			
		}
		else if(level == 11) {
			
		}
		else {
			
		}
		return true;
	}
}
