--
-- Table structure for table `CAMPAIGN_PLAYER`
--

DROP TABLE IF EXISTS `CAMPAIGN_PLAYER`;
CREATE TABLE `CAMPAIGN_PLAYER` (
                            `ID_CAMPAIGN` BIGINT NOT NULL,
                            `ID_PLAYER` BINARY(16) NOT NULL,

                            PRIMARY KEY (`ID_CAMPAIGN`,`ID_PLAYER`),
                            KEY `ID_PLAYER` (`ID_PLAYER`),
                            CONSTRAINT `FK_CAMPAIGN` FOREIGN KEY (`ID_CAMPAIGN`) REFERENCES `CAMPAIGN` (`ID_CAMPAIGN`),
                            CONSTRAINT `FK_PLAYER_2` FOREIGN KEY (`ID_PLAYER`) REFERENCES `PLAYER` (`ID_PLAYER`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;