/*
SQLyog Community
MySQL - 10.4.25-MariaDB : Database - stocks
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`stocks` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `stocks`;

/*Table structure for table `complaint` */

DROP TABLE IF EXISTS `complaint`;

CREATE TABLE `complaint` (
  `complaint_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `complaint` varchar(300) DEFAULT NULL,
  `reply` varchar(300) DEFAULT NULL,
  `date` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`complaint_id`)
) ENGINE=MyISAM AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;

/*Data for the table `complaint` */

insert  into `complaint`(`complaint_id`,`user_id`,`complaint`,`reply`,`date`) values 
(28,21,'ghgf','pending','2024-01-20'),
(27,21,'hhhhee','pending','2024-01-20'),
(26,21,'ghghgg','pending','2024-01-15'),
(25,21,'','pending','2024-01-15'),
(24,21,'xdff','pending','2024-01-12'),
(23,20,'','pending','2023-12-28'),
(22,20,'yyy','pending','2023-12-28'),
(21,20,'hdhdj','pending','2023-12-28'),
(20,20,'mmmm','pending','2023-12-26'),
(19,20,'kkkk','pending','2023-12-26'),
(18,20,'ddddd','pending','2023-12-26'),
(17,20,'fffff','pending','2023-12-26'),
(16,20,'jammoooo','pending','2023-12-26');

/*Table structure for table `doubt` */

DROP TABLE IF EXISTS `doubt`;

CREATE TABLE `doubt` (
  `doubt_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `expert_id` int(11) DEFAULT NULL,
  `doubt` varchar(300) DEFAULT NULL,
  `reply` varchar(300) DEFAULT NULL,
  `date` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`doubt_id`)
) ENGINE=MyISAM AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;

/*Data for the table `doubt` */

insert  into `doubt`(`doubt_id`,`user_id`,`expert_id`,`doubt`,`reply`,`date`) values 
(1,1,1,'i have a query','i am looking','2023-12-16 09:18:12'),
(2,1,1,'can  i call you','sure','2023-12-16 09:20:31'),
(3,4,4,'i have a query','pending','2023-12-18 15:13:12'),
(4,10,10,'can  i call you','sure','2023-12-19 08:47:02'),
(5,11,10,'i want to know your opinion','sure','2023-12-19 08:51:22'),
(6,11,10,'i have a query','pending','2023-12-19 09:44:33'),
(7,11,10,'i want to know your opinion','pending','2023-12-19 09:46:43'),
(8,20,7,'Name','pending','2023-12-23'),
(10,20,10,'Namehaaaaaji','pending','2023-12-27'),
(28,21,10,'fghhuuuuuu','pending','2024-01-20'),
(27,21,10,'hhhh','pending','2024-01-15'),
(26,21,10,'heee','pending','2023-12-28'),
(25,20,10,'hhhhh','pending','2023-12-28'),
(24,20,10,'hlooo','pending','2023-12-27');

/*Table structure for table `expert` */

DROP TABLE IF EXISTS `expert`;

