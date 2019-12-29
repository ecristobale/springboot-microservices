INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) 
VALUES 
('edu', '12345', 1, 'Eduardo', 'Cristobal', 'edu@ecristobale.com'),
('admin', '12345', 1, 'John', 'Doe', 'john.doe@ecristobale.com');

INSERT INTO roles (nombre) 
VALUES 
('ROLE_USER'),
('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id, role_id) 
VALUES 
(1, 1),
(2, 1),
(2, 2);