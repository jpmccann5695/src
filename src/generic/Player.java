package generic;

public class Player {

	String Name;
	Hand Hand;
	Player Partner = this;
	
	public Player(String Name){
		this.Name = Name;
	}
	public Player(String Name, Hand Hand){
		this.Name = Name;
		this.Hand = Hand;
	}
	public void setHand(Hand update){
		Hand = update;
	}
	public Hand getHand(){
		return Hand;
	}
	public void setPlayer(Player update){
		Partner = update;
	}
	public Player getPartner(){
		return Partner;
	}
	
}
