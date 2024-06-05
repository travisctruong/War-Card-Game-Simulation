package war;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {

	private ArrayList<Card> cards;
	
	
	public Deck() {
		this.cards = new ArrayList<Card>();
	}
	
	
	public int size() {
		return this.cards.size();
	}
	
	
	public void initialize() {
		String[] suits = {"Spades","Diamonds","Hearts","Clubs"}; 
		String[] ranks = {"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};
		int[] values = {1,2,3,4,5,6,7,8,9,10,11,12,13};
		
		for (String suit : suits) {
			for (int i = 0; i < ranks.length; i++) {
				Card card = new Card(suit, ranks[i], values[i]);
				cards.add(card);
			}
		}
	}
	
	
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	
	public Card dealCard() {
		shuffle();
		return cards.remove(0);
	}
}
