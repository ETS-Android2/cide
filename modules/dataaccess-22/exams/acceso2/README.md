# ORM Hibernate

Un centro concertado, cuyo nombre podría ser CIDE, le gustaría disponer de una base de datos que contenga la estructura necesaria para gestionar determinados aspectos. 

La estructura deseada sería la siguiente:
* Información sobre los departamentos que tiene el centro: identificador del departamento y nombre del departamento. Los nombres de los distintos departamentos se pueden encontrar en la web https://cide.es/ apartado Depts. didàctics.
* Información sobre los datos personales de las personas: número de identificación, nif, nombre, primer apellido, segundo apellido, fecha de nacimiento, dirección, sexo y numero de telefono.
* Información sobre los datos del profesor: número de identificación del profesor, número de identificación como persona e identificador del departamento al que pertenece. Los identificadores de persona y departamento tienen que ir ligados a sus respectivas estructuras.
La base de datos que se utilizará será MySQL.

Mapea las tablas utilizando JPA/Hibernate y realiza un proyecto Java llamado CIDE cuyo objetivo sea el siguiente:
- Crear la base de datos.
- Configurar y crear la ORM Hibernate.
- Realizar los procedimientos necesarios para dar de alta, dar de baja y modificar las distintas estructuras creadas.

Criterios de puntuación. Total 10 puntos.
Creación e Instalación de la base de datos: 1 punto.
Configuración de Hibernate: 1 punto.
Sentencias DML. Utilización de los métodos de Hibernate save, saveOrUpdate, get, update y delete siempre que sea posible, dejando HQL para cuando no haya otra alternativa:  3 puntos.