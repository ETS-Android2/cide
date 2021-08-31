"""

Una cooperativa de viticultors fixa un preu inicial 
al quilo de raïm, la qual es classifica en tipus A i B
, a més del tamany que és 1 i 2.
Quan es realitza la venda del producte
, aquesta és d'un sol tipus i grandària
, es requereix determinar quant rebrà un 
productor pel raïm que lliura, considerant el 
següent: si és de tipus A, se li carreguen 20 € 
al preu inicial quan és de tamany 1; i 
30 € si és de mida 2. Si és de tipus B
, es rebaixen 30 € quan és de tamany 1, i 50 € quan és de mida 2. 
Recorda en iniciar les variables que necessitis 
i sol·licitar la informació requerida al usuari. 

"""

from os import system, name
import csv

# AUTHOR CARLOS POMARES
# https://www.github.com/pomaretta

class Vinicultor:
    """ Se establecen los parámetros por defecto, pero pueden instanciarse con ellos de manera opcional. """
    def __init__(self,recaudacion = 0.0,productos = 0,name = None, network = None):
        super().__init__()
        self.recaudacion = recaudacion
        self.productos = productos
        self.nombre = name
        self.red = network
    def vender(self,producto):
        # Suma la cantidad de productos vendidos
        self.productos = float(self.productos) + producto.quantity
        # Incrementa la recaudación con el resultado del cálculo de la tasa del producto.
        self.recaudacion = float(self.recaudacion) + float(producto.calcularTasa())
    def comprar(self,producto):
        # Decrementa la recaudación con el resultado del cálculo de la tasa del producto.
        self.recaudacion = float(self.recaudacion) - float(producto.calcularTasa())

class Producto:
    def __init__(self,type,size,quantity,market):
        super().__init__()
        # Comprueba que el tipo corresponda con A o B
        if "a" in type.lower() or "b" in type.lower():
            self.type = type
        else:
            raise Exception("Not valid type")
        # Comprueba que el tamano corresponda con 1 o 2
        if "1" in str(size) or "2" in str(size):
            self.size = size
        else:
            raise Exception("Not valid size")
        # Comprueba que la cantidad no sea 0
        if quantity == 0:
            raise Exception("Not valid quantity")
        self.quantity = quantity
        self.market = market
    def calcularTasa(self):
        tipo = (self.type.lower() == "a")
        # Segun el tipo dado obtendra cierta pareja de valores del cual se basara en el tamano introducido para elegir el valor.
        precio = ((self.market.b_2,self.market.b_1)[self.size == 1],(self.market.a_2,self.market.a_1)[self.size == 1])[tipo]
        total = float(precio) * self.quantity
        return total

class Mercado:
    def __init__(self,a,b):
        super().__init__()
        self.a_1 = a[0]
        self.a_2 = a[1]
        self.b_1 = b[0]
        self.b_2 = b[1]

# Variables
USER = None
GUEST = None
MARKET = None

# VISUAL
bl = "\n"
tl = "\t"

