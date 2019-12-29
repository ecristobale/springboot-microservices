DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS usuarios;
DROP TABLE IF EXISTS usuarios_roles;

CREATE TABLE roles(
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   nombre VARCHAR (30) UNIQUE
);

CREATE TABLE usuarios(
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   nombre VARCHAR (255),
   apellido VARCHAR (255),
   enabled BOOLEAN,
   username VARCHAR (20) UNIQUE,
   password VARCHAR (60),
   email VARCHAR (100) UNIQUE
);

CREATE TABLE usuarios_roles(
   usuario_id BIGINT NOT NULL,
   role_id BIGINT NOT NULL,
   PRIMARY KEY (usuario_id, role_id),
   FOREIGN KEY (role_id) REFERENCES roles,
   FOREIGN KEY (usuario_id) REFERENCES usuarios
);
