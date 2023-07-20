CREATE DATABASE `mybatis`;

USE `mybatis`;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
                        `id` int(20) NOT NULL,
                        `name` varchar(30) DEFAULT NULL,
                        `pwd` varchar(30) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert  into `user`(`id`,`name`,`pwd`) values (1,'阿渌','123456'),(2,'张三','abcdef'),(3,'李四','987654');
