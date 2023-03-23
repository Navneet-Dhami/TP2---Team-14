-- Active: 1676462839008@@127.0.0.1@3306@phpmyadmin
-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Feb 14, 2023 at 09:49 PM
-- Server version: 8.0.32-0ubuntu0.20.04.2
-- PHP Version: 8.1.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `soundwaves`
--

-- --------------------------------------------------------
-- MAIN CODE

INSERT INTO category (category_id, category_name, image_url) 
VALUES 
    (1, 'Headphones', 'headphone-icon.png'), 
    (2, 'Speakers', 'speaker-icon.png'), 
    (3, 'CDs', 'cd-icon.png'), 
    (4, 'Vinyls (7\' 10\' & 12\' variations)', 'vinyl-icon.png'), 
    (5, 'Posters & Merch', 'merch-icon.png');


INSERT INTO product (id, name, category_id, price, stock, description, img)
VALUES
(1, 'Product 1 for Headphones', 1, 60.0, 5, 'Product 1 for Headphones description', 'something.jpg'),
(2, 'Product 2 for Headphones', 1, 70.0, 200, 'Product 2 for Headphones description', 'something.jpg'),
(3, 'Product 3 for Speakers', 2, 100.0, 30, 'Product 3 for Speakers description', 'something.jpg'),
(4, 'Product 4 for Speakers', 2, 75.0, 40, 'Product 4 for Speakers description', 'something.jpg'),
(5, 'Product 5 for CDs', 3, 10.0, 50, 'Product 5 for CDs description', 'something.jpg'),
(6, 'Product 6 for CDs', 3, 10.0, 60, 'Product 6 for CDs description', 'something.jpg'),
(7, 'Product 7 for Vinyls', 4, 70.0, 70, 'Product 7 for Vinyls description', 'something.jpg'),
(8, 'Product 8 for Vinyls', 4, 80.0, 80, 'Product 8 for Vinyls description', 'something.jpg'),
(9, 'Product 9 for Posters & Merch', 5, 40.0, 300, 'Product 9 for Posters & Merch description', 'something.jpg'),
(10, 'Product 10 for Posters & Merch', 5, 40.0, 350, 'Product 10 for Posters & Merch description', 'something.jpg');




/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
