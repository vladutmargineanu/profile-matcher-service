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
                          CONSTRAINT `FK_INVENTORY_1` FOREIGN KEY (`INVENTORY`) REFERENCES `INVENTORY` (`ID_INVENTORY`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;