-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 13, 2018 at 11:50 PM
-- Server version: 10.1.32-MariaDB
-- PHP Version: 7.2.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `evidencija_studenti`
--

-- --------------------------------------------------------

--
-- Table structure for table `kolegij`
--

CREATE TABLE `kolegij` (
  `ID` int(11) NOT NULL,
  `Naziv` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PredavacID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `kolegij`
--

INSERT INTO `kolegij` (`ID`, `Naziv`, `PredavacID`) VALUES
(1, 'Baze Podataka', 2),
(9, 'Programiranje 1', 2),
(11, 'Racunalni vid', 17);

-- --------------------------------------------------------

--
-- Table structure for table `kolegijsudionik`
--

CREATE TABLE `kolegijsudionik` (
  `ID` int(11) NOT NULL,
  `StudentID` int(255) DEFAULT NULL,
  `KolegijID` int(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `kolegijsudionik`
--

INSERT INTO `kolegijsudionik` (`ID`, `StudentID`, `KolegijID`) VALUES
(2, 10, 1),
(3, 11, 1),
(5, 3, 9),
(21, 7, 1),
(24, 10, 9),
(25, 11, 9),
(26, 12, 9),
(41, 12, 1),
(42, 3, 11),
(43, 7, 11),
(44, 10, 11),
(46, 12, 11),
(47, 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `ocjena`
--

CREATE TABLE `ocjena` (
  `ID` int(11) NOT NULL,
  `Value` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `Opis` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `Datum` date DEFAULT NULL,
  `StudentID` int(255) DEFAULT NULL,
  `KolegijID` int(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `ocjena`
--

INSERT INTO `ocjena` (`ID`, `Value`, `Opis`, `Datum`, `StudentID`, `KolegijID`) VALUES
(18, '4', 'asdasd', '2018-06-06', 7, 1),
(21, '5', 'qwqweqweqew', '2018-06-16', 10, 9),
(23, '5', 'dfghjkxdcfghj', '2018-06-01', 10, 1),
(24, '5', 'zuhhjh', '0118-05-01', 11, 9),
(25, '5', 'Pismeni ispit', '0118-05-01', 11, 1),
(26, '5', 'sadadasd', '2018-06-08', 7, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `ID` int(11) NOT NULL,
  `Ime` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `Prezime` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `Username` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `Password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `Email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `BrojTelefona` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `BrojIndeksa` varchar(255) COLLATE utf8_bin NOT NULL,
  `Privilegije` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`ID`, `Ime`, `Prezime`, `Username`, `Password`, `Email`, `BrojTelefona`, `BrojIndeksa`, `Privilegije`) VALUES
(1, 'admin', 'admin', 'admin', 'asd', 'admin@gmail.com', '063 123 456', '', 'admin'),
(2, 'profesor', 'profesor', 'profesor', 'asd', 'profesor@gmail.com', '063 444 555', '', 'profesor'),
(3, 'ucenik', 'ucenik', 'ucenik', 'asd', 'ucenik@gmail.com', '063 111 222', '7499', 'ucenik'),
(7, 'ucenik2', 'ucenik2', 'ucenik2', 'asd', 'ucenik2@gmail.com', '063 444 555', '7500', 'ucenik'),
(10, 'Ivo', 'Ivic', 'ivo91', 'asd', 'ivo91@gmail.com', '1231231231', '7501', 'ucenik'),
(11, 'Mate', 'Matic', 'mate91', 'asd', 'mate91@gmail.com', '12412512', '7502', 'ucenik'),
(12, 'Ana', 'Anic', 'ana91', 'asd', 'ana91@gmail.com', '9485948', '7503', 'ucenik'),
(17, 'Danijel', 'Vasic', 'danijel', 'asd', 'asdsad', 'asdasd', '', 'profesor');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `kolegij`
--
ALTER TABLE `kolegij`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `fk_predavac` (`PredavacID`);

--
-- Indexes for table `kolegijsudionik`
--
ALTER TABLE `kolegijsudionik`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `fk_kolegijsudionik_student` (`StudentID`),
  ADD KEY `fk_kolegijsudionik_kolegij` (`KolegijID`);

--
-- Indexes for table `ocjena`
--
ALTER TABLE `ocjena`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `fk_ocjena_student` (`StudentID`),
  ADD KEY `fk_ocjena_kolegij` (`KolegijID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `kolegij`
--
ALTER TABLE `kolegij`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `kolegijsudionik`
--
ALTER TABLE `kolegijsudionik`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT for table `ocjena`
--
ALTER TABLE `ocjena`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `kolegij`
--
ALTER TABLE `kolegij`
  ADD CONSTRAINT `fk_predavac` FOREIGN KEY (`PredavacID`) REFERENCES `user` (`ID`);

--
-- Constraints for table `kolegijsudionik`
--
ALTER TABLE `kolegijsudionik`
  ADD CONSTRAINT `fk_kolegijsudionik_kolegij` FOREIGN KEY (`KolegijID`) REFERENCES `kolegij` (`ID`),
  ADD CONSTRAINT `fk_kolegijsudionik_student` FOREIGN KEY (`StudentID`) REFERENCES `user` (`ID`);

--
-- Constraints for table `ocjena`
--
ALTER TABLE `ocjena`
  ADD CONSTRAINT `fk_ocjena_kolegij` FOREIGN KEY (`KolegijID`) REFERENCES `kolegij` (`ID`),
  ADD CONSTRAINT `fk_ocjena_student` FOREIGN KEY (`StudentID`) REFERENCES `user` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
