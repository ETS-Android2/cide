"""

Crear un caixa de sobre que tindrà els atributs de IdentificadorCaixa 
(data i hora de fabricació), DescripcioCaixa y un array de sobres.
El programa ha de poder llistar totes les cartes
que conté una caixa amb 5 sobre (25 cartes).

"""

# AUTHOR CARLOS POMARES
# https://carlospomares.es

import time

from Components.Sobre import generatePackage

class Caja:
    def __init__(self,description):
        self.identifier = time.gmtime()
        self.description = description
        self.packages = self._generatePackages() 
    def _generatePackages(self):
        packages = []
        for x in range(5):
            packages.append(generatePackage("DAM Avengers: Last Warrior","RPG Adventure",9.99))
        return packages
    def print(self):
        for package in range(len(self.packages)):
            print(f"\nPackage {package + 1}:\n")
            self.packages[package].veureCartes()