def menu():

    bcolors.clearStyles(self=bcolors)

    exit = False

    while not exit:
        
        clear()

        print(
            f"""\t{bcolors.HEADER}¡Bienvenido a la red de Vinicultores!{bcolors.ENDC}
            \n\tPuedes realizar las siguientes gestiones:
            {("",f"{bl}{tl}{bcolors.BOLD}USUARIO: {bcolors.OKBLUE}{USER.nombre}{bcolors.ENDC} RED: {bcolors.OKBLUE}{USER.red}{bcolors.ENDC} RECAUDACIÓN: {bcolors.OKBLUE}{USER.recaudacion}€{bcolors.ENDC} PRODUCTOS VENDIDOS: {bcolors.OKBLUE}{USER.productos}{bcolors.ENDC}")[USER.nombre != None]}
            {("",f"{bl}{tl}{bcolors.BOLD}USUARIO: {bcolors.OKBLUE}{GUEST.nombre}{bcolors.ENDC} RED: {bcolors.OKBLUE}{GUEST.red}{bcolors.ENDC} RECAUDACIÓN: {bcolors.OKBLUE}{GUEST.recaudacion}€{bcolors.ENDC} PRODUCTOS VENDIDOS: {bcolors.OKBLUE}{GUEST.productos}{bcolors.ENDC}")[GUEST.nombre != None]}
            \n\t[{bcolors.OKGREEN}1{bcolors.ENDC}] {bcolors.BOLD}Crear mercado{("",f" --> {bcolors.OKGREEN}CREADO")[MARKET != None]}{bcolors.ENDC}
            \n\t[{bcolors.OKGREEN}2{bcolors.ENDC}] {bcolors.BOLD}Comprar productos{("",f" {bcolors.WARNING}sin perfil")[GUEST.nombre != None]}{("",f"{bcolors.FAIL} -- SIN MERCADO")[MARKET == None]}{bcolors.ENDC}
            \n\t[{bcolors.OKGREEN}3{bcolors.ENDC}] {bcolors.BOLD}Vender productos{("",f" {bcolors.WARNING}sin perfil")[GUEST.nombre != None]}{("",f"{bcolors.FAIL} -- SIN MERCADO")[MARKET == None]}{bcolors.ENDC}
            \n\t[{bcolors.OKGREEN}4{bcolors.ENDC}] {bcolors.BOLD}Recuperar perfil{("",f"{bcolors.BOLD}{bcolors.OKGREEN} - USUARIO: {USER.nombre}")[USER.nombre != None]}{bcolors.ENDC}
            \n\t[{bcolors.OKGREEN}5{bcolors.ENDC}] {bcolors.BOLD}Guardar perfil{(f"{bcolors.BOLD}{bcolors.FAIL} DENEGADO{bcolors.ENDC}",f"{bcolors.BOLD}{bcolors.OKGREEN} PERMITIDO {bcolors.ENDC}")[USER.nombre != None]}{bcolors.ENDC}
            \n\t[{bcolors.OKGREEN}6{bcolors.ENDC}] {bcolors.BOLD}Guardar mercado{(f"{bcolors.BOLD}{bcolors.FAIL} DENEGADO{bcolors.ENDC}",f"{bcolors.BOLD}{bcolors.OKGREEN} PERMITIDO{bcolors.ENDC}")[MARKET != None]}{bcolors.ENDC}
            \n\t[{bcolors.FAIL}7{bcolors.ENDC}] {bcolors.BOLD}Salir{bcolors.ENDC}
            """
        )

        try:
            
            order = input(f"\n\t{bcolors.BOLD}{bcolors.HEADER}>{bcolors.ENDC} ")

            if "1" in order:
                if MARKET == None:
                    stablishMarket()
                else:
                    raise Exception("Mercado establecido")
            elif "2" in order:
                if MARKET != None:
                    buyMenu()
                else:
                    raise Exception("No hay mercado...")
            elif "3" in order:
                if MARKET != None:
                    sellMenu()
                else:
                    raise Exception("No hay mercado...")
            elif "4" in order:
                if USER.nombre != "":
                    stablishUser()
                else:
                    raise Exception("Usuario establecido")
            elif "5" in order:
                if USER.nombre != "":
                    saveUser(USER)
                else:
                    raise Exception("Usuario no establecido")
            elif "6" in order:
                if MARKET != None:
                    saveMarket(MARKET)
                else:
                    raise Exception("Mercado no establecido")
            elif "7" in order:
                exit = True

        except Exception as error:
            print(error)

