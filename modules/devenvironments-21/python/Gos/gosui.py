"""

Crea un gos amb energia 5, fam 0, estat "content" i aplica les següent cas:
Acaricia al gos.
Juga amb el gos 3 cops.
Menjar 3 costelles.
Juga amb el gos una vegada.
El gos es va a dormir.

"""

from gos import Gos

GOS = None

def main():
    
    global GOS
    GOS = Gos()
        
    print(GOS.caress())

    for x in range(3):
        try:
            GOS.play()
        except Exception as error:
            print(error)
    
    for x in range(3):
        try:
            GOS.feed()
        except Exception as error:
            print(error)

    GOS.play()

    GOS.sleep(12)

    print(GOS.print())


if __name__ == '__main__':
    main()