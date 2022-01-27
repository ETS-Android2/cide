# Ejercicio entregable JavaFX

Hecho por [Carlos Pomares](https://www.github.com/pomaretta)

Crear un simple formulario y a través de sus campos obtener la información necasaria como:
* Nombre
* Apellidos
* Email

Posteriormente crear un fichero .csv con la información del usuario.

## Ejecución

Terminal:

En la terminal escribe el siguiente comando. (macOS/Linux)
```
./mvnw clean javafx:run
```

En la terminal escribe el siguiente comando. (Windows)
```
./mvnw.cmd clean javafx:run
```

## Cosas a tener en cuenta

Si no tienes Java instalado, y usas un IDE como IntelliJ IDEA.
Puede importar la carpeta, abriendola con el IDE. De esa forma detectará el proyecto de Maven.

Después podras configurar la ejecución del plugin de JavaFX que sería darle el goal de `javafx:run` al IDE.