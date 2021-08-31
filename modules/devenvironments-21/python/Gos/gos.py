"""

Crea la classe gos amb 3 atributs:
Energia: capacitat i força per actuar físicament. Valors entre 0 i 5; 0 té poca energia i 5 té molta energia.
Fam: Necessitat o ganes de menjar. Valors entre 0 i 3; 0 no té fam i 3 té molta fam.
Estat: com es sent (famolenc, content).

Mètodes a crear:
Menjar (quantitat): cada quantitat que menja disminueix la fam en una unitat. Menja si l'estat és “famolenc”.
Acariciar: imprimeix per pantalla l'estat actual i canvia l'estat a “content”.
Jugar: si l'energia és menor o igual a 2, o la fam és major o igual a 2, el gos no voldrà jugar. En cas contrari el gos juga si l'estat és “content". En conseqüència, és decrementa l'energia actual en 1 unitat i la fam s'incrementa en 1 unitats. Després de jugar l’estat del gos passa a “famolenc”.
Dormir(hores): incrementa l'energia segons les hores especificades. El gos dormirà si la energia és menor a 2. Un cop acabat de dormir, l'estat passa a "famolenc".

"""

# AUTHOR CARLOS POMARES
# https://www.github.com/pomaretta

class Gos:

    def __init__(self,energy = 5,hungry = 0,state = True):
        super().__init__()
        # ENERGY: 0-5 [6]
        # 0 - EMPTY
        # 5 - FULL
        self.energy = int(energy)
        # HUNGRY: 0-3 [4]
        # 0 - NOT HUNGRY
        # 3 - HUNGRY
        self.hungry = int(hungry)
        # STATE: HUNGRY-HAPPY [False-True]
        # False - HUNGRY
        # True - HAPPY
        self.state = state

    def feed(self):
        if self.state:
            raise Exception("No te fam")
        self.hungry -= 1
        self._checkFeed()

    def play(self):
        if self.energy <= 2 or self.hungry >= 2:
            raise Exception("No vol jugar")
        self.energy -= 1
        self.hungry += 1
        self._checkEnergy
        self._checkFeed()

    def caress(self):
        self.state = True
        return self.print()

    def sleep(self,hours):
        if self.energy >= 5:
            raise Exception("No te son")
        self.energy += (((1,2)[hours >= 3],3)[hours >= 6],(abs(self.energy - 5)))[hours > 12]
        self._checkEnergy()

    def _checkFeed(self):
        if self.hungry > 0:
            if self.hungry > 3:
                self.hungry = 3
            self.state = False
        else:
            if self.hungry < 0:
                self.hungry = 0
            self.state = True
        return self.hungry > 0

    def _checkEnergy(self):
        if self.energy > 5:
            self.energy = 5
        elif self.energy < 0:
            self.energy = 0
        return self.energy > 0

    def print(self):
        return f"Dog - ENERGY: {self.energy} FEED: {self.hungry} STATE: {('HUNGRY','HAPPY')[self.state]}"