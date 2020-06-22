public class GamePlay {
    private Deck    cards;
    private Hand[]  hands;
    private int     turn;
    private int     start;
    private int     deal;
    private int[]   points;
    private int[]   tricksTaken;
    private int     calledIt;
    private int     loan;

    GamePlay(){
        cards = new Deck();
        hands = new Hand[4];
        turn = 0;
        start = 0;
        deal = 0;
        points = new int[2];
        tricksTaken = new int[4];
        calledIt = 0;
        loan = -1;
    }

    /**
     * Call this to
     * get the kitty
     * @return kitty Card
     */
    public Card getKitty() {
        return cards.getKitty();                // Returns kitty
    }

    /**
     * Shuffles cards
     */
    public void shuffle() {
        cards.shuffle();                        // Shuffles deck
    }

    /**
     * Plays one round of 
     * Euchre and sets the 
     * first to play next round
     */
    public void playRound() {
        turn = start;                                                   // The person's turn is who starts the round
        Card[] playedThisRound = new Card[4];                           // Make array for cards played
        playedThisRound[turn] = Logic.getCardPlayed(hands, turn);       // Get the card played
        played();                                                       // Incriment turn
        playedThisRound[turn] = Logic.getCardPlayed(hands, turn);       // Get the card played
        played();                                                       // Incriment turn
        playedThisRound[turn] = Logic.getCardPlayed(hands, turn);       // Get the card played
        played();                                                       // Incriment turn
        playedThisRound[turn] = Logic.getCardPlayed(hands, turn);       // Get the card played
        played();                                                       // Incriment turn
        start = Logic.trickTaker(playedThisRound, cards.getTrump());    // Use trickTaker to find who wins round
        tricksTaken[start]++;                                           // The person who starts next round will have taken a trick
    }

    /**
     * Call this function to 
     * update who's turn it is
     */
    public void played() {
        turn++;             // Next person's turn
        if (turn > 3){      // If out of range
            turn -= 4;      // It is player 0's turn
        }
    }
    
    /**
     * Call this method
     * to choose trump
     * @return false = chosen, true = not chosen
     */
    public boolean chooseTrump() {
        boolean trumpChosen = false, ffa = false, done = false;     // A bunch of local variables
        Card.Suit chosen = getKitty().getSuit();                    // The chosen suit is that of the kitty
        turn = deal + 1;                                            // Start with the person after the dealer
        while (!trumpChosen){                                       // While trump not chosen
            if (!ffa){                                              // If not open to choose suit
                trumpChosen = Logic.chooseTrumpKitty();             // Call chooseTrumpKitty;
            } else {                                                // Otherwise
                chosen = Logic.chooseTrump();                       // Call chooseTrump
                if (chosen != getKitty().getSuit()){                // If it is not the suit of the kitty
                    trumpChosen = true;                             // We have chosen trump
                }                                                   // Otherwise
            }
            turn++;                                                 // Next person's turn
            if (turn > 3){                                          // If turn out of bounds
                turn -= 4;                                          // Make in bounds
            }                                                       // Otherwise
            if (turn == deal + 1){                                  // If turn is the person after the dealer's
                if (ffa == false){                                  // If it isn't ffa trump choice
                    ffa = true;                                     // Now it is
                } else {                                            // Othewise
                    done = !trumpChosen;                            // Done gets not trumpChosen
                    trumpChosen = true;                             // trumpChosen is true
                }                                                   // So that we
            }                                                       // Will be exiting
        }                                                           // The while loop
        calledIt = turn % 2;                                        // Mark the team that called trump
        if (done){                                                  // If done is high
            return done;                                            // Return it
        } else {                                                    // Otherwise
            deal++;                                                 // the next round, next person deals
            makeTrump(chosen);                                      // Trump chosen is made global
            if (chosen == getKitty().getSuit()){                    // If the chosen trump is that of the kitty
                dealReplace();                                      // Have dealer pick it up
            }                                                       // If not
            return done;                                            // Return done
        }
    }

    /**
     * Helper method that replaces
     * kitty with that in dealer's hand
     */
    private void dealReplace() {
        var scan = new java.util.Scanner(System.in);                // Make scanner object
        int index = scan.nextInt();                                 // Get card to replace
        scan.close();                                               // Close scanner object
        hands[deal].replaceCard(index, cards.getKitty());           // Replace card
    }

    /**
     * Helper method
     * to change trump
     * @param trumpToBe Card.Suit of trump
     */
    public void makeTrump(Card.Suit trumpToBe) {
        cards.makeTrump(trumpToBe);             // Make new suit trump
    }

    /**
     * Call this method at
     * the end of the round
     * to add points to team
     */
    public void endOfRound() {
        int pointsToAdd = Logic.pointsEarned(tricksTaken, calledIt, loan);          // Points to add
        if ((tricksTaken[0] + tricksTaken[2]) > (tricksTaken[1] + tricksTaken[3])){ // If team 0 got more tricks
            points[0] += pointsToAdd;                                               // They get the points
        } else {                                                                    // Otherwise
            points[1] += pointsToAdd;                                               // Team 1 gets the points
        }
        for (int x = 0; x < 4; x++){
            tricksTaken[x] = 0;                                                     // Zero the tricks taken
        }
        loan = -1;                                                                  // No one is going alone yet
        deal++;                                                                     // Next person deals
    }

    /**
     * Deals out the cards
     */
    public void deal(int[] shuffleStyle) {
        int[][] cardsToGet = new int[4][5];     // Make 2D array for indexing cards to get for players
        int[] cardsReceived = new int [4];      // Array counter for cards received in hands of players
        int player = 0;                         // Player number
        int cardIndex = 0;                      // Deck card index

        for (int x = 0; x < 8; x++){
            for (int y = 0; y < shuffleStyle[x]; y++){
                cardsToGet[player][cardsReceived[player]++] = cardIndex++;      // Load to 2D array player number and card number, and incriment cardIndex and number of cards received
            }
            player++;                           // Incriment player
            if(player > 3){                     // If greater than 3 (player 4 undefined)
                player -= 4;                    // Reset to player 0
            }
        }
        for (int x = 0; x < 4; x++){
            cardsReceived[x] = deal + x;        // Consider that dealer exists
            if (cardsReceived[x] > 3){          // If out of range
                cardsReceived[x] -= 4;          // Subtract 4 to make in range
            }
        }
        hands[cardsReceived[0]] = new Hand(cards.getCard(cardsToGet[0][0]), cards.getCard(cardsToGet[0][1]), cards.getCard(cardsToGet[0][2]), cards.getCard(cardsToGet[0][3]), cards.getCard(cardsToGet[0][4]));   // give player one hand
        hands[cardsReceived[1]] = new Hand(cards.getCard(cardsToGet[1][0]), cards.getCard(cardsToGet[1][1]), cards.getCard(cardsToGet[1][2]), cards.getCard(cardsToGet[1][3]), cards.getCard(cardsToGet[1][4]));   // give player two hand
        hands[cardsReceived[2]] = new Hand(cards.getCard(cardsToGet[2][0]), cards.getCard(cardsToGet[2][1]), cards.getCard(cardsToGet[2][2]), cards.getCard(cardsToGet[2][3]), cards.getCard(cardsToGet[2][4]));   // give player three hand
        hands[cardsReceived[3]] = new Hand(cards.getCard(cardsToGet[3][0]), cards.getCard(cardsToGet[3][1]), cards.getCard(cardsToGet[3][2]), cards.getCard(cardsToGet[3][3]), cards.getCard(cardsToGet[3][4]));   // give player four hand
    }

    @Override
    public String toString() {
        String localString = "";
        localString += "Hand 1:\n" + hands[0] + "------------------\n";
        localString += "Hand 2:\n" + hands[1] + "------------------\n";
        localString += "Hand 3:\n" + hands[2] + "------------------\n";
        localString += "Hand 4:\n" + hands[3] + "------------------\n";
        return localString;
    }
}