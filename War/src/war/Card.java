package war;
public class Card {

	private String suit;
	private String rank;
	private int value;
	
	
	public Card(String suit, String rank, int value) {
		this.suit = suit;
		this.rank = rank;
		this.value = value;
	}
	
	public Card(Card other) {
		this(other.suit, other.rank, other.value);
	}
	
	
	public String getSuit() {
		return this.suit;
	}
	
	
	public String getRank() {
		return this.rank;
	}
	
	
	public int getValue() {
		return this.value;
	}
	
	
	public void setSuit(String suit) {
		this.suit = suit;
	}
	
	
	public void setRank(String rank) {
		this.rank = rank;
	}
	
	
	public void setValue(int value) {
		this.value = value;
	}
}
