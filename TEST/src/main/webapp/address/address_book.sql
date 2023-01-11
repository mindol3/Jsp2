CREATE TABLE `address_book` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `name` varchar(15) NOT NULL,
 `email` varchar(50) DEFAULT NULL,
 `comdept` varchar(20) NOT NULL,
 `birth` varchar(10) NOY NULL,
 `tel` varchar(20) DEFAULT NULL,
 `memo` varchar(100) DEFAULT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;