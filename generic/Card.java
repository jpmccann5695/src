// MADE BY JONAHTAN MCCANN SUMMER 2017 ALL ORIGINAL WORK

package generic;

public class Card {
	public String Suit;
	public String Name;
	public int Value, suitValue; //Diamond = 0, Clubs = 1, Hearts = 2, Spades = 3, Joker 4
								

	public Card(String Suit, String Name, int Value, int suitValue) {
		this.Suit = Suit;
		this.Name = Name;
		this.Value = Value;
		this.suitValue = suitValue;
	}

	@Override
	public String toString() {
		if (Value > 1 && Value < 11)
			return Value + "" + Suit.charAt(0);
		return Name.charAt(0) + "" + Suit.charAt(0);
	}
	public String getSuit(){
		return this.Suit;
	}
	public boolean isSame(int value, int suitValue){
		if(value == this.Value){
			if(suitValue == this.suitValue)
				return true;
		}
		return false;
	}
	public boolean nonSuitFinder(int value){
		if(value == this.Value)
			return true;
		return false;
	}
}
