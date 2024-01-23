--
-- Table structure for table `PLAYER`
--

DROP TABLE IF EXISTS `PLAYER`;
CREATE TABLE `PLAYER` (
                            `ID_PLAYER` BINARY(16) NOT NULL,
                            `CREDENTIAL` VARCHAR(255) NOT NULL,
                            `CREATED` TIMESTAMP(6) NOT NULL,
                            `MODIFIED` TIMESTAMP(6),
                            `LAST_SESSION` TIMESTAMP(6),
                            `TOTAL_SPENT` DECIMAL(10,2),
                            `TOTAL_REFUND` DECIMAL(10,2),
                            `TOTAL_TRANSACTIONS` INTEGER,
                            `LAST_PURCHASE` TIMESTAMP(6),
                            `LEVEL` INTEGER,
                            `XP` INTEGER,
                            `TOTAL_PLAYTIME` DECIMAL(10,2),
                            `COUNTRY` CHAR(2),
                            `LANGUAGE` CHAR(2),
                            `BIRTHDATE` TIMESTAMP(6),
                            `GENDER` CHAR(10),
                            `INVENTORY` BIGINT NOT NULL,
                            `CLAN` BIGINT NOT NULL,
                            `CUSTOM_FIELD` VARCHAR(255),

                            PRIMARY KEY (`ID_PLAYER`),
                            CONSTRAINT `FK_INVENTORY` FOREIGN KEY (`INVENTORY`) REFERENCES `INVENTORY` (`ID_INVENTORY`),
                            CONSTRAINT `FK_CLAN` FOREIGN KEY (`CLAN`) REFERENCES `CLAN` (`ID_CLAN`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;