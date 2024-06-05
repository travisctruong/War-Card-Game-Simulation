package war;
import java.util.ArrayList;

public class Player {

	private ArrayList<Card> hand;
	private String name;
	
	
	public Player(String name) {
		this.hand = new ArrayList<Card>();
		this.name = name;
	}
	
	
	public Player(Player other) {
		this(other.name);
	}
	
	
	public String getName() {
		return this.name;
	}
	
	
	public int getScore() {
		return this.hand.size();
	}
	
	
	public void drawCard(Deck deck) {
		hand.add(deck.dealCard());
	}
	
	
	public Card playCard() {
		return hand.remove(0);
	}
	
	
	public void winRound(ArrayList<Card> cards) {
		for (Card card : cards) {
			hand.add(card);
		}
	}
}
