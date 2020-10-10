import java.util.Arrays;

// Create GameManager class.
public class GameManager{

	// Declare (and initialise some) instance variables.
	
	// Round number.
	private int roundNumber = 1;
	private int gameNumber = 1;
	
	// No. of Fingers Per Round.
	private int playerNumberOfFingers;
	private int computerNumberOfFingers;
	
	// No. of Fingers (Computer + Player) Per Round.
	private int totalFingers;
	
	// Odd or Even Player.
	private int playerType;
	private int computerType;
	
	// Current Score per Round (being added to constantly).
	private int playerScore;
	private int computerScore;
	
	// Total number if rounds won/lost by the player.
	private int playerTotalRoundWins;
	private int playerTotalRoundsLost;
	
	// Total number if rounds won/lost by the computer.
	private int computerTotalRoundWins;
	private int computerTotalRoundsLost;
	
	// Total number of Even/Odd numbers chosen - Note: Variable no longer required.
	// private int playerTotalNumberOfEvenChosen;
	// private int playerTotalNumberOfOddChosen;
	
	// Total number of Even/Odd numbers chosen - Note: Variable no longer required.
	// private int computerTotalNumberOfEvenChosen;
	// private int computerTotalNumberOfOddChosen;
	
	// Remainder of fingers from total fingers (in round).
	private int playerDistanceFromTotal;
	private int computerDistanceFromTotal;
	
	// Points values for standard point and bonus point.
	private final int roundWinPoints = 3;
	private final int closestToTotalFingersPoints = 2;
	
	// Number of times a bonus point is scored.
	private int playerTotalClosestToTotalFingersPoints = 0;
	private int computerTotalClosestToTotalFingersPoints = 0;
	
	// Number of times chose even/odd chosen across all games.
	private int playerChoseEvenTotal = 0;
	private int computerChoseEvenTotal = 0;
	private int playerChoseOddTotal = 0;
	private int computerChoseOddTotal = 0;
	
	// Round win messages.
	String roundWinnerMessage;
	String closestToTotalFingersMessage;
	
	/*********2. Arrays Declared***********/ // Declare empty 1D array to store number of rounds won/lost by player.
	int[] numberOfRoundsWonByPlayer;
	int[] numberOfRoundsLostByPlayer;
	
	// Declare empty 1D array to store number of even/odd chosen by player.
	int[] numberOfEvenChosenByPlayer;
	int[] numberOfOddChosenByPlayer;
	
	// Declare empty 1D array to store number of even/odd chosen by computer.
	int[] numberOfEvenChosenByComputer;
	int[] numberOfOddChosenByComputer;
	
	// Declare empty 1D array to store number of bonus points received by player/computer.
	int[] numberOfBonusPointsReceivedByPlayer;
	int[] numberOfBonusPointsReceivedByComputer;
	
	// constructor(s)
	// Constructs a game manager object - JH: REMOVE?
	public GameManager(int playerType, int computerType, int gameNumber, int roundNumber){
		this.playerType = playerType;
		this.computerType = computerType;
	
		this.gameNumber = gameNumber;
		this.roundNumber = roundNumber;
	}
	
	// Constructs a game manager object.
	public GameManager(){}
	
	// methods
	// Method to create/initialise arrays required to store information about the game.
	public void createArrays(){
		
		// Create 1D array (capacity 100) to store number of rounds won/lost by player.
		numberOfRoundsWonByPlayer = new int [100];
		numberOfRoundsLostByPlayer = new int [100];
		
		// Create 1D array (capacity 100) to store number of Even/odd chosen by player.
		numberOfEvenChosenByPlayer = new int [100];
		numberOfOddChosenByPlayer = new int [100];
		
		// Create 1D array (capacity 100) to store number of Even/odd chosen by computer.
		numberOfEvenChosenByComputer  = new int [100];
		numberOfOddChosenByComputer  = new int [100];
		
		// Create 1D array (capacity 100) to store number of bonus points received by player/computer.
		numberOfBonusPointsReceivedByPlayer = new int [100];
		numberOfBonusPointsReceivedByComputer = new int [100];
	}
	
	// Method to calculate the total number of fingers.
	public int getTotalFingers(int playerNumberOfFingers, int computerNumberOfFingers){
	
		this.playerNumberOfFingers = playerNumberOfFingers;
		this.computerNumberOfFingers = computerNumberOfFingers;
		
		totalFingers = playerNumberOfFingers + computerNumberOfFingers;
		return totalFingers;
	}
	
	// Method to calculate the round winner.
	public void decideRoundWinner(){
	
		roundNumber = roundNumber +1;
		
		// if total fingers is even
		if((totalFingers % 2) == 0){
			System.out.println("Number is even!");
			
			// if player is even
			if(playerType == 0){
				playerWinsRound();
			} else {
				computerWinsRound();
				}
		}
		// total fingers must be odd
		else {
			System.out.println("Number is odd!");
			
			// if player is odd
			if(playerType == 1){
				playerWinsRound();
				
			} else {
				computerWinsRound();
				}
		}
			
		// run the odd even counter
		oddEvenCounter();
	}
	
	// Method to calculate remainder of fingers to total fingers to total fingers.
	public void checkDistancesFromTotalFingers(){
	
	playerDistanceFromTotal = totalFingers - playerNumberOfFingers;
	computerDistanceFromTotal = totalFingers - computerNumberOfFingers;
	
	if(playerDistanceFromTotal < computerDistanceFromTotal){
		closestToTotalFingersMessage = "Player is closer to total fingers! Player receives 2 addition points!";
		
		// Bonus points get added for Player.
		playerScore = playerScore + closestToTotalFingersPoints;
		playerTotalClosestToTotalFingersPoints = playerTotalClosestToTotalFingersPoints + closestToTotalFingersPoints;
		
	} else if (playerDistanceFromTotal > computerDistanceFromTotal){
		closestToTotalFingersMessage = "Computer is closer to total fingers! Computer receives 2 addition points!";
		
		// Bonus points get added for Computer.
		computerScore = computerScore + closestToTotalFingersPoints;
		computerTotalClosestToTotalFingersPoints = computerTotalClosestToTotalFingersPoints + closestToTotalFingersPoints;
		
		} else {
				closestToTotalFingersMessage = "Both players displayed the same amount of fingers! Neither player receives any addition points!";
			}
	}
	
	// Method to add a won round to total rounds won (player).
	public void playerWinsRound(){
		roundWinnerMessage = "Player wins the round! Player receives 3 points!";
		
		// Standard points get added to Player's existing score.
		playerScore = playerScore + roundWinPoints;
		playerTotalRoundWins = playerTotalRoundWins +1;
		computerTotalRoundsLost = computerTotalRoundsLost +1;
	}
	
	// Method to add a won round to total rounds won (computer).
	public void computerWinsRound(){
		roundWinnerMessage = "Computer wins the round! Computer receives 3 points!";
		computerScore = computerScore + roundWinPoints;
		computerTotalRoundWins = computerTotalRoundWins +1;
		playerTotalRoundsLost = playerTotalRoundsLost +1;
	}
	
	// Counting number of times an even/odd number chosen and adding this to the total.
	public void oddEvenCounter(){
	
		if ((playerNumberOfFingers % 2) == 0){
			playerChoseEvenTotal = playerChoseEvenTotal +1;
		}
		
		if ((playerNumberOfFingers % 2) != 0) {
			playerChoseOddTotal = playerChoseOddTotal +1;
		}
			
		if ((computerNumberOfFingers % 2) == 0){
			computerChoseEvenTotal = computerChoseEvenTotal +1;
		}
			
		if ((computerNumberOfFingers % 2) != 0){
			computerChoseOddTotal = computerChoseOddTotal +1;
		}
		
	}
	
	// Method to display round stats at end of each round.
	public void displayRoundStats(){
		
		System.out.println(roundWinnerMessage);

		System.out.println("");

		System.out.println("Player distance from total: " + playerDistanceFromTotal);
		System.out.println("Computer distance from total: " + computerDistanceFromTotal);
		
		System.out.println("");

		System.out.println(closestToTotalFingersMessage);
		
		System.out.println("");

		System.out.println("Player score: " + playerScore);
		System.out.println("Computer score: " + computerScore);
		
		System.out.println("");
		
		System.out.println("Player total round wins: " + playerTotalRoundWins);
		System.out.println("Computer total round wins: " + computerTotalRoundWins);

		System.out.println("");
		
		System.out.println("Player total rounds lost: " + playerTotalRoundsLost);
		System.out.println("Computer total rounds lost: " + computerTotalRoundsLost);
		
		System.out.println("");
		
		System.out.println("Player total additional points: " + playerTotalClosestToTotalFingersPoints);
		System.out.println("Computer total additional points: " + computerTotalClosestToTotalFingersPoints);
		
		System.out.println("");

		System.out.println("Times player chose even: " + playerChoseEvenTotal);
		System.out.println("Times computer chose even: " + computerChoseEvenTotal);
		
		System.out.println("");

		System.out.println("Times player chose odd: " + playerChoseOddTotal);
		System.out.println("Times computer chose odd: " + computerChoseOddTotal);
		
	}
	
	// Method to display game stats at end of game.
	public void displayGameOverStats(){
	
		// System.out.println("Player total games won: " + playerNumberOfGamesWon);
		System.out.println("Final player score: " + playerScore);
		System.out.println("Player total round wins: " + playerTotalRoundWins);
		System.out.println("Player total rounds lost: " + playerTotalRoundsLost);
		System.out.println("Player total additional points: " + playerTotalClosestToTotalFingersPoints);
		System.out.println("Total times player chose even: " + playerChoseEvenTotal);
		System.out.println("Total times player chose odd: " + playerChoseOddTotal);
		
		System.out.println("---");
		
		// System.out.println("Computer total games won: " + computerNumberOfGamesWon);
		System.out.println("Final computer score: " + computerScore);		
		System.out.println("Computer total round wins: " + computerTotalRoundWins);		
		System.out.println("Computer total rounds lost: " + computerTotalRoundsLost);		
		System.out.println("Computer total additional points: " + computerTotalClosestToTotalFingersPoints);		
		System.out.println("Total times computer chose even: " + computerChoseEvenTotal);		
		System.out.println("Total times computer chose odd: " + computerChoseOddTotal);

		numberOfRoundsWonByPlayer[gameNumber-1] = playerTotalRoundWins;
		numberOfRoundsLostByPlayer[gameNumber-1] = playerTotalRoundsLost;
		
		numberOfEvenChosenByPlayer[gameNumber-1] = playerChoseEvenTotal;
		numberOfOddChosenByPlayer[gameNumber-1] = playerChoseOddTotal;
		
		numberOfEvenChosenByComputer[gameNumber-1] = computerChoseEvenTotal;
		numberOfOddChosenByComputer[gameNumber-1] = computerChoseOddTotal;
		
		numberOfBonusPointsReceivedByPlayer[gameNumber-1] = playerTotalClosestToTotalFingersPoints;
		numberOfBonusPointsReceivedByComputer[gameNumber-1] = computerTotalClosestToTotalFingersPoints;
		
		/*
		// Testing.
		for(int k = 0; k < gameNumber; k++){
			System.out.println(" Array other attempt: " + numberOfRoundsWonByPlayer[k]);
		}
		*/
		
	}
	
	// Method to display information saved to Array at end of game.
	public void displayArrayHistory() {
		
		// PLAYER ROUNDS WON ARRAY
		System.out.println("The total rounds won by the player in each game is: ");

		// Traverse the array.
		for(int k = 0; k < gameNumber; k++){
			System.out.println("Game No " + (k+1) + ": " + numberOfRoundsWonByPlayer[k] + " Rounds Won");
		}
		
		//System.out.println(Arrays.toString(numberOfRoundsWonByPlayer));
		System.out.println("");
		
		// PLAYER ROUNDS LOST ARRAY
		System.out.println("The total rounds Lost by the player in each game is: ");
		
		// Traverse the array.
		for(int k = 0; k < gameNumber; k++){
			System.out.println("Game No " + (k+1) + ": " + numberOfRoundsLostByPlayer[k] + " Rounds Lost");
		}
		
		//System.out.println(Arrays.toString(numberOfRoundsLostByPlayer));
		System.out.println("");
		System.out.println("");
		System.out.println("");
		
		// PLAYER CHOSE EVEN ARRAY
		System.out.println("The total times player chose even in each game is: ");

		// Traverse the array.
		for(int k = 0; k < gameNumber; k++){
			System.out.println("Game No " + (k+1) + ": " + numberOfEvenChosenByPlayer[k] + " Even numbers chose");
		}
		
		//System.out.println(Arrays.toString(numberOfEvenChosenByPlayer));
		System.out.println("");
		
		// PLAYER CHOSE ODD ARRAY
		System.out.println("The total times player chose odd in each game is: ");

		// Traverse the array.
		for(int k = 0; k < gameNumber; k++){
			System.out.println("Game No " + (k+1) + ": " + numberOfOddChosenByPlayer[k] + " Odd numbers chose");
		}
		
		//System.out.println(Arrays.toString(numberOfOddChosenByPlayer));
		System.out.println("");
		System.out.println("");
		System.out.println("");
		
		// COMPUTER CHOSE EVEN ARRAY
		System.out.println("The total times computer chose even in each game is: ");

		// Traverse the array.
		for(int k = 0; k < gameNumber; k++){
			System.out.println("Game No " + (k+1) + ": " + numberOfEvenChosenByComputer[k] + " Even numbers chose");
		}
		
		//System.out.println(Arrays.toString(numberOfEvenChosenByComputer));
		System.out.println("");
	
		// COMPUTER CHOSE ODD ARRAY
		System.out.println("The total times computer chose odd in each game is: ");

		// Traverse the array.
		for(int k = 0; k < gameNumber; k++){
			System.out.println("Game No " + (k+1) + ": " + numberOfOddChosenByComputer[k] + " Odd numbers chose");
		}
		
		//System.out.println(Arrays.toString(numberOfOddChosenByComputer));
		System.out.println("");
		System.out.println("");
		System.out.println("");
		
		// PLAYER BONUS POINTS ARRAY
		System.out.println("The total bonus points recieved by the player in each game is: ");
		
		// Traverse the array.
		for(int k = 0; k < gameNumber; k++){
			System.out.println("Game No " + (k+1) + ": " + numberOfBonusPointsReceivedByPlayer[k] + " Bonus points recieved");
		}
		
		//System.out.println(Arrays.toString(numberOfBonusPointsReceivedByPlayer));
		System.out.println("");
		
		// COMPUTER BONUS POINTS ARRAY
		System.out.println("The total bonus points recieved by the computer in each game is: ");
		
		// Traverse the array.
		for(int k = 0; k < gameNumber; k++){
			System.out.println("Game No " + (k+1) + ": " + numberOfBonusPointsReceivedByComputer[k] + " Bonus points recieved");
		}
		
		//System.out.println(Arrays.toString(numberOfBonusPointsReceivedByComputer));
		System.out.println("");
		
	}

	// Getters - to send outputs from processing in the GameManager class to the MoraApp class.
	public int getPlayerScore(){
		return playerScore;
	}
	
	public int getComputerScore(){
		return computerScore;
	}
	
	public int getRoundNumber(){
		return roundNumber;
	}
	
	// Setters - to assign user inputs from the MoraApp class to variables in the GameManager class.
	public void setRoundNumber(int roundNumber){
		this.roundNumber = roundNumber;
	}

	public void setGameNumber(int gameNumber){
		this.gameNumber = gameNumber;
	}

	public void setPlayerScore(int playerScore){
		this.playerScore = playerScore;
	}

	public void setComputerScore(int computerScore){
		this.computerScore = computerScore;
	}

	public void setPlayerTotalRoundWins(int playerTotalRoundWins){
		this.playerTotalRoundWins = playerTotalRoundWins;
	}

	public void setPlayerTotalRoundsLost(int playerTotalRoundsLost){
		this.playerTotalRoundsLost = playerTotalRoundsLost;
	}

	public void setComputerTotalRoundWins(int computerTotalRoundWins){
		this.computerTotalRoundWins = computerTotalRoundWins;
	}

	public void setComputerTotalRoundsLost(int computerTotalRoundsLost){
		this.computerTotalRoundsLost = computerTotalRoundsLost;
	}

	public void setPlayerTotalClosestToTotalFingersPoints(int playerTotalClosestToTotalFingersPoints){
		this.playerTotalClosestToTotalFingersPoints = playerTotalClosestToTotalFingersPoints;
	}

	public void setComputerTotalClosestToTotalFingersPoints(int computerTotalClosestToTotalFingersPoints){
		this.computerTotalClosestToTotalFingersPoints = computerTotalClosestToTotalFingersPoints;
	}

	public void setPlayerChoseEvenTotal(int playerChoseEvenTotal){
		this.playerChoseEvenTotal = playerChoseEvenTotal;
	}

	public void setComputerChoseEvenTotal(int computerChoseEvenTotal){
		this.computerChoseEvenTotal = computerChoseEvenTotal;
	}

	public void setPlayerChoseOddTotal(int playerChoseOddTotal){
		this.playerChoseOddTotal = playerChoseOddTotal;
	}

	public void setComputerChoseOddTotal(int computerChoseOddTotal){
		this.computerChoseOddTotal = computerChoseOddTotal;
	}

}