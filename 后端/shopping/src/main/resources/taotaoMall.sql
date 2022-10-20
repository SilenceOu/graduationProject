/*
SQLyog Ultimate v11.27 (32 bit)
MySQL - 5.5.40 : Database - store
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`store` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `store`;

/*Table structure for table `address` */

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `detailAddress` varchar(50) DEFAULT NULL,
  `userId` int(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6007 DEFAULT CHARSET=utf8;

/*Data for the table `address` */

insert  into `address`(`id`,`name`,`phone`,`city`,`detailAddress`,`userId`) values (6001,'欧振超','13543053778','广东省-珠海市-香洲区','淮海街56号',2001),(6004,'卢本伟','10086','广东省-湛江市-吴川市','博铺镇',2008),(6005,'ozc','13543053778','广东省-中山市-沙溪镇','临海街88号',2001),(6006,'gffdg','5565','湖北省-黄石市-黄石港区','fdgfdg',2001);

/*Table structure for table `cart` */

DROP TABLE IF EXISTS `cart`;

CREATE TABLE `cart` (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `commodityId` int(30) DEFAULT NULL,
  `amount` int(30) DEFAULT NULL,
  `userId` int(30) DEFAULT NULL,
  `number` int(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=4012 DEFAULT CHARSET=utf8;

/*Data for the table `cart` */

/*Table structure for table `commodity` */

DROP TABLE IF EXISTS `commodity`;

CREATE TABLE `commodity` (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  `content` varchar(9999) DEFAULT NULL,
  `money` int(30) DEFAULT NULL,
  `number` int(30) DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3011 DEFAULT CHARSET=utf8;

/*Data for the table `commodity` */

insert  into `commodity`(`id`,`name`,`image`,`content`,`money`,`number`,`status`) values (3001,'不可思议事件簿','http://localhost:3001/img/z0TdWozJHAfsyuGLrJaRRnqf.png','<p>【现货速发】不可思议事件薄全套7册 墨多多谜境冒险全集查理9世正版查理里九9世全册不可思议的事件簿墨多多秘境冒险历险记漫画书</p>',159,197,'上架'),(3002,'牧马人机械键盘','http://localhost:3001/img/nfLR4EJR3-5cu-lLJQsTPv3x.png','<p>牧马人蒸汽朋克机械键盘鼠标套装电脑台式家用外设电竞游戏键鼠茶轴青轴黑轴耳机有线无线三件套专用两件套</p>',109,68,'上架'),(3004,'安踏运动鞋','http://localhost:3001/img/H9976yZWBAD7_9SVMP26F9Io.png','<p>安踏运动鞋男鞋官网正品男士跑步鞋2021新款夏季休闲旅游透气鞋子</p>',269,40,'上架'),(3008,'洁丽雅纯棉毛巾','http://localhost:3001/img/WG6N0f5Bj1vNeLvCq5fUgd3T.png','<p>洁丽雅纯棉毛巾10条家庭装全棉洗脸不掉毛毛巾柔软吸水儿童成人</p>',38,15,'上架'),(3009,'松下吹风机','http://localhost:3001/img/dsu1n5NFDRFk7dGrdkdJHWMO.png','<p>松下吹风机家用水离子纳诺怡大功率智能冷热风交替电吹风EH-WNA8B</p><p>智能冷热交替大功率恒温速干多功能造型风嘴</p>',899,10,'上架'),(3010,'牛皮女包','http://localhost:3001/img/7X-P5hkixg7bXg5V0ig4fnoR.png','<p>牛皮女包2021新款时尚真皮包包女手提斜跨中年女包女士妈妈包百搭</p>',269,30,'上架');

/*Table structure for table `manager` */

DROP TABLE IF EXISTS `manager`;

CREATE TABLE `manager` (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `avatar` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1007 DEFAULT CHARSET=utf8;

/*Data for the table `manager` */

insert  into `manager`(`id`,`username`,`password`,`phone`,`avatar`) values (1001,'admin','123456','13609001924','http://localhost:3001/img/J1xZR-50n96gIoxqrXF8M71a.png'),(1002,'dgsfsdg','123','18344260955','http://localhost:3001/img/QcaHz9PgBfrBmb-r4CwjNzmz.png');

/*Table structure for table `salesorder` */

DROP TABLE IF EXISTS `salesorder`;

CREATE TABLE `salesorder` (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `userId` int(30) DEFAULT NULL,
  `addressId` int(30) DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL,
  `allMoney` int(30) DEFAULT NULL,
  `commodityIds` varchar(100) DEFAULT NULL,
  `number` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5032 DEFAULT CHARSET=utf8;

/*Data for the table `salesorder` */

insert  into `salesorder`(`id`,`userId`,`addressId`,`status`,`allMoney`,`commodityIds`,`number`) values (5009,2001,6001,'已收货',27107,'3001,3002,3004','2,3,2'),(5025,2001,6001,'已收货',786,'3004,3008','1,3'),(5026,2008,6004,'已收货',1110,'3004','2'),(5027,2008,6004,'等待发货',17999,'3001,3002','3,1'),(5028,2001,6005,'已发货',477,'3001','3'),(5029,2001,6001,'等待发货',269,'3004','1'),(5030,2001,6005,'等待发货',1345,'3004','5'),(5031,2001,6005,'已收货',1266,'3004,3008','4,5');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `avatar` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2009 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`phone`,`avatar`) values (2001,'ozc','123456','13543053778','http://localhost:3001/img/TXIafdduwJ7-1laDkOyaPAN9.png'),(2002,'ohc','123456','13619002924','http://localhost:3001/img/3_9am5PoOomYnWJ-Eo-a1aF9.png'),(2008,'lbw','123456','10086','http://localhost:3001/img/wGuZpctLFm1eYI9qy7K37f7J.jpeg');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
