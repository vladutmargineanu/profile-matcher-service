--
-- Table structure for table `INVENTORY`
--

DROP TABLE IF EXISTS `INVENTORY`;
CREATE TABLE `INVENTORY` (
                        `ID_INVENTORY` BIGINT NOT NULL AUTO_INCREMENT,
                        `CASH` DECIMAL(10,2),
                        `COINS` DECIMAL(10,2),
                        `START_DATE` TIMESTAMP(6),
                        `END_DATE` TIMESTAMP(6),

                        PRIMARY KEY (`ID_INVENTORY`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;