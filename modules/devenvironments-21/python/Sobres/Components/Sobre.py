"""

Sobre:
Identificador → identificador del sobre (utilitzar data i hora per a la creació).
Nom → nom de sobre.
Descripció → descripció del producte.
Preu → preu del sobre.
Cartes[] → array o llista de cartes que conté el sobre.

"""

# AUTHOR CARLOS POMARES
# https://carlospomares.es

import time

from Components.Carta import generateCard

def generatePackage(name,description,price):
    cards = []
    for x in range(5):
        cards.append(generateCard())
    return Sobre(
        name=name
        ,description=description
        ,price=price
        ,cards=cards
    )

class Sobre:
    def __init__(self,name,description,price,cards):
        super().__init__()
        self.identifier = time.gmtime()
        self.name = name
        self.description = description
        self.price = price
        self.cards = cards
    def veureCartes(self):
        for card in range(len(self.cards)):
            print(self.cards[card].print())