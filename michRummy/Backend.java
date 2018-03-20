package michRummy;

import java.util.concurrent.ThreadLocalRandom;

import generic.Deck;
import generic.Hand;
import generic.Player;

public class Backend {

	Deck deal, discard;
	Player user, ai1, ai2, ai3;
	int userScore, ai1Score, ai2Score, ai3Score, dealer, level;
	boolean gameOver;

	void newGame() {
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

	void playHand() {
		boolean complete = false;
		if (level > 12) {
			gameOver = true;
			return;
		}
		deal = new Deck('m');
		deal.shuffle();
		user.setHand(new Hand(level, deal));
		ai1.setHand(new Hand(level, deal));
		ai2.setHand(new Hand(level, deal));
		ai3.setHand(new Hand(level, deal));

		// while(1) {
		//
		// }
	}

	int bookCount(Hand check) {
		int count = 0;
		int[] valueCount = check.valueCount();
		if (valueCount[0] > 0) {
			int tempJoker = valueCount[0];
			for (int i = 1; i < valueCount.length; i++) {
				if (valueCount[i] > 2) {
					count++;
				} else if (valueCount[i] > 1) {
					if (tempJoker > 0) {
						tempJoker--;
						count++;
					}
				}
			}
		} else {
			for (int i = 1; i < valueCount.length; i++) {
				if (valueCount[i] > 2)
					count++;
			}
		}
		return count;
	}

	int runCount(Hand check) {
		int count = 0;
		int[] valueCount = check.valueCount();
		if (valueCount[0] > 0) {

		} else {
			//
			// USE THIS AS OLD CODE UPDATED TO WHILE LOOP
			//
			// for(int i = 1; i< 12; i++) { //on the ace search if there is up to 5
			// boolean fourFlag = false;
			// boolean fiveFlag = false;
			// if(i==1) {
			// for(int j = 1; j<5; j++) {
			// if(valueCount[i+j]==0)
			// break;
			// else if(j == 3)
			// fourFlag=true;
			// else if(j==4)
			// fiveFlag=true;
			// }
			// if(fourFlag) {
			// valueCount[1]--;
			// valueCount[2]--;
			// valueCount[3]--;
			// valueCount[4]--;
			// }
			// else if(fiveFlag) {
			// valueCount[2]--;
			// valueCount[3]--;
			// valueCount[4]--;
			// valueCount[5]--;
			// }
			// }
			// else if(valueCount[i] >1 && i!=11 ){
			//
			// }
			// else if(valueCount[11] >1) {
			//
			// }
			//
			// }
			int i = 1;
			// int runLength = 0;
			boolean singleRun = false;
			boolean doubleRun = false;
			boolean trippleRun = false;
			while (i < 13) {
				if (valueCount[i] > 0 && i < 13) {// check for run at this position
					// singleRun = false;
					// doubleRun = false;
					// trippleRun = false;

					// Ace Checker for a-4 2-5 use ace up top
					if (i == 1) {
						boolean aceFour = false;
						boolean aceFive = false;
						int j = 2;
						while (j < 15) {
							if (valueCount[j] < 1)
								break;
							else if (j - i == 3) {
								aceFour = true;
								singleRun = true;
							} else if (j - i == 4)
								aceFive = true;
							else if (j - i == 7)
								doubleRun = true;
							else if (j - i == 11)
								trippleRun = true;
							j++;
						}
						if (aceFive) {
							if (valueCount[11] > 0) {
								if (valueCount[12] > 0) {
									if (valueCount[13] > 0) {
										count++;
										valueCount[1]--;
										valueCount[11]--;
										valueCount[12]--;
										valueCount[13]--;
										singleRun = false;
										doubleRun = false;
										trippleRun = false;
																			}
								}
							}
						}
						j++;
					}
					else {
						int j = i + 1;
						while (j < 15) {
							if (j == 14) {
								if (valueCount[1] > 0) {
									if (i == 11) {
										count++;
										valueCount[1]--;
										valueCount[11]--;
										valueCount[12]--;
										valueCount[13]--;
										break;
									}
								}
							} else if (valueCount[j] < 1)
								break;
							else if (j - i == 3)
								singleRun = true;
							else if (j - i == 7)
								doubleRun = true;
							else if (j - i == 11)
								trippleRun = true;
							j++;
						}
					}

					// Remove the cards from the count array
					if (trippleRun) {
						for (int k = i; k < i + 12; k++) {
							valueCount[k]--;
						}
						count += 3;
						break;
					} else if (doubleRun) {
						for (int k = i; k < i + 8; k++) {
							valueCount[k]--;
						}
						count += 2;
					} else if (singleRun) {
						for (int k = i; k < i + 4; k++) {
							valueCount[k]--;
						}

						count++;
					}
					if (!singleRun && !doubleRun && !trippleRun) {
						i++;
					}
					singleRun = false;
					doubleRun = false;
					trippleRun = false;
				}
			
			else
				i++;
			}

		}
		return count;
	}

	boolean layableHand(Hand check) {

		if (level == 6) {
			if (this.bookCount(check) == 2)
				return true;
		} else if (level == 7) {
			// TODO
		} else if (level == 8) {
			if (this.runCount(check) == 2)
				return true;
		} else if (level == 9) {
			if (this.bookCount(check) == 3)
				return true;
		} else if (level == 10) {

		} else if (level == 11) {

		} else {
			if (this.runCount(check) == 3)
				// TODO this has to be all cards.
				return true;

		}
		return false;
	}
}
