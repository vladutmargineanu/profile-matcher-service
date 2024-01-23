--
-- Table structure for table `CLAN`
--

DROP TABLE IF EXISTS `CLAN`;
CREATE TABLE `CLAN` (
                          `ID_CLAN` BIGINT NOT NULL AUTO_INCREMENT,
                          `NAME` VARCHAR(255) NOT NULL,
                          `START_DATE` TIMESTAMP(6),
                          `END_DATE` TIMESTAMP(6),

                          PRIMARY KEY (`ID_CLAN`),
                          UNIQUE KEY `NAME_UNIQUE` (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;