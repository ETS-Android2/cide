init:
	docker run --platform linux/amd64 --name cide-mysql \
		--rm -d \
		-e MYSQL_ROOT_PASSWORD=Cide2050 \
		-v ${PWD}/database.sql://docker-entrypoint-initdb.d/database.sql \
		-p 16001:3306 \
		-t mysql
	javac -encoding utf-8 -d ./bin \
		./src/SQL_carlos_pomares.java

run:
	java -cp ./lib/mysql-connector-java-8.0.27.jar:./bin SQL_carlos_pomares