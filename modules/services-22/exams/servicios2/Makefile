check_defined = \
	$(strip $(foreach 1,$1, \
		$(call __check_defined,$1,$(strip $(value 2)))))
__check_defined = \
	$(if $(value $1),, \
	$(error Undefined $1$(if $2, ($2))))

run:
	$(call check_defined, ROL, Rol to be run (Client/Server))
	mvn compile test install exec:java \
		-Dexec.mainClass="com.github.pomaretta.cide.$(ROL)"