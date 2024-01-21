--
-- Table structure for table `ITEM`
--

DROP TABLE IF EXISTS `ITEM`;
CREATE TABLE `ITEM` (
                          `ID_ITEM` BIGINT NOT NULL AUTO_INCREMENT,
                          `NAME` VARCHAR(255) NOT NULL,
                          `QUANTITY` INTEGER,
                          `INVENTORY` BIGINT NOT NULL,
                          `START_DATE` TIMESTAMP(6),
                          `END_DATE` TIMESTAMP(6),

                          PRIMARY KEY (`ID_ITEM`),
                          CONSTRAINT `inventory_ibfk_1` FOREIGN KEY (`INVENTORY`) REFERENCES `INVENTORY` (`ID_INVENTORY`),
                          UNIQUE KEY `NAME_UNIQUE` (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;