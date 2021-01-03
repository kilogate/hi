DROP TABLE IF EXISTS `Authors`;
CREATE TABLE `Authors` (`Author_Id` varchar(6) DEFAULT NULL, `Name` varchar(30) DEFAULT NULL, `Fname` varchar(30) DEFAULT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO Authors VALUES ('1', '罗贯中', '明朝');
INSERT INTO Authors VALUES ('2', '曹雪芹', '清朝');
INSERT INTO Authors VALUES ('3', '施耐庵', '明朝');
INSERT INTO Authors VALUES ('4', '吴承恩', '明朝');
SELECT * FROM Authors;

DROP TABLE IF EXISTS `Books`;
CREATE TABLE `Books` (`Title` varchar(60) DEFAULT NULL, `ISBN` varchar(30) DEFAULT NULL, `Publisher_Id` varchar(6) DEFAULT NULL, `Price` int(16)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO Books VALUES ('三国演义', '0-201-98766-1', '1', 8000);
INSERT INTO Books VALUES ('红楼梦', '0-201-98766-2', '2', 9000);
INSERT INTO Books VALUES ('水浒传', '0-201-98766-3', '3', 8800);
INSERT INTO Books VALUES ('西游记', '0-201-98766-4', '2', 8900);
SELECT * FROM Books;

DROP TABLE IF EXISTS `BooksAuthors`;
CREATE TABLE `BooksAuthors` (`ISBN` varchar(30) DEFAULT NULL, `Author_Id` varchar(6) DEFAULT NULL, `Seq_No` int(16)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO BooksAuthors VALUES ('0-201-98766-1', '1', 1);
INSERT INTO BooksAuthors VALUES ('0-201-98766-2', '2', 2);
INSERT INTO BooksAuthors VALUES ('0-201-98766-3', '3', 3);
INSERT INTO BooksAuthors VALUES ('0-201-98766-4', '4', 4);
SELECT * FROM BooksAuthors;

DROP TABLE IF EXISTS `Publishers`;
CREATE TABLE `Publishers` (`Publisher_Id` varchar(6) DEFAULT NULL, `Name` varchar(30) DEFAULT NULL, `URL` varchar(80) DEFAULT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO Publishers VALUES ('1', '清华出版社', 'www.qinghua.com');
INSERT INTO Publishers VALUES ('2', '北大出版社', 'www.beida.com');
INSERT INTO Publishers VALUES ('3', '机械出版社', 'www.jixie.com');
SELECT * FROM Publishers;