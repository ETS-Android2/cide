# AUTHOR CARLOS POMARES

from Puja import Puja
from Usuario import Usuario


class Subasta:
    def __init__(self,nombre: str,propietario: Usuario) -> None:
        self.nombre = nombre
        self.propietario = propietario
        self.propietario.registrarSubasta(self)
        self.estado = True
        self.puja = None
        self.pujas = list()
    def pujar(self,usuario: Usuario, cantidad: float) -> bool:
        if not self.estado:
            raise Exception("La subasta no esta abierta")
        if usuario.credito < cantidad:
            raise Exception("La cantidad de crédito del usuario es insuficiente.")
        if self.propietario == usuario:
            raise Exception("El propietario no puede pujar.")
        if self.puja != None and cantidad < self.puja.cantidad:
            raise Exception("La cantidad de la puja es menor que la actual puja.")
        puja = Puja(usuario=usuario,cantidad=cantidad)
        self.pujas.append(puja)
        self.puja = puja
        return True
    def pujaAutomatica(self,usuario: Usuario) -> bool:
        if not self.estado:
            raise Exception("La subasta no esta abierta")
        if self.puja != None and usuario.credito < self.puja.cantidad + 1:
            raise Exception("La cantidad de crédito del usuario es insuficiente.")
        if self.propietario == usuario:
            raise Exception("El propietario no puede pujar.")
        if self.puja == None:
            cantidad = 1
        else:
            cantidad = self.puja.cantidad + 1
        puja = Puja(usuario=usuario,cantidad=cantidad)
        self.puja = puja
        self.pujas.append(puja)
        return True
    def ejecutar(self) -> bool:
        if not self.estado:
            raise Exception("La subasta ya está cerrada.")
        if self.puja == None and len(self.pujas) == 0:
            raise Exception("La subasta no tiene pujas.")
        self.estado = False
        self.puja.usuario.decrementar(self.puja.cantidad)
        self.propietario.incrementar(self.puja.cantidad)
        return True