def configureMarket():

    bcolors.clearStyles(self=bcolors)
    
    global MARKET    
    
    typeA = None
    typeB = None

    exit = False

    while not exit:
        
        clear()

        print(
            f"""\t{bcolors.HEADER}Creación de Mercado{bcolors.ENDC}
            \n\tPuedes realizar las siguientes gestiones:
            \n\t[{bcolors.OKGREEN}1{bcolors.ENDC}] {bcolors.BOLD}Establecer valor tipo A{("",f" --> {typeA}")[typeA != None]}{bcolors.ENDC}
            \n\t[{bcolors.OKGREEN}2{bcolors.ENDC}] {bcolors.BOLD}Establecer valor tipo B{("",f" --> {typeB}")[typeB != None]}{bcolors.ENDC}
            \n\t[{bcolors.FAIL}3{bcolors.ENDC}] {bcolors.BOLD}Salir y guardar{bcolors.ENDC}
            """
        )

        try:
            
            order = input(f"\n\t{bcolors.BOLD}{bcolors.HEADER}>{bcolors.ENDC} ")

            if "1" in order:
                typeA = stablishTypeA()
            elif "2" in order:
                typeB = stablishTypeB()
            elif "3" in order:
                if typeA != None and typeB != None:
                    exit = True
                else:
                    raise Exception("Valores no completos")
        except Exception as error:
            print(error)
    
    return Mercado(a=typeA,b=typeB)

def stablishMarket():
    global MARKET

    bcolors.clearStyles(self=bcolors)

    exit = False

    while not exit:
        
        clear()

        print(
            f"""\t{bcolors.HEADER}Utilidad de mercado{bcolors.ENDC}
            \n\tPuedes realizar las siguientes gestiones:
            \n\t[{bcolors.OKGREEN}1{bcolors.ENDC}] {bcolors.BOLD}Obtener de archivo{bcolors.ENDC}
            \n\t[{bcolors.OKGREEN}2{bcolors.ENDC}] {bcolors.BOLD}Crear nuevo mercado{bcolors.ENDC}
            \n\t[{bcolors.FAIL}3{bcolors.ENDC}] {bcolors.BOLD}Salir sin crear{bcolors.ENDC}
            """
        )

        try:
            
            order = input(f"\n\t{bcolors.BOLD}{bcolors.HEADER}>{bcolors.ENDC} ")

            if "1" in order:
                data = retrieveMarket(input("Introduce la ruta del archivo: "))
                MARKET = Mercado(a=data[0],b=data[1])
                exit = True
            elif "2" in order:
                MARKET = configureMarket()
                exit = True
            elif "3" in order:
                exit = True

        except Exception as error:
            print(error)

def stablishTypeA():
    
    bcolors.clearStyles(self=bcolors)

    exit = False

    v1 = None
    v2 = None

    while not exit:
        
        clear()

        print(
            f"""\t{bcolors.HEADER}Establecer valores tipo A{bcolors.ENDC}
            \n\tPuedes realizar las siguientes gestiones:
            \n\t[{bcolors.OKGREEN}1{bcolors.ENDC}] {bcolors.BOLD}Establecer valor tamaño 1{("",f" - {v1}")[v1 != None]}{bcolors.ENDC}
            \n\t[{bcolors.OKGREEN}2{bcolors.ENDC}] {bcolors.BOLD}Establecer valor tamaño 2{("",f" - {v2}")[v2 != None]}{bcolors.ENDC}
            \n\t[{bcolors.FAIL}3{bcolors.ENDC}] {bcolors.BOLD}Salir y establecer{bcolors.ENDC}
            """
        )

        try:
            
            order = input(f"\n\t{bcolors.BOLD}{bcolors.HEADER}>{bcolors.ENDC} ")

            if "1" in order:
                clear()
                v1 = int(input("Introduce el valor del tamaño 1: "))
            elif "2" in order:
                clear()
                v2 = int(input("Introduce el valor del tamaño 2: "))
            elif "3" in order:
                if v1 != None and v2 != None:
                    exit = True
                else:
                    raise Exception("Valores no completos")

        except Exception as error:
            print(error)
        
    return [v1,v2]

