"""

En un col·legi estan organitzant un viatge d'estudis, i requereix 
determinar quant s’ha de cobrar a cada alumne i quant s'ha de pagar a l'agència de viatges pel servei. La manera de cobrar és la següent: 
Si són 100 alumnes o més, l'import per cada alumne és de 65 €; 
Si són de 50 a 99 alumnes, i és de 70 €; 
Si són de 30 a 49, de 95 €;
i si són menys de 30 és de 115€.
A part, l'import del lloguer de l'autobús és de 4.000€, sense importar el nombre de alumnes.
Realitza un algoritme que permet determinar el pagament a l'agència de lloguer d'autobusos i el que ha de pagar cada alumne pel viatge.
Recorda en iniciar les variables que necessitis i sol·licitar la informació requerida al usuari.


"""

from os import system, name
import csv

# AUTHOR CARLOS POMARES
# https://www.github.com/pomaretta

class Curso:
    def __init__(self,alumnos):
        # Establece los alumnos de manera inicial.
        self.alumnos = alumnos
    def calcularTasa(self):
        # Calcula la tasa teniendo en cuenta la cantidad de alumnos y sus precios
        tasa = (((115,95)[self.alumnos >= 30],70)[self.alumnos >= 50],65)[self.alumnos >= 100]
        return int(tasa) * int(self.alumnos)
    def generarPresupuesto(self):
        # Retorna la tasa más el coste del autobus fijo
        return 4000 + self.calcularTasa()

bl = "\n"
tl = "\t"

def menu():
    bcolors.clearStyles(self=bcolors)

    exit = False

    alumnos = None
    presupuesto = None
    c = None

    while not exit:
        
        clear()

        print(
            f"""\t{bcolors.HEADER}¡Bienvenido a la agencia de viajes {bcolors.BOLD}{bcolors.OKCYAN}Somalia{bcolors.ENDC}{bcolors.HEADER}!{bcolors.ENDC}
            \n\tPuedes realizar las siguientes gestiones:
            \n\t[{bcolors.OKGREEN}1{bcolors.ENDC}] {bcolors.BOLD}Establecer número de alumnos{("",f" --> {bcolors.HEADER}{alumnos}")[alumnos != None]}{bcolors.ENDC}
            \n\t[{bcolors.OKGREEN}2{bcolors.ENDC}] {bcolors.BOLD}Generar presupuesto{bcolors.ENDC}
            \n\t[{bcolors.OKGREEN}3{bcolors.ENDC}] {bcolors.BOLD}Guardar presupuesto{bcolors.ENDC}
            \n\t[{bcolors.FAIL}4{bcolors.ENDC}] {bcolors.BOLD}Salir{bcolors.ENDC}
            {("",f"{bl}{tl}{bcolors.BOLD}TOTAL PRESUPUESTO: {bcolors.OKGREEN}{presupuesto}€{bcolors.ENDC}")[presupuesto != None]}
            """
        )

        try:
            
            order = input(f"\t{bcolors.BOLD}{bcolors.HEADER}>{bcolors.ENDC} ")

            if "1" in order:
                clear()
                alumnos = int(input("Introduce los alumnos: "))
                if alumnos <= 0:
                    alumnos = None
                    raise Exception("Alumnos no válidos...")
            elif "2" in order:
                if alumnos != None:
                    c = Curso(alumnos=alumnos)
                    presupuesto = int(c.generarPresupuesto())
                else:
                    raise Exception("No hay alumnos")
            elif "3" in order:
                if c != None:
                    saveBudget(c)
                else:
                    raise Exception("No hay curso")
            elif "4" in order:
                exit = True

        except Exception as error:
            print(error)

def saveBudget(curso):

    with open("budget.csv",'w') as file:
        writer = csv.writer(file)
        writer.writerow([curso.alumnos,curso.generarPresupuesto()])
        file.close()

def clear():
    if name == 'nt':
        _ = system('cls')
    else:
        _ = system('clear')

class bcolors:
    HEADER = '\033[95m'
    OKBLUE = '\033[94m'
    OKCYAN = '\033[96m'
    OKGREEN = '\033[92m'
    WARNING = '\033[93m'
    FAIL = '\033[91m'
    ENDC = '\033[0m'
    BOLD = '\033[1m'
    UNDERLINE = '\033[4m'

    def clearStyles(self):
        print(f"{self.ENDC}")

if __name__ == "__main__":
    menu()