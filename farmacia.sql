-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 01-06-2023 a las 19:27:30
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `farmacia`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `doctors`
--

CREATE TABLE `doctors` (
  `mail` varchar(50) NOT NULL,
  `pass` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `last_log` date NOT NULL,
  `session` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `doctors`
--

INSERT INTO `doctors` (`mail`, `pass`, `name`, `last_log`, `session`) VALUES
('miguel11nemo@gmail.com', '1234', 'Miguel', '2023-06-01', '2965354840'),
('prueba@gmail.com', '12345678', 'Manolo', '2023-06-01', '2569622302'),
('ramago1715@gmail.com', '12345678', 'Raul Maroto', '2023-06-01', '4259144942');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medicines`
--

CREATE TABLE `medicines` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(50) NOT NULL,
  `tmax` float NOT NULL COMMENT 'temperatura maxima que soporta el medicamento',
  `tmin` float NOT NULL COMMENT 'temperatura minima que soporta el medicamento'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `medicines`
--

INSERT INTO `medicines` (`id`, `name`, `tmax`, `tmin`) VALUES
(12, 'Ibuprofeno', 20, 2),
(420, 'Amoxicilina', 2, 40),
(432, 'Omeprazol', 2, 23),
(443, 'Lorazepam', 2, 45),
(943, 'Paracetamol', 21, 67);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `patients`
--

CREATE TABLE `patients` (
  `mail` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL COMMENT 'complet del pacient'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `patients`
--

INSERT INTO `patients` (`mail`, `name`) VALUES
('elektrojuanjo@gmail.com', 'Juanjo'),
('franperu@gmail.com', 'Fran'),
('morteroballesta@gmail.com', 'Elmillor'),
('nitrofamous@gmail.com', 'Juan Victor'),
('ramago1715@gmail.com', 'Juan Fernandez');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `xips`
--

CREATE TABLE `xips` (
  `id` int(10) NOT NULL,
  `doctor_mail` varchar(50) NOT NULL,
  `id_medicine` bigint(20) UNSIGNED NOT NULL,
  `id_patient` varchar(50) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `xips`
--

INSERT INTO `xips` (`id`, `doctor_mail`, `id_medicine`, `id_patient`, `date`) VALUES
(1, 'prueba@gmail.com', 12, 'ramago1715@gmail.com', '2023-07-07'),
(45, 'prueba@gmail.com', 12, 'ramago1715@gmail.com', '2023-08-10'),
(56, 'ramago1715@gmail.com', 420, 'morteroballesta@gmail.com', '2035-12-12'),
(345, 'prueba@gmail.com', 12, 'ramago1715@gmail.com', '2023-09-14'),
(543, 'prueba@gmail.com', 12, 'ramago1715@gmail.com', '2023-10-06'),
(789, 'prueba@gmail.com', 12, 'ramago1715@gmail.com', '2023-10-26'),
(2324, 'ramago1715@gmail.com', 443, 'morteroballesta@gmail.com', '2023-08-10'),
(3245, 'ramago1715@gmail.com', 443, 'franperu@gmail.com', '2023-08-01'),
(4444, 'ramago1715@gmail.com', 12, 'franperu@gmail.com', '3025-12-12'),
(6443, 'ramago1715@gmail.com', 443, 'franperu@gmail.com', '2023-09-14'),
(7445, 'ramago1715@gmail.com', 432, 'franperu@gmail.com', '2023-10-19'),
(8754, 'ramago1715@gmail.com', 443, 'morteroballesta@gmail.com', '2023-09-08'),
(25242, 'ramago1715@gmail.com', 443, 'franperu@gmail.com', '2023-06-29'),
(43535, 'ramago1715@gmail.com', 420, 'nitrofamous@gmail.com', '2023-09-08'),
(121212, 'ramago1715@gmail.com', 12, 'elektrojuanjo@gmail.com', '2023-09-08'),
(244242, 'ramago1715@gmail.com', 12, 'nitrofamous@gmail.com', '2023-08-11'),
(525242, 'ramago1715@gmail.com', 12, 'franperu@gmail.com', '2023-07-07'),
(3412341, 'ramago1715@gmail.com', 443, 'nitrofamous@gmail.com', '2023-08-16'),
(43242525, 'ramago1715@gmail.com', 432, 'nitrofamous@gmail.com', '2023-08-12'),
(123456789, 'ramago1715@gmail.com', 432, 'franperu@gmail.com', '2022-10-15');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `doctors`
--
ALTER TABLE `doctors`
  ADD PRIMARY KEY (`mail`);

--
-- Indices de la tabla `medicines`
--
ALTER TABLE `medicines`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Indices de la tabla `patients`
--
ALTER TABLE `patients`
  ADD PRIMARY KEY (`mail`);

--
-- Indices de la tabla `xips`
--
ALTER TABLE `xips`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_Doctor_mail` (`doctor_mail`),
  ADD KEY `FK_id_patient` (`id_patient`),
  ADD KEY `FK_id_medicine` (`id_medicine`) USING BTREE;

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `medicines`
--
ALTER TABLE `medicines`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=944;

--
-- AUTO_INCREMENT de la tabla `xips`
--
ALTER TABLE `xips`
  MODIFY `id_medicine` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=444;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `xips`
--
ALTER TABLE `xips`
  ADD CONSTRAINT `FK_Doctor_mail` FOREIGN KEY (`doctor_mail`) REFERENCES `doctors` (`mail`),
  ADD CONSTRAINT `FK_id_medicine` FOREIGN KEY (`id_medicine`) REFERENCES `medicines` (`id`),
  ADD CONSTRAINT `FK_id_patient` FOREIGN KEY (`id_patient`) REFERENCES `patients` (`mail`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
