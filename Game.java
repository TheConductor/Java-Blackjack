import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

/**
 * Main class used to play the game.
 * 
 * @author Alex Aziz
 */
public class Game {

	private Player dealer;
	private Player user;

	/*
	 * Master list of cards. Used to simulate a standard deck of 52 cards.
	 */
	private Card[] deck = new Card[52];

	/*
	 * Used to simulate the deck in the actual game.
	 */
	private Stack<Card> gamedeck = new Stack<Card>();

	/*
	 * Used to make sure the same card isn't dealt twice. Is 22 in size because
	 * that would be both the dealer and user having the largest possible hands.
	 */
	ArrayList<Card> cardsInUse = new ArrayList<Card>();

	/**
	 * Main method, calls all methods to start the game
	 * 
	 * @author Alex Aziz
	 */
	public static void main(String[] args) {
		Game newGame = new Game();
		newGame.createDeck();
		newGame.createDealer();
		newGame.createUser();
		newGame.playGame();
	}// End main

	/**
	 * Used to create all the cards and put them in the deck array
	 * 
	 * @author Alex Aziz
	 */
	private void createDeck() {
		String[] suits = { "diamonds", "hearts", "clubs", "spades" };// Used to
																		// make
																		// the 4
																		// suits
		int index = 0; // used to keep track of the place the new card should be
						// put in the deck array
		// Create Deck
		for (int i = 0; i < 4; i++) {
			// Used to make the cards of each suit
			for (int j = 0; j < 13; j++) {
				if (j < 10) {
					char faceValue = (char) j;
					if (j == 1) {
						faceValue = 'A';
					} else {
						int convertToASCII = j + 48;
						faceValue = (char) convertToASCII;
					}// End if-else to make A through 10 cards
					Card newCard = new Card(faceValue, suits[i]);
					deck[index] = newCard;
					index++;
				} else {
					char faceValue = 'J';
					if (j == 10) {
						faceValue = 'J';
					} else if (j == 11) {
						faceValue = 'Q';
					} else if (j == 12) {
						faceValue = 'K';
					}// End if-else to assign symbols to cards
					Card newCard = new Card(faceValue, suits[i]);
					deck[index] = newCard;
					index++;
				}// End if-else to make Deck
			}// End for
		}// End for

		/*
		 * // Check the results of the shuffling for (int i = 0; i <
		 * deck.length; i++) { System.out.println(i); printCard(deck[i]); }//
		 * End for to print deck
		 */

		this.shuffle();
	}// End createDeck

	/**
	 * Called every six rounds to randomize the deck
	 * 
	 * @author Alex Aziz
	 */
	private void shuffle() {
		// Shuffle the deck numberOfShuffles times.
		// random distribution of cards.
		for (int numberOfShuffles = 0; numberOfShuffles < 20; numberOfShuffles++) {
			Card temp;// Used to switch places of cards in the deck
			Random rand = new Random();
			for (int i = 0; i < deck.length; i++) {
				// picks two random cards and switch their place in the deck.
				int card1Index = rand.nextInt(deck.length);
				int card2Index = rand.nextInt(deck.length);
				temp = deck[card1Index];
				deck[card1Index] = deck[card2Index];
				deck[card2Index] = temp;
			}// End for to shuffle deck
		}// End for to govern number of times the deck is shuffled

		/*
		 * // Check the results of the shuffling for (int i = 0; i <
		 * deck.length; i++) { System.out.println(i); printCard(deck[i]); }//
		 * End for to print deck
		 */

	}// End shuffle

	/**
	 * Creates the dealer for the game.
	 * 
	 * @author Alex Aziz
	 */
	private void createDealer() {
		Player newDealer = new Player("Dealer", 0);
		dealer = newDealer;
	}// End createDealer

	/**
	 * Creates the user's Player for the game.
	 * 
	 * @author Alex Aziz
	 */
	private void createUser() {
		System.out.println("What is your name?");
		Scanner scan = new Scanner(System.in);
		String name = scan.nextLine();
		Player newPlayer = new Player(name, 1);
		user = newPlayer;
	}// End createDealer

