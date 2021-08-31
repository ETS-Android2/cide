# AUTHOR CARLOS POMARES

from Usuario import Usuario

class Puja:
    def __init__(self,usuario: Usuario,cantidad: float) -> None:
        self.usuario = usuario
        self.cantidad = cantidad