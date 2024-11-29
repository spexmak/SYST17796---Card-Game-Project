/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 * A class that models each Player in the game. Players have an identifier,
 * which should be unique.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 * @author Nickolaos Tassos, Andrei Androsov, Nov 30 2024
 */
public class Player {
    private String name;
    private ArrayList<Card> hand;
    private int score;

    // constructor for player
    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.score = 0;
    }

    // add card to player's hand
    public void hit(Card card) {
        hand.add(card);
        calculateScore();
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    // calculation of player's score
    private void calculateScore() {
        score = 0;
        int aces = 0;

        for (Card card : hand) {
            score += card.getValue();
            if (card.getRank().equals("Ace")) {
                aces++;
            }
        }

        // score gets adjusted if score is over 21 but player has an ace
        while (score > 21 && aces > 0) {
            score -= 10;
            aces--;
        }
    }

    // display the player's hand
    public void showHand() {
        System.out.println(name + "'s Hand:");
        for (Card card : hand) {
            System.out.println("  " + card);
        }
        System.out.println("Score: " + score);
    }

    // getters
    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
