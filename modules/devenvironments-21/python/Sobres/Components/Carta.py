"""

Carta:
Nom → nom de la carta.
Tipus → tipus de la carta (lliure imaginació)
Raresa → raresa de la carta (normal, rara, super rara, super super rara, ultra rara, legendaria rara).
Atac → valor d’atac de la carta.
Defensa → valor de la defensa de la carta.
Vida → valor de la vida de la carta.
Passiva → habilitat passiva de la carta (descripció).

"""

# AUTHOR CARLOS POMARES
# https://carlospomares.es

import random

def generateCard():

    names = [
        "Werand Sealance",
        "Mytallath Bladeleaf",
        "Hadriel Leafdancer",
        "Mealar Treesword",
        "Hydraeth Silverarrow",
        "Denrol Featherscribe",
        "Kharnorion Duskdancer",
        "Cerelnar Duskwatcher",
        "Sidaar Nightwhisper",
        "Reras Oceancrest"
    ]

    types = [
        "MAGIC"
        ,"BERSERKER"
        ,"WARRIOR"
        ,"WARLOCK"
        ,"ARCHER"
    ]

    rarities = [
        "LEGENDARY",
        "EPIC",
        "RARE",
        "NORMAL",
        "COMMON"
    ]

    passives = [
        "Kro'zol",
        "Jom'kin",
        "Joug'zonaath",
        "Nor'shis",
        "Elaz",
        "Vuztiz",
        "Somnox",
        "Skrou'gish",
        "Skra'larol",
        "Vas'til"
    ]

    name = names[random.randint(0,len(names) - 1)]
    type = types[random.randint(0,len(types) - 1)]
    rarity = rarities[random.randint(0,len(rarities) - 1)]
    passive = passives[random.randint(0,len(passives) - 1)]

    damage = random.randint(10,100)
    defense = random.randint(20,100)
    life = random.randint(1,10)

    return Carta(
        name=name
        ,cardType=type
        ,rarity=rarity
        ,passive=passive
        ,damage=damage
        ,defense=defense
        ,life=life
    )

class Carta:
    def __init__(self,name,cardType,rarity,damage,defense,life,passive):
        super().__init__()
        self.name = name
        self.type = cardType
        self.rarity = rarity
        self.damage = damage
        self.defense = defense
        self.life = life
        self.passive = passive
    def print(self):
        return "NAME: {} - {} - {} STATS: {} HP - {} DMG - {} DEF PASSIVE: {}".format(self.name,self.rarity,self.type,self.life,self.damage,self.defense,self.passive)