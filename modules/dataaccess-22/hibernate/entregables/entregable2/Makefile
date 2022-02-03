run:
	mvn clean compile test install exec:java \
		-Dexec.mainClass="com.github.pomaretta.cide.hibernate.BaseApp"

mysql:
	docker run --rm -d \
		--platform linux/amd64 \
		-p 3306:3306 \
		--name mysql \
		-e MYSQL_ROOT_PASSWORD=Cide2050 \
		-e MYSQL_DATABASE=cide \
		-v ${PWD}/src/main/resources/com/github/pomaretta/cide/hibernate/database.sql:/docker-entrypoint-initdb.d/database.sql \
		mysql:5.7

mysql-stop:
	docker stop mysql