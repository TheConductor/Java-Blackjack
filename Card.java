
/**
 * Data type used to simulate a standard playing card
 * 
 * @author Alex Aziz
 */
public class Card {
	private char faceValue; // Used to determine if card is 2 through A. Needs
							// to be a char to deal with J Q K A. 0 stands for
							// 10
	private String suit; // Can be diamond, heart, club or spade.
	private int value;// Used to determine the point value of a card

	/*
	 * Constructor Parameters: char newCardFaceValue, used to set the card's
	 * face value. String newCardSuit, used to set the card's suit.
	 * 
	 * @author Alex Aziz
	 */
	public Card(char newCardFaceValue, String newCardSuit) {
		faceValue = newCardFaceValue;
		suit = newCardSuit;

		// Determine value of cards.
		// all values are manually to allow for future implementation of special
		// rules.
		if (faceValue == 'A') {
			value = 11; // Can change based on circumstances of the game
		} else if (faceValue == '2') {
			value = 2;
		} else if (faceValue == '3') {
			value = 3;
		} else if (faceValue == '4') {
			value = 4;
		} else if (faceValue == '5') {
			value = 5;
		} else if (faceValue == '6') {
			value = 6;
		} else if (faceValue == '7') {
			value = 7;
		} else if (faceValue == '8') {
			value = 8;
		} else if (faceValue == '9') {
			value = 9;
		} else if (faceValue == '0') {
			value = 10;
		} else if (faceValue == 'J') {
			value = 10;
		} else if (faceValue == 'Q') {
			value = 10;
		} else if (faceValue == 'K') {
			value = 10;
		}// End if-else to assign card values
	}// End Constructor

	// Start Getters and Setters

	public char getFaceValue() {
		return faceValue;
	}

	public String getSuit() {
		return suit;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	// End Getters and Setters

}// End Card Class
