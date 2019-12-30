INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) 
VALUES 
('edu', '$2a$10$B4nYD09o/ppUyfumO6x.WOm3jnRClDLdSDt81UhFWIFF1JTDGPwiO', 1, 'Eduardo', 'Cristobal', 'edu@ecristobale.com'),
('admin', '$2a$10$IIaysJ/YIZWdLTS0tI28S.RRjLpn3tujqZ8RiyZcNCXnPy3l3kEFa', 1, 'John', 'Doe', 'john.doe@ecristobale.com');

INSERT INTO roles (nombre) 
VALUES 
('ROLE_USER'),
('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id, role_id) 
VALUES 
(1, 1),
(2, 1),
(2, 2);