import java.util.Random;

public class Computer {

	// instance variables
	private int computerType;
	private int computerNumberOfFingers;
	
	// constructor(s)
	public Computer (int computerType){
		this.computerType = computerType;
	}
	
	
	public void generateComputerNumberOfFingers(){
		Random rand = new Random();
		computerNumberOfFingers = rand.nextInt(10) + 1;
	}
	
	public int getComputerNumberOfFingers(){
		return computerNumberOfFingers;
	}

	public int getComputerType(){
		return computerType;
	}
	
	

}