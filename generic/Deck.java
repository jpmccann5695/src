// MADE BY JONAHTAN MCCANN SUMMER 2017 ALL ORIGINAL WORK

package generic;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	public ArrayList<Card> cardArray = new ArrayList<Card>();
	
	public Deck(){
		this.addDeck();
	}
	public Deck(int numberOfDecks){
		for(int i = 0;i <numberOfDecks;i++){
			this.addDeck();
		}
	}
	public Deck(Deck copy){
		for(int i = 0; i < copy.getSize(); i++){
			cardArray.add(copy.cardArray.get(i));
		}
	}
	public Card getCardAtIndex(int index){
		return cardArray.get(index);
	}
	public Deck(char e){
		if(e == 'e'){
		//Euchre Deck
		cardArray.add(new Card("Diamond","Ace",1,0));
		cardArray.add(new Card("Diamond","Nine",9,0));
		cardArray.add(new Card("Diamond","Ten",10,0));
		cardArray.add(new Card("Diamond","Jack",11,0));
		cardArray.add(new Card("Diamond","Queen",12,0));
		cardArray.add(new Card("Diamond","King",13,0));
		cardArray.add(new Card("Club","Ace",1,1));
		cardArray.add(new Card("Club","Nine",9,1));
		cardArray.add(new Card("Club","Ten",10,1));
		cardArray.add(new Card("Club","Jack",11,1));
		cardArray.add(new Card("Club","Queen",12,1));
		cardArray.add(new Card("Club","King",13,1));
		cardArray.add(new Card("Heart","Ace",1,2));
		cardArray.add(new Card("Heart","Nine",9,2));
		cardArray.add(new Card("Heart","Ten",10,2));
		cardArray.add(new Card("Heart","Jack",11,2));
		cardArray.add(new Card("Heart","Queen",12,2));
		cardArray.add(new Card("Heart","King",13,2));
		cardArray.add(new Card("Spade","Ace",1,3));
		cardArray.add(new Card("Spade","Nine",9,3));
		cardArray.add(new Card("Spade","Ten",10,3));
		cardArray.add(new Card("Spade","Jack",11,3));
		cardArray.add(new Card("Spade","Queen",12,3));
		cardArray.add(new Card("Spade","King",13,3));
		this.acesHigh();
		}
		else if(e == 'm'){
			this.addDeck();
			this.addDeck();
			this.addJoker();
			this.addJoker();
			this.addJoker();
			this.addJoker();
		}
		else
			this.addDeck();
	}
	public void addDeck(){
		cardArray.add(new Card("Diamond","Ace",1,0));
		cardArray.add(new Card("Diamond","Two",2,0));
		cardArray.add(new Card("Diamond","Three",3,0));
		cardArray.add(new Card("Diamond","Four",4,0));
		cardArray.add(new Card("Diamond","Five",5,0));
		cardArray.add(new Card("Diamond","Six",6,0));
		cardArray.add(new Card("Diamond","Ace",7,0));
		cardArray.add(new Card("Diamond","Eight",8,0));
		cardArray.add(new Card("Diamond","Nine",9,0));
		cardArray.add(new Card("Diamond","Ten",10,0));
		cardArray.add(new Card("Diamond","Jack",11,0));
		cardArray.add(new Card("Diamond","Queen",12,0));
		cardArray.add(new Card("Diamond","King",13,0));
		cardArray.add(new Card("Club","Ace",1,1));
		cardArray.add(new Card("Club","Two",2,1));
		cardArray.add(new Card("Club","Three",3,1));
		cardArray.add(new Card("Club","Four",4,1));
		cardArray.add(new Card("Club","Five",5,1));
		cardArray.add(new Card("Club","Six",6,1));
		cardArray.add(new Card("Club","Ace",7,1));
		cardArray.add(new Card("Club","Eight",8,1));
		cardArray.add(new Card("Club","Nine",9,1));
		cardArray.add(new Card("Club","Ten",10,1));
		cardArray.add(new Card("Club","Jack",11,1));
		cardArray.add(new Card("Club","Queen",12,1));
		cardArray.add(new Card("Club","King",13,1));
		cardArray.add(new Card("Heart","Ace",1,2));
		cardArray.add(new Card("Heart","Two",2,2));
		cardArray.add(new Card("Heart","Three",3,2));
		cardArray.add(new Card("Heart","Four",4,2));
		cardArray.add(new Card("Heart","Five",5,2));
		cardArray.add(new Card("Heart","Six",6,2));
		cardArray.add(new Card("Heart","Ace",7,2));
		cardArray.add(new Card("Heart","Eight",8,2));
		cardArray.add(new Card("Heart","Nine",9,2));
		cardArray.add(new Card("Heart","Ten",10,2));
		cardArray.add(new Card("Heart","Jack",11,2));
		cardArray.add(new Card("Heart","Queen",12,2));
		cardArray.add(new Card("Heart","King",13,2));
		cardArray.add(new Card("Spade","Ace",1,3));
		cardArray.add(new Card("Spade","Two",2,3));
		cardArray.add(new Card("Spade","Three",3,3));
		cardArray.add(new Card("Spade","Four",4,3));
		cardArray.add(new Card("Spade","Five",5,3));
		cardArray.add(new Card("Spade","Six",6,3));
		cardArray.add(new Card("Spade","Ace",7,3));
		cardArray.add(new Card("Spade","Eight",8,3));
		cardArray.add(new Card("Spade","Nine",9,3));
		cardArray.add(new Card("Spade","Ten",10,3));
		cardArray.add(new Card("Spade","Jack",11,3));
		cardArray.add(new Card("Spade","Queen",12,3));
		cardArray.add(new Card("Spade","King",13,3));
	}
	public void addJoker(){
		cardArray.add(new Card("","Joker",0,4));
	}
	public void addCard(Card added){
		this.cardArray.add(added);
	}
	public void addCardTop(Card added) {
		this.cardArray.add(0, added);
	}
	public int getSize(){
		return cardArray.size();
	}
	public void shuffle(){
		Collections.shuffle(cardArray);
	}
	public int containsCard(int value, int suitValue){
		int count = 0;
		for(int i = 0; i < cardArray.size(); i++){
			if(cardArray.get(i).isSame(value,suitValue))
				count++;
		}
		return count;
	}
	public void acesHigh(){
		for(int i = 0; i< cardArray.size(); i++){
			Card current = cardArray.get(i);
			if(current.Value==1)
				current.Value=14;
				
		}
	}
	@Override
	public String toString(){
		String returner="[";
		for(int i=0; i< cardArray.size();i++){
			returner+=",";
			returner+= cardArray.get(i).toString();
		}
		if(returner.contains(",")){
			returner = returner.replaceFirst(",", "");
		}
		return returner;
	}
	public Card getNextCard(){
		Card returner = cardArray.get(0);
		cardArray.remove(0);
		return returner;
	}
	public String topCardToString() {
		return cardArray.get(0).toString();
	}
	
}
