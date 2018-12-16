-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 10, 2018 at 06:17 PM
-- Server version: 5.7.24-0ubuntu0.16.04.1
-- PHP Version: 7.0.32-0ubuntu0.16.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `TpJdbcSujet4`
--
CREATE DATABASE IF NOT EXISTS `TpJdbcSujet4` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `TpJdbcSujet4`;

-- --------------------------------------------------------

--
-- Table structure for table `Adresse`
--

CREATE TABLE `Adresse` (
  `IDAdresse` int(11) NOT NULL,
  `NRue` int(11) NOT NULL,
  `NomRue` text NOT NULL,
  `CodePostal` int(11) NOT NULL,
  `Ville` text NOT NULL,
  `Pays` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Adresse`
--

INSERT INTO `Adresse` (`IDAdresse`, `NRue`, `NomRue`, `CodePostal`, `Ville`, `Pays`) VALUES
(1, 5, 'Rue Emile Delahaye', 37000, 'Vierzon', 'France'),
(2, 27, 'Rue Robert Surcouf', 78960, 'Voisins le Bretonneux', 'France'),
(11, 1, 'rue nationale', 37200, 'Tours', 'France'),
(12, 2, 'rue nationale', 37000, 'Tours', 'France'),
(13, 1, 'test', 1, 'Tours', 'France'),
(14, 2, 'test2', 2, 'tours', 'france');

-- --------------------------------------------------------

--
-- Table structure for table `Annonce`
--

CREATE TABLE `Annonce` (
  `IDAnnonce` int(11) NOT NULL,
  `Nom` text NOT NULL,
  `TypeTransaction` text NOT NULL,
  `Position` int(11) NOT NULL,
  `Prix` float NOT NULL,
  `DescriptionAnnonce` text NOT NULL,
  `Categorie` int(11) NOT NULL,
  `Vendeur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Annonce`
--

INSERT INTO `Annonce` (`IDAnnonce`, `Nom`, `TypeTransaction`, `Position`, `Prix`, `DescriptionAnnonce`, `Categorie`, `Vendeur`) VALUES
(8, 'ordinateur', 'Carte bancaire', 11, 300, 'Tres bonne qualite', 6, 5),
(9, 'tv', 'espece', 12, 500, 'une jolie tv', 8, 6),
(10, 'test', 'carte', 13, 1, 'test', 1, 7);

-- --------------------------------------------------------

--
-- Table structure for table `Categorie`
--

CREATE TABLE `Categorie` (
  `IDCategorie` int(11) NOT NULL,
  `Nom` text NOT NULL,
  `DescriptionCategorie` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Categorie`
--

INSERT INTO `Categorie` (`IDCategorie`, `Nom`, `DescriptionCategorie`) VALUES
(1, 'Immobilier', 'Vente de maisons ou appartements'),
(3, 'Artisanat', 'Une activité impliquant la capacité de faire des choses à la main.'),
(4, 'Automobile', 'Véhicule qui se meut à l’aide d’un moteur.'),
(5, 'Livre', 'Écrit reproduit à un certain nombre d’exemplaires.'),
(6, 'Ordinateur', 'Appareil de traitement automatique de données.'),
(7, 'Mode', 'Une tendance populaire, en particulier dans les styles vestimentaires et de parure ou dans les comportements.'),
(8, 'Film et tv', 'Une histoire ou un événement enregistré par une caméra sous la forme d\'un ensemble d\'images en mouvement et présenté au théâtre ou à la télévision; un film.'),
(9, 'Santé et ménage', 'L\'état d\'être libre de maladie ou de blessure.\r\nMaison et ses occupants considérés comme une unité.'),
(10, 'Sport', 'Ensemble des exercices physiques pratiqués individuellement ou collectivement et ayant pour but le développement du corps et de la saine compétitivité.'),
(11, 'Jouets', 'Objet destiné à amuser un enfant.');

-- --------------------------------------------------------

--
-- Table structure for table `Offre`
--

CREATE TABLE `Offre` (
  `IDOffre` int(11) NOT NULL,
  `Annonce` int(11) NOT NULL,
  `Utilisateur` int(11) NOT NULL,
  `Acceptation` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Offre`
--

INSERT INTO `Offre` (`IDOffre`, `Annonce`, `Utilisateur`, `Acceptation`) VALUES
(1, 9, 14, 0);

-- --------------------------------------------------------

--
-- Table structure for table `Personne`
--

CREATE TABLE `Personne` (
  `IDPersonne` varchar(64) NOT NULL COMMENT 'Email address',
  `Mot2Passe` text NOT NULL,
  `Nom` text NOT NULL,
  `Prenom` text NOT NULL,
  `Adresse` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Personne`
--

INSERT INTO `Personne` (`IDPersonne`, `Mot2Passe`, `Nom`, `Prenom`, `Adresse`) VALUES
('1@1.fr', '1@1.fr', 'ddd', 'ddd', NULL),
('2@1.fr', '2@1.fr', 'RIBEIRO', 'Catarina', 1),
('catarina.ribeiro@gmail.com', 'Ribeiro', 'catarina', 'ribeiro', 12),
('k@k.it', 'kk', 'kk', 'kk', NULL),
('test@test.fr', 'test', 'test', 'test', 14),
('yongda.lin@etu.univ-tours.fr', 'LIN', 'Yongda', 'lin', 11);

-- --------------------------------------------------------

--
-- Table structure for table `Utilisateur`
--

CREATE TABLE `Utilisateur` (
  `IDUtilisateur` int(11) NOT NULL,
  `Personne` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Utilisateur`
--

INSERT INTO `Utilisateur` (`IDUtilisateur`, `Personne`) VALUES
(13, 'catarina.ribeiro@gmail.com'),
(14, 'test@test.fr'),
(12, 'yongda.lin@etu.univ-tours.fr');

-- --------------------------------------------------------

--
-- Table structure for table `Vendeur`
--

CREATE TABLE `Vendeur` (
  `IDVendeur` int(11) NOT NULL,
  `Personne` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Vendeur`
--

INSERT INTO `Vendeur` (`IDVendeur`, `Personne`) VALUES
(6, 'catarina.ribeiro@gmail.com'),
(7, 'test@test.fr'),
(5, 'yongda.lin@etu.univ-tours.fr');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Adresse`
--
ALTER TABLE `Adresse`
  ADD PRIMARY KEY (`IDAdresse`);

--
-- Indexes for table `Annonce`
--
ALTER TABLE `Annonce`
  ADD PRIMARY KEY (`IDAnnonce`),
  ADD KEY `Categorie` (`Categorie`),
  ADD KEY `Position` (`Position`),
  ADD KEY `Vendeur` (`Vendeur`);

--
-- Indexes for table `Categorie`
--
ALTER TABLE `Categorie`
  ADD PRIMARY KEY (`IDCategorie`);

--
-- Indexes for table `Offre`
--
ALTER TABLE `Offre`
  ADD PRIMARY KEY (`IDOffre`),
  ADD KEY `Annonce` (`Annonce`),
  ADD KEY `Utilisateur` (`Utilisateur`);

--
-- Indexes for table `Personne`
--
ALTER TABLE `Personne`
  ADD PRIMARY KEY (`IDPersonne`),
  ADD UNIQUE KEY `IDPersonne` (`IDPersonne`),
  ADD UNIQUE KEY `IDPersonne_2` (`IDPersonne`),
  ADD KEY `Adresse` (`Adresse`);

--
-- Indexes for table `Utilisateur`
--
ALTER TABLE `Utilisateur`
  ADD PRIMARY KEY (`IDUtilisateur`),
  ADD KEY `Personne` (`Personne`);

--
-- Indexes for table `Vendeur`
--
ALTER TABLE `Vendeur`
  ADD PRIMARY KEY (`IDVendeur`),
  ADD KEY `Personne` (`Personne`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Adresse`
--
ALTER TABLE `Adresse`
  MODIFY `IDAdresse` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table `Annonce`
--
ALTER TABLE `Annonce`
  MODIFY `IDAnnonce` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `Categorie`
--
ALTER TABLE `Categorie`
  MODIFY `IDCategorie` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `Offre`
--
ALTER TABLE `Offre`
  MODIFY `IDOffre` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `Utilisateur`
--
ALTER TABLE `Utilisateur`
  MODIFY `IDUtilisateur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table `Vendeur`
--
ALTER TABLE `Vendeur`
  MODIFY `IDVendeur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `Annonce`
--
ALTER TABLE `Annonce`
  ADD CONSTRAINT `ctAdres2` FOREIGN KEY (`Position`) REFERENCES `Adresse` (`IDAdresse`),
  ADD CONSTRAINT `ctCate` FOREIGN KEY (`Categorie`) REFERENCES `Categorie` (`IDCategorie`),
  ADD CONSTRAINT `ctOffreVend` FOREIGN KEY (`Vendeur`) REFERENCES `Vendeur` (`IDVendeur`);

--
-- Constraints for table `Offre`
--
ALTER TABLE `Offre`
  ADD CONSTRAINT `ctAnno` FOREIGN KEY (`Annonce`) REFERENCES `Annonce` (`IDAnnonce`),
  ADD CONSTRAINT `ctUtil` FOREIGN KEY (`Utilisateur`) REFERENCES `Utilisateur` (`IDUtilisateur`);

--
-- Constraints for table `Personne`
--
ALTER TABLE `Personne`
  ADD CONSTRAINT `ctAdre` FOREIGN KEY (`Adresse`) REFERENCES `Adresse` (`IDAdresse`);

--
-- Constraints for table `Utilisateur`
--
ALTER TABLE `Utilisateur`
  ADD CONSTRAINT `ctPers` FOREIGN KEY (`Personne`) REFERENCES `Personne` (`IDPersonne`);

--
-- Constraints for table `Vendeur`
--
ALTER TABLE `Vendeur`
  ADD CONSTRAINT `ctPers2` FOREIGN KEY (`Personne`) REFERENCES `Personne` (`IDPersonne`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
