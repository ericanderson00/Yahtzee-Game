package cpi221Assignment1;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.List;

public class Game {
	
	private DiceCup cup;
	private Scanner scan;
	
	private int currentRound;
	private int maxRounds = 13;
	private int currentPlayerIndex;
	
    private Player[] players;
    
    
	public Game(Player[] players) {
    
		cup = new DiceCup();
		scan = new Scanner(System.in);
	
		this.players = players;
        this.currentRound = 0;
        this.currentPlayerIndex = 0;
        
        
	}
	
	public void startGame() {
		//loops through each round 
		for (currentRound = 1; currentRound <= maxRounds; currentRound++) {
            System.out.println("Round " + currentRound);
            
            //loops through each players turn
            for (currentPlayerIndex = 0; currentPlayerIndex < players.length; currentPlayerIndex++) {
            	Player currentPlayer = players[currentPlayerIndex];
            	System.out.println("Player's turn: " + players[currentPlayerIndex].getPlayer());
                System.out.println(players[currentPlayerIndex].getPlayer() + "'s Score Card");
                
                //prints current players ScoreCard
                currentPlayer.getScoreCard().printScoreCard();
                
                //executes dice rolling for the players turn
                playRound(players[currentPlayerIndex]);

                //calculates player score
                scorePlayer(players[currentPlayerIndex]);
                
        
            }
            if (currentRound == maxRounds) {
            	finishGame();
            }
            
		}	
	}
	//executes players intial roll, first roll and finall roll
	private void playRound(Player player) {
		
		//inital roll
		cup.unHoldAllDice();
		cup.rollAll();
		System.out.println("Initial Roll : \n" + cup);
		holdDice();
				
		//roll 1
		cup.rerollUnheldDice();
		System.out.println("First roll: \n" + cup);
		holdDice();
		
		//final roll
		cup.rerollUnheldDice();
		cup.holdAllDice();
		System.out.println("Final Roll: \n" + cup + "\n");
		
		
	}
	
	//prompts user which dice to hold and updates list of held dice
	private void holdDice() {
		System.out.println(" - Index starts at 1");
		System.out.println(" - (x) indicates which dice you hold");
		System.out.println(" - Which indices do you want to hold? (Enter numbers separated by space)");
		System.out.println(" - Enter 0 if you dont want to hold dice");
		
		String line = scan.nextLine(); 
		
		
		String[] tokens = line.split(" ");
		
		int[] diceHeld = new int[tokens.length];

		// Convert each token to an integer
		for (int i = 0; i < tokens.length; i++) {
		    diceHeld[i] = Integer.parseInt(tokens[i]) - 1; // Subtract 1 if your dice indices are 1-based
		}
		cup.holdDice(diceHeld);
		
	}
	
	//prompts player how they wanna be scored from the final roll and add to score card
	private void scorePlayer(Player player) {
		
		//prompts the player to chose a way to be score until choses an avalible one
	    boolean validCategory = false;
	    while (!validCategory) {
	    	
	        System.out.println(player.getPlayer() + ", how would you like to be scored?");
	        System.out.println("Available categories:");

	        //shows available categories
	        for (String category : player.getScoreCard().getCategories()) {
	            if (player.getScoreCard().isCategoryAvailable(category)) {
	                System.out.println(category);
	            }
	        }
	        
	        System.out.println("Enter Category: ");
	        
	        String chosenCategory = scan.nextLine().toUpperCase();

	        if (player.getScoreCard().isCategoryAvailable(chosenCategory)) {
	            
	        	// Calculate the score for the chosen category
	            int score = player.getScoreCard().calculateScore(chosenCategory, cup.getHeldDice());
	            player.getScoreCard().recordScore(chosenCategory, score);

	            // Print the player's updated score card
	            System.out.println(player.getPlayer() + "'s Scorecard:");
	            player.getScoreCard().printScoreCard();
	            validCategory = true;
	        } else {
	            System.out.println("This category has already been used or is invalid. Please choose another.");
	        }
	    }
	}
	
	//calculates highest score and determines a winner
	public void finishGame() {
		int highestScore = 0;
		Player winner = null;
		
		for(Player player: players) {
			int totalScore = player.getScoreCard().calculateTotalScore();
			if(totalScore > highestScore) {
				highestScore = totalScore;
				winner = player;
			}
		}
		if (winner != null) {
			System.out.println("Winner		: " + winner.getPlayer());
			System.out.println("Total Score	: " + highestScore);
		}else {
			System.out.println("No winner");
		}
	}
	
	

	
	
}
