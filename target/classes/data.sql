INSERT INTO estudiantes (id, nombre, apellido, matricula, email) VALUES (1, 'Juan', 'Pérez', 'MAT123', 'juan@mail.com');
INSERT INTO materias (id, nombre, codigo, creditos, profesor) VALUES (1, 'Matemáticas', 'MAT101', 6, 'Dr. García');
INSERT INTO inscripciones (id, estudiante_id, materia_id, fecha_inscripcion, estado) VALUES (1, 1, 1, NOW(), 'ACTIVA');
