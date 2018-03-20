package michRummy;

import java.io.IOException;

import generic.Card;

public class game {
	
	public static void main(String[] args) throws InterruptedException, IOException {
		Backend game = new Backend();
		System.out.println("Welcome to Michigan Rummy");
		game.newGame();
		game.playHand();
		game.ai1.getHand().add(new Card("Club","Six",6,1));
		game.ai1.getHand().add(new Card("Club","Six",6,1));
		game.ai1.getHand().add(new Card("Club","Six",6,1));
		game.ai1.getHand().add(new Card("Heart","Five",5,2));
		game.ai1.getHand().add(new Card("Heart","Three",3,2));
		game.ai1.getHand().add(new Card("Heart","Four",4,2));
		game.ai1.getHand().add(new Card("Heart","Four",4,2));
		game.ai1.getHand().add(new Card("Heart","Four",4,2));
		game.ai1.getHand().add(new Card("Heart","Jack",11,2));
		game.ai1.getHand().add(new Card("Heart","Quenn",12,2));
		game.ai1.getHand().add(new Card("Heart","King",13,2));
		game.ai1.getHand().add(new Card("Heart","Ace",1,2));
		System.out.println(game.bookCount(game.ai1.getHand()));
		System.out.println(game.bookCount(game.ai2.getHand()));
		System.out.println(game.bookCount(game.ai3.getHand()));
		System.out.println(game.bookCount(game.user.getHand()));
		System.out.println(game.runCount(game.ai1.getHand()));
		System.out.println(game.runCount(game.ai2.getHand()));
		System.out.println(game.runCount(game.ai3.getHand()));
		System.out.println(game.runCount(game.user.getHand()));
		System.out.println(game.layableHand(game.ai1.getHand()));
	}

}
