# Servicio de cache distribuida

Se debe desarrollar un servicio que simule una caché distribuida de tipo clave-valor.

El servidor, una vez aceptada la petición de registro por parte de un cliente, debe mantener un control sobre qué clientes forman parte del clúster y qué cliente lo han abandonado.

Una vez que el cliente se ha registrado en el servicio de caché distribuida las operaciones que se permiten son la siguientes:

- Almacenar un par clave-valor
- Eliminar un par clave-valor
- Consultar el valor asociado a una clave
- Modificar el valor asociado a una clave

Únicamente los clientes registrados podrán realizar las operaciones anteriores.

El alumno debe definir tanto el protocolo a seguir entre cliente y servidor como el tipo de comunicación entre ellos, además de cualquier otro detalle que afecte directamente al funcionamiento propuesto del servicio.

Hecho por [Carlos Pomares](https://www.github.com/pomaretta)