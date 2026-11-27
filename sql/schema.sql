CREATE DATABASE IF NOT EXISTS medirecord
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE medirecord;

CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(120) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    rol VARCHAR(30) NOT NULL DEFAULT 'USER',
    creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE IF NOT EXISTS medicamentos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    nombre VARCHAR(120) NOT NULL,
    dosis VARCHAR(80),
    presentacion VARCHAR(80),
    stock_actual INT NOT NULL DEFAULT 0,
    fecha_vencimiento DATE NULL,
    CONSTRAINT fk_medicamentos_usuario
        FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS tratamientos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    medicamento_id INT NOT NULL,
    frecuencia_horas INT NOT NULL,
    hora_inicio TIME NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT fk_tratamientos_usuario
        FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_tratamientos_medicamento
        FOREIGN KEY (medicamento_id) REFERENCES medicamentos(id)
        ON DELETE RESTRICT
);
		
CREATE TABLE IF NOT EXISTS historial_tomas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tratamiento_id INT NOT NULL,
    fecha_hora_programada DATETIME NOT NULL,
    fecha_hora_real DATETIME NULL,
    estado VARCHAR(20) NOT NULL DEFAULT 'PENDIENTE',
    CONSTRAINT fk_historial_tratamiento
        FOREIGN KEY (tratamiento_id) REFERENCES tratamientos(id)
        ON DELETE CASCADE
);

-- Datos de prueba iniciales
INSERT IGNORE INTO usuarios (id, nombre, correo, password_hash, rol) VALUES
(1, 'Paciente Demo', 'demo@medirecord.local', '$2a$10$ejemploHashTemporalCambiar1234567890123456789012', 'USER');

INSERT IGNORE INTO medicamentos (id, usuario_id, nombre, dosis, presentacion, stock_actual, fecha_vencimiento) VALUES
(1, 1, 'Ibuprofeno 400mg', '1 tableta', 'Tabletas', 20, DATE_ADD(CURDATE(), INTERVAL 180 DAY)),
(2, 1, 'Amoxicilina 500mg', '1 cápsula', 'Cápsulas', 15, DATE_ADD(CURDATE(), INTERVAL 120 DAY));

INSERT IGNORE INTO tratamientos (id, usuario_id, medicamento_id, frecuencia_horas, hora_inicio, fecha_inicio, fecha_fin, activo) VALUES
(1, 1, 1, 8, '08:00:00', CURDATE(), DATE_ADD(CURDATE(), INTERVAL 7 DAY), TRUE);

INSERT IGNORE INTO historial_tomas (id, tratamiento_id, fecha_hora_programada, fecha_hora_real, estado) VALUES
(1, 1, NOW(), NOW(), 'TOMADA');