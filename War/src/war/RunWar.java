package war;

/**
 * RunWar class - Simulates an instance of the card game War
 * 
 * @author Travis Truong
 *
 */
public class RunWar {

	public static void main(String[] args) {
		
		Game playWar = new Game();
		playWar.startGame();
		
		while (playWar.determineVictor() == 0) {  // 0 = No winner; 1 = player 1 wins; -1 = player 2 wins
			playWar.playRound();
		}
		
		playWar.declareVictor();
	}
}
