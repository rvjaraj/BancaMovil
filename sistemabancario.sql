-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-06-2020 a las 00:45:41
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.2.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sistemabancario`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alogin`
--

CREATE TABLE `alogin` (
  `id` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `ip` varchar(45) NOT NULL,
  `acceso` tinyint(4) NOT NULL,
  `Cliente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `alogin`
--

INSERT INTO `alogin` (`id`, `fecha`, `ip`, `acceso`, `Cliente`) VALUES
(1, '2020-05-31', '192.168.100.213', 1, 3),
(2, '2020-05-31', '192.168.100.213', 0, 3),
(3, '2020-05-31', '192.168.100.213', 0, 3),
(4, '2020-05-31', '192.168.100.213', 1, 3),
(5, '2020-05-31', '192.168.100.213', 1, 3),
(6, '2020-05-31', '192.168.100.213', 1, 3),
(7, '2020-05-31', '192.168.100.213', 1, 3),
(8, '2020-05-31', '192.168.100.213', 1, 3),
(9, '2020-05-31', '192.168.100.213', 1, 3),
(10, '2020-05-31', '192.168.100.213', 0, 3),
(11, '2020-05-31', '192.168.100.213', 1, 3),
(12, '2020-05-31', '192.168.100.213', 1, 3),
(13, '2020-05-31', '192.168.100.213', 1, 3),
(14, '2020-05-31', '192.168.100.213', 1, 3),
(15, '2020-05-31', '192.168.100.213', 1, 3),
(16, '2020-05-31', '192.168.100.213', 1, 3),
(17, '2020-05-31', '192.168.100.213', 1, 3),
(18, '2020-05-31', '192.168.100.213', 0, 3),
(19, '2020-05-31', '192.168.100.213', 0, 3),
(20, '2020-05-31', '192.168.100.213', 0, 2),
(21, '2020-05-31', '192.168.100.213', 0, 3),
(22, '2020-05-31', '192.168.100.213', 1, 3),
(23, '2020-06-01', '192.168.100.215', 1, 3),
(24, '2020-06-01', '192.168.100.215', 1, 3),
(25, '2020-06-01', '192.168.100.215', 1, 3),
(26, '2020-06-01', '192.168.100.215', 0, 3),
(27, '2020-06-01', '192.168.100.215', 1, 3),
(28, '2020-06-01', '192.168.100.215', 1, 3),
(29, '2020-06-01', '192.168.100.215', 1, 3),
(30, '2020-06-01', '192.168.100.215', 1, 3),
(31, '2020-06-01', '192.168.100.215', 1, 3),
(32, '2020-06-03', '192.168.100.205', 1, 3),
(33, '2020-06-03', '192.168.100.205', 0, 3),
(34, '2020-06-03', '192.168.100.205', 1, 3),
(35, '2020-06-03', '192.168.100.205', 1, 3),
(36, '2020-06-03', '192.168.100.205', 1, 3),
(37, '2020-06-03', '192.168.100.205', 1, 3),
(38, '2020-06-03', '192.168.100.205', 1, 3),
(39, '2020-06-03', '192.168.100.205', 1, 3),
(41, '2020-06-03', '192.168.100.205', 1, 3),
(42, '2020-06-03', '192.168.100.205', 1, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `amortizacion`
--

CREATE TABLE `amortizacion` (
  `id` int(11) NOT NULL,
  `periodo` int(11) NOT NULL,
  `couta` double NOT NULL,
  `interes` double NOT NULL,
  `total` double NOT NULL,
  `deuda` double NOT NULL,
  `estado` tinyint(4) NOT NULL,
  `fecha` date NOT NULL,
  `credito` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id` int(11) NOT NULL,
  `cedula` varchar(45) NOT NULL,
  `nombres` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `contracenia` varchar(45) NOT NULL,
  `eliminado` tinyint(4) DEFAULT 0,
  `activo` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id`, `cedula`, `nombres`, `apellido`, `telefono`, `direccion`, `correo`, `contracenia`, `eliminado`, `activo`) VALUES
(1, '0105452171', 'Mi R', 'Tienda', '0990550716', 'Turuhuayco 9-92', 'vinicio1004@hotmail.com', '1234', 0, 0),
(2, '0107051534', 'Usuario', 'Usuario', '0990550716', 'Usuario', 'vinicio1004@hotmail.com', 'user', 0, 0),
(3, '0106805419', 'Juan Juan', 'Canar j', '0990550716', 'Turuhuayco 9-92', 'vinicio1004@hotmail.com', 'juan', 0, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `credito`
--

CREATE TABLE `credito` (
  `id` int(11) NOT NULL,
  `plazo` int(11) NOT NULL,
  `proposito` varchar(500) NOT NULL,
  `cantidad` double NOT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `cuenta` int(10) UNSIGNED NOT NULL,
  `elimado` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuenta`
--

CREATE TABLE `cuenta` (
  `id` int(10) UNSIGNED NOT NULL,
  `numero` varchar(45) NOT NULL,
  `saldo` double NOT NULL,
  `fecha` date NOT NULL,
  `cliente` int(11) NOT NULL,
  `eliminado` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `cuenta`
--

INSERT INTO `cuenta` (`id`, `numero`, `saldo`, `fecha`, `cliente`, `eliminado`) VALUES
(1, 'CUHA06S3', 432.12, '2020-06-06', 1, 0),
(2, 'CUHA16S10', 9000, '2020-06-06', 2, 0),
(3, 'CUHA20S11', 406, '2020-05-31', 3, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `preguntas`
--

CREATE TABLE `preguntas` (
  `id` int(11) NOT NULL,
  `pregunta` varchar(255) DEFAULT NULL,
  `respuesta` varchar(255) DEFAULT NULL,
  `cliente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `solicitud`
--

CREATE TABLE `solicitud` (
  `id` int(11) NOT NULL,
  `plazo` int(11) NOT NULL,
  `historial` varchar(45) DEFAULT NULL,
  `proposito` varchar(500) NOT NULL,
  `cantidad` double NOT NULL,
  `saldocuenta` varchar(45) DEFAULT NULL,
  `tiempoempleo` double DEFAULT NULL,
  `tasadepagos` double DEFAULT NULL,
  `estadocivil` varchar(45) DEFAULT NULL,
  `garante` varchar(45) DEFAULT NULL,
  `avaluovivienda` double DEFAULT NULL,
  `trebajadorextrangero` tinyint(4) DEFAULT NULL,
  `edad` int(11) DEFAULT NULL,
  `tipovivienda` varchar(45) DEFAULT NULL,
  `numerocreditos` int(11) DEFAULT NULL,
  `empleo` varchar(45) DEFAULT NULL,
  `tipocliente` varchar(45) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `cliente` int(11) NOT NULL,
  `elimado` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `trabajador`
--

CREATE TABLE `trabajador` (
  `id` int(11) NOT NULL,
  `cedula` varchar(45) NOT NULL,
  `nombres` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `contracenia` varchar(45) NOT NULL,
  `rol` varchar(45) NOT NULL,
  `sueldo` double NOT NULL,
  `eliminado` tinyint(4) DEFAULT 0,
  `activo` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `trabajador`
--

INSERT INTO `trabajador` (`id`, `cedula`, `nombres`, `apellido`, `telefono`, `direccion`, `correo`, `contracenia`, `rol`, `sueldo`, `eliminado`, `activo`) VALUES
(1, '0105452171', 'Ricardo Jara', 'Tienda jara', '099we', 'Turuhuae e', 'rjaraj@est.ups.edu.ec', 'abcd', 'Administrador', 100, 0, 0),
(2, '0107051534', 'Cajero', 'Cajero', '0990550716', 'Cajero', '1234@gmail', 'xxxx', 'Cajero', 1000, 0, 0),
(3, '0106458581', 'Gabriela Eriaka', 'Dután', '0990550716', 'Padre Aguirre y calle larga', 'gabudp27@gmail.com', 'gaby', 'Administrador', 450, 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transaciones`
--

CREATE TABLE `transaciones` (
  `id` int(11) NOT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  `cantidad` varchar(45) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `Cuenta_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `transaciones`
--

INSERT INTO `transaciones` (`id`, `tipo`, `cantidad`, `fecha`, `Cuenta_id`) VALUES
(18, 'Retiro', '90.0', '2020-05-31', 2),
(19, 'Retiro', '100.0', '2020-05-31', 3),
(20, 'Deposito', '1000.0', '2020-05-31', 2),
(21, 'Retiro', '100.0', '2020-05-31', 2),
(22, 'Deposito', '100.0', '2020-05-31', 2),
(23, 'Retiro', '100.0', '2020-05-31', 2),
(24, 'Deposito', '100.0', '2020-05-31', 1),
(25, 'Deposito', '100.0', '2020-05-31', 1),
(26, 'Deposito', '100.0', '2020-05-31', 2),
(27, 'Deposito', '1000.0', '2020-05-31', 3),
(28, 'Retiro', '1000.0', '2020-05-31', 3),
(29, 'Retiro', '1000.0', '2020-05-31', 2),
(30, 'Deposito', '9000.0', '2020-05-31', 2),
(31, 'Retiro', '100.0', '2020-05-31', 1),
(32, 'Retiro', '1000.0', '2020-05-31', 1),
(33, 'Retiro', '100.0', '2020-05-31', 1),
(34, 'Deposito', '1.0', '2020-05-31', 3),
(35, 'Retiro', '1.0', '2020-05-31', 3),
(36, 'Deposito', '1.0', '2020-05-31', 3),
(37, 'Retiro', '1.0', '2020-05-31', 3),
(38, 'Deposito', '1.0', '2020-05-31', 3),
(39, 'Retiro', '1.0', '2020-05-31', 3),
(40, 'Deposito', '100.0', '2020-06-01', 1),
(41, 'Deposito', '100.0', '2020-06-01', 3),
(42, 'Retiro', '500.0', '2020-06-01', 3),
(43, 'Deposito', '100.0', '2020-06-01', 1),
(44, 'Retiro', '100.0', '2020-06-01', 1),
(45, 'Retiro', '1.0', '2020-06-01', 1),
(46, 'Deposito', '100.0', '2020-06-01', 3),
(47, 'Retiro', '100.0', '2020-06-01', 3),
(48, 'Retiro', '50000.0', '2020-06-01', 1),
(49, 'Retiro', '-50000.0', '2020-06-01', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transferencias`
--

CREATE TABLE `transferencias` (
  `id` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `cantidad` double NOT NULL,
  `concepto` varchar(45) NOT NULL,
  `Ordenante` int(10) UNSIGNED NOT NULL,
  `Beneficiario` int(10) UNSIGNED NOT NULL,
  `transferenciascol` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alogin`
--
ALTER TABLE `alogin`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_ALogin_Cliente1_idx` (`Cliente`);

--
-- Indices de la tabla `amortizacion`
--
ALTER TABLE `amortizacion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_Amortizacion_Credito1_idx` (`credito`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `cedula_UNIQUE` (`cedula`);

--
-- Indices de la tabla `credito`
--
ALTER TABLE `credito`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_Credito_Cuenta1_idx` (`cuenta`);

--
-- Indices de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `numero_UNIQUE` (`numero`),
  ADD KEY `fk_Cuenta_Cliente1_idx` (`cliente`);

--
-- Indices de la tabla `preguntas`
--
ALTER TABLE `preguntas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_Preguntas_Cliente1_idx` (`cliente`);

--
-- Indices de la tabla `solicitud`
--
ALTER TABLE `solicitud`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_Solicitud_Cliente1_idx` (`cliente`);

--
-- Indices de la tabla `trabajador`
--
ALTER TABLE `trabajador`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `cedula_UNIQUE` (`cedula`);

--
-- Indices de la tabla `transaciones`
--
ALTER TABLE `transaciones`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_Transaciones_Cuenta1_idx` (`Cuenta_id`);

--
-- Indices de la tabla `transferencias`
--
ALTER TABLE `transferencias`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_transferencias_Cuenta1_idx` (`Ordenante`),
  ADD KEY `fk_transferencias_Cuenta2_idx` (`Beneficiario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alogin`
--
ALTER TABLE `alogin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT de la tabla `amortizacion`
--
ALTER TABLE `amortizacion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `credito`
--
ALTER TABLE `credito`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `preguntas`
--
ALTER TABLE `preguntas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `solicitud`
--
ALTER TABLE `solicitud`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `trabajador`
--
ALTER TABLE `trabajador`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `transaciones`
--
ALTER TABLE `transaciones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT de la tabla `transferencias`
--
ALTER TABLE `transferencias`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `alogin`
--
ALTER TABLE `alogin`
  ADD CONSTRAINT `fk_ALogin_Cliente1` FOREIGN KEY (`Cliente`) REFERENCES `cliente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `amortizacion`
--
ALTER TABLE `amortizacion`
  ADD CONSTRAINT `fk_Amortizacion_Credito1` FOREIGN KEY (`credito`) REFERENCES `credito` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `credito`
--
ALTER TABLE `credito`
  ADD CONSTRAINT `fk_Credito_Cuenta1` FOREIGN KEY (`cuenta`) REFERENCES `cuenta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD CONSTRAINT `fk_Cuenta_Cliente1` FOREIGN KEY (`cliente`) REFERENCES `cliente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `preguntas`
--
ALTER TABLE `preguntas`
  ADD CONSTRAINT `fk_Preguntas_Cliente1` FOREIGN KEY (`cliente`) REFERENCES `cliente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `solicitud`
--
ALTER TABLE `solicitud`
  ADD CONSTRAINT `fk_Solicitud_Cliente1` FOREIGN KEY (`cliente`) REFERENCES `cliente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `transaciones`
--
ALTER TABLE `transaciones`
  ADD CONSTRAINT `fk_Transaciones_Cuenta1` FOREIGN KEY (`Cuenta_id`) REFERENCES `cuenta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `transferencias`
--
ALTER TABLE `transferencias`
  ADD CONSTRAINT `fk_transferencias_Cuenta1` FOREIGN KEY (`Ordenante`) REFERENCES `cuenta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_transferencias_Cuenta2` FOREIGN KEY (`Beneficiario`) REFERENCES `cuenta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
