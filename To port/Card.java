/**
 * This is a class object that
 * is a card in a set of cards
 */
public class Card {

    /**
     * The is an enumerated type
     * that contains the suits 
     * in a deck of cards.
     */
    public enum Suit{
        SPADE, CLUB, HEART, DIAMOND
    }

    /**
     * The is an enumerated type
     * that contains the types of 
     * cards in a deck of cards.
     */
    public enum Type{
        ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
    }

    private Suit cardSuit;                  // Holds the suit of the card
    private Type cardType;                  // Holds the type of card
    private boolean played;                 // Contains whether or not card is played
    
    /**
     * Initializes a card object
     * @param cardSuit Contains the suit
     * @param cardType Contains the type
     */
    Card(Suit cardSuit, Type cardType){
        this.cardSuit = cardSuit;           // Assign the card suit
        this.cardType = cardType;           // Assign the card type
        this.played   = false;              // Card has not been played
    }

    /**
     * Call to get the
     * suit of the card
     * @return the suit
     */
    public Suit getSuit(){
        return cardSuit;                    // Return the card suit
    }

    /**
     * Call to get the 
     * type of card
     * @return the type
     */
    public Type getType(){
        return cardType;                    // Return the card type
    }

    /**
     * Call to determine
     * if the card is played
     * @return true == played
     */
    public boolean getPlayed(){
        return played;
    }
    
    /**
     * Call to play card
     * @return true == successful playing
     */
    public boolean playCard(){
        if (played == false){
            played = true;
            return true;
        } else {
            return false;
        }
    }

    /**
     *  Call this method to convert 
     *  int to Suit enumerated type
     *  0 = Suit.SPADE
     *  1 = Suit.CLUB
     *  2 = Suit.HEART
     *  All Others = Suit.DIAMOND
     *  @param value Int value
     *  @return Suit enum type
     */
    public static Suit intToSuit(int value) {
      switch (value){
        case 0:
            return Suit.SPADE;
        case 1:
            return Suit.CLUB;
        case 2:
            return Suit.HEART;
        default:
            return Suit.DIAMOND;
      }  
    } 

    /**
     *  Call this method to convert 
     *  int to Type enumerated type
     *  0 = Type.ONE
     *  1 = Type.TWO
     *  2 = Type.THREE
     *  3 = Type.FOUR
     *  4 = Type.FIVE
     *  5 = Type.SIX
     *  6 = Type.SEVEN
     *  7 = Type.EIGHT
     *  8 = Type.NINE
     *  9 = Type.TEN
     *  10 = Type.JACK
     *  11 = Type.QUEEN
     *  12 = Type.KING
     *  All Others = Type.ACE
     *  @param value Int value
     *  @return Type enum type
     */
    public static Type intToType(int value) {
        switch (value){
            case 0:
                return Type.ONE;
            case 1:
                return Type.TWO;
            case 2:
                return Type.THREE;
            case 3:
                return Type.FOUR;
            case 4:
                return Type.FIVE;
            case 5:
                return Type.SIX;
            case 6:
                return Type.SEVEN;
            case 7:
                return Type.EIGHT;
            case 8:
                return Type.NINE;
            case 9:
                return Type.TEN;
            case 10:
                return Type.JACK;
            case 11:
                return Type.QUEEN;
            case 12:
                return Type.KING;
            default:
                return Type.ACE;
          }  
    }
    
    @Override
    public String toString() {
        String localType;
        String localSuit;

        if (cardSuit == Suit.SPADE){
            localSuit = "spades";
        } else if (cardSuit == Suit.CLUB){
            localSuit = "clubs";
        } else if (cardSuit == Suit.HEART){
            localSuit = "hearts";
        } else {
            localSuit = "diamonds";
        }

        if (cardType == Type.ONE){
            localType = "one";
        } else if (cardType == Type.TWO){
            localType = "two";
        } else if (cardType == Type.THREE){
            localType = "three";
        } else if (cardType == Type.FOUR){
            localType = "four";
        } else if (cardType == Type.FIVE){
            localType = "five";
        } else if (cardType == Type.SIX){
            localType = "six";
        } else if (cardType == Type.SEVEN){
            localType = "seven";
        } else if (cardType == Type.EIGHT){
            localType = "eight";
        } else if (cardType == Type.NINE){
            localType = "nine";
        } else if (cardType == Type.TEN){
            localType = "ten";
        } else if (cardType == Type.JACK){
            localType = "jack";
        } else if (cardType == Type.QUEEN){
            localType = "queen";
        } else if (cardType == Type.KING){
            localType = "king";
        } else{
            localType = "ace";
        } 
        return localType + " of " + localSuit;
    }
}