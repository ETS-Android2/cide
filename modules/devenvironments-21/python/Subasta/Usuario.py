# AUTHOR CARLOS POMARES

class Usuario:
    def __init__(self,nombre: str,credito: float) -> None:
        self.nombre = nombre
        self.credito = credito
        self.subastas = list()
    def incrementar(self,cantidad: float):
        self.credito += cantidad
    def decrementar(self,cantidad: float):
        self.credito -= cantidad
    def registrarSubasta(self,subasta):
        self.subastas.append(subasta)