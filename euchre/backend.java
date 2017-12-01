// MADE BY JONAHTAN MCCANN SUMMER 2017 ALL ORIGINAL WORK

package euchre;

import generic.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class backend {

	Deck deal, reff, pile;
	Hand player, ai1, ai2, ai3;
	int playerScore, aiScore, dealer, trump, orderedTeam, whoOrdered, followSuit, firstAct, current;
	String dealerString;
	boolean alone, trumpLayed;
	private Scanner scanner = new Scanner(System.in);
	Card option;

	void newGame() {
		dealer = ThreadLocalRandom.current().nextInt(0, 4);
		playerScore = 0;
		aiScore = 0;
	}

	void newHand() throws InterruptedException, IOException {
		System.err.println("The Score is You: " + this.playerScore + " AIs: " + this.aiScore);
		TimeUnit.SECONDS.sleep((long) 1.0);
		alone = false;
		deal = new Deck('e');
		deal.shuffle();
		deal.shuffle();
		deal.shuffle();
		reff = new Deck(deal);
		player = new Hand(5, deal);
		ai1 = new Hand(5, deal);
		ai2 = new Hand(5, deal);
		ai3 = new Hand(5, deal);
		setDealerString();
		option = deal.getNextCard();
		// trump = option.suitValue;
		player.sortValue();
		player.sortSuit();

		System.out.println("The Dealer is... " + dealerString);
		System.out.println("Your cards are...");
		System.out.println(player.toString());
		System.out.println("");
		System.out.println("The pickup card is " + option.toString() + " and " + dealerString + " will pick it up.");
		System.out.println("The trump will be " + option.getSuit() + "s if the card is picked up\n");

		TimeUnit.SECONDS.sleep((long) 1.0);
		whoOrdered = drawCardOrder(option);
		if (whoOrdered != 0)
			playerPickup(option);
		if (whoOrdered == 1 || whoOrdered == 3)
			orderedTeam = 0;
		else if (whoOrdered == 2 || whoOrdered == 4)
			orderedTeam = 1;
		else {
			System.out.println("We will now open it up for any other suit for trump.");
			whoOrdered = callTrumpOrder();
			if (whoOrdered == 0) {
				orderedTeam = -1;
			}
			if (whoOrdered == 1 || whoOrdered == 3)
				orderedTeam = 0;
			else if (whoOrdered == 2 || whoOrdered == 4)
				orderedTeam = 1;
			else {

			}
		}
		if (orderedTeam >= 0) {
			if (orderedTeam == 0)
				System.out.println(
						"\nYour team has ordered up trump! You MUST take 3 tricks to make your point or you will go set!");
			else if (orderedTeam == 1)
				System.out.println(
						"\nIf you get THREE tricks you will set the AIs! Get atleast ONE trick to avoid them getting TWO points!");
			euchreSort();
			System.out.println(player.toString());
		} else {
			System.out.println("THESE CARDS SUCK! Lets get some fresh ones!\n\n");
			dealer = (dealer + 1) % 4;
		}
	}

	String trumpToString() {
		if (trump == 0)
			return "Diamonds";
		if (trump == 1)
			return "Clubs";
		if (trump == 2)
			return "Hearts";
		if (trump == 3)
			return "Spades";
		else
			return "";
	}

	void playHand() throws InterruptedException {

		int playerScore = 0;
		int aiScore = 0;
		String trumpSuit = this.trumpToString();
		trump = 5;
		firstAct = (dealer + 1) % 4;
		current = firstAct;
		while (player.hasCards()) {
			pile = new Deck(0);
			current = firstAct;
			for (int i = 0; i < 4; i++) {

				Card played;
				if (current == 0) {
					boolean cheater = false;
					int card = -5;
					while (card < 0 || card > player.getSize() - 1 || cheater) {
						String extra = "";
						if (current == firstAct)
							extra = "You are on the lead! Trump is " + trumpSuit;
						else {
							String suitFound = "";
							if (pile.getCardAtIndex(0).Value == 15)
								suitFound = trumpSuit;
							else
								suitFound = pile.cardArray.get(0).Suit;
							extra = "The lead was " + suitFound + ". Trump is " + trumpSuit;
						}
						System.out.println("\nWhat card do you want to play? " + extra);
						System.out.println(player.toString());
						String input = scanner.nextLine();
						while (!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4")
								&& !input.equals("5")) {
							System.out.println("\nNot a valid Number! Try Again!");
							System.out.println("What card do you want to play? " + extra);
							System.out.println(player.toString());
							input = scanner.nextLine();
						}
						card = Integer.parseInt(input) - 1;
						//check to see if playing out of suit!
						
						int attemptSuit = player.getCardAt(card).suitValue;
						if(pile.getSize()>0){
							if(pile.getCardAtIndex(0).suitValue != attemptSuit){
								if(player.countSuit(pile.getCardAtIndex(0).suitValue)>0){
									System.out.println("HEY NOW! You are CHEATING!!!! Try a different card!");
									cheater = true;
								}
								else
									cheater = false;
							}
							else
								cheater = false;
						}
					}

					played = player.playCard(card);
					pile.addCard(played);
					if (firstAct == 0) {
						followSuit = played.suitValue;
						System.out.println("You LED " + played.toString());
					} else
						System.out.println("You played " + played.toString());
				} else {
					played = aiGameplayLogic();
					pile.addCard(played);
					System.out.println(playerToString(current) + " played " + played.toString());
				}
				current = (current + 1) % 4;
			}
			int trickWinner = this.whoWonTrick();
			if (trickWinner == 0 || trickWinner == 2)
				playerScore++;
			else
				aiScore++;
			System.out.println(this.playerToString(trickWinner) + " has won the trick with a "
					+ this.whatCardWon(trickWinner).toString() + "\n");
			firstAct = trickWinner;
			System.out.println("Hand Score - You: " + playerScore + " AIs: " + aiScore);
		}

		if (orderedTeam == 0) {
			if (playerScore == 5) {
				System.out.println("\n Congrats! You took all the tricks and will be awarded 2 match points!");
				this.playerScore += 2;
			} else if (playerScore > 2) {
				System.out.println(
						"\n Alright, Alright, Alriiiiigggghhhht you got your needed amount and will get 1 match point!");
				this.playerScore++;
			} else {
				System.out.println(
						"\nHEYYYY PART TIMER! Try harder next time! The AIs get 2 points for beaking you! They will soon take over the world at this rate!");
				this.aiScore += 2;
			}
		} else {
			if (aiScore == 5) {
				System.out.println(
						"\n What are you doing? You let the AIs take all the tricks. They get 2 match points!");
				this.aiScore += 2;
			} else if (aiScore > 2) {
				System.out.println("\n The AIs made their point and will get 1 match point.");
				this.aiScore++;
			} else {
				System.out.println("\nIT'S A PARTY IN THE USA! You set the AI's and will get 2 Match Points\n\n");
				this.playerScore += 2;
			}
		}
		TimeUnit.SECONDS.sleep((long) 1.0);
		dealer = (dealer + 1) % 4;
	}

	Card whatCardWon(int player) {
		if (firstAct == player)
			return pile.getCardAtIndex(0);
		if (player < firstAct) {
			return pile.getCardAtIndex((player + 4) - firstAct);
		} else
			return pile.getCardAtIndex(player - firstAct);
	}

	int whoWonTrick() {
		Card winner = pile.getCardAtIndex(0);
		int winnerIndex = 0;
		for (int i = 1; i < pile.getSize(); i++) {
			if (winner.suitValue == trump) {
				if (pile.getCardAtIndex(i).suitValue == trump && winner.Value < pile.getCardAtIndex(i).Value) {
					winner = pile.getCardAtIndex(i);
					winnerIndex = i;
				}
			} else {
				if (pile.getCardAtIndex(i).suitValue == trump) {
					winner = pile.getCardAtIndex(i);
					winnerIndex = i;
				} else {
					if (pile.getCardAtIndex(i).suitValue == followSuit && pile.getCardAtIndex(i).Value > winner.Value) {
						winner = pile.getCardAtIndex(i);
						winnerIndex = i;
					}
				}
			}
		}
		return (firstAct + winnerIndex) % 4;

	}

	Card aiGameplayLogic() {
		Hand hand = this.getHand(current);
		if (current == firstAct) {
			if (hand.countSuit(trump) > 0) {
				// Play Biggest Trump
				Card played = hand.playCard(hand.getSize() - 1);
				followSuit = played.suitValue;
				return played;
			} else {
				// Play your highest card
				Card played = hand.playCard(hand.findHighestNonTrumpCard());
				followSuit = played.suitValue;
				return played;
			}
		} else {
			Card partner;
			if (pile.getSize() > 1) {
				if (pile.getSize() == 2)
					partner = pile.getCardAtIndex(0);
				else
					partner = pile.getCardAtIndex(1);
				if (areWinning(partner)) {
					if (hand.countSuit(followSuit) > 0) {
						for (int i = 0; i < hand.getSize(); i++) {
							if (hand.getCardAt(i).suitValue == followSuit) {
								return hand.playCard(i);
							}
						}
					} else {
						hand.sortValue();
						return hand.playCard(0);
					}
				}
			}
			// Not Winning
			if (hand.countSuit(followSuit) > 0)
				return hand.playCard(hand.findHighestCardOfSuit(followSuit));
			if (hand.countSuit(trump) > 0) {
				return hand.playCard(hand.findLowestCardOfSuit(trump));
			}
			hand.sortValue();
			return hand.playCard(0);
		}
	}

	boolean shouldAiOrder(Hand hand, Card option, int player) {
		hand.sortValue();
		hand.sortSuit();
		if (hand.countSuit(option.suitValue) > 2)
			return true;
		if (hand.countSuit(option.suitValue) == 2 && dealer == player)
			return true;
		if (hand.countSuit(option.suitValue) == 2) {
			int goodCardCounter = 0;
			if (hand.containsCard(11, option.suitValue) == 1)
				goodCardCounter++;
			if (hand.containsCard(11, (option.suitValue + 2) % 4) == 1)
				goodCardCounter++;
			if (hand.containsCard(14, option.suitValue) == 1)
				goodCardCounter++;
			if (goodCardCounter > 1)
				return true;
			else
				return false;
		} else
			return false;
	}

	boolean areWinning(Card partner) {
		if (partner == null) {
			return false;
		} else {
			if (partner.suitValue != followSuit && partner.suitValue != trump)
				return false;
			else if (partner.suitValue == trump) {
				int partnerValue = partner.Value;
				for (int i = 0; i < pile.getSize(); i++) {
					if (partnerValue < pile.getCardAtIndex(i).Value && pile.getCardAtIndex(i).suitValue == trump)
						return false;
				}
				return true;
			} else {
				int partnerValue = partner.Value;
				for (int i = 0; i < pile.getSize(); i++) {
					if (pile.getCardAtIndex(i).suitValue == trump)
						return false;
					else if (pile.getCardAtIndex(i).suitValue == followSuit) {
						if (partnerValue < pile.getCardAtIndex(i).Value)
							return false;
					}
				}
				return true;
			}
		}
	}

	int shouldAiCallTrump(Hand hand, int player) {
		for (int i = 0; i < 4; i++) {
			int suitCounter = 0;
			if (i == trump) {
				// Do nothing becuase you dont have the option to call this
			} else {
				int offJackSuit = (i + 2) % 4;
				for (int j = 0; j < hand.getSize(); j++) {
					if (hand.getCardAt(j).suitValue == i)
						suitCounter++;
					if (hand.getCardAt(j).suitValue == offJackSuit && hand.getCardAt(j).Value == 11)
						suitCounter++;
				}
				if (suitCounter > 2)
					return i;
				else
					return -1;
			}
		}
		return -1;
	}

	void dropWorstCard(Hand hand) {
		hand.sortValue();
		hand.sortSuit();
		Card worst = hand.cardArray.get(0);
		if (worst.suitValue == trump) {
			for (int i = 1; i < hand.getSize(); i++) {
				if (hand.cardArray.get(i).suitValue == trump) {

				} else {
					worst = hand.cardArray.get(i);
					break;
				}

			}
		}
		for (int i = 1; i < hand.getSize(); i++) {
			if (hand.cardArray.get(i).Value < worst.Value && hand.cardArray.get(i).suitValue != trump)
				worst = hand.cardArray.get(i);
		}
		hand.cardArray.remove(worst);
	}

	void setDealerString() {
		if (dealer == 0)
			dealerString = "You";
		if (dealer == 1)
			dealerString = "AI West";
		if (dealer == 2)
			dealerString = "AI North";
		if (dealer == 3)
			dealerString = "AI East";
	}

	int callTrumpOrder() throws InterruptedException {
		boolean ordered = false;
		for (int i = 1; i < 5; i++) {
			current = (current + 1) % 4;
			// current = (dealer + i) % 4;
			if (current == 0) {
				System.out.println(player.toString());
				System.out.println("Would you like to call a trump? Y/N");
				String input = scanner.nextLine();
				while (!input.equals("y") && !input.equals("Y") && !input.equals("Yes") && !input.equals("n")
						&& !input.equals("N") && !input.equals("No")) {
					System.out.println("You entered an INVALID response. Try again!\n");
					System.out.println(player.toString());
					System.out.println("Would you like to call a trump? Y/N");
					input = scanner.nextLine();
				}
				if (input.equals("y") || input.equals("Y") || input.equals("Yes")) {
					String sOptions = "";
					for (int j = 0; j < 4; j++) {
						if (j == option.suitValue) {

						} else {
							if (j == 0)
								// options.add("Diamoonds (D)");
								sOptions += "Diamonds (D) ";
							else if (j == 1)
								// options.add("Clubs (C)");
								sOptions += "Clubs (C) ";
							else if (j == 2)
								// options.add("Hearts (H)");
								sOptions += "Hearts (H) ";
							else
								// options.add("Spades (S)");
								sOptions += "Spades (S)";
						}
					}
					System.out.println("You may bring in " + sOptions);
					input = scanner.nextLine();
					while (!ordered) {
						while (input.equals(option.Suit.substring(0, 1))
								|| input.equals(option.Suit.substring(0, 1).toLowerCase())) {
							System.out.println("You had the chance to pick that suit but passed. Try anohter.");
							input = scanner.nextLine();
						}
						if (input.equals("d") || input.equals("D")) {
							trump = 0;
							ordered = true;
						} else if (input.equals("c") || input.equals("C")) {
							trump = 1;
							ordered = true;
						} else if (input.equals("H") || input.equals("h")) {
							trump = 2;
							ordered = true;
						} else if (input.equals("S") || input.equals("s")) {
							trump = 3;
							ordered = true;
						} else
							System.out.println("You have entered an invalid option.");
						input = scanner.nextLine();
					}
					if (ordered) {
						whoOrdered = 1;
						orderedTeam = 0;
						return 1;
					}
				} else {

					System.out.println("No was the detected You lose your ability to order up a card!");
				}
			} else {
				int j = shouldAiCallTrump(this.getHand(current), current);
				if (j > 0) {
					ordered = true;

					trump = j;
					whoOrdered = i;
					orderedTeam = (i - 1) % 2;
					String suit = "";
					if (j == 0)
						suit = "Diamonds";
					if (j == 1)
						suit = "Clubs";
					if (j == 2)
						suit = "Hearts";
					if (j == 3)
						suit = "Spades";
					System.out.println(this.playerToString(current) + " Has ordered up trump of " + suit);
					return current;
				} else
					System.out.println(playerToString(current) + " passed!");
			}
			TimeUnit.SECONDS.sleep((long) 1.0);

		}
		// dealer = (dealer + 1) % 4;
		return 0;
	}

	void euchreSort() {
		int offJackSuit = (trump + 2) % 4;
		// for(int i = 0 ;i < 3; i++){
		// Hand current = getHand(i);
		// if(current.containsCard(11,offJackSuit)>0){
		// current.getCard(11, offJackSuit).Value = 15;
		// current.getCard(15, offJackSuit).suitValue= trump;
		// break;
		// }
		// }
		// for(int i = 0; i <3; i++){
		// Hand current = getHand(i);
		// if(current.containsCard(11, trump)>0){
		// current.getCard(11, trump).Value = 16;
		// break;
		// }
		// }
		for (int person = 0; person < 4; person++) {
			for (int card = 0; card < 5; card++) {
				Card current = this.getHand(person).getCardAt(card);
				if (current.isSame(11, trump))
					current.Value = 16;
				if (current.isSame(11, offJackSuit)) {
					current.Value = 15;
					current.suitValue = trump;
					// current.Suit = this.suitValueToString(trump);
				}
			}
		}
		player.sortTrump(trump);
		ai1.sortTrump(trump);
		ai2.sortTrump(trump);
		ai3.sortTrump(trump);
	}

	int drawCardOrder(Card option) throws InterruptedException {
		boolean ordered = false;
		for (int i = 1; i < 5; i++) {
			current = (dealer + i) % 4;
			if (current == 0) {
				System.out.println(player.toString());
				System.out.println(
						"Would you like to order up the " + option.toString() + " to " + dealerString + " Y/N");
				String input = scanner.nextLine();
				while (!input.equals("y") && !input.equals("Y") && !input.equals("Yes") && !input.equals("n")
						&& !input.equals("N") && !input.equals("No")) {
					System.out.println("You entered an INVALID response. Try again!\n");
					System.out.println(player.toString());
					System.out.println(
							"Would you like to order up the " + option.toString() + " to " + dealerString + " Y/N");
					input = scanner.nextLine();
				}
				if (input.equals("y") || input.equals("Y") || input.equals("Yes")) {
					ordered = true;
					trump = option.suitValue;
					return 1;
				} else
					System.out.println("No was the detected. You lose your ability to order up this card!");
			} else if (current == 1) {
				System.out.println("AI West Thinking");
				TimeUnit.SECONDS.sleep((long) 1.0);
				if (shouldAiOrder(ai1, option, 1) == true) {
					System.out.println("Ai West ordered up the " + option.toString());
					ordered = true;
					trump = option.suitValue;
					return 2;
				}
				System.out.println("AI West passed");
			} else if (current == 2) {
				System.out.println("AI North Thinking");
				TimeUnit.SECONDS.sleep((long) 1.0);
				if (shouldAiOrder(ai2, option, 2) == true) {
					System.out.println("Ai North ordered up the " + option.toString());
					ordered = true;
					trump = option.suitValue;
					return 3;
				}
				System.out.println("AI North passed");
			} else {
				System.out.println("AI East Thinking");
				TimeUnit.SECONDS.sleep((long) 1.0);
				if (shouldAiOrder(ai3, option, 3) == true) {
					System.out.println("Ai East ordered up the " + option.toString());
					ordered = true;
					trump = option.suitValue;
					return 4;
				}
				System.out.println("AI East passed");
			}

		}
		if (!ordered) {
			System.out.println("");
			System.out.println("No one picked up the card!");
			System.out.println("The " + option.toString() + " is dead!");
			return 0;
		} else
			return 0;
	}

	String playerToString(int player) {
		if (player == 0)
			return "You";
		if (player == 1)
			return "AI West";
		if (player == 2)
			return "AI North:";
		if (player == 3)
			return "AI East";
		else
			return "";
	}

	Hand getHand(int player) {
		if (player == 0)
			return this.player;
		if (player == 1)
			return ai1;
		if (player == 2)
			return ai2;
		else
			return ai3;
	}

	String suitValueToString(int suitValue) {
		if (suitValue == 0)
			return "Diamonds";
		if (suitValue == 1)
			return "Clubs";
		if (suitValue == 2)
			return "Hearts";
		if (suitValue == 3)
			return "Spades";
		else
			return "";
	}

	void playerPickup(Card option) {
		if (dealer == 0) {
			System.out.println("What card do you want to get rid of? (1-5)");
			System.out.println(player.toString());
			String input = scanner.nextLine();
			while (!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4")
					&& !input.equals("5")) {
				System.out.println("\nNot a valid Number! Try Again!");
				System.out.println("What card do you want to get rid of? (1-5)");
				System.out.println(player.toString());
				input = scanner.nextLine();
			}
			int dumpCard = Integer.parseInt(input) - 1;
			while (dumpCard > 4 || dumpCard < 0) {
				System.out.println("Invailid Entry!!! Try again!");
				dumpCard = Integer.parseInt(scanner.nextLine()) - 1;
			}
			player.removeCard(dumpCard);
			player.add(option);
			euchreSort();
			System.out.println("This is your updated hand...\n" + player.toString());

		} else if (dealer == 1) {
			dropWorstCard(ai1);
			ai1.add(option);
		} else if (dealer == 2) {
			dropWorstCard(ai2);
			ai2.add(option);
		} else {
			dropWorstCard(ai3);
			ai3.add(option);
		}
	}
}
