# AUTHOR CARLOS POMARES

from Subasta import Subasta
from Usuario import Usuario
import unittest

class SubastaTest(unittest.TestCase):

    @classmethod
    def setUpClass(cls) -> None:
        
        cls.usuarios = dict()
        cls.subastas = list()

        return super().setUpClass()

    def step_1_crear_usuarios(self):
        self.usuarios["toni"] = Usuario("Toni",100)
        self.usuarios["pep"] = Usuario("Pep",150)
        self.usuarios["enric"] = Usuario("Enric",300)
    
    def step_2_crear_subasta(self):
        self.subastas.append(
            Subasta("Telèfon Mòbil",self.usuarios["toni"])
        )
    
    def step_3_pep_action(self):
        self.subastas[0].pujar(self.usuarios["pep"],100)
    
    def step_4_pep_confirmation(self):
        self.assertEqual("Pep",self.subastas[0].puja.usuario.nombre)
        print(f"{self.subastas[0].puja.usuario.nombre} - {self.subastas[0].puja.cantidad}€")
    
    def step_5_enric_exception(self):
        try:
            self.subastas[0].pujar(self.usuarios["enric"],50)
            self.fail("Expected failure not raised.")
        except Exception as e:
            self.assertEqual("La cantidad de la puja es menor que la actual puja.",str(e))

    def step_6_mayor_puja(self):
        self.assertEqual("Pep",self.subastas[0].puja.usuario.nombre)
        print(f"{self.subastas[0].puja.usuario.nombre} - {self.subastas[0].puja.cantidad}€")

    def step_7_end_subasta(self):
        self.subastas[0].ejecutar()
        self.assertFalse(self.subastas[0].estado)

    def step_8_enric_end_exception(self):
        try:
            self.subastas[0].pujar(self.usuarios["enric"],200)
            self.fail("Expected failure not raised.")
        except Exception as e:
            self.assertEqual("La subasta no esta abierta",str(e))

    def step_9_0_pep_new_subasta(self):
        self.subastas.append(
            Subasta("Impresora 3D",self.usuarios["pep"])
        )

    def step_9_1_enric_auto_puja(self):
        self.subastas[1].pujaAutomatica(self.usuarios["enric"])

    def step_9_2_enric_confirmation(self):
        self.assertEqual("Enric",self.subastas[1].puja.usuario.nombre)

    def step_9_3_pep_execute(self):
        self.subastas[1].ejecutar()
        self.assertFalse(self.subastas[1].estado)

    def step_9_4_confirm_user_credit(self):
        for usuario in self.usuarios:
            print(f"{self.usuarios[usuario].nombre} - {self.usuarios[usuario].credito}")

    def step_9_5_confirm_user_subastas(self):
        for usuario in self.usuarios:
            print(f"{self.usuarios[usuario].nombre} - {self.usuarios[usuario].credito}")
            for idx, subasta in enumerate(self.usuarios[usuario].subastas):
                print(f"{idx} - {subasta.nombre} - PUJA: {subasta.puja.usuario.nombre} / {subasta.puja.cantidad}")

    def _steps(self):
        for name in dir(self):
            if name.startswith('step'):
                yield name, getattr(self,name)

    def test_sequence(self):
        for name, step in self._steps():
            try:
                step()
            except unittest.SkipTest:
                pass
            except Exception as e:
                print("PASSED FAIL")
                self.fail("{} Failed ({}: {})".format(step,type(e),e))

if __name__ == "__main__":
    unittest.main()