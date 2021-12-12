-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-11-2020 a las 12:51:07
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `demo`
--
CREATE DATABASE demo;
use demo;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bonus`
--

CREATE TABLE `bonus` (
  `ENAME` varchar(10) NOT NULL,
  `JOB` varchar(9) DEFAULT NULL,
  `SAL` int(11) DEFAULT NULL,
  `COMM` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dept`
--

CREATE TABLE `dept` (
  `DEPTNO` int(2) NOT NULL,
  `DNAME` varchar(14) DEFAULT NULL,
  `LOC` varchar(13) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `dept`
--

INSERT INTO `dept` (`DEPTNO`, `DNAME`, `LOC`) VALUES
(10, 'ACCOUNTING', 'NEW YORK'),
(20, 'RESEARCH', 'DALLAS'),
(30, 'SALES', 'CHICAGO'),
(40, 'OPERATIONS', 'BOSTON');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dummy`
--

CREATE TABLE `dummy` (
  `DUMMY` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `dummy`
--

INSERT INTO `dummy` (`DUMMY`) VALUES
(0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `emp`
--

CREATE TABLE `emp` (
  `EMPNO` int(4) NOT NULL,
  `ENAME` varchar(10) DEFAULT NULL,
  `JOB` varchar(9) DEFAULT NULL,
  `MGR` int(4) DEFAULT NULL,
  `HIREDATE` date DEFAULT NULL,
  `SAL` decimal(7,2) DEFAULT NULL,
  `COMM` decimal(7,2) DEFAULT NULL,
  `DEPTNO` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `emp`
--

INSERT INTO `emp` (`EMPNO`, `ENAME`, `JOB`, `MGR`, `HIREDATE`, `SAL`, `COMM`, `DEPTNO`) VALUES
(7369, 'SMITH', 'CLERK', 7902, '1980-12-17', '800.00', NULL, 20),
(7499, 'ALLEN', 'SALESMAN', 7698, '1981-02-20', '1600.00', '300.00', 30),
(7521, 'WARD', 'SALESMAN', 7698, '1981-02-22', '1250.00', '500.00', 30),
(7566, 'JONES', 'MANAGER', 7839, '1981-04-02', '2975.00', NULL, 20),
(7654, 'MARTIN', 'SALESMAN', 7698, '1981-09-28', '1250.00', '1400.00', 30),
(7698, 'BLAKE', 'MANAGER', 7839, '1981-05-01', '2850.00', NULL, 30),
(7782, 'CLARK', 'MANAGER', 7839, '1981-06-09', '2450.00', NULL, 10),
(7788, 'SCOTT', 'ANALYST', 7566, '1982-12-09', '3000.00', NULL, 20),
(7839, 'KING', 'PRESIDENT', NULL, '1981-11-17', '5000.00', NULL, 10),
(7844, 'TURNER', 'SALESMAN', 7698, '1981-09-08', '1500.00', '0.00', 30),
(7876, 'ADAMS', 'CLERK', 7788, '1983-01-12', '1100.00', NULL, 20),
(7900, 'JAMES', 'CLERK', 7698, '1981-12-03', '950.00', NULL, 30),
(7902, 'FORD', 'ANALYST', 7566, '1981-12-03', '3000.00', NULL, 20),
(7934, 'MILLER', 'CLERK', 7782, '1982-01-23', '1300.00', NULL, 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `salgrade`
--

CREATE TABLE `salgrade` (
  `GRADE` int(11) NOT NULL,
  `LOSAL` int(11) DEFAULT NULL,
  `HISAL` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `salgrade`
--

INSERT INTO `salgrade` (`GRADE`, `LOSAL`, `HISAL`) VALUES
(1, 700, 1200),
(2, 1201, 1400),
(3, 1401, 2000),
(4, 2001, 3000),
(5, 3001, 9999);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `bonus`
--
ALTER TABLE `bonus`
  ADD PRIMARY KEY (`ENAME`);

--
-- Indices de la tabla `dept`
--
ALTER TABLE `dept`
  ADD PRIMARY KEY (`DEPTNO`);

--
-- Indices de la tabla `dummy`
--
ALTER TABLE `dummy`
  ADD PRIMARY KEY (`DUMMY`);

--
-- Indices de la tabla `emp`
--
ALTER TABLE `emp`
  ADD PRIMARY KEY (`EMPNO`),
  ADD KEY `DEPTNO` (`DEPTNO`);

--
-- Indices de la tabla `salgrade`
--
ALTER TABLE `salgrade`
  ADD PRIMARY KEY (`GRADE`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `emp`
--
ALTER TABLE `emp`
  ADD CONSTRAINT `emp_ibfk_1` FOREIGN KEY (`DEPTNO`) REFERENCES `dept` (`DEPTNO`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;