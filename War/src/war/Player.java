package war;
import java.util.ArrayList;
import java.util.Collections;

public class Player {

	private ArrayList<Card> hand;
	private ArrayList<Card> winnings;
	private String name;
	private int usedCards;
	
	
	public Player(String name) {
		this.hand = new ArrayList<Card>();
		this.winnings = new ArrayList<Card>();
		this.name = name;
		this.usedCards = 0;
	}
	
	
	public Player(Player other) {
		this(other.name);
	}
	
	
	public String getName() {
		return name;
	}
	
	
	public int getScore() {
		return hand.size() + winnings.size();
	}
	
	
	public int getUsedCards() {
		return usedCards;
	}
	
	
	public void resetUsed() {
		usedCards = 0;
	}
	
	
	public void drawCard(Deck deck) {
		hand.add(deck.dealCard());
	}
	
	
	public Card playCard() {
		if (hand.size() == 0 && winnings.size() != 0) {
			transferWinnings();
		}
		usedCards += 1;
		return hand.remove(0);
	}
	
	
	public void winRound(ArrayList<Card> cards) {
		for (Card card : cards) {
			winnings.add(card);
		}
	}
	
	
	public void transferWinnings() {
		Collections.shuffle(winnings);
		while (winnings.size() != 0) {
			hand.add(winnings.remove(0));
		}
	}
}
