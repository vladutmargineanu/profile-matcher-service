--
-- Table structure for table `CLAN`
--

DROP TABLE IF EXISTS `CLAN`;
CREATE TABLE `CLAN` (
                          `ID_CLAN` BIGINT NOT NULL AUTO_INCREMENT,
                          `NAME` VARCHAR(255) NOT NULL,
                          `PLAYER` BINARY(16) NOT NULL,
                          `START_DATE` TIMESTAMP(6),
                          `END_DATE` TIMESTAMP(6),

                          PRIMARY KEY (`ID_CLAN`),
                          UNIQUE KEY `NAME_UNIQUE` (`NAME`),
                          CONSTRAINT `FK_PLAYER_2` FOREIGN KEY (`PLAYER`) REFERENCES `PLAYER` (`ID_PLAYER`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;