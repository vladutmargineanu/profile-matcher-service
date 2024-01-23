--
-- Dumping data for `CLAN` table
--

INSERT INTO
    `matcherapplication`.`clan` (
    `ID_CLAN`,
    `NAME`,
    `START_DATE`,
    `END_DATE`
)
VALUES
    (
        '123456',
        'Hello world clan', -- NAME
        CURRENT_TIMESTAMP,
        NULL
    );