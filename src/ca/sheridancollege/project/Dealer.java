package ca.sheridancollege.project;

/**
 * @author Nickolaos Tassos, Andrei Androsov, Nov 30 2024
 */

public class Dealer extends Player {

    // constructor
    public Dealer() {
        super("Dealer");
    }

    // dealer's automatic turn
    public void playTurn(GroupOfCards deck) {
        while (getScore() < 17) {
            hit(deck.drawCard());
        }
    }
}
