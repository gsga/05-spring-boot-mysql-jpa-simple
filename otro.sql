-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Oct 04, 2022 at 12:37 AM
-- Server version: 8.0.27
-- PHP Version: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Database: `simple`
--
CREATE DATABASE IF NOT EXISTS `simple` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `simple`;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `name`) VALUES
(1, 'uno@gmail.com', 'Uno'),
(2, 'dos@gmail.com', 'Dos'),
(3, 'other@gmail.com', 'Other'),
(4, 'some@gmail.com', 'Some'),
(5, 'first@gmail.com', 'First'),
(6, 'primero@gmail.com', 'Primero'),
(7, 'segundo@gmail.com', 'Segundo'),
(8, 'tercero@gmail.com', 'Tercero'),
(9, 'cuarto@gmail.com', 'Cuarto');
COMMIT;
