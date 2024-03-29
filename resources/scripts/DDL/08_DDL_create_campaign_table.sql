--
-- Table structure for table `CAMPAIGN`
--

DROP TABLE IF EXISTS `CAMPAIGN`;
CREATE TABLE `CAMPAIGN` (
                            `ID_CAMPAIGN` BIGINT NOT NULL AUTO_INCREMENT,
                            `GAME` VARCHAR(255) NOT NULL,
                            `NAME` VARCHAR(255) NOT NULL,
                            `PRIORITY` DECIMAL(10,2),
                            `ENABLED` BOOLEAN NOT NULL,
                            `LAST_UPDATED` TIMESTAMP(6),
                            `START_DATE` TIMESTAMP(6),
                            `END_DATE` TIMESTAMP(6),

                            PRIMARY KEY (`ID_CAMPAIGN`),
                            UNIQUE KEY `NAME_UNIQUE` (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;