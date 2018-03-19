package euchre;

import java.io.IOException;

public class game {
	
	public static void main(String[] args) throws InterruptedException, IOException {
		backend game = new backend();
		System.out.println("Welcome to Euchre");
		game.newGame();
	//	System.out.println(game.deal.toString());
		while(game.playerScore < 10 &&
				game.aiScore < 10){
			game.newHand();
			if(game.orderedTeam>=0)
			game.playHand();
		}
		if(game.playerScore>game.aiScore){
			System.out.println("\n\nCongrats!!! You have beat the computers and there is still a chance in the world for humanity!");

		}
		else
			System.out.println("\n\nYou Lost! What happned?!? You had one job! Now artificial intelegance will take over the world!");
		System.err.println("The Score was You: " + game.playerScore + " AIs: " + game.aiScore);
	}
}