def stablishTypeB():
    
    bcolors.clearStyles(self=bcolors)

    exit = False

    v1 = None
    v2 = None

    while not exit:
        
        clear()

        print(
            f"""\t{bcolors.HEADER}Establecer valores tipo B{bcolors.ENDC}
            \n\tPuedes realizar las siguientes gestiones:
            \n\t[{bcolors.OKGREEN}1{bcolors.ENDC}] {bcolors.BOLD}Establecer valor tamaño 1{("",f" - {v1}")[v1 != None]}{bcolors.ENDC}
            \n\t[{bcolors.OKGREEN}2{bcolors.ENDC}] {bcolors.BOLD}Establecer valor tamaño 2{("",f" - {v2}")[v2 != None]}{bcolors.ENDC}
            \n\t[{bcolors.FAIL}3{bcolors.ENDC}] {bcolors.BOLD}Salir y establecer{bcolors.ENDC}
            """
        )

        try:
            
            order = input(f"\n\t{bcolors.BOLD}{bcolors.HEADER}>{bcolors.ENDC} ")

            if "1" in order:
                clear()
                v1 = int(input("Introduce el valor del tamaño 1: "))
            elif "2" in order:
                clear()
                v2 = int(input("Introduce el valor del tamaño 2: "))
            elif "3" in order:
                if v1 != None and v2 != None:
                    exit = True
                else:
                    raise Exception("Valores no completos")

        except Exception as error:
            print(error)
        
    return [v1,v2]

def sellMenu():
    
    bcolors.clearStyles(self=bcolors)

    exit = False

    while not exit:
        
        clear()

        print(
            f"""\t{bcolors.HEADER}Conectado al canal de venta de {("INVIDATOS",f"{USER.red}")[USER.nombre != None]}{bcolors.ENDC}
            \n\tPuedes realizar las siguientes gestiones:
            \n\t[{bcolors.OKGREEN}1{bcolors.ENDC}] {bcolors.BOLD}Vender producto de tipo {bcolors.UNDERLINE}{bcolors.HEADER}A{bcolors.ENDC}
            \n\t[{bcolors.OKGREEN}2{bcolors.ENDC}] {bcolors.BOLD}Vender producto de tipo {bcolors.UNDERLINE}{bcolors.HEADER}B{bcolors.ENDC}
            \n\t[{bcolors.FAIL}3{bcolors.ENDC}] {bcolors.BOLD}Salir{bcolors.ENDC}
            """
        )

        # try:
            
        order = input(f"\n\t{bcolors.BOLD}{bcolors.HEADER}>{bcolors.ENDC} ")

        if "1" in order:
            p = sellItem(tipo="a")
            USER.vender(p)
        elif "2" in order:
            p = sellItem(tipo="b")
            USER.vender(p)
        elif "3" in order:
            exit = True

        # except Exception as error:
        #     print(error)

def buyMenu():

    bcolors.clearStyles(self=bcolors)

    exit = False

    while not exit:
        
        clear()

        print(
            f"""\t{bcolors.HEADER}Conectado al canal de compra de {("INVIDATOS",f"{USER.red}")[USER.nombre != None]}{bcolors.ENDC}
            \n\tPuedes realizar las siguientes gestiones:
            \n\t[{bcolors.OKGREEN}1{bcolors.ENDC}] {bcolors.BOLD}Comprar producto de tipo {bcolors.UNDERLINE}{bcolors.HEADER}A{bcolors.ENDC}
            \n\t[{bcolors.OKGREEN}2{bcolors.ENDC}] {bcolors.BOLD}Comprar producto de tipo {bcolors.UNDERLINE}{bcolors.HEADER}B{bcolors.ENDC}
            \n\t[{bcolors.FAIL}3{bcolors.ENDC}] {bcolors.BOLD}Salir{bcolors.ENDC}
            """
        )

        try:
            
            order = input(f"\n\t{bcolors.BOLD}{bcolors.HEADER}>{bcolors.ENDC} ")

            if "1" in order:
                p = buyItem(tipo="a")
                USER.comprar(p)
            elif "2" in order:
                p = buyItem(tipo="b")
                USER.comprar(p)
            elif "3" in order:
                exit = True

        except Exception as error:
            print(error)

