CREATE TABLE IF NOT EXISTS  Estudiante (
  id serial,
  nombres VARCHAR(45) NOT NULL,
  apellidos VARCHAR(45) NULL,
  curso VARCHAR(45)  NULL,
  telefono INT NULL,
  cedula INT NULL,
  PRIMARY KEY (id)
  );


CREATE TABLE IF NOT EXISTS  Docente (
  id serial,
  nombres VARCHAR(45) NOT NULL,
  apellidos VARCHAR(45) NULL,
  correo VARCHAR(45)  NULL,
  telefono INT NULL,
  cedula INT NULL,
  PRIMARY KEY (id)
  );

Create TABLE IF NOT EXISTS Asignatura (

    id serial,
    Materia VARCHAR (20) NOT NULL,
    PRIMARY KEY (id),
    Estudiante_id INT NULL,
    FOREIGN KEY (Estudiante_id ) REFERENCES Estudiante (id),
    Docente_id INT NULL,
    FOREIGN KEY (Docente_id) REFERENCES Docente (id)

);