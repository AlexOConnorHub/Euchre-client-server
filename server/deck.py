import random

class card():
    def __init__(self, suit: str, value: int):
        self.suit = suit[0].upper() + suit[1:].lower()
        self.value = value

    def getPicture(self):
        if self.value == 10:
            return(f'/images/cards/J{self.suit[0]}.png')
        if self.value == 11:
            return(f'/images/cards/Q{self.suit[0]}.png')
        if self.value == 12:
            return(f'/images/cards/K{self.suit[0]}.png')
        if self.value == 13:
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

    def shuffle(self):
        random.shuffle(self.deck)
