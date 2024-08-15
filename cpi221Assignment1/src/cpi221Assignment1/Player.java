package cpi221Assignment1;

public class Player {
	private String player;
	private ScoreCard scoreCard;

	public Player(String player) {
		this.player = player;
		this.scoreCard = new ScoreCard();
		
	}
	
	public String getPlayer() {
        return player;
    }

    public ScoreCard getScoreCard() {
        return scoreCard;
    
    }

}
