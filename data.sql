-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: dodhev.com    Database: jhldxtce_practica
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.24-MariaDB-cll-lve

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `act_actores`
--

LOCK TABLES `act_actores` WRITE;
/*!40000 ALTER TABLE `act_actores` DISABLE KEYS */;
INSERT INTO `act_actores` VALUES (9,'Adam Driver'),(15,'Chris Evans'),(8,'Daisy Ridley'),(27,'Dan Stevens'),(21,'Daniel Radcliffe'),(19,'Dwayne Johnson'),(22,'Emma Watson'),(24,'Idina Menzel'),(12,'Jeff Goldblum'),(26,'Josh Gad'),(6,'Kate Winslet'),(7,'Kathy Bates'),(25,'Kristen Bell'),(11,'Laura Dern'),(5,'Leonardo DiCaprio'),(28,'Luke Evans'),(10,'Mark Hamill'),(20,'Mark Ruffalo'),(2,'Michelle Rodriguez'),(17,'Paul Walker'),(23,'Ralph Fiennes'),(16,'Robert Downey Jr'),(13,'Sam Neill'),(1,'Sam Worthington'),(14,'Scarlett Johansson'),(4,'Stephen Lang'),(18,'Vin Diesel'),(3,'Zoe Saldaña');
/*!40000 ALTER TABLE `act_actores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `canc_canciones`
--

LOCK TABLES `canc_canciones` WRITE;
/*!40000 ALTER TABLE `canc_canciones` DISABLE KEYS */;
INSERT INTO `canc_canciones` VALUES (1,'Wanna Be Startin’ Somethin’',0,2),(2,'Thriller',1,2),(3,'Who Made Who',0,3),(4,'Thunderstruck',1,3),(5,'Time',0,4),(6,'Run Like Hell',1,4),(7,'I Will Always Love You',0,5),(8,'When You Believe',1,5),(9,'Good Times Bad Times',0,6),(10,'You Shook Me',1,6),(11,'Wild Honey Pie',0,7),(12,'Martha My Dear',1,7),(13,'My Heart Will Go On',0,8),(14,'I\'m Alive',1,8),(15,'Lucky Star',0,9),(16,'Borderline',1,9);
/*!40000 ALTER TABLE `canc_canciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `con_contenido`
--

LOCK TABLES `con_contenido` WRITE;
/*!40000 ALTER TABLE `con_contenido` DISABLE KEYS */;
INSERT INTO `con_contenido` VALUES (6,'Avatar','111','avatar.jpg','2017-05-05 00:00:00',20),(7,'Titanic','112','titanic.jpg','2017-06-06 00:00:00',120),(8,'Star Wars: el despertar de la fuerza','113','starwars.jpg','2017-07-07 00:00:00',50),(9,'Parque Jurasico','114','parquejurasico.jpg','2017-08-08 00:00:00',122),(10,'Los Vengadores','115','vengadores.jpg','2017-09-09 00:00:00',76),(11,'Rápidos y furiosos 7','116','rapidosyfuriosos.jpg','2017-10-10 00:00:00',43),(12,'Vengadores: la era de Ultrón','117','vengadoresultron.jpg','2017-11-11 00:00:00',65),(13,'Harry Potter y las reliquias de la muerte','118','harrypotter.jpg','2017-10-28 00:00:00',23),(14,'Frozen','119','frozen.jpg','2017-06-14 00:00:00',212),(15,'La Bella y la Bestia','120','bellaybestia.jpg','2017-07-25 00:00:00',43),(16,'Los pilares de la tierra','200','pilarestierra.jpg','2017-07-25 00:00:00',123),(17,'La biblia','2001','biblia.jpg','2017-07-26 00:00:00',521),(18,'El jugador','202','eljugador.jpg','2017-07-27 00:00:00',125),(19,'Historias de Terramar','203','historiasterramar.jpg','2017-07-28 00:00:00',421),(20,'Memorias de Idhún','204','memoriasidhun.jpg','2017-07-29 00:00:00',321),(21,'El nombre del viento','205','nombreviento.jpg','2017-07-30 00:00:00',124),(22,'El talento de Mr. Ripley','206','talentomrripley.jpg','2017-08-02 00:00:00',872),(23,'Las penas del joven Werther','207','werther.jpg','2017-08-02 00:00:00',521),(24,'Don Quijote','208','donquijote.jpg','2017-08-03 00:00:00',212),(25,'Thriller','301','thriller.jpg','2017-09-01 00:00:00',421),(26,'AC/DC Live','302','acdc.jpg','2017-09-02 00:00:00',231),(27,'Pink Floyd','303','pinkfloyd.jpg','2017-09-03 00:00:00',452),(28,'Whitney Houston','304','whitney.jpg','2017-09-04 00:00:00',122),(29,'Led Zeppelin','305','led.jpg','2017-09-05 00:00:00',231),(30,'The Beatles','306','beatles.jpg','2017-09-06 00:00:00',245),(31,'Céline Dion','307','dion.jpg','2017-09-07 00:00:00',278),(32,'Madonna','308','madonna.jpg','2017-09-08 00:00:00',726);
/*!40000 ALTER TABLE `con_contenido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `dir_directores`
--

LOCK TABLES `dir_directores` WRITE;
/*!40000 ALTER TABLE `dir_directores` DISABLE KEYS */;
INSERT INTO `dir_directores` VALUES (2,'Aitor'),(1,'Aitor Tilla'),(4,'Bill Condon'),(5,'Chris Buck'),(6,'David Yates'),(10,'J. J. Abrams'),(3,'James Cameron'),(8,'James Wan'),(7,'Joss Whedon'),(9,'Steven Spielberg');
/*!40000 ALTER TABLE `dir_directores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `disc_discografica`
--

LOCK TABLES `disc_discografica` WRITE;
/*!40000 ALTER TABLE `disc_discografica` DISABLE KEYS */;
INSERT INTO `disc_discografica` VALUES (3,'EMI'),(6,'funciona'),(4,'Sony Music Entertainment'),(5,'Universal Music Group'),(2,'Warner Music Group');
/*!40000 ALTER TABLE `disc_discografica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `lib_libro`
--

LOCK TABLES `lib_libro` WRITE;
/*!40000 ALTER TABLE `lib_libro` DISABLE KEYS */;
INSERT INTO `lib_libro` VALUES (2,16,500,'pilarestierra.txt'),(3,17,600,'biblia.txt'),(4,18,550,'eljugador.txt'),(5,19,450,'historiasterramar.txt'),(6,20,470,'memoriasidhun.txt'),(7,21,520,'nombreviento.txt'),(8,22,510,'talentomrripley.txt'),(9,23,430,'werther.txt'),(10,24,1020,'donquijote.txt');
/*!40000 ALTER TABLE `lib_libro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `mus_musica`
--

LOCK TABLES `mus_musica` WRITE;
/*!40000 ALTER TABLE `mus_musica` DISABLE KEYS */;
INSERT INTO `mus_musica` VALUES (3,26,2),(9,32,2),(4,27,3),(8,31,3),(10,25,3),(2,25,4),(6,29,4),(5,28,5),(7,30,5);
/*!40000 ALTER TABLE `mus_musica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `pel_pelicula`
--

LOCK TABLES `pel_pelicula` WRITE;
/*!40000 ALTER TABLE `pel_pelicula` DISABLE KEYS */;
INSERT INTO `pel_pelicula` VALUES (1,6,1,3),(2,7,1,3),(3,8,3,10),(4,9,5,9),(5,10,6,7),(6,11,5,8),(7,12,6,7),(8,13,7,6),(9,14,4,5),(10,15,4,4);
/*!40000 ALTER TABLE `pel_pelicula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `pelact_pelicula_actores`
--

LOCK TABLES `pelact_pelicula_actores` WRITE;
/*!40000 ALTER TABLE `pelact_pelicula_actores` DISABLE KEYS */;
INSERT INTO `pelact_pelicula_actores` VALUES (2,5),(2,6),(3,8),(3,9),(3,10),(4,11),(4,12),(4,13),(5,14),(5,15),(5,16),(6,17),(6,18),(6,19),(7,14),(7,16),(7,20),(8,21),(8,22),(8,23),(9,24),(9,25),(9,26),(10,22),(10,27),(10,28);
/*!40000 ALTER TABLE `pelact_pelicula_actores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `pres_prestamo`
--

LOCK TABLES `pres_prestamo` WRITE;
/*!40000 ALTER TABLE `pres_prestamo` DISABLE KEYS */;
INSERT INTO `pres_prestamo` VALUES (12,10,'2017-10-29 13:06:58');
/*!40000 ALTER TABLE `pres_prestamo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `pro_productora`
--

LOCK TABLES `pro_productora` WRITE;
/*!40000 ALTER TABLE `pro_productora` DISABLE KEYS */;
INSERT INTO `pro_productora` VALUES (1,' 20th Century Fox'),(3,'Lucasfilm'),(6,'Marvel Studios'),(2,'Paramount Pictures'),(5,'Universal Studios'),(4,'Walt Disney Pictures'),(7,'Warner Bros');
/*!40000 ALTER TABLE `pro_productora` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tipousu_tipo_usuario`
--

LOCK TABLES `tipousu_tipo_usuario` WRITE;
/*!40000 ALTER TABLE `tipousu_tipo_usuario` DISABLE KEYS */;
INSERT INTO `tipousu_tipo_usuario` VALUES (1,'Usuario'),(2,'Administrador');
/*!40000 ALTER TABLE `tipousu_tipo_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `usu_usuarios`
--

LOCK TABLES `usu_usuarios` WRITE;
/*!40000 ALTER TABLE `usu_usuarios` DISABLE KEYS */;
INSERT INTO `usu_usuarios` VALUES (7,1,'Aitor','Tilla','contrasena','asdasd@hotmail.com'),(8,2,'Pepe','username','b221d9dbb083a7f33428d7c2a3c3198ae925614d70210e28716ccaa7cd4ddb79','email'),(9,1,'gabri','garacha','5bd22a7e16e64f5099b3038e11ac266442731a0da4d0d7c2a9819d9fbc22fc8b','garacha6@hotmail.com'),(10,1,'root','root','0242c0436daa4c241ca8a793764b7dfb50c223121bb844cf49be670a3af4dd18','example@example.com'),(11,1,'hola','hola','0242c0436daa4c241ca8a793764b7dfb50c223121bb844cf49be670a3af4dd18','example2@example.com');
/*!40000 ALTER TABLE `usu_usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-29 17:57:28
