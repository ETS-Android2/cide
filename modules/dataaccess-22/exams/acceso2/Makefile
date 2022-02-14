run:
	mvn compile test install exec:java \
		-Dexec.mainClass="com.github.pomaretta.cide.Main"

mysql:
	docker run --rm -d \
		--platform linux/amd64 \
		-p 3306:3306 \
		--name mysql \
		-e MYSQL_ROOT_PASSWORD=Cide2050 \
		-e MYSQL_DATABASE=cide \
		-v ${PWD}/src/main/resources/com/github/pomaretta/cide/data/schema.sql:/docker-entrypoint-initdb.d/schema.sql \
		mysql:5.7

mysql-stop:
	docker stop mysql