CREATE TABLE `expert` (
  `expert_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `firstname` varchar(300) DEFAULT NULL,
  `lastname` varchar(300) DEFAULT NULL,
  `place` varchar(300) DEFAULT NULL,
  `post` varchar(1000) DEFAULT NULL,
  `pin` varchar(300) DEFAULT NULL,
  `phone` varchar(300) DEFAULT NULL,
  `email` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`expert_id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

/*Data for the table `expert` */

insert  into `expert`(`expert_id`,`login_id`,`firstname`,`lastname`,`place`,`post`,`pin`,`phone`,`email`) values 
(10,3,'ashna','ps','idukki','cat.png','675858','9090089874','ashna12@yahoo.co.in');

/*Table structure for table `marketprice` */

DROP TABLE IF EXISTS `marketprice`;

CREATE TABLE `marketprice` (
  `marketprice_id` int(11) NOT NULL AUTO_INCREMENT,
  `open` varchar(100) DEFAULT NULL,
  `close` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`marketprice_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `marketprice` */

/*Table structure for table `notification` */

DROP TABLE IF EXISTS `notification`;

CREATE TABLE `notification` (
  `notification_id` int(11) NOT NULL AUTO_INCREMENT,
  `expert_id` int(11) DEFAULT NULL,
  `notification` varchar(300) DEFAULT NULL,
  `datetime` varchar(300) DEFAULT NULL,
  `utype` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`notification_id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

/*Data for the table `notification` */

insert  into `notification`(`notification_id`,`expert_id`,`notification`,`datetime`,`utype`) values 
(11,10,'please respond','2023-12-20T09:39','average'),
(3,4,'i have  doubts','2023-12-22T17:41','below'),
(9,9,'i have doubts','2023-12-19','high'),
(13,10,'asdrftg','2024-02-18','good'),
(14,10,'ddddd','good','2024-02-18'),
(15,10,'srfghjk','average','2024-02-18');

/*Table structure for table `predicts` */

DROP TABLE IF EXISTS `predicts`;

CREATE TABLE `predicts` (
  `predict_id` int(11) NOT NULL AUTO_INCREMENT,
  `open` varchar(50) DEFAULT NULL,
  `high` varchar(50) DEFAULT NULL,
  `low` varchar(50) DEFAULT NULL,
  `close` varchar(50) DEFAULT NULL,
  `out` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`predict_id`)
) ENGINE=InnoDB AUTO_INCREMENT=173 DEFAULT CHARSET=utf8mb4;

/*Data for the table `predicts` */

insert  into `predicts`(`predict_id`,`open`,`high`,`low`,`close`,`out`) values 
(1,'108','112','105','110','8404903.0'),
(2,'108','112','105','110','8404903.0'),
(3,'108','112','105','110','8404903.0'),
(4,'108','112','105','110','8404903.0'),
(5,'108','112','105','110','8404903.0'),
(6,'108','112','105','110','8404903.0'),
(7,'108','112','105','110','8404903.0'),
(8,'108','112','105','110','8404903.0'),
(9,'108','112','105','110','8404903.0'),
(10,'108','112','105','110','8404903.0'),
(11,'108','112','105','110','8404903.0'),
(12,'108','112','105','110','8404903.0'),
(13,'108','112','105','110','8404903.0'),
(14,'108','112','105','110','8404903.0'),
(15,'108','112','105','110','8404903.0'),
(16,'108','112','105','110','8404903.0'),
(17,'108','112','105','110','8404903.0'),
(18,'108','112','105','110','8404903.0'),
(19,'108','112','105','110','8404903.0'),
(20,'108','112','105','110','8404903.0'),
(21,'108','112','105','110','8404903.0'),
(22,'108','112','105','110','8404903.0'),
(23,'108','112','105','110','8404903.0'),
(24,'108','112','105','110','8404903.0'),
(25,'108','112','105','110','8404903.0'),
(26,'108','112','105','110','8404903.0'),
(27,'108','112','105','110','8404903.0'),
(28,'108','112','105','110','8404903.0'),
(29,'108','112','105','110','8404903.0'),
(30,'108','112','105','110','8404903.0'),
(31,'108','112','105','110','8404903.0'),
(32,'108','112','105','110','8404903.0'),
(33,'108','112','105','110','8404903.0'),
(34,'108','112','105','110','8404903.0'),
(35,'108','112','105','110','8404903.0'),
(36,'108','112','105','110','8404903.0'),
(37,'108','112','105','110','8404903.0'),
(38,'108','112','105','110','8404903.0'),
(39,'108','112','105','110','8404903.0'),
(40,'108','112','105','110','8404903.0'),
(41,'108','112','105','110','8404903.0'),
(42,'108','112','105','110','8404903.0'),
(43,'108','112','105','110','8404903.0'),
(44,'108','112','105','110','8404903.0'),
(45,'108','112','105','110','8404903.0'),
(46,'108','112','105','110','8404903.0'),
(47,'108','112','105','110','8404903.0'),
(48,'108','112','105','110','8404903.0'),
(49,'108','112','105','110','8404903.0'),
(50,'108','112','105','110','8404903.0'),
(51,'108','112','105','110','8404903.0'),
(52,'108','112','105','110','8404903.0'),
(53,'108','112','105','110','8404903.0'),
(54,'108','112','105','110','8404903.0'),
(55,'108','112','105','110','8404903.0'),
(56,'108','112','105','110','8404903.0'),
(57,'108','112','105','110','8404903.0'),
(58,'108','112','105','110','8404903.0'),
(59,'108','112','105','110','8404903.0'),
(60,'108','112','105','110','8404903.0'),
(61,'108','112','105','110','8404903.0'),
(62,'108','112','105','110','8404903.0'),
(63,'108','112','105','110','8404903.0'),
(64,'108','112','105','110','8404903.0'),
(65,'108','112','105','110','8404903.0'),
(66,'108','112','105','110','8404903.0'),
(67,'108','112','105','110','8404903.0'),
(68,'108','112','105','110','8404903.0'),
(69,'108','112','105','110','8404903.0'),
(70,'108','112','105','110','8404903.0'),
(71,'108','112','105','110','8404903.0'),
(72,'108','112','105','110','8404903.0'),
(73,'108','112','105','110','8404903.0'),
(74,'108','112','105','110','8404903.0'),
(75,'108','112','105','110','8404903.0'),
(76,'108','112','105','110','8404903.0'),
(77,'108','112','105','110','8404903.0'),
(78,'108','112','105','110','8404903.0'),
(79,'108','112','105','110','8404903.0'),
(80,'108','112','105','110','8404903.0'),
(81,'108','112','105','110','8404903.0'),
(82,'108','112','105','110','8404903.0'),
(83,'108','112','105','110','8404903.0'),
(84,'108','112','105','110','8404903.0'),
(85,'108','112','105','110','8404903.0'),
(86,'108','112','105','110','8404903.0'),
(87,'108','112','105','110','8404903.0'),
(88,'108','112','105','110','8404903.0'),
(89,'108','112','105','110','8404903.0'),
(90,'108','112','105','110','8404903.0'),
(91,'108','112','105','110','8404903.0'),
(92,'108','112','105','110','8404903.0'),
(93,'108','112','105','110','8404903.0'),
(94,'108','112','105','110','8404903.0'),
(95,'108','112','105','110','8404903.0'),
(96,'108','112','105','110','8404903.0'),
(97,'108','112','105','110','8404903.0'),
(98,'108','112','105','110','8404903.0'),
(99,'108','112','105','110','8404903.0'),
(100,'108','112','105','110','8404903.0'),
(101,'108','112','105','110','8404903.0'),
(102,'108','112','105','110','8404903.0'),
(103,'108','112','105','110','8404903.0'),
(104,'108','112','105','110','8404903.0'),
(105,'108','112','105','110','8404903.0'),
(106,'108','112','105','110','8404903.0'),
(107,'108','112','105','110','8404903.0'),
(108,'108','112','105','110','8404903.0'),
(109,'108','112','105','110','8404903.0'),
(110,'108','112','105','110','8404903.0'),
(111,'108','112','105','110','8404903.0'),
(112,'108','112','105','110','8404903.0'),
(113,'108','112','105','110','8404903.0'),
(114,'108','112','105','110','8404903.0'),
(115,'108','112','105','110','8404903.0'),
(116,'108','112','105','110','8404903.0'),
(117,'108','112','105','110','8404903.0'),
(118,'108','112','105','110','8404903.0'),
(119,'108','112','105','110','8404903.0'),
(120,'108','112','105','110','8404903.0'),
(121,'108','112','105','110','8404903.0'),
(122,'108','112','105','110','8404903.0'),
(123,'108','112','105','110','8404903.0'),
(124,'108','112','105','110','8404903.0'),
(125,'108','112','105','110','8404903.0'),
(126,'108','112','105','110','8404903.0'),
(127,'108','112','105','110','8404903.0'),
(128,'108','112','105','110','8404903.0'),
(129,'108','112','105','110','8404903.0'),
(130,'108','112','105','110','8404903.0'),
(131,'108','112','105','110','8404903.0'),
(132,'108','112','105','110','8404903.0'),
(133,'108','112','105','110','8404903.0'),
(134,'108','112','105','110','8404903.0'),
(135,'108','112','105','110','8404903.0'),
(136,'108','112','105','110','8404903.0'),
(137,'108','112','105','110','8404903.0'),
(138,'108','112','105','110','8404903.0'),
(139,'108','112','105','110','8404903.0'),
(140,'108','112','105','110','8404903.0'),
(141,'108','112','105','110','8404903.0'),
(142,'108','112','105','110','8404903.0'),
(143,'108','112','105','110','8404903.0'),
(144,'108','112','105','110','8404903.0'),
(145,'108','112','105','110','8404903.0'),
(146,'108','112','105','110','8404903.0'),
(147,'108','112','105','110','8404903.0'),
(148,'108','112','105','110','8404903.0'),
(149,'108','112','105','110','8404903.0'),
(150,'108','112','105','110','8404903.0'),
(151,'108','112','105','110','8404903.0'),
(152,'108','112','105','110','8404903.0'),
(153,'108','112','105','110','8404903.0'),
(154,'108','112','105','110','8404903.0'),
(155,'108','112','105','110','8404903.0'),
(156,'108','112','105','110','8404903.0'),
(157,'108','112','105','110','8404903.0'),
(158,'108','112','105','110','8404903.0'),
(159,'108','112','105','110','8404903.0'),
(160,'108','112','105','110','8404903.0'),
(161,'108','112','105','110','8404903.0'),
(162,'108','112','105','110','8404903.0'),
(163,'108','112','105','110','8404903.0'),
(164,'108','112','105','110','8404903.0'),
(165,'100','100','100','100','2576786.0'),
(166,'100','100','100','100','2576786.0'),
(167,'100','100','100','100','2576786.0'),
(168,'100','200','130','150','8811475.0'),
(169,'100','200','130','150','8811475.0'),
(170,'100','200','130','150','8811475.0'),
(171,'100','200','130','150','8811475.0'),
(172,'100','200','130','150','8811475.0');

/*Table structure for table `rating` */

DROP TABLE IF EXISTS `rating`;

CREATE TABLE `rating` (
  `rating_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `expert_id` int(11) DEFAULT NULL,
  `rating` varchar(300) DEFAULT NULL,
  `review` varchar(300) DEFAULT NULL,
  `date` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`rating_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `rating` */

insert  into `rating`(`rating_id`,`user_id`,`expert_id`,`rating`,`review`,`date`) values 
(1,1,1,'5','superb','2023-12-16 17:06:58'),
(2,4,1,'3','superb','2023-12-18 14:39:38'),
(3,4,4,'3','superb','2023-12-18 15:14:14'),
(4,10,4,'1','worst','2023-12-18 15:48:46'),
(5,10,4,'1','worst','2023-12-18 15:57:13'),
(6,10,7,'4','superb','2023-12-18 16:09:32'),
(7,11,10,'4','superb','2023-12-19 08:51:12'),
(8,11,10,'5','superb','2023-12-19 09:46:52'),
(9,20,10,'0.0','','2023-12-27 20:34:37'),
(10,21,10,'3.5','jj','2024-01-15 15:17:11');

/*Table structure for table `slogin` */

DROP TABLE IF EXISTS `slogin`;

CREATE TABLE `slogin` (
  `login_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(300) DEFAULT NULL,
  `password` varchar(300) DEFAULT NULL,
  `usertype` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=MyISAM AUTO_INCREMENT=50 DEFAULT CHARSET=latin1;

/*Data for the table `slogin` */

insert  into `slogin`(`login_id`,`username`,`password`,`usertype`) values 
(2,'admin','Admin123456','admin'),
(3,'mahesh','Mahesh12356','expert'),
(49,'Amal3456','Amal@@3457','user'),
(48,'admintfg','Admain@3456','user'),
(47,'admintfg','Admain@3456','user'),
(46,'admintfg','Admain@3456','user'),
(45,'admintfg','Admain@3456','user'),
(44,'Arun345','Arun@12345','user'),
(43,'Arun345','Arun@12345','user'),
(42,'Arun345','Arun@12345','user'),
(41,'asccs','asss','user'),
(40,'asccs','asss','user'),
(25,'jojo','Jojo123456','expert'),
(39,'asccs','asss','user'),
(38,'asccs','asss','user'),
(37,'vddd','xvdd','user'),
(36,'vddd','xvdd','user'),
(35,'','','user'),
(34,'u','u','user'),
(33,'a','a','user');

/*Table structure for table `stockdetails` */

DROP TABLE IF EXISTS `stockdetails`;

CREATE TABLE `stockdetails` (
  `stocks_id` int(11) NOT NULL AUTO_INCREMENT,
  `open` varchar(50) DEFAULT NULL,
  `high` varchar(50) DEFAULT NULL,
  `low` varchar(50) DEFAULT NULL,
  `close` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`stocks_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `stockdetails` */

insert  into `stockdetails`(`stocks_id`,`open`,`high`,`low`,`close`) values 
(1,'100','100','100','100'),
(2,'100','200','130','150');

/*Table structure for table `tips` */

DROP TABLE IF EXISTS `tips`;

CREATE TABLE `tips` (
  `tips_id` int(11) NOT NULL AUTO_INCREMENT,
  `expert_id` int(11) DEFAULT NULL,
  `tips` varchar(1000) DEFAULT NULL,
  `date` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`tips_id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

/*Data for the table `tips` */

insert  into `tips`(`tips_id`,`expert_id`,`tips`,`date`) values 
(11,10,'invest according to plans','2023-12-20T08:43:38');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `firstname` varchar(100) DEFAULT NULL,
  `lastname` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `post` varchar(100) DEFAULT NULL,
  `pin` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `utype` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`user_id`,`login_id`,`firstname`,`lastname`,`place`,`post`,`pin`,`phone`,`email`,`utype`) values 
(12,25,'jojo','s','Kerala','cat.png','655789','9876768789','joj89@gmail.com','good'),
(20,35,'arun','arun ','9061442264','Kottayam','pala','686578','arun7@gmail.com','good'),
(21,34,'Amala','amala','9061442264','Kottayam','kollam','686578','arunps487@gmail.com','good'),
(23,33,'vsvs','svsv','9061442264','vavz','vavsvz','gsgs','czczv@gamil.com','average'),
(24,49,'fff','ggg','1234567890','ffcc','vhv','123456','arunps7@gmail.com','low');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
