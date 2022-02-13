check_defined = \
	$(strip $(foreach 1,$1, \
		$(call __check_defined,$1,$(strip $(value 2)))))
__check_defined = \
	$(if $(value $1),, \
	$(error Undefined $1$(if $2, ($2))))

build:
	find src -name "*.java" > sources.txt
	javac -d ./bin -cp ./bin/ -sourcepath ./src/ @sources.txt
	rm sources.txt

run:
	$(call check_defined, EXERCICE, Exercice to run)
	$(call check_defined, ROL, Rol to be run (Client/Server))
	java -cp ./bin/ com.github.pomaretta.cide.sockets.$(EXERCICE).$(ROL)