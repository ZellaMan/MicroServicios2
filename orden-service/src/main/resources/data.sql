INSERT INTO orden (id, cliente, fecha) VALUES (1, 'Ivan Ruiz', CURRENT_TIMESTAMP);

INSERT INTO orden_productos (orden_id, producto_id, cantidad) VALUES (1, 1, 2);
INSERT INTO orden_productos (orden_id, producto_id, cantidad) VALUES (1, 3, 1);