	/**
	 * Game is played in this method. Governs all rules of the game.
	 * 
	 * @author Alex Aziz
	 */
	private void playGame() {
		// Used as a boolean to determine if player would like to keep playing.
		int playAgain = 1;
		for (int i = 0; i < deck.length; i++) {
			gamedeck.push(deck[i]);
		}// End for

		int roundNumber = 0;

		// Game played in this loop
		while (playAgain == 1) {
			if(roundNumber>1){
				float winPrecentage = ((float)user.getNumberOfWins()) / (( (float)user.getNumberOfWins())+( (float)user.getNumberOFLosses()));
				System.out.println("Your win precentage is "+winPrecentage+"%");
				System.out.println("You've won "+user.getNumberOfWins()+" games and lost "+user.getNumberOFLosses()+" games.");
			}// End if 
			// Shuffle every 6 turns
			if (roundNumber % 6 == 0) {
				System.out.println("It's been 6 rounds, shuffling the deck.");
				shuffle();
			}// End if to shuffle

			// Deal Cards

			user.getHand().add(checkCardDelt());
			cardsInUse.add(user.getHand().get(0));
			System.out.println("You were delt a");
			printCard(user.getHand().get(0));

			dealer.getHand().add(checkCardDelt());
			cardsInUse.add(dealer.getHand().get(0));
			System.out.println("The Dealer was delt a card.");

			user.getHand().add(checkCardDelt());
			cardsInUse.add(user.getHand().get(1));
			System.out.println("You were delt a");
			printCard(user.getHand().get(1));

			dealer.getHand().add(checkCardDelt());
			cardsInUse.add(dealer.getHand().get(1));
			System.out.println("The dealer is showing a");
			printCard(dealer.getHand().get(1));

			System.out.println("Your total score is " + user.getScore());
			
			Scanner scan = new Scanner(System.in);
			// Game rules start here
			if (user.getScore() == 21) {
				// Check for blackjack
				System.out.println("Blackjack you win!");
				user.setNumberOfWins(user.getNumberOfWins() + 1);
				System.out.println("Would you like to play again? Y/N");
				String promptToPlayAgain = scan.nextLine();
				if (promptToPlayAgain.toLowerCase().equals("y")
						|| promptToPlayAgain.toLowerCase().equals("yes")) {
					roundNumber++;
					// Clear Hands
					for (int i = 0; i < user.getHand().size(); i++) {
						user.getHand().remove(i);
					}// End for
					for (int i = 0; i < dealer.getHand().size(); i++) {
						dealer.getHand().remove(i);
					}// End for
				} else {
					System.exit(0);
				}// End if-else
			} else {
				// User's turn.
				int usersTurn = 0;
				String userAction = ""; // What the user wants to do, hit or
				// stand.
				
				while (usersTurn == 0) {
					// Get user input
					int validInput = 0; // Used to check for valid input

					// Validate input
					while (validInput == 0) {
						System.out
								.println("Would you like to hit or stand? (H for hit / S for stand)");
						userAction = scan.nextLine();
						// Check for valid input
						if (userAction.toLowerCase().equals("h")
								|| userAction.toLowerCase().equals("hit")
								|| userAction.toLowerCase().equals("s")
								|| userAction.toLowerCase().equals("stand")) {
							validInput = 1;
						} else {
							validInput = 0;
						}// End if-else
					}// End While

					/*
					 * Perform action based on user input allows users to get
					 * cards until they go over 21 or stand
					 */
					if (userAction.toLowerCase().equals("h")
							|| userAction.toLowerCase().equals("hit")) {
						if (gamedeck.isEmpty() == false) {
							Card dealtCard = checkCardDelt();
							user.getHand().add(dealtCard);
							cardsInUse.add(dealtCard);
							printCard(dealtCard);

							System.out.println("Your current Score is "
									+ user.getScore());

							if (user.getScore() == 21) {
								// Check for blackjack
								System.out.println("Blackjack you win!");
								user.setNumberOfWins(user.getNumberOfWins() + 1);
								System.out
										.println("Would you like to keep playing?");
							} else if (user.getScore() > 21) {
								System.out.println("You went over 21.");
								user.setNumberOFLosses(user.getNumberOFLosses() + 1);
								System.out
										.println("Would you like to play again? Y/N");
								String promptToPlayAgain = scan.nextLine();
								if (promptToPlayAgain.toLowerCase().equals("y")
										|| promptToPlayAgain.toLowerCase()
												.equals("yes")) {
									roundNumber++;
									usersTurn = 1;
									// Clear Hands
									for (int i = 0; i < user.getHand().size(); i++) {
										user.getHand().remove(i);
									}// End for
									for (int i = 0; i < dealer.getHand().size(); i++) {
										dealer.getHand().remove(i);
									}// End for
								} else {
									System.exit(0);
								}// End if-else
							}// End if-else
						} else {
							shuffle();
							for (int i = 0; i < deck.length; i++) {
								gamedeck.push(deck[i]);
							}// End for
							Card dealtCard = checkCardDelt();
							user.getHand().add(dealtCard);
							cardsInUse.add(dealtCard);
							printCard(dealtCard);
							System.out.println("Your current Score is "
									+ user.getScore());

							if (user.getScore() == 21) {
								// Check for blackjack
								System.out.println("Blackjack you win!");
								user.setNumberOfWins(user.getNumberOfWins() + 1);
								System.out
										.println("Would you like to keep playing?");
							} else if (user.getScore() > 21) {
								System.out.println("You went over 21.");
								user.setNumberOFLosses(user.getNumberOFLosses() + 1);
								System.out
										.println("Would you like to play again? Y/N");
								String promptToPlayAgain = scan.nextLine();
								if (promptToPlayAgain.toLowerCase().equals("y")
										|| promptToPlayAgain.toLowerCase()
												.equals("yes")) {
									roundNumber++;
									usersTurn = 1;
									// Clear Hands
									for (int i = 0; i < user.getHand().size(); i++) {
										user.getHand().remove(i);
									}// End for
									for (int i = 0; i < dealer.getHand().size(); i++) {
										dealer.getHand().remove(i);
									}// End for
								} else {
									System.exit(0);
								}// End if-else
							}// End if-else
						}// End if-else
					} else {
						usersTurn = 1;
					}// End if-else to process user input;
				}// End while - Users turn

				// Dealers Turn
				if (userAction.toLowerCase().equals("s")
						|| userAction.toLowerCase().equals("stand")) {
					System.out.println("The Dealer was shows a");
					printCard(dealer.getHand().get(0));
					while (dealer.getScore() < 17) {
						System.out.println("The Dealer hits and shows a");
						Card dealtCard = checkCardDelt();
						dealer.getHand().add(dealtCard);
						cardsInUse.add(dealtCard);
						printCard(dealtCard);
						System.out.println("The dealers current Score is "
								+ dealer.getScore());
						if (dealer.getScore() == 17) {
							boolean hasAce = false;
							for (int i = 0; i < dealer.getHand().size(); i++) {
								if (dealer.getHand().get(i).getFaceValue() == 'A') {
									hasAce = true;
								}// End if
							}// End for
							if (hasAce == true) {
								System.out
										.println("The Dealer hits and shows a");
								if (gamedeck.isEmpty() == true) {
									shuffle();
									for (int i = 0; i < deck.length; i++) {
										gamedeck.push(deck[i]);
									}// End for
								}// End if
								dealtCard = checkCardDelt();
								dealer.getHand().add(dealtCard);
								cardsInUse.add(dealtCard);
								printCard(dealtCard);
								System.out
										.println("The dealers current Score is "
												+ dealer.getScore());
							}// End if to determine soft 17
						}// End if
					}// End while

					// Check Scores
					if (dealer.getScore() > 21) {
						// Check if dealer went over
						System.out.println("The dealer went over 21. You win!");
						user.setNumberOfWins(user.getNumberOfWins() + 1);
					} else {
						if (user.getScore() > dealer.getScore()) {
							System.out.println("usersScore " + user.getScore());
							System.out.println("dealersScore "
									+ dealer.getScore());
							System.out.println("You win!");
							user.setNumberOfWins(user.getNumberOfWins() + 1);
						} else {
							System.out.println("usersScore " + user.getScore());
							System.out.println("dealersScore "
									+ dealer.getScore());
							System.out.println("The dealer wins.");
							user.setNumberOFLosses(user.getNumberOFLosses() + 1);
						}// End if-else
					}// End if-else
					System.out.println("Would you like to play again? Y/N");
					String promptToPlayAgain = scan.nextLine();
					if (promptToPlayAgain.toLowerCase().equals("y")
							|| promptToPlayAgain.toLowerCase().equals("yes")) {
						roundNumber++;
						usersTurn = 1;
						// Clear Hands
						for (int i = 0; i < user.getHand().size(); i++) {
							user.getHand().remove(i);
						}// End for
						for (int i = 0; i < dealer.getHand().size(); i++) {
							dealer.getHand().remove(i);
						}// End for
					} else {
						System.exit(0);
					}// End if-else
				}// End if-else
			}// End while for Dealers turn
			for (int i = 0; i < user.getHand().size(); i++) {
				user.getHand().remove(i);
			}// End for
			user.setScore(0);
			for (int i = 0; i < dealer.getHand().size(); i++) {
				dealer.getHand().remove(i);
			}// End for
			dealer.setScore(0);
			for (int i = 0; i < cardsInUse.size(); i++) {
				cardsInUse.remove(i);
			}// End for
		}// End while
	}// End playGame

	/**
	 * Checks that a card being dealt hasn't already been used
	 * 
	 * @return newCard the card that will be dealt
	 */
	private Card checkCardDelt() {
		if (gamedeck.isEmpty() == true) {
			shuffle();
			for (int i = 0; i < deck.length; i++) {
				gamedeck.push(deck[i]);
			}// End for
		}// End if
		Card newCard = gamedeck.pop();
		int foundValidCard = 0;
		if (cardsInUse.size() != 0) {
			while (foundValidCard == 0) {
				for (int i = 0; i < cardsInUse.size(); i++) {
					if (newCard.getFaceValue() == cardsInUse.get(i)
							.getFaceValue()
							&& newCard.getSuit() == cardsInUse.get(i).getSuit()) {
						newCard = checkCardDelt();
					} else {
						foundValidCard = 1;
					}// End if to check card
				}// End for to check card
			}// End while
		}// End if
		return newCard;
	}// End checkCardDelt

	private void printCard(Card dealtCard) {
		System.out.println("|||||||||||");
		System.out.println("|        " + dealtCard.getFaceValue() + "|");
		if (dealtCard.getSuit() == "diamonds") {
			System.out.println("|  /   \\  |");
			System.out.println("| /     \\ |");
			System.out.println("| |  D  | | ");
			System.out.println("| \\     / |");
			System.out.println("|  \\   /  |");
		} else if (dealtCard.getSuit() == "hearts") {
			System.out.println("| /\\   /\\ |");
			System.out.println("|/  \\ /  \\|");
			System.out.println("|\\   H   /|  ");
			System.out.println("| \\     / |");
			System.out.println("|  \\___/  |");
		} else if (dealtCard.getSuit() == "clubs") {
			System.out.println("|   / \\   |");
			System.out.println("|  /   \\  |");
			System.out.println("| (__ __) |");
			System.out.println("|    |    |");
			System.out.println("|    |    |");
		} else if (dealtCard.getSuit() == "spades") {
			System.out.println("|    0    |");
			System.out.println("|  _| |_  |");
			System.out.println("| 0_ C _0 |");
			System.out.println("|   | |   |");
			System.out.println("|    |    |");
		}
		System.out.println("|" + dealtCard.getFaceValue() + "        |");
		System.out.println("|||||||||||");
	}// printCard
}// End Game Class
