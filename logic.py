import game

def trick(cards: [game.card], lead: str, trump: str) -> int:
    """
    >>> round  = [game.card("spade", 9)]
    >>> round += [game.card("spade", 10)]
    >>> round += [game.card("spade", 11)]
    >>> round += [game.card("spade", 12)]
    >>> trick(round, "S", "H")
    3
    >>> round[0] = game.card("spade", 13)
    >>> trick(round, "S", "H")
    0
    >>> round[2] = game.card("diamond", 14)
    >>> trick(round, "S", "H")
    0
    >>> round[2] = game.card("heart", 14)
    >>> trick(round, "S", "H")
    2
    >>> round[3] = game.card("diamond", 11)
    >>> trick(round, "S", "H")
    3
    >>> round[0] = game.card("heart", 11)
    >>> round[1] = game.card("club", 11)
    >>> trick(round, "S", "H")
    0
    """
    final = 0
    secondHighSeen = False
    altTrump = "S"
    if trump == "S":
        altTrump = "C"
    elif trump == "C":
        altTrump = "S"
    elif trump == "H":
        altTrump = "D"
    elif trump == "D":
        altTrump = "H"
    for i in range(4):
        if cards[i].getSuit() == lead:
            final = i
        if cards[i].getSuit() == trump and cards[i].getCard() == 11:
            return i
        if cards[i].getSuit() == altTrump and cards[i].getCard() == 11:
            secondHighSeen = i
    if (secondHighSeen != False):
        return secondHighSeen
    for i in range(len(cards)):
        if (cards[i].getSuit() == trump):
            if not (cards[final].getSuit() == trump):
                final = i
        elif (cards[i].getSuit() == lead) and (cards[final].getCard() < cards[i].getCard()):
            final = i
    return final

if __name__ == "__main__":
    import doctest
    doctest.testmod()