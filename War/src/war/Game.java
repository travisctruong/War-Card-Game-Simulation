package war;
import java.util.ArrayList;

public class Game {

	private Deck deck;
	private Player player1;
	private Player player2;
	
	
	public Game() {
		this.deck = new Deck();
		this.player1 = new Player("Player 1");
		this.player2 = new Player("Player 2");
	}
	
	
	public void startGame() {
		deck.initialize();
		deck.shuffle();
		
		while (deck.size() > 0) {
			player1.drawCard(deck);
			player2.drawCard(deck);
		}
	}
	
	
	public int determineVictor() {
		if (player2.getScore() == 0) {
			return 1;
		}
		else if (player1.getScore() == 0) {
			return -1;
		}
		else {
			return 0;
		}
	}
	
	
	public void declareVictor() {
		if (determineVictor() == 1) {
			System.out.println();
			System.out.println(player1.getName() + " has won the game!");
		}
		else if (determineVictor() == -1) {
			System.out.println();
			System.out.println(player2.getName() + " has won the game!");
		}
		else {
			System.out.println();
			System.out.println("ERROR NO VICTOR");
		}
	}
	
	
	public void resetUsed() {
		player1.resetUsed();
		player2.resetUsed();
	}
	
	
	public void assembleCards(Card card1, Card card2, ArrayList<Card> cards) {
		cards.add(card1);
		cards.add(card2);
	}
	
	
	public void playRound() {
		Card card1 = player1.playCard();
		Card card2 = player2.playCard();
		ArrayList<Card> cards = new ArrayList<Card>();
		
		assembleCards(card1, card2, cards);
		
		if (card1.getValue() > card2.getValue()) {
			player1.winRound(cards);
			resetUsed();
			System.out.println(player1.getName() + "'s " + card1.getRank() + " of " + card1.getSuit() + " beat " + player2.getName() + "'s " + card2.getRank() + " of " + card2.getSuit() + " (" + player1.getScore() + "-" + player2.getScore() + ")");
		}
		else if (card1.getValue() < card2.getValue()) {
			player2.winRound(cards);
			resetUsed();
			System.out.println(player2.getName() + "'s " + card2.getRank() + " of " + card2.getSuit() + " beat " + player1.getName() + "'s " + card1.getRank() + " of " + card1.getSuit() + " (" + player1.getScore() + "-" + player2.getScore() + ")");
		}
		else {
			System.out.println("Both players played " + card2.getRank() + " of " + card2.getSuit());
			System.out.println();
			System.out.println("WAR!!!");

			if ((player1.getScore() < 4 && player1.getScore() != 0) || (player2.getScore() < 4 && player2.getScore() != 0)) {
				warSmall(cards);
			}
			else if (player1.getScore() == 0) {
				player2.winRound(cards);
				resetUsed();
				System.out.println(player1.getName() + " has insufficient cards to play out the war");
			}
			else if (player2.getScore() == 0) {
				player1.winRound(cards);
				resetUsed();
				System.out.println(player2.getName() + " has insufficient cards to play out the war");
			}
			else {
				warRegular(cards);
			}
		}
	}
	
	
	public void warRegular(ArrayList<Card> cards) {
		
		for (int i = 0; i < 3; i++) {
			assembleCards(player1.playCard(), player2.playCard(), cards);
		}
		computeWar(cards, 3);
	}
	
	
	public void warSmall(ArrayList<Card> cards) {
		int gambitSize = 0;
		
		if (player1.getScore() < 4 && player1.getScore() > 1) {
			gambitSize = player1.getScore() - 1;
			for (int i = 0; i < gambitSize; i++) {
				assembleCards(player1.playCard(), player2.playCard(), cards);
			}
			computeWar(cards, gambitSize);
		}
		else if (player2.getScore() < 4 && player2.getScore() > 1) {
			gambitSize = player2.getScore() - 1;
			for (int i = 0; i < gambitSize; i++) {
				assembleCards(player1.playCard(), player2.playCard(), cards);
			}
			computeWar(cards, gambitSize);
		}
		else {
			computeWar(cards, gambitSize);
		}
	}
	
	
	public void computeWar(ArrayList<Card> cards, int gambitSize) {
		Card card1 = player1.playCard();
		Card card2 = player2.playCard();
		assembleCards(card1, card2, cards);

		if (card1.getValue() > card2.getValue()) {
			player1.winRound(cards);
			System.out.println(player1.getName() + "'s " + card1.getRank() + " of " + card1.getSuit() + " beat " + player2.getName() + "'s " + card2.getRank() + " of " + card2.getSuit());
			System.out.println(player1.getName() + " wins the war and gains " + player2.getUsedCards() + " cards!" + " (" + player1.getScore() + "-" + player2.getScore() + ")");
			System.out.println();
			resetUsed();
		}
		else if (card1.getValue() < card2.getValue()) {
			player2.winRound(cards);
			System.out.println(player2.getName() + "'s " + card2.getRank() + " of " + card2.getSuit() + " beat " + player1.getName() + "'s " + card1.getRank() + " of " + card1.getSuit());
			System.out.println(player2.getName() + " wins the war and gains " + player1.getUsedCards() + " cards!" + " (" + player1.getScore() + "-" + player2.getScore() + ")");
			System.out.println();
			resetUsed();
		}
		else {
			System.out.println("Both players played " + card2.getRank() + " of " + card2.getSuit());
			System.out.println();
			System.out.println("WAR AGAIN!!!");
			
			if ((player1.getScore() < 4 && player1.getScore() != 0) || (player2.getScore() < 4 && player2.getScore() != 0)) {
				warSmall(cards);
			}
			else if (player1.getScore() == 0 || player2.getScore() == 0) {
				warAgainSmall(card1, card2, cards, gambitSize);
			}
			else {
				warRegular(cards);
			}
		}
	}
		
	
	public void warAgainSmall(Card card1, Card card2, ArrayList<Card> cards, int gambitSize) {
		if (player1.getScore() == 0) {
			for (int i = 0; i < gambitSize; i++) {
				Card tempCard = player2.playCard();
				cards.add(tempCard);
			}
			card2 = player2.playCard();
			cards.add(card2);
			if (card1.getValue() > card2.getValue()) {
				player1.winRound(cards);
				System.out.println(player1.getName() + "'s " + card1.getRank() + " of " + card1.getSuit() + " beat " + player2.getName() + "'s " + card2.getRank() + " of " + card2.getSuit());
				System.out.println(player1.getName() + " wins the war and gains " + player2.getUsedCards() + " cards!" + " (" + player1.getScore() + "-" + player2.getScore() + ")");
				System.out.println();
				resetUsed();
			}
			else if (card1.getValue() < card2.getValue()) {
				player2.winRound(cards);
				System.out.println(player2.getName() + "'s " + card2.getRank() + " of " + card2.getSuit() + " beat " + player1.getName() + "'s " + card1.getRank() + " of " + card1.getSuit());
				System.out.println(player2.getName() + " wins the war and gains " + player1.getUsedCards() + " cards!" + " (" + player1.getScore() + "-" + player2.getScore() + ")");
				System.out.println();
				resetUsed();
			}
			else {
				System.out.println("Both players played " + card2.getRank() + " of " + card2.getSuit());
				System.out.println();
				System.out.println("WAR AGAIN!!!");

				warAgainSmall(card1, card2, cards, gambitSize);
			}
		}
		else if (player2.getScore() == 0) {
			for (int i = 0; i < gambitSize; i++) {
				Card tempCard = player1.playCard();
				cards.add(tempCard);
			}
			card1 = player1.playCard();
			if (card1.getValue() > card2.getValue()) {
				player1.winRound(cards);
				System.out.println(player1.getName() + "'s " + card1.getRank() + " of " + card1.getSuit() + " beat " + player2.getName() + "'s " + card2.getRank() + " of " + card2.getSuit());
				System.out.println(player1.getName() + " wins the war and gains " + player2.getUsedCards() + " cards!" + " (" + player1.getScore() + "-" + player2.getScore() + ")");
				System.out.println();
				resetUsed();
			}
			else if (card1.getValue() < card2.getValue()) {
				player2.winRound(cards);
				System.out.println(player2.getName() + "'s " + card2.getRank() + " of " + card2.getSuit() + " beat " + player1.getName() + "'s " + card1.getRank() + " of " + card1.getSuit());
				System.out.println(player2.getName() + " wins the war and gains " + player1.getUsedCards() + " cards!" + " (" + player1.getScore() + "-" + player2.getScore() + ")");
				System.out.println();
				resetUsed();
			}
			else {
				System.out.println("Both players played " + card2.getRank() + " of " + card2.getSuit());
				System.out.println();
				System.out.println("WAR AGAIN!!!");
				
				warAgainSmall(card1, card2, cards, gambitSize);
			}
		}
	}
}
