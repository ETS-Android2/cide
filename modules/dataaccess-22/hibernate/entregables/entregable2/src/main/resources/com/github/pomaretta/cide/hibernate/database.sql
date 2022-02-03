CREATE DATABASE IF NOT EXISTS cide;
USE cide;

CREATE TABLE IF NOT EXISTS Libros (
    id_libro INT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    ejemplares INT NOT NULL DEFAULT 0,
    editorial VARCHAR(255) NOT NULL,
    paginas INT NOT NULL,
    edicion INT NOT NULL,
    PRIMARY KEY (id_libro),
    UNIQUE KEY (titulo, editorial, edicion)
);

CREATE TABLE IF NOT EXISTS Socios (
    id_socio INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    apellidos VARCHAR(255) NOT NULL,
    edad INT NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    telefono VARCHAR(9) NOT NULL,
    PRIMARY KEY (id_socio)
);

CREATE TABLE IF NOT EXISTS Prestamos (
    id_libro INT NOT NULL,
    id_socio INT NOT NULL,
    inicio DATE NOT NULL,
    fin DATE NOT NULL,
    CONSTRAINT fk_libros FOREIGN KEY (id_libro) REFERENCES Libros(id_libro),
    CONSTRAINT fk_socios FOREIGN KEY (id_socio) REFERENCES Socios(id_socio),
    PRIMARY KEY (id_libro, id_socio)
);