def buyItem(tipo):

    bcolors.clearStyles(self=bcolors)

    exit = False

    size = None
    quantity = None
    p = None

    while not exit:
        
        clear()

        if size != None and quantity != None:
            p = Producto(tipo,size,quantity,MARKET)

        print(
            f"""\t{bcolors.HEADER}Comprar productos de tipo {tipo.upper()}{bcolors.ENDC}
            \n\tPuedes realizar las siguientes gestiones:
            \n\t[{bcolors.OKGREEN}1{bcolors.ENDC}] {bcolors.BOLD}Establecer tamaño{("",f" - {size}")[size != None]}{bcolors.ENDC}
            \n\t[{bcolors.OKGREEN}2{bcolors.ENDC}] {bcolors.BOLD}Establecer cantidad{("",f" - {quantity}")[quantity != None]}{bcolors.ENDC}
            \n\t[{bcolors.FAIL}3{bcolors.ENDC}] {bcolors.BOLD}Salir y establecer{bcolors.ENDC}
            """
        )

        if p != None:
            print(f"{bl}{tl}{bcolors.BOLD}{bcolors.WARNING}TOTAL A PERCIBIR:{bcolors.ENDC} {bcolors.OKGREEN}{p.calcularTasa()}€{bcolors.ENDC}")


        try:
            
            order = input(f"\n\t{bcolors.BOLD}{bcolors.HEADER}>{bcolors.ENDC} ")

            if "1" in order:
                clear()
                size = int(input("Introduce el tamaño (1 | 2): "))
                if not size == 1 and not size == 2:
                    size = None
                    raise Exception("NOT VALID VALUE")
            elif "2" in order:
                clear()
                quantity = float(input("Introduce la cantidad: "))
            elif "3" in order:
                if size != None and quantity != None:
                    exit = True
                else:
                    raise Exception("Valores no completos")

        except Exception as error:
            print(error)
        
    return p

def sellItem(tipo):
    
    bcolors.clearStyles(self=bcolors)

    exit = False

    size = None
    quantity = None
    p = None

    while not exit:
        
        clear()

        if(size != None and quantity != None):
            p = Producto(tipo,size,quantity,MARKET)

        print(
            f"""\t{bcolors.HEADER}Comprar productos de tipo {tipo.upper()}{bcolors.ENDC}
            \n\tPuedes realizar las siguientes gestiones:
            \n\t[{bcolors.OKGREEN}1{bcolors.ENDC}] {bcolors.BOLD}Establecer tamaño{("",f" - {size}")[size != None]}{bcolors.ENDC}
            \n\t[{bcolors.OKGREEN}2{bcolors.ENDC}] {bcolors.BOLD}Establecer cantidad{("",f" - {quantity}")[quantity != None]}{bcolors.ENDC}
            \n\t[{bcolors.FAIL}3{bcolors.ENDC}] {bcolors.BOLD}Salir y establecer{bcolors.ENDC}
            """
        )

        if p != None:
            print(f"{bl}{tl}{bcolors.BOLD}{bcolors.WARNING}TOTAL:{bcolors.ENDC} {bcolors.OKGREEN}{p.calcularTasa()}€{bcolors.ENDC}")

        try:
            
            order = input(f"\n\t{bcolors.BOLD}{bcolors.HEADER}>{bcolors.ENDC} ")

            if "1" in order:
                clear()
                size = int(input("Introduce el tamaño (1 | 2): "))
                if not "1" in str(size) and not "2" in str(size):
                    raise Exception("NOT VALID VALUE")
            elif "2" in order:
                clear()
                quantity = float(input("Introduce la cantidad: "))
            elif "3" in order:
                if size != None and quantity != None:
                    exit = True
                else:
                    raise Exception("Valores no completos")

        except Exception as error:
            print(error)
        
    return p

