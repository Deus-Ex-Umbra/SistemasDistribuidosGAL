-- Database to deudas

CREATE DATABASE DBDEUDAS;
USE DBDEUDAS;

CREATE TABLE Deuda (
    CI VARCHAR(10),
    Anio INT,
    Impuesto ENUM('Vehículo', 'Casa') NOT NULL,
    Monto DOUBLE,
    PRIMARY KEY (CI, Anio, Impuesto)
) ENGINE = INNODB;

INSERT INTO Deuda (CI, Anio, Impuesto, Monto) VALUES 
('1234567', 2022, 'Vehículo', 2451),
('1234567', 2022, 'Casa', 2500),
('555587', 2021, 'Vehículo', 5000),
('333357', 2023, 'Casa', 24547);
