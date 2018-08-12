INSERT INTO privileges(name) VALUES ('ADMIN_READ'), ('ADMIN_WRITE');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
INSERT INTO users(username, password, enabled) VALUES ('admin', '$2a$10$FVA0sHaB1tw97ZfsxmEu3OG1xitkt4AJLlU3X7C6dJ7yVySJ6LSc6', 'true');
INSERT INTO users_roles(user_id, role_id) VALUES(1, 1);
INSERT INTO roles_privileges(role_id, privilege_id) VALUES (1,1), (1,2);