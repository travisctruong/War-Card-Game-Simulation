package war;

public class RunWar {

	public static void main(String[] args) {
		Game playWar = new Game();
		playWar.startGame();
		
		while (playWar.determineVictor() == 0) {
			playWar.playRound();
		}
		
		playWar.declareVictor();
	}
}
