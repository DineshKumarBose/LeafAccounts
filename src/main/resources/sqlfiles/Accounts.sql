CREATE TABLE `LeafUsers` (
  `lid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `enabled` int(1) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  PRIMARY KEY (`lid`)
) ENGINE=InnoDB AUTO_INCREMENT=1000000 DEFAULT CHARSET=latin1;

CREATE TABLE `UserRoles` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `lid` int(11) NOT NULL,
  `rolename` varchar(100) NOT NULL,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `uni_userid_role` (`rolename`,`lid`),
  KEY `fk_lid_idx` (`lid`),
  CONSTRAINT `fk_lid` FOREIGN KEY (`lid`) REFERENCES `LeafUsers` (`lid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;