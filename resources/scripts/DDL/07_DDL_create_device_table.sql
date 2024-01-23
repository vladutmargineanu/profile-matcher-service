--
-- Table structure for table `DEVICE`
--

DROP TABLE IF EXISTS `DEVICE`;
CREATE TABLE `DEVICE` (
                        `ID_DEVICE` BIGINT NOT NULL AUTO_INCREMENT,
                        `PLAYER` BINARY(16) NOT NULL,
                        `MODEL` VARCHAR(100) NOT NULL,
                        `CARRIER` VARCHAR(100) NOT NULL,
                        `FIRMWARE` VARCHAR(100) NOT NULL,
                        `START_DATE` TIMESTAMP(6),
                        `END_DATE` TIMESTAMP(6),

                        PRIMARY KEY (`ID_DEVICE`),
                        CONSTRAINT `FK_PLAYER` FOREIGN KEY (`PLAYER`) REFERENCES `PLAYER` (`ID_PLAYER`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;