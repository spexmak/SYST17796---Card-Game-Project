/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

import java.util.Scanner;

/**
 * The class that models your game. You should create a more specific child of
 * this class and instantiate the methods
 * given.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 * @author Nickolaos Tassos, Andrei Androsov, Nov 30 2024
 */
public class Game {
    private GroupOfCards deck;
    private Player player;
    private Dealer dealer;

    public Game() {
        deck = new GroupOfCards();
        player = new Player("Player");
        dealer = new Dealer();
    }

    // the game starts here
    public void startGame() {
        deck.shuffle();

        // cards dealt initially
        player.hit(deck.drawCard());
        player.hit(deck.drawCard());
        dealer.hit(deck.drawCard());
        dealer.hit(deck.drawCard());

        // show hands
        player.showHand();
        System.out.println("Dealer's visible card: " + dealer.getHand().get(0));

        if (player.getScore() == 21 || dealer.getScore() == 21) {
            System.out.println("Blackjack! Game ends immediately.");
            determineOutcome();
            return;
        }

    }

    // playing the game
    public void playGame() {
        Scanner scanner = new Scanner(System.in);

        // turn of player
        while (true) {
            System.out.println("\nDo you want to 'hit' or 'stand'? ");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("hit")) {
                player.hit(deck.drawCard());
                player.showHand();

                if (player.getScore() > 21) {
                    System.out.println("You busted! Dealer wins!");
                    return;
                } else if (player.getScore() == 21) {
                    System.out.println("You reached 21!");
                    break;
                }
            } else if (choice.equalsIgnoreCase("stand")) {
                break;
            } else {
                System.out.println("Invalid input. Please type 'hit' or 'stand'.");
            }
        }

        // dealer's turn
        dealer.playTurn(deck);
        System.out.println("\nDealer's turn:");
        dealer.showHand();

        // outcome gets determined
        determineOutcome();
    }

    // winner gets determined
    private void determineOutcome() {
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();

        if (playerScore > 21) {
            System.out.println("Dealer wins!");
        } else if (dealerScore > 21 || playerScore > dealerScore) {
            System.out.println("You win!");
        } else if (playerScore < dealerScore) {
            System.out.println("Dealer wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    // MAIN METHOD HERE
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Single scanner instance for input

        while (true) {
            Game game = new Game(); // Create a new game instance
            game.startGame();
            game.playGame();

            while (true) {
                System.out.println("\nDo you want to play again? (yes/no)");
                String choice = scanner.nextLine().trim().toLowerCase(); // Normalize input

                if (choice.equals("yes")) {
                    break; // Start a new game session
                } else if (choice.equals("no")) {
                    System.out.println("Thanks for playing!");
                    scanner.close(); // Close scanner
                    return; // Exit the program
                } else {
                    System.out.println("Invalid input. Please type 'yes' or 'no'.");
                }
            }
        }
    }
}