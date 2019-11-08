-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 08-11-2019 a las 01:27:32
-- Versión del servidor: 8.0.17
-- Versión de PHP: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `trolleyes`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compra`
--

CREATE TABLE `compra` (
  `id` int(11) NOT NULL,
  `cantidad` int(10) NOT NULL,
  `factura_id` int(11) NOT NULL,
  `producto_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `compra`
--

INSERT INTO `compra` (`id`, `cantidad`, `factura_id`, `producto_id`) VALUES
(1, 48, 16, 16),
(2, 36, 13, 5),
(3, 18, 17, 11),
(4, 10, 15, 25),
(5, 7, 12, 15),
(6, 33, 9, 7),
(7, 4, 15, 7),
(8, 12, 11, 5),
(9, 48, 25, 10),
(10, 33, 22, 16),
(11, 36, 7, 8),
(12, 4, 14, 18),
(13, 29, 2, 10),
(14, 30, 23, 8),
(15, 12, 17, 16),
(16, 39, 15, 19),
(17, 8, 21, 13),
(18, 20, 7, 17),
(19, 11, 15, 7),
(20, 4, 17, 11),
(21, 40, 4, 4),
(22, 32, 22, 17),
(23, 26, 16, 22),
(24, 33, 25, 25),
(25, 5, 6, 9),
(26, 9, 1, 9),
(27, 22, 12, 10),
(28, 35, 14, 18),
(29, 47, 5, 1),
(30, 31, 8, 15),
(31, 5, 24, 15),
(32, 18, 15, 23),
(33, 16, 13, 13),
(34, 34, 19, 4),
(35, 2, 13, 6),
(36, 2, 9, 14),
(37, 2, 5, 20),
(38, 10, 19, 15),
(39, 23, 24, 21),
(40, 3, 19, 23),
(41, 4, 6, 11),
(42, 40, 17, 23),
(43, 23, 23, 4),
(44, 5, 16, 5),
(45, 14, 2, 25),
(46, 33, 24, 10),
(47, 19, 3, 18),
(48, 16, 3, 5),
(49, 5, 8, 2),
(50, 2, 22, 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE `factura` (
  `id` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `iva` int(3) NOT NULL,
  `usuario_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `factura`
--

INSERT INTO `factura` (`id`, `fecha`, `iva`, `usuario_id`) VALUES
(1, '2015-08-04', 21, 15),
(2, '2014-07-09', 21, 18),
(3, '2014-12-18', 21, 4),
(4, '2016-03-02', 21, 23),
(5, '2016-05-25', 21, 22),
(6, '2017-11-24', 21, 25),
(7, '2014-10-17', 21, 5),
(8, '2014-11-21', 21, 5),
(9, '2016-09-04', 21, 7),
(10, '2017-02-28', 21, 18),
(11, '2016-03-20', 21, 19),
(12, '2017-09-21', 21, 15),
(13, '2014-09-06', 21, 25),
(14, '2015-02-18', 21, 9),
(15, '2014-06-15', 21, 2),
(16, '2019-11-10', 21, 20),
(17, '2018-01-03', 21, 12),
(18, '2015-03-05', 21, 2),
(19, '2019-04-01', 21, 5),
(20, '2019-07-22', 21, 17),
(21, '2018-01-31', 21, 26),
(22, '2019-06-10', 21, 20),
(23, '2016-04-18', 21, 9),
(24, '2017-07-20', 21, 9),
(25, '2016-06-14', 21, 3),
(26, '2016-05-16', 21, 8),
(27, '2017-10-28', 21, 7),
(28, '2016-11-15', 21, 9),
(29, '2017-12-19', 21, 13),
(30, '2017-10-13', 21, 22),
(31, '2017-02-26', 21, 2),
(32, '2014-06-07', 21, 25),
(33, '2014-04-19', 21, 21),
(34, '2017-12-07', 21, 16),
(35, '2014-01-08', 21, 24),
(36, '2016-10-13', 21, 18),
(37, '2015-09-24', 21, 8),
(38, '2017-01-11', 21, 13),
(39, '2014-09-03', 21, 14),
(40, '2017-09-04', 21, 17),
(41, '2015-11-14', 21, 9),
(42, '2016-03-02', 21, 3),
(43, '2018-05-12', 21, 3),
(44, '2019-07-15', 21, 24),
(45, '2015-11-24', 21, 21),
(46, '2019-04-23', 21, 10),
(47, '2015-08-08', 21, 9),
(48, '2015-11-24', 21, 4),
(49, '2018-11-02', 21, 9),
(50, '2015-08-11', 21, 22);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id` int(11) NOT NULL,
  `codigo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `existencias` int(10) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `imagen` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `descripcion` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `tipo_producto_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id`, `codigo`, `existencias`, `precio`, `imagen`, `descripcion`, `tipo_producto_id`) VALUES
(1, '135390', 426, '7.68', 'link a una imagen', 'Maquina de manzana. ', 3),
(2, '662160', 36, '7.91', 'link a una imagen', 'Interruptor para montar tubos. ', 11),
(3, '657741', 341, '7.61', 'link a una imagen', 'Bebida de  manzana. ', 3),
(4, '565238', 824, '14.00', 'link a una imagen', 'Maquina de montar tubos. ', 12),
(5, '420289', 515, '9.12', 'link a una imagen', 'Maquina de dientes. ', 5),
(6, '919481', 676, '6.22', 'link a una imagen', 'Maquina de emparejar. ', 6),
(7, '561968', 165, '6.34', 'link a una imagen', 'Maquina de manzana. ', 4),
(8, '725835', 429, '2.23', 'link a una imagen', 'Bebida de  montar tubos. ', 11),
(9, '504450', 8, '9.80', 'link a una imagen', 'Maquina de emparejar. ', 8),
(10, '125152', 287, '9.16', 'link a una imagen', 'Maquina de montar tubos. ', 7),
(11, '240116', 640, '9.56', 'link a una imagen', 'Interruptor para manzana. ', 9),
(12, '711181', 78, '7.14', 'link a una imagen', 'Bebida de  emparejar. ', 8),
(13, '134370', 610, '1.27', 'link a una imagen', 'Libro de montar tubos. ', 7),
(14, '100653', 68, '1.20', 'link a una imagen', 'Maquina de dientes. ', 9),
(15, '585159', 650, '1.27', 'link a una imagen', 'Maquina de emparejar. ', 4),
(16, '273151', 715, '9.10', 'link a una imagen', 'Maquina de dientes. ', 7),
(17, '176212', 646, '8.95', 'link a una imagen', 'Libro de emparejar. ', 8),
(18, '782505', 288, '9.83', 'link a una imagen', 'Maquina de dientes. ', 8),
(19, '219783', 655, '6.18', 'link a una imagen', 'Bebida de  manzana. ', 9),
(20, '245988', 950, '3.29', 'link a una imagen', 'Libro de emparejar. ', 9),
(21, '809609', 699, '4.11', 'link a una imagen', 'Maquina de dientes. ', 12),
(22, '737798', 834, '8.52', 'link a una imagen', 'Interruptor para manzana. ', 2),
(23, '595284', 109, '7.34', 'link a una imagen', 'Interruptor para dientes. ', 10),
(24, '649211', 553, '8.00', 'link a una imagen', 'Bebida de  montar tubos. ', 6),
(25, '345438', 916, '3.91', 'link a una imagen', 'Interruptor para emparejar. ', 7),
(26, '129175', 866, '9.68', 'link a una imagen', 'Bebida de  manzana. ', 11),
(27, '388862', 199, '7.55', 'link a una imagen', 'Interruptor para montar tubos. ', 12),
(28, '686263', 459, '7.53', 'link a una imagen', 'Bebida de  montar tubos. ', 6),
(29, '310138', 233, '5.23', 'link a una imagen', 'Libro de montar tubos. ', 5),
(30, '915606', 330, '3.37', 'link a una imagen', 'Libro de emparejar. ', 12),
(31, '929357', 92, '8.96', 'link a una imagen', 'Libro de manzana. ', 4),
(32, '712736', 907, '9.72', 'link a una imagen', 'Bebida de  manzana. ', 2),
(33, '614150', 601, '5.69', 'link a una imagen', 'Libro de manzana. ', 7),
(34, '650279', 802, '4.11', 'link a una imagen', 'Maquina de montar tubos. ', 2),
(35, '956545', 586, '3.18', 'link a una imagen', 'Maquina de manzana. ', 6),
(36, '623001', 65, '9.65', 'link a una imagen', 'Interruptor para montar tubos. ', 1),
(37, '797070', 9, '2.66', 'link a una imagen', 'Libro de emparejar. ', 9),
(38, '311920', 924, '6.19', 'link a una imagen', 'Interruptor para dientes. ', 8),
(39, '194034', 879, '1.29', 'link a una imagen', 'Interruptor para montar tubos. ', 5),
(40, '579064', 251, '8.42', 'link a una imagen', 'Libro de dientes. ', 10),
(41, '617075', 836, '8.52', 'link a una imagen', 'Libro de manzana. ', 3),
(42, '480891', 394, '5.05', 'link a una imagen', 'Interruptor para manzana. ', 10),
(43, '390863', 852, '81.00', 'link a una imagen', 'Libro de manzana. ', 1),
(44, '780486', 642, '3.47', 'link a una imagen', 'Bebida de  montar tubos. ', 9),
(45, '227205', 869, '8.09', 'link a una imagen', 'Maquina de emparejar. ', 8),
(46, '446387', 271, '9.97', 'link a una imagen', 'Bebida de  montar tubos. ', 11),
(47, '361873', 55, '4.38', 'link a una imagen', 'Libro de montar tubos. ', 6),
(48, '349182', 44, '4.30', 'link a una imagen', 'Interruptor para montar tubos. ', 10),
(49, '856092', 987, '1.47', 'link a una imagen', 'Maquina de manzana. ', 3),
(50, '206050', 66, '76.00', 'link a una imagen', 'Interruptor para dientes. ', 7);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_producto`
--

CREATE TABLE `tipo_producto` (
  `id` int(11) NOT NULL,
  `descripcion` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `tipo_producto`
--

INSERT INTO `tipo_producto` (`id`, `descripcion`) VALUES
(1, 'Alimentario'),
(2, 'Maquinaria'),
(3, 'Higiene'),
(4, 'Lectura'),
(5, 'Audiovisual'),
(6, 'Ropa'),
(7, 'Calzado'),
(8, 'Informatica'),
(9, 'Tecnologia'),
(10, 'Consumibles'),
(11, 'Hogar y decoracion'),
(12, 'Deportes');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_usuario`
--

CREATE TABLE `tipo_usuario` (
  `id` int(11) NOT NULL,
  `descripcion` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `tipo_usuario`
--

INSERT INTO `tipo_usuario` (`id`, `descripcion`) VALUES
(1, 'Administrador'),
(2, 'Cliente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `dni` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `nombre` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `apellido1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `apellido2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `login` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `tipo_usuario_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `dni`, `nombre`, `apellido1`, `apellido2`, `login`, `password`, `email`, `tipo_usuario_id`) VALUES
(1, '12345678A', 'Troll', 'Eyes', 'Daw', 'trolleyes', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'troll@eyes.com', 1),
(2, '41746666D', 'Jaumet', 'el de', 'Murallot', 'Jaumet', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'jaumet@trolleyes.com', 2),
(3, '41746666D', 'Pompeu', 'el de', 'Cigrons', 'Pompeu', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'pompeu@trolleyes.com', 2),
(4, '41746666D', 'Cirili', 'Ca la', 'Porrons', 'Cirili', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'cirili@trolleyes.com', 2),
(5, '41746666D', 'Vidal', 'el de', 'Murallot', 'Vidal', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'vidal@trolleyes.com', 2),
(6, '41746666D', 'Maurici', 'de Can', 'Freda', 'Maurici', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'maurici@trolleyes.com', 2),
(7, '41746666D', 'Jaumet', 'de Cal', 'Palla', 'Jaumet', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'jaumet@trolleyes.com', 2),
(8, '41746666D', 'Bernat', 'de Cal', 'Cargols', 'Bernat', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'bernat@trolleyes.com', 2),
(9, '41746666D', 'Miqueleta', 'de Cal', 'Trencapins', 'Miqueleta', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'miqueleta@trolleyes.com', 2),
(10, '41746666D', 'Marcel·li', 'Ca la', 'Murallot', 'Marcel·li', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'marcel·li@trolleyes.com', 2),
(11, '41746666D', 'Bernat', 'Pacoco', 'Freda', 'Bernat', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'bernat@trolleyes.com', 2),
(12, '41746666D', 'Cirili', 'de les', 'Trencapins', 'Cirili', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'cirili@trolleyes.com', 2),
(13, '41746666D', 'Marcel·li', 'de Cal', 'Faves', 'Marcel·li', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'marcel·li@trolleyes.com', 2),
(14, '41746666D', 'Domènec', 'de Can', 'Cargols', 'Domènec', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'domènec@trolleyes.com', 2),
(15, '41746666D', 'Vidal', 'de les', 'Llobarro', 'Vidal', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'vidal@trolleyes.com', 2),
(16, '41746666D', 'Paco', 'Ca la', 'Cebes', 'Paco', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'paco@trolleyes.com', 2),
(17, '41746666D', 'Cirili', 'de Can', 'Murallot', 'Cirili', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'cirili@trolleyes.com', 2),
(18, '41746666D', 'Josepa', 'el de', 'Pacoco', 'Josepa', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'josepa@trolleyes.com', 2),
(19, '41746666D', 'Eudald', 'Pacoco', 'Metge', 'Eudald', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'eudald@trolleyes.com', 2),
(20, '41746666D', 'Paco', 'de les', 'Clapés', 'Paco', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'paco@trolleyes.com', 2),
(21, '41746666D', 'Marcel·li', 'Pacoco', 'Cigrons', 'Marcel·li', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'marcel·li@trolleyes.com', 2),
(22, '41746666D', 'Maurici', 'de Cal', 'Trencapins', 'Maurici', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'maurici@trolleyes.com', 2),
(23, '41746666D', 'Cirili', 'de Cal', 'Palla', 'Cirili', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'cirili@trolleyes.com', 2),
(24, '41746666D', 'Paco', 'Ca la', 'Pacoco', 'Paco', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'paco@trolleyes.com', 2),
(25, '41746666D', 'Jaumet', 'dels', 'Cigrons', 'Jaumet', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'jaumet@trolleyes.com', 2),
(26, '41746666D', 'Eudald', 'Pacoco', 'Cargols', 'Eudald', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'eudald@trolleyes.com', 2),
(27, '41746666D', 'Pepet', 'Pacoco', 'Cigrons', 'Pepet', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'pepet@trolleyes.com', 2),
(28, '41746666D', 'Cirili', 'el de', 'Cebes', 'Cirili', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'cirili@trolleyes.com', 2),
(29, '41746666D', 'Marcel·li', 'de Cal', 'Palla', 'Marcel·li', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'marcel·li@trolleyes.com', 2),
(30, '41746666D', 'Vidal', 'de Cal', 'Murallot', 'Vidal', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'vidal@trolleyes.com', 2),
(31, '41746666D', 'Miqueleta', 'el de', 'Cebes', 'Miqueleta', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'miqueleta@trolleyes.com', 2),
(32, '41746666D', 'Miqueleta', 'Ca la', 'Cargols', 'Miqueleta', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'miqueleta@trolleyes.com', 2),
(33, '41746666D', 'Jaumet', 'de Cal', 'Faves', 'Jaumet', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'jaumet@trolleyes.com', 2),
(34, '41746666D', 'Josepa', 'dels', 'Porrons', 'Josepa', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'josepa@trolleyes.com', 2),
(35, '41746666D', 'Miqueleta', 'de Cal', 'Murallot', 'Miqueleta', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'miqueleta@trolleyes.com', 2),
(36, '41746666D', 'Vidal', 'dels', 'Freda', 'Vidal', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'vidal@trolleyes.com', 2),
(37, '41746666D', 'Domènec', 'el de', 'Cebes', 'Domènec', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'domènec@trolleyes.com', 2),
(38, '41746666D', 'Josepa', 'dels', 'Murallot', 'Josepa', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'josepa@trolleyes.com', 2),
(39, '41746666D', 'Jaumet', 'el de', 'Freda', 'Jaumet', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'jaumet@trolleyes.com', 2),
(40, '41746666D', 'Pompeu', 'Pacoco', 'Llobarro', 'Pompeu', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'pompeu@trolleyes.com', 2),
(41, '41746666D', 'Domènec', 'Ca la', 'Llobarro', 'Domènec', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'domènec@trolleyes.com', 2),
(42, '41746666D', 'Bernat', 'el de', 'Freda', 'Bernat', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'bernat@trolleyes.com', 2),
(43, '41746666D', 'Vidal', 'de la', 'Faves', 'Vidal', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'vidal@trolleyes.com', 2),
(44, '41746666D', 'Bernat', 'Pacoco', 'Metge', 'Bernat', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'bernat@trolleyes.com', 2),
(45, '41746666D', 'Cirili', 'de les', 'Palla', 'Cirili', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'cirili@trolleyes.com', 2),
(46, '41746666D', 'Paco', 'el de', 'Llobarro', 'Paco', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'paco@trolleyes.com', 2),
(47, '41746666D', 'Vidal', 'de Cal', 'Murallot', 'Vidal', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'vidal@trolleyes.com', 2),
(48, '41746666D', 'Pepet', 'de les', 'Clapés', 'Pepet', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'pepet@trolleyes.com', 2),
(49, '41746666D', 'Eudald', 'Pacoco', 'Cargols', 'Eudald', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'eudald@trolleyes.com', 2),
(50, '41746666D', 'Domènec', 'Ca la', 'Pacoco', 'Domènec', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'domènec@trolleyes.com', 2),
(51, '41746666D', 'Paco', 'de Can', 'Palla', 'Paco', 'da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04', 'paco@trolleyes.com', 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `compra`
--
ALTER TABLE `compra`
  ADD PRIMARY KEY (`id`,`factura_id`,`producto_id`),
  ADD KEY `fk_compra_factura_idx` (`factura_id`),
  ADD KEY `fk_compra_producto_idx` (`producto_id`);

--
-- Indices de la tabla `factura`
--
ALTER TABLE `factura`
  ADD PRIMARY KEY (`id`,`usuario_id`),
  ADD KEY `fk_factura_usuario_idx` (`usuario_id`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id`,`tipo_producto_id`),
  ADD KEY `fk_producto_tipo_producto_idx` (`tipo_producto_id`);

--
-- Indices de la tabla `tipo_producto`
--
ALTER TABLE `tipo_producto`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tipo_usuario`
--
ALTER TABLE `tipo_usuario`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`,`tipo_usuario_id`),
  ADD KEY `fk_usuario_tipo_usuario_idx` (`tipo_usuario_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `compra`
--
ALTER TABLE `compra`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT de la tabla `factura`
--
ALTER TABLE `factura`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT de la tabla `tipo_producto`
--
ALTER TABLE `tipo_producto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `tipo_usuario`
--
ALTER TABLE `tipo_usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `compra`
--
ALTER TABLE `compra`
  ADD CONSTRAINT `fk_compra_factura` FOREIGN KEY (`factura_id`) REFERENCES `factura` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_compra_producto` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE;

--
-- Filtros para la tabla `factura`
--
ALTER TABLE `factura`
  ADD CONSTRAINT `fk_factura_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE;

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `fk_producto_tipo_producto` FOREIGN KEY (`tipo_producto_id`) REFERENCES `tipo_producto` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `fk_usuario_tipo_usuario` FOREIGN KEY (`tipo_usuario_id`) REFERENCES `tipo_usuario` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