def configureUser():
    
    bcolors.clearStyles(self=bcolors)

    global GUEST

    exit = False

    nombre = None
    red = None

    while not exit:
        
        clear()

        print(
            f"""\t{bcolors.HEADER}¡Creación de perfil!{bcolors.ENDC}
            \n\tPuedes realizar las siguientes gestiones:
            \n\t[{bcolors.OKGREEN}1{bcolors.ENDC}] {bcolors.BOLD}Establecer nombre{("",f" - {nombre}")[nombre != None]}{bcolors.ENDC}
            \n\t[{bcolors.OKGREEN}2{bcolors.ENDC}] {bcolors.BOLD}Establecer red{("",f" - {red}")[red != None]}{bcolors.ENDC}
            \n\t[{bcolors.FAIL}3{bcolors.ENDC}] {bcolors.BOLD}Salir y establecer{bcolors.ENDC}
            """
        )

        try:
            
            order = input(f"\n\t{bcolors.BOLD}{bcolors.HEADER}>{bcolors.ENDC} ")

            if "1" in order:
                clear()
                nombre = input("Introduce el nombre del ususario: ")
            elif "2" in order:
                clear()
                red = input("Introduce la red del usuario: ")
            elif "3" in order:
                if nombre != None and red != None:
                    exit = True
                else:
                    raise Exception("Valores no completos")

        except Exception as error:
            print(error)
    
    return [nombre,red]

def retrieveUser(path):

    data = None

    with open(path, 'r', newline='') as file:
        reader = csv.reader(file)
        data = next(reader)
        file.close()

    if len(data) == 0:
        raise Exception("No data")

    return data

def saveUser(user):

    path = "vinicultor.csv"

    with open(path,'w') as file:
        writer = csv.writer(file)
        writer.writerow([user.nombre,user.red,user.recaudacion,user.productos])
        file.close()

def retrieveMarket(path):
    data = None

    with open(path, 'r', newline='') as file:
        reader = csv.reader(file)
        data = next(reader)
        file.close()

    if len(data) == 0:
        raise Exception("No data")

    return [[data[0],data[1]],[data[2],data[3]]]

def saveMarket(market):
    path = "mercado.csv"

    with open(path,'w') as file:
        writer = csv.writer(file)
        writer.writerow([market.a_1,market.a_2,market.b_1,market.b_2])
        file.close()

def stablishUser():

    global USER
    global GUEST

    bcolors.clearStyles(self=bcolors)

    exit = False

    while not exit:
        
        clear()

        print(
            f"""\t{bcolors.HEADER}Utilidad de perfil{bcolors.ENDC}
            \n\tPuedes realizar las siguientes gestiones:
            \n\t[{bcolors.OKGREEN}1{bcolors.ENDC}] {bcolors.BOLD}Obtener de archivo{bcolors.ENDC}
            \n\t[{bcolors.OKGREEN}2{bcolors.ENDC}] {bcolors.BOLD}Crear nuevo perfil{bcolors.ENDC}
            \n\t[{bcolors.FAIL}3{bcolors.ENDC}] {bcolors.BOLD}Salir sin crear{bcolors.ENDC}
            """
        )

        try:
            
            order = input(f"\n\t{bcolors.BOLD}{bcolors.HEADER}>{bcolors.ENDC} ")

            if "1" in order:
                data = retrieveUser(input("Introduce la ruta del archivo: "))
                USER = Vinicultor(name=data[0],network=data[1],recaudacion=data[2],productos=data[3])
                GUEST.nombre = None
                exit = True
            elif "2" in order:
                data = configureUser()
                USER = Vinicultor(name=data[0],network=data[1])
                GUEST.nombre = None
                exit = True
            elif "3" in order:
                exit = True

        except Exception as error:
            print(error)

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
    GUEST = Vinicultor(name="Usuario sin registrar")
    USER = Vinicultor()
    menu()