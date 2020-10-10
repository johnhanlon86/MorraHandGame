import java.util.Arrays;
import java.util.Scanner;

// Create MoraApp class.
public class MoraApp{
	
	// Create Main Method.
	public static void main (String [] args){
		
		// Even = 0, Odds = 1.
		int playerType;
		int computerType;
		
		// No. of fingers per round.
		int playerNumberOfFingers = 0;
		int computerNumberOfFingers = 0;
		
		// Total no. of fingers per round (player + computer).
		int totalNumberOfFingers;

		int gameNumber = 1;
		int roundNumber = 1;
		
		// Current score within round (being added to).
		int playerScore;
		int computerScore;
		
		// Option to play again (at end of game).
		int playAgain;
		
		// Total win/draw stats across all games.
		int playerNumberOfGamesWon = 0;
		int computerNumberOfGamesWon = 0;
		int totalNumberOfDraws = 0;
		
		// Win message.
		String gameOutcomeMessage;
		
		Scanner input;
		input = new Scanner (System.in);
		
		Player p1;
		Computer c1;
		GameManager gameManager;


		// Display welcome and state rules/addition rules		
		System.out.println("Welcome to Mora!");
		System.out.println("");
		System.out.println("------------------");
		System.out.println("Rules:");
		System.out.println("At the beginning of each game - choose to be the odd or even player.");
		System.out.println("At each round you show a certain number of fingers on your hands. Your opponent (the computer!) will do the same.");
		System.out.println("The total of all fingers shown will be calculated.");
		System.out.println("If the total number of fingers is ODD and you are the ODD player - you WIN!.");
		System.out.println("If the total number of fingers is EVEN and you are the EVEN player - you WIN!.");
		System.out.println("The winner of each round recieves 3 points.");
		System.out.println("Whichever player is closest to the total fingers on show recieves 2 addition bonus points.");
		System.out.println("The first player to reach 12 points wins!");
		System.out.println("");
		System.out.println("Lastly..");
		System.out.println("You cannot show 0 fingers!");
		System.out.println("If both players show the same number of fingers bonus points will not be given to either player as they will both be the same distance from the total fingers on show!");
		System.out.println("------------------");
		System.out.println("");

		gameManager = new GameManager();
		gameManager.createArrays(); /*****************1. Arrays created by calling function from Game Manager**********************/
		
		// loop the entire process if player wants to play again
		do{
		
		// Variable for Error Handling of Strings.
		String playerTypeString;
		
		// prompt user to choose to be even or odd player
		System.out.println("Enter 0 to be 'Even' player, enter 1 to be 'Odd' player!");
		playerTypeString = input.nextLine();
		
		// error handling - if player enters anything other than a '0' or '1'
		// display error message
		// ask for another valid input
		while (!playerTypeString.equals("0") && !playerTypeString.equals("1") ){
			
			System.out.println("");
			System.out.println("You have entered an invalid choice. Please choose again.");
			System.out.println("Enter 0 to be 'Even' player, enter 1 to be 'Odd' player!");
			playerTypeString = input.nextLine();
		}
		
		playerType = Integer.parseInt(playerTypeString);
		
		// if player chose even
		if(playerType == 0){
		computerType = 1;
		System.out.println("Player 1 is even!");
		System.out.println("Computer is odd!");
		System.out.println("");

		// if player chose odd
		} else {
			computerType = 0;
			System.out.println("Player 1 is odd!");
			System.out.println("Computer is even!");
			}
		
		// create player object with playerType passed to state if the computer is even or odd
		p1 = new Player(playerType);
		
		// create computer with computerType passed to state if the computer is even or odd
		c1 = new Computer(computerType);
		
		// create gameManager object with playerType, computerType, gameNumber 1, roundNumber 1
		// gameManager = new GameManager(playerType, computerType, gameNumber, roundNumber);
		gameManager.setGameNumber(gameNumber);
		gameManager.setRoundNumber(roundNumber);

		do{
	
		// display game and round number
		System.out.println("------------------");
		System.out.println("");
		System.out.println("Game number: " + gameNumber);
		roundNumber = gameManager.getRoundNumber();
		System.out.println("Round number: " + roundNumber);
		System.out.println("");
		
		String playerNumberOfFingersString;
		Scanner input02 = new Scanner (System.in);
		
		// prompt user to choose to be even or odd player
		System.out.println("Enter the number of fingers you wish to show.");
		playerNumberOfFingersString = input02.nextLine();
		
		while (!playerNumberOfFingersString.equals("1") && !playerNumberOfFingersString.equals("2") && !playerNumberOfFingersString.equals("3") && !playerNumberOfFingersString.equals("4") && !playerNumberOfFingersString.equals("5") && !playerNumberOfFingersString.equals("6") && !playerNumberOfFingersString.equals("7") && !playerNumberOfFingersString.equals("8") && !playerNumberOfFingersString.equals("9") && !playerNumberOfFingersString.equals("10")) {
			
			System.out.println("You have entered an invalid choice. Please enter a valid number (1-10) of fingers you wish show.");
			playerNumberOfFingersString = input02.nextLine();
		}
		playerNumberOfFingers = Integer.parseInt(playerNumberOfFingersString);
		
		// generate random number of computer fingers
		c1.generateComputerNumberOfFingers();
		computerNumberOfFingers = c1.getComputerNumberOfFingers();

		// display both player and computers choice
		System.out.println("");
		System.out.println("Player choice: " +  playerNumberOfFingers);
		System.out.println("Computer choice: " + computerNumberOfFingers);
		
		// get the total number of fingers on show and display
		totalNumberOfFingers = gameManager.getTotalFingers(playerNumberOfFingers, computerNumberOfFingers);
		System.out.println("Total fingers on show: " + totalNumberOfFingers);
		
		// decide who won the round and allocate points
		gameManager.decideRoundWinner();
		
		// check both player and computer distances from total fingers show and allocate points
		gameManager.checkDistancesFromTotalFingers();

		System.out.println("");

		// display game statistics
		System.out.println("------------------");
		System.out.println("Round Stats");
		System.out.println("");
		
		gameManager.displayRoundStats();

		playerScore = gameManager.getPlayerScore();
		computerScore = gameManager.getComputerScore();

		} while( playerScore < 12 && computerScore < 12 );
		
		
		// determine who won the game
		if(playerScore >= 12 && playerScore > computerScore){
			gameOutcomeMessage = "Player 1 wins!!";
			playerNumberOfGamesWon = playerNumberOfGamesWon + 1;
			
		} else if (computerScore >= 12 && computerScore > playerScore){
				gameOutcomeMessage = "Computer wins!!";
				computerNumberOfGamesWon = computerNumberOfGamesWon + 1;

			} else {
					gameOutcomeMessage = "Game ends as a draw!!";
					totalNumberOfDraws = totalNumberOfDraws + 1;
				}
				
		System.out.println("------------------");
		System.out.println(gameOutcomeMessage);
		System.out.println("------------------");

		System.out.println("Game " + gameNumber + ": " + "Game Stats");
		
		System.out.println("Player total games won: " + playerNumberOfGamesWon);
		System.out.println("Computer total games won: " + computerNumberOfGamesWon);
		System.out.println("Total number of draws: " + totalNumberOfDraws);

		System.out.println("");
		gameManager.displayGameOverStats();
		System.out.println("------------------");
		
		String playAgainString;
		Scanner input03 = new Scanner (System.in);
		
		// prompt user to choose to be even or odd player
		System.out.println("Would you like to play another game? Enter 0 for 'Yes' and 1 for 'No");
		playAgainString = input03.nextLine();
		
		while (!playAgainString.equals("0") && !playAgainString.equals("1")) {
			
			System.out.println("You have entered an invalid choice. Please choose again.");
			System.out.println("Would you like to play another game? Enter 0 for 'Yes' and 1 for 'No");
			playAgainString = input03.nextLine();
		}
		
		playAgain = Integer.parseInt(playAgainString);
		
		if(playAgain == 0){
			System.out.println("Player would like to play again.");
			gameNumber = gameNumber +1;
			
			// Reset round number before starting new game
			roundNumber = 1;
			gameManager.setPlayerScore(0);
			gameManager.setComputerScore(0);

			gameManager.setPlayerTotalRoundWins(0);
			gameManager.setPlayerTotalRoundsLost(0);

			gameManager.setComputerTotalRoundWins(0);
			gameManager.setComputerTotalRoundsLost(0);

			gameManager.setPlayerTotalClosestToTotalFingersPoints(0);
			gameManager.setComputerTotalClosestToTotalFingersPoints(0);

			gameManager.setPlayerChoseEvenTotal(0);
			gameManager.setComputerChoseEvenTotal(0);

			gameManager.setPlayerChoseOddTotal(0);
			gameManager.setComputerChoseOddTotal(0);

			gameManager.setGameNumber(gameNumber);

		} else {
			System.out.println("Player would like to exit the entire game.");
			}
			
		}
		while(playAgain == 0);

		System.out.println("");
		System.out.println("-------- Final Game History ---------");
		System.out.println("");

		// Display the game history.
		gameManager.displayArrayHistory();
	}
}
