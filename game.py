class card():
    def __init__(self, suit: str, value: int):
        self.suit = suit[0].upper() + suit[1:].lower()
        self.value = value

    def getCard(self):
        return self.value

    def getSuit(self):
        return self.suit[0]

    def getPicture(self):
        if self.value == 11:
            return(f'/images/cards/J{self.suit[0]}.png')
        if self.value == 12:
            return(f'/images/cards/Q{self.suit[0]}.png')
        if self.value == 13:
            return(f'/images/cards/K{self.suit[0]}.png')
        if self.value == 14:
            return(f'/images/cards/A{self.suit[0]}.png')
        else:
            return(f'/images/cards/{self.value}{self.suit[0]}.png')

    def __str__(self):
        if self.value == 11:
            return(f'Jack of {self.suit}s')
        if self.value == 12:
            return(f'Queen of {self.suit}s')
        if self.value == 13:
            return(f'King of {self.suit}s')
        if self.value == 14:
            return(f'Ace of {self.suit}s')
        else:
            return(f'{self.value} of {self.suit}s')

    def __repr__(self):
        return(f'card({self.suit!r}, {self.value})')

import random

class deck():
    def __init__(self, lower: int = 1, upper: int = 14):
        self.deck  = [ card("Spade", value)   for value in range(lower, upper + 1)]
        self.deck += [ card("Club", value)    for value in range(lower, upper + 1)]
        self.deck += [ card("Heart", value)   for value in range(lower, upper + 1)]
        self.deck += [ card("Diamond", value) for value in range(lower, upper + 1)]
    
    def __repr__(self):
        return "deck()"

    def __str__(self):
        final = ""
        for string in self.deck:
            final += (str(string)) + "\n"
        return final

    def getCard(self, index: int) -> card:
        return self.deck[index]

    def shuffle(self):
        random.shuffle(self.deck)

class game():
    def __init__(self):
        self.deck = deck(9)
        self.turn = 0
        self.dealer = 0
        self.newDeal()
        self.sitting = 0

    def __str__(self):
        final = ""
        count = 1
        for hand in self.hands:
            final += f'\nHand {count}:\n'
            for card in hand:
                final += str(card) + '\n'
            count += 1
        final += f'\nKitty: {self.kitty}'
        return final

    def __repr__(self):
        return 'game()'

    def newDeal(self):
        self.deck.shuffle()
        self.hands = [
            [self.deck.getCard(i) for i in ( 0,  1,  2, 10, 11 )],
            [self.deck.getCard(i) for i in ( 3,  4, 12, 13, 14 )],
            [self.deck.getCard(i) for i in ( 5,  6,  7, 15, 16 )],
            [self.deck.getCard(i) for i in ( 8,  9, 17, 18, 19 )]
        ]
        self.valid = [
            [True, True, True, True, True],
            [True, True, True, True, True],
            [True, True, True, True, True],
            [True, True, True, True, True]
        ]
        self.kitty = self.deck.getCard(20)
        self.dealer = (self.dealer + 1 ) % 4

    def setTrump(self, trump: str):
        self.trump = trump[0].upper() + trump[1:].lower()

    def getTrump(self) -> str:
        return self.trump[0]

    def getKitty(self) -> card:
        return self.kitty

    def nextTurn(self):
        self.turn = (self.turn + 1) % 4
    
    def sitDown(self):
        self.sitting += 1

    def howManySitting(self) -> int:
        return self.sitting