public class Player {

	// instance variables
	private int playerType;
	private int playerNumberOfFingers;
	
	// constructor(s)
	public Player (int playerType){
		this.playerType = playerType;
	}
	
	// methods
	public void setPlayerNumberOfFingers(int playerNumberOfFingers){
		this.playerNumberOfFingers = playerNumberOfFingers;
	}
	
	public int getPlayerNumberOfFingers(){
		return playerNumberOfFingers;
	}
	
	public int getPlayerType(){
		return playerType;
	}
	

}