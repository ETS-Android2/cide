"""

Crea un sobre que contingui 5 cartes creades.
El programa ha de poder llistar les cartes contingudes
dins el sobre mitjannat un mètode anomenat 
VeureCartesSobre() (per exemple veure el nom de la carta,
el tipus i la raresa).

"""

# AUTHOR CARLOS POMARES
# https://carlospomares.es

from Components.Carta import Carta, generateCard
from Components.Sobre import Sobre, generatePackage
from Components.Caja import Caja
import random

def main():
    # Caja de 25 Cartas / 5 Sobres
    caja = Caja("25 Card box")
    caja.print()
    
    # Sobre individual
    sobre = generatePackage("Magico Magic","Sobre mágico",9.99)
    sobre.veureCartes()

if __name__ == '__main__':
    main()