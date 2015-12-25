 CREATE TABLE `OrgDetails` (
  `orgid` int(11) NOT NULL AUTO_INCREMENT,
  `orgname` varchar(100) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `state` varchar(75) DEFAULT NULL,
  `city` varchar(75) DEFAULT NULL,
  `zipcode` varchar(50) DEFAULT NULL,
  `timetype` varchar(25) DEFAULT NULL,
  `dateformat` varchar(25) DEFAULT NULL,
  `currencycode` varchar(25) DEFAULT NULL,
  `createdtime` bigint(20) NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '-1',
  `country` varchar(100) NOT NULL,
  PRIMARY KEY (`orgid`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=latin1;

CREATE TABLE `OrgUsers` (
  `luid` int(11) NOT NULL AUTO_INCREMENT,
  `lid` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `createtime` bigint(20) NOT NULL,
  PRIMARY KEY (`luid`)
) ENGINE=InnoDB AUTO_INCREMENT=1000000 DEFAULT CHARSET=latin1;

CREATE TABLE `OrgUserRoles` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `orgid` int(11) NOT NULL,
  `luid` int(11) NOT NULL,
  `rolename` varchar(100) NOT NULL,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `uni_userid_role` (`rolename`,`luid`,`orgid`),
  KEY `fk_luid_idx` (`luid`),
  CONSTRAINT `fk_luid` FOREIGN KEY (`luid`) REFERENCES `OrgUsers` (`luid`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=latin1;
