public class Logic{

    /**
     * Call this method to get
     * the winner of a trick
     * @param cards Array of cards played (0 == first card played)
     * @param trump Card.Suit of trump
     * @return index of winning card
     */
    public static int trickTaker(Card[] cards, Card.Suit trump) {
        int winner = 0;                                         // When played, first card would always take trick
        Card.Suit lead = cards[0].getSuit();                    // Get lead suit
        for (int x = 1; x < 4; x++){
            if (greater(cards[winner], cards[x], trump, lead)){ // If new card would be current winner
                winner = x;                                     // New card is now winning
            }
        }
        return winner;
    }

    /**
     * Helper method to determine which 
     * of two cards would win in a 1v1
     * @param one   First card
     * @param two   Second card
     * @param trump Suit of trump
     * @param lead  Suit of lead
     * @return      true means second card won
     */
    public static boolean greater(Card one, Card two, Card.Suit trump, Card.Suit lead){
        boolean winner = false;                 // false = one wins
        boolean offSuitTrumpOne = false;        // If card one is off suit trump (jack)
        boolean offSuitTrumpTwo = false;        // If card two is off suit trump (jack)
        int tempOne = 0;                        // for determining winner
        int tempTwo = 0;                        // for determining winner

        if (trump == Card.Suit.CLUB & one.getSuit() == Card.Suit.SPADE & one.getType() == Card.Type.JACK){
            offSuitTrumpOne = true;             // If trump is clubs and card one is jack of spades, is off suit trump
        }
        if (trump == Card.Suit.SPADE & one.getSuit() == Card.Suit.CLUB & one.getType() == Card.Type.JACK){
            offSuitTrumpOne = true;             // If trump is spades and card one is jack of clubs, is off suit trump
        }
        if (trump == Card.Suit.HEART & one.getSuit() == Card.Suit.DIAMOND & one.getType() == Card.Type.JACK){
            offSuitTrumpOne = true;             // If trump is heart and card one is jack of diamonds, is off suit trump
        }
        if (trump == Card.Suit.DIAMOND & one.getSuit() == Card.Suit.HEART & one.getType() == Card.Type.JACK){
            offSuitTrumpOne = true;             // If trump is diamonds and card one is jack of heards, is off suit trump
        }
        
        if (trump == Card.Suit.CLUB & two.getSuit() == Card.Suit.SPADE & two.getType() == Card.Type.JACK){
            offSuitTrumpTwo = true;             // If trump is clubs and card two is jack of spades, is off suit trump
        }
        if (trump == Card.Suit.SPADE & two.getSuit() == Card.Suit.CLUB & two.getType() == Card.Type.JACK){
            offSuitTrumpTwo = true;             // If trump is spades and card two is jack of clubs, is off suit trump
        }
        if (trump == Card.Suit.HEART & two.getSuit() == Card.Suit.DIAMOND & two.getType() == Card.Type.JACK){
            offSuitTrumpTwo = true;             // If trump is heart and card two is jack of diamonds, is off suit trump
        }
        if (trump == Card.Suit.DIAMOND & two.getSuit() == Card.Suit.HEART & two.getType() == Card.Type.JACK){
            offSuitTrumpTwo = true;             // If trump is diamonds and card two is jack of heards, is off suit trump
        }

        if (one.getSuit() == trump | two.getSuit() == trump | offSuitTrumpOne | offSuitTrumpTwo){
            // If atleast one of the cards is trump
            if ((one.getSuit() == trump | offSuitTrumpOne)& (two.getSuit() != trump & !offSuitTrumpTwo)){
                winner = false;                 // If card one is trump, but not card two
            } else if ((one.getSuit() != trump  & !offSuitTrumpOne) & (two.getSuit() == trump | offSuitTrumpTwo)){
                winner = true;                  // If card two is trump, but not card one
            } else {
                if       (one.getType() ==  Card.Type.NINE){
                    tempOne = 0;                // If card one is nine, is lowest on winning tree
                }else if (one.getType() == Card.Type.TEN){
                    tempOne = 1;                // If card one is ten, is middle on winning tree
                }else if (one.getType() == Card.Type.QUEEN){
                    tempOne = 2;                // If card one is queen, is middle on winning tree
                }else if (one.getType() == Card.Type.KING){
                    tempOne = 3;                // If card one is king, is middle on winning tree
                }else if (one.getType() == Card.Type.ACE){
                    tempOne = 4;                // If card one is ace, is middle on winning tree
                }else if (one.getType() == Card.Type.JACK & one.getSuit() == trump){
                    tempOne = 6;                // If card one is on suit jack, is hightest on winning tree
                }else {
                    tempOne = 5;                // Else, is off suit jack, and is second hightest on winning tree
                }

                if       (two.getType() ==  Card.Type.NINE){
                    tempTwo = 0;                // If card two is nine, is lowest on winning tree
                }else if (two.getType() == Card.Type.TEN){
                    tempTwo = 1;                // If card two is ten, is middle on winning tree
                }else if (two.getType() == Card.Type.QUEEN){
                    tempTwo = 2;                // If card two is queen, is middle on winning tree
                }else if (two.getType() == Card.Type.KING){
                    tempTwo = 3;                // If card two is king, is middle on winning tree
                }else if (two.getType() == Card.Type.ACE){
                    tempTwo = 4;                // If card two is ace, is middle on winning tree
                }else if (two.getType() == Card.Type.JACK & two.getSuit() == trump){
                    tempTwo = 6;                // If card two is on suit jack, is hightest on winning tree
                }else {
                    tempTwo = 5;                // Else, is off suit jack, and is second hightest on winning tree
                }
                winner = (tempTwo > tempOne)? true:false;   // If card one is greater than card one, wins, else loses
            }
        } else {
            if (one.getSuit() == lead & two.getSuit() != lead){
                winner = false;
            } else if (one.getSuit() != lead & two.getSuit() == lead){
                winner = true;
            } else {
                if       (one.getType() ==  Card.Type.NINE){
                    tempOne = 0;                // If card one is nine, is lowest on winning tree
                }else if (one.getType() == Card.Type.TEN){
                    tempOne = 1;                // If card one is ten, is middle on winning tree
                }else if (one.getType() == Card.Type.JACK){
                    tempOne = 2;                // If card one is jack, is middle on winning tree
                }else if (one.getType() == Card.Type.QUEEN){
                    tempOne = 3;                // If card one is queen, is middle on winning tree
                }else if (one.getType() == Card.Type.KING){
                    tempOne = 4;                // If card one is king, is middle on winning tree
                }else {
                    tempOne = 5;                // Else, card is ace
                }

                if       (two.getType() ==  Card.Type.NINE){
                    tempTwo = 0;                // If card two is nine, is lowest on winning tree
                }else if (two.getType() == Card.Type.TEN){
                    tempTwo = 1;                // If card two is ten, is middle on winning tree
                }else if (two.getType() == Card.Type.JACK){
                    tempTwo = 2;                // If card two is jack, is middle on winning tree
                }else if (two.getType() == Card.Type.QUEEN){
                    tempTwo = 3;                // If card two is queen, is middle on winning tree
                }else if (two.getType() == Card.Type.KING){
                    tempTwo = 4;                // If card two is king, is middle on winning tree
                }else{
                    tempTwo = 5;                // Else, card is ace
                }
                winner = (tempTwo > tempOne)? true:false;   // If card one is greater than card one, wins, else loses
            }
        }
        return winner;
    }
        /**
     * Helper method to
     * get the card played,
     * make sure it is playable, 
     * and mark it as played
     * @return played card
     */
    public static Card getCardPlayed(Hand[] hands, int turn) {
        Card cardPlayed;                                // Card to be played
        boolean playable = false;                       // Was it playable
        var scan = new java.util.Scanner(System.in);    // Scanner object
        int index = 0;                                  // Index of card in hand
        while (!playable){                              // While no card has been played
            index = scan.nextInt();                     // Get index of card to be played
            playable = hands[turn].playCard(index);     // play card, if possible if not, loop back
        }
        scan.close();                                   // Close scanner
        cardPlayed = hands[turn].getCard(index);        // Get card at index of played card
        return cardPlayed;                              // And return it
    }

    /**
     * Creates array for deal
     * @param a ammount to first player for first pass
     * @param b ammount to second player for first pass
     * @param c ammount to third player for first pass
     * @param d ammount to fourth player for first pass
     * @return
     */
    public static int[] dealStyle(int a, int b, int c, int d) {
        int[] style = new int[8];                                                       // Initialize array
        style[0] = a;                                                                   // First player
        style[1] = b;                                                                   // Second player
        style[2] = c;                                                                   // Third player
        style[3] = d;                                                                   // Forth player
        style[4] = 5 - style[0];                                                        // Finish first player
        style[5] = 5 - style[1];                                                        // Finish second player
        style[6] = 5 - style[2];                                                        // Finish third player
        style[7] = 5 - style[3];                                                        // Finish fourth player
        return style;                                                                   // Return array
    }

    /**
     * Call this to get the
     * number of points earned
     * @param tricksTaken   The int array of tricksTaken
     * @param calledIt      Team who called trump
     * @param loan          Who called loaner
     * @return              Number of points received
     */
    public static int pointsEarned(int[] tricksTaken, int calledIt, int loan) {
        int pointsToAdd = 0;                                                                        // Count number of points to get
        if ((tricksTaken[0] + tricksTaken[2]) > 2){                                                 // If team 0 got most tricks
                pointsToAdd = (((tricksTaken[0] + tricksTaken[2]) == 5 | (calledIt == 1)))? 2:1;    // If they didn't call it or got all tricks, they get 2 points, else 1 point
        }
        if ((tricksTaken[1] + tricksTaken[3]) > 2){                                                 // If team 1 got most tricks
                pointsToAdd = (((tricksTaken[1] + tricksTaken[3]) == 5 | (calledIt == 0)))? 2:1;    // If they didn't call it or got all tricks, they get 2 points, else 1 point
        }
        for (int x = 0; x < 4; x++){                                                                // For all tricksTaken
            if ((tricksTaken[x] == 5) & (loan == x)){                                               // If all tricks were taken and called alone
                pointsToAdd = 4;                                                                    // You get four points
            }
        }
        return pointsToAdd;                                                                         // Return pointsToAdd
    }

    /**
     * Logic for choosing trump
     * during kitty round
     * @return true = chosen
     */
    public static boolean chooseTrumpKitty() {
        var scan = new java.util.Scanner(System.in);            // Make scanner object
        int choice = scan.nextInt();                            // Get selection
        scan.close();                                           // Close scanner object
        if (choice == 1){                                       // If choice was 1
            return true;                                        // Return true
        } else {                                                // Otherwise
            return false;                                       // Return false
        }
    }

    /**
     * Logic to get trump
     * during ffa round
     * @return Card.Suit chosen
     */
    public static Card.Suit chooseTrump() {
        Card.Suit choosen = Card.Suit.CLUB;             // Local variable
        var scan = new java.util.Scanner(System.in);    // Scanner object
        int choice = scan.nextInt();                    // Get choice
        scan.close();                                   // Close scanner object
        switch (choice){                                // Switch on choice
            case 0:                                     // 0 == SPADE
                choosen = Card.Suit.SPADE;              // assign chosen suit
                break;                                  // End case
            case 1:                                     // 1 == CLUB
                choosen = Card.Suit.CLUB;               // assign chosen suit
                break;                                  // End case
            case 2:                                     // 2 == HEART
                choosen = Card.Suit.HEART;              // assign chosen suit
                break;                                  // End case
            case 3:                                     // 3 == DIAMOND
                choosen = Card.Suit.DIAMOND;            // assign chosen suit
                break;                                  // End case
        }                                               // Finally
        return choosen;                                 // Return chosen suit
    }
}