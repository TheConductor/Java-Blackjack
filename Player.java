import java.util.ArrayList;

/**
 * Data type used to keep track of the Player's information
 * 
 * @author Alex Aziz
 */
public class Player {

	private String name;
	private int type; // Used to determine if the player is the dealer or user.
						// 0 is dealer 1 is player.
	private ArrayList<Card> hand = new ArrayList<Card>(); // Used to keep track of the cards the
										// player currently has. The largest
										// hand possible in blackjack without
										// going over 21.
	private int numberOfWins = 0; // Keep track of the number of wins in the
									// current sitting
	private int numberOFLosses = 0; // Keep track of the number of losses in the
									// current sitting
	private int score = 0;
	/*
	 * Constructor 
	 * Parameters: 
	 * String newPlayerName used to set the name value
	 * int newPlayerType used to set the type value
	 * @author Alex Aziz
	 */
	public Player(String newPlayerName, int newPlayerType) {
		name = newPlayerName;
		type = newPlayerType;
	}// End Constructor

	// Start Getters and Setters
	
	public String getName() {
		return name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}

	public int getNumberOfWins() {
		return numberOfWins;
	}

	public void setNumberOfWins(int numberOfWins) {
		this.numberOfWins = numberOfWins;
	}

	public int getNumberOFLosses() {
		return numberOFLosses;
	}

	public void setNumberOFLosses(int numberOFLosses) {
		this.numberOFLosses = numberOFLosses;
	}
	
	public int getScore(){
		score = 0;
		for(int i=0; i<hand.size(); i++){
			score = score + hand.get(i).getValue();
		}// End for
		
		if(score>21){
			for(int i=0; i<hand.size(); i++){
				if(hand.get(i).getFaceValue() == 'A'){
					score = score - 11;
					score = score + 1;
				}// End if
			}// End for
		}// End if
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	// End Getters and Setters

}// End Player Class
