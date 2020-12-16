/**
 * This is a class object
 * that contains a hand,
 * a collection of cards
 */
public class Hand {
    final private int HAND_SIZE = 5;
    private Card[] cardsInHand = new Card[HAND_SIZE];   // Contains a hand of five cards

   /**
    * Initialize the hand with array
    * @param cards Array of five Card objects
    */
    Hand(Card[] cards){
        cardsInHand[0] = new Card(cards[0].getSuit(), cards[0].getType());  // Initialize hand
        cardsInHand[1] = new Card(cards[1].getSuit(), cards[1].getType());  // Initialize hand
        cardsInHand[2] = new Card(cards[2].getSuit(), cards[2].getType());  // Initialize hand
        cardsInHand[3] = new Card(cards[3].getSuit(), cards[3].getType());  // Initialize hand
        cardsInHand[4] = new Card(cards[4].getSuit(), cards[4].getType());  // Initialize hand
    }
    
    /**
    * Initialize the hand with cards
    * @param cards Array of five Card objects
    */
    Hand(Card cardOne, Card cardTwo, Card cardThree, Card cardFour, Card cardFive){
        cardsInHand[0] = new Card(cardOne.getSuit(),cardOne.getType());     // Initialize hand
        cardsInHand[1] = new Card(cardTwo.getSuit(), cardTwo.getType());    // Initialize hand
        cardsInHand[2] = new Card(cardThree.getSuit(), cardThree.getType());// Initialize hand
        cardsInHand[3] = new Card(cardFour.getSuit(), cardFour.getType());  // Initialize hand
        cardsInHand[4] = new Card(cardFive.getSuit(), cardFive.getType());  // Initialize hand
    }

    /**
     * Call to get an array
     * containing the entire hand
     * @return entire hand
     */
    public Card[] getHand(){
        return cardsInHand;                             // Return array of cards
    }
    
    /**
     * Return specific card at
     * index provided
     * @param indexOfCard index of card in question
     * @return Card object
     */
    public Card getCard(int indexOfCard){
        return cardsInHand[indexOfCard];                // Return card at index indexOfCard
    }

    /**
     * Call to play a card,
     * passing in the index
     * of the card
     * @param indexOfCard Index of the card to be palyed
     * @return  True == successful card play
     */
    public boolean playCard(int indexOfCard){
      return cardsInHand[indexOfCard].playCard();       // Play card in hand
    }

    /**
     * Call this method
     * to replace a card
     * @param index index of card to be replaced
     * @param card card to put in hand
     */
    public void replaceCard(int index, Card card){
        cardsInHand[index] = card;                     // Replace card at index with card
    }

    @Override
    public String toString() {
        String localString = "";
        for (int x = 0; x < HAND_SIZE; x++){
            localString += ((cardsInHand[x].getPlayed())?"****":"    ") + cardsInHand[x] + "\n";
        }
        return localString;
    }
}