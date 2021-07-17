-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 17, 2021 at 10:47 PM
-- Server version: 10.4.20-MariaDB
-- PHP Version: 7.3.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `reportes`
--

-- --------------------------------------------------------

--
-- Table structure for table `adoa`
--

CREATE TABLE `adoa` (
  `Nombre` varchar(16) NOT NULL,
  `Apellido` varchar(16) DEFAULT NULL,
  `Reporte` varchar(72) DEFAULT NULL,
  `PC` int(2) DEFAULT NULL,
  `Reporte2` varchar(16) DEFAULT NULL,
  `Año` int(11) DEFAULT NULL,
  `Laboratorio` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `adob`
--

CREATE TABLE `adob` (
  `Nombre` varchar(16) DEFAULT NULL,
  `Apellido` varchar(16) DEFAULT NULL,
  `Reporte` varchar(72) DEFAULT NULL,
  `PC` int(2) DEFAULT NULL,
  `Reporte2` varchar(16) DEFAULT NULL,
  `Año` int(11) DEFAULT NULL,
  `Laboratorio` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `electronica`
--

CREATE TABLE `electronica` (
  `Nombre` varchar(16) DEFAULT NULL,
  `Apellido` varchar(16) DEFAULT NULL,
  `Reporte` varchar(72) DEFAULT NULL,
  `PC` int(2) DEFAULT NULL,
  `Reporte2` varchar(16) DEFAULT NULL,
  `Año` int(11) DEFAULT NULL,
  `Laboratorio` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `infoa`
--

CREATE TABLE `infoa` (
  `Nombre` varchar(16) DEFAULT NULL,
  `Apellido` varchar(16) DEFAULT NULL,
  `Reporte` varchar(72) DEFAULT NULL,
  `PC` int(2) DEFAULT NULL,
  `Reporte2` varchar(16) DEFAULT NULL,
  `Año` int(11) DEFAULT NULL,
  `Laboratorio` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `infob`
--

CREATE TABLE `infob` (
  `Nombre` varchar(16) DEFAULT NULL,
  `Apellido` varchar(16) DEFAULT NULL,
  `Reporte` varchar(72) DEFAULT NULL,
  `PC` int(2) DEFAULT NULL,
  `Reporte2` varchar(16) DEFAULT NULL,
  `Año` int(1) DEFAULT NULL,
  `Laboratorio` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `multimedios`
--

CREATE TABLE `multimedios` (
  `Nombre` varchar(16) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Apellido` varchar(16) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Reporte` varchar(72) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `PC` int(2) NOT NULL,
  `Reporte2` varchar(16) DEFAULT NULL,
  `Año` int(11) DEFAULT NULL,
  `Laboratorio` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `quimica`
--

CREATE TABLE `quimica` (
  `Nombre` varchar(16) DEFAULT NULL,
  `Apellido` varchar(16) DEFAULT NULL,
  `Reporte` varchar(72) DEFAULT NULL,
  `PC` int(2) DEFAULT NULL,
  `Reporte2` varchar(16) DEFAULT NULL,
  `Año` int(11) DEFAULT NULL,
  `Laboratorio` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
