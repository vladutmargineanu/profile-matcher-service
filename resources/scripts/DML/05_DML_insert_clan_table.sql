--
-- Dumping data for `CLAN` table
--

INSERT INTO
    `matcherapplication`.`clan` (
    `ID_CLAN`,
    `NAME`,
    `PLAYER`,
    `START_DATE`,
    `END_DATE`
)
VALUES
    (
        '123456',
        'Hello world clan', -- NAME
        unhex(replace('97983be2-98b7-11e7-90cf-082e5f28d836', '-', '')), -- PLAYER (assuming an existing ID_PLAYER from the PLAYER table)
        CURRENT_TIMESTAMP,
        NULL
    );