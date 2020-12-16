import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This is a class object
 * that contains a deck
 * of cards
 */
public class Deck {
    final private int NUMBER_OF_CARDS = 24;
    private final Card[] Cards = new Card[NUMBER_OF_CARDS];
    private final Random rand = ThreadLocalRandom.current();
    private Card kittyTop;
    private Card.Suit trump;

    /**
     * Loads all cards in deck
     */
    Deck() {
        int tempCounter = 0;                                                            // Counts index of Cards
        for (int x = 0; x < 4; x++) {                                                   // Index for Suit
            for (int y = (14 - (NUMBER_OF_CARDS / 4)); y < 14; y++) {                   // Index for Type
                Cards[tempCounter++] = new Card(Card.intToSuit(x), Card.intToType(y));  // Initialize card, and increment tempCounter
            }
        }
    }

    /**
     * Performs Fisher–Yates 
     * shuffle on deck and
     * assigns new kitty
     */
    public void shuffle() {
        int index;                                                                      // Index of swap
        Card a;                                                                         // Temp spot for swap
        for (int i = Cards.length - 1; i > 0; i--) {                                    // For all cards
            index = rand.nextInt(i + 1);                                                // Index for swap
            a = Cards[index];                                                           // Perform
            Cards[index] = Cards[i];                                                    // The
            Cards[i] = a;                                                               // Swap
        }
        this.kittyTop = this.Cards[20];                                                 // Assign kitty new value
    }

    /**
     * Call this method to get kitty
     * @return kitty card
     */
    public Card getKitty() {
        return kittyTop;                                                                // Return kitty
    }

    /**
     * Call this to change the
     * active trump
     * @param newTrump Card.Suit of the new trump
     */
    public void changeTrump(Card.Suit newTrump) {
        trump = newTrump;                                                               // Changes trump to new card
    }

    /**
     * Call this to
     * get trump
     * @return Card.Suit trump
     */
    public Card.Suit getTrump() {
        return trump;                                                                   // Return trump
    }

    /**
     * Call this method
     * to change trump
     * @param trumpToBe Card.Suit of trump
     */
    public void makeTrump(Card.Suit trumpToBe) {
        trump = trumpToBe;             // Make new suit trump
    }

    /**
     * Return specific card at
     * index provided
     * @param indexOfCard index of card in question
     * @return Card object
     */
    public Card getCard(int indexOfCard){
        return Cards[indexOfCard];                // Return card at index indexOfCard
    }

    @Override
    public String toString() {
        String localString = "";
        for (int x = 0; x < NUMBER_OF_CARDS; x++){
            localString += Cards[x] + "\n";
        }
        return localString;
    }
}