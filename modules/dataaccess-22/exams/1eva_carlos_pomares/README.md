# Examen 1ra Evaluación

<!-- Description -->

## Ejecución

Está basado en ejecución manual, por lo cual un paso previo es compilar las clases y después ejecutar el programa principal que és **MainApp.java**

Nos ponemos en la carpeta ```src/```

Compilamos con el siguiente comando.
```
javac -encoding UTF-8 -d ./bin \
		./src/MainApp.java \
		./src/Alumno.java \
		./src/Reader.java \
		./src/Writer.java \
		./src/XMLReader.java \
		./src/XMLWriter.java \
		./src/SAXWriter.java \
		./src/SAXReader.java \
		./src/SAXHandler.java \
		./src/DOMWriter.java \
		./src/DOMReader.java
```

Ejecutamos el programa con ```java```
```
java -cp ./bin MainApp
```