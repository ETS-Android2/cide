# SQL Practice

## Ejecución

Para la base de datos en **MySQL** usando el puerto **16001**, para la inicialización utilizamos **Docker**:

**Windows**
```
docker run --name cide-mysql --rm -d -e MYSQL_ROOT_PASSWORD=Cide2050  -v ./database.sql://docker-entrypoint-initdb.d/database.sql -p 16001:3306 -t mysql
```

**Linux/macOS**
```
docker run --name cide-mysql \
		--rm -d \
		-e MYSQL_ROOT_PASSWORD=Cide2050 \
		-v ${PWD}/database.sql://docker-entrypoint-initdb.d/database.sql \
		-p 16001:3306 \
		-t mysql
```

Está basado en ejecución manual, por lo cual un paso previo es compilar las clases y después ejecutar el programa principal que és **SQL_carlos_pomares.java**

Compilamos con el siguiente comando.
```
javac -encoding utf-8 -d ./bin \
		./src/SQL_carlos_pomares.java
```

Ejecutamos el programa con ```java```
```
java -cp ./lib/mysql-connector-java-8.0.27.jar:./bin SQL_carlos_pomares
```