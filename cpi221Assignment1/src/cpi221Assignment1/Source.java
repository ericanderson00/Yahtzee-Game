/* Eric Anderson
 * 2/5/2024
 * CPI221 Assignment OOP Yahtzee!
 * Description: This game plays exactly like yahtzee for 1 - 6 players. 
 * 				i have a game class that controls the flow of the game with players turn and calling dice
 * 				rolls, getting score card for each player and ending the game with the player with the 
 * 				the highest score. Player class has get player and get score card methods. The die class
 * 				has the gettter and setter methods for the dice sides, value, randomness, and hold methods.
 * 				My DiceCup class is for creating all the methods for rolling the dice, holding 
 * 				the dice, geting the values, toString to print all dice. Finally score card class has all the 
 * 				methods of calculating score from dice, printing score card and calculating total score.
 *
 * 
 * */



package cpi221Assignment1;


import java.util.Scanner;


public class Source {
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Welcome to YAHTZEE!!");
        System.out.println("Enter the number of players (1 to 6): ");
        
        int numPlayers = scan.nextInt();
    
        while (numPlayers < 1 || numPlayers > 6) {
            System.out.println("Invalid number of players. Please enter a number between 1 and 6:");
            numPlayers = scan.nextInt();
        }
        
        //initialize player obj to create players
        Player[] players = createPlayers(numPlayers, scan);

        //game object to start game 
		Game game = new Game(players);
		game.startGame();
		
	}
	
	//method creates players
	private static Player[] createPlayers(int numPlayers, Scanner scan) {
        Player[] players = new Player[numPlayers];

        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Enter name for player " + (i + 1) + ":");
            String playerName = scan.next();
            players[i] = new Player(playerName);
        }

        return players;
    }
}
