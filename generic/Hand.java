// MADE BY JONAHTAN MCCANN SUMMER 2017 ALL ORIGINAL WORK

package generic;

import java.util.ArrayList;

public class Hand {
	public ArrayList<Card> cardArray = new ArrayList<Card>();
	public Deck deck;

	public Hand(int size, Deck deck) {
		this.deck = deck;
		for (int i = 0; i < size; i++) {
			cardArray.add(deck.cardArray.get(i));
			deck.cardArray.remove(i);
		}
	}

	public Card getCard(int value, int suitValue) {
		for (int i = 0; i < cardArray.size(); i++) {
			if (cardArray.get(i).suitValue == suitValue && cardArray.get(i).Value == value)
				return cardArray.get(i);
		}
		return null;
	}

	public int getSize() {
		return cardArray.size();
	}

	public void add(Card card) {
		cardArray.add(card);
	}

	public int containsCard(int value, int suitValue) {
		int count = 0;
		for (int i = 0; i < cardArray.size(); i++) {
			if (cardArray.get(i).isSame(value, suitValue))
				count++;
		}
		return count;
	}

	public void sortValue() {
		for (int i = 1; i < cardArray.size(); i++) {
			if (cardArray.size() == 1)
				break;
			if (cardArray.get(i).Value < cardArray.get(i - 1).Value) {
				Card temp = cardArray.get(i);
				cardArray.remove(i);
				for (int j = 0; j < i; j++) {
					if (temp.Value < cardArray.get(j).Value) {
						cardArray.add(j, temp);
						break;
					}
				}
			}
		}
	}

	public Card playCard(int cardNumber) {
		Card picked = this.cardArray.get(cardNumber);
		this.removeCard(cardNumber);
		return picked;
	}

	public void sortSuit() {
		for (int i = 1; i < cardArray.size(); i++) {
			if (cardArray.size() == 1)
				break;
			// Diamonds, Clubs, Hearts, Spades
			if (cardArray.get(i).suitValue < cardArray.get(i - 1).suitValue) {
				Card temp = cardArray.get(i);
				cardArray.remove(i);
				for (int j = 0; j < i; j++) {
					if (temp.suitValue < cardArray.get(j).suitValue) {
						cardArray.add(j, temp);
						break;
					}
				}
			}
		}
	}
	public int findLowestCardOfSuit(int suit){
		int returner = -1;
		for(int i = 0; i< cardArray.size();i++){
			Card current = cardArray.get(i);
			if(returner == -1){
				if(current.suitValue == suit){
					returner = cardArray.indexOf(current);
				}
			}
			else{
				if(current.suitValue == suit && current.Value < returner)
					returner = cardArray.indexOf(current);
			}
		}
		return returner;
	}
	public int findHighestCardOfSuit(int suit) {
		int returner = -1;
		for (int i = 0; i < cardArray.size(); i++) {
			Card current = cardArray.get(i);
			if (returner == -1) {
				if (current.suitValue == suit) {
					returner = cardArray.indexOf(current);
				}
			} else {
				if (current.suitValue == suit && current.Value > returner) {
					returner = cardArray.indexOf(current);
				}
			}
		}
		return returner;
	}

	public int findHighestNonTrumpCard() {
		if (cardArray.get(0).suitValue == 5)
			return -1;
		else {
			int highest = 0;
			Card highestCard = cardArray.get(0);
			for (int i = 1; i < cardArray.size(); i++) {
				Card currentCard = cardArray.get(i);
				if (currentCard.suitValue != 5) {
					if (highestCard.Value < currentCard.Value) {
						highestCard = currentCard;
						highest = i;
					}
				}

			}
			return highest;
		}
	}

	public Card getCardAt(int i) {
		return cardArray.get(i);
	}

	public void sortTrump(int trump) {
		this.sortValue();
		for (int i = 0; i < this.getSize(); i++) {
			if (this.cardArray.get(i).suitValue == trump)
				this.cardArray.get(i).suitValue = 5;
		}
		this.sortSuit();
	}

	public boolean hasCards() {
		if (this.cardArray.size() > 0)
			return true;
		else
			return false;
	}

	public int countSuit(int suitValue) {
		int returner = 0;
		for (int i = 0; i < cardArray.size(); i++) {
			if (cardArray.get(i).suitValue == suitValue)
				returner++;
		}
		return returner;
	}

	public void removeCard(int place) {
		cardArray.remove(place);
	}

	@Override
	public String toString() {
		String returner = "[";
		for (int i = 0; i < cardArray.size(); i++) {
			returner += ",";
			returner += cardArray.get(i).toString();
		}
		if (returner.contains(",")) {
			returner = returner.replaceFirst(",", "");
		}
		return returner + "]";
	}

	public ArrayList<Card> getHand() {
		return cardArray;
	}
}
