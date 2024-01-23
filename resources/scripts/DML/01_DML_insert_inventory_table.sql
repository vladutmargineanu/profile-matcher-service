--
-- Dumping data for `INVENTORY` table
--

INSERT INTO
    `matcherapplication`.`inventory` (
    `ID_INVENTORY`,
    `CASH`,
    `COINS`,
    `START_DATE`,
    `END_DATE`
)
VALUES
    (
        (
            SELECT
                    coalesce(MAX(`I`.`ID_INVENTORY`), 0) + 1
            FROM
                `matcherapplication`.`inventory` `I`
        ),
        123.00, -- CASH
        123.00, -- COINS
        CURRENT_TIMESTAMP,
        NULL
    );

INSERT INTO
    `matcherapplication`.`inventory` (
    `ID_INVENTORY`,
    `CASH`,
    `COINS`,
    `START_DATE`,
    `END_DATE`
)
VALUES
    (
        (
            SELECT
                    coalesce(MAX(`I`.`ID_INVENTORY`), 0) + 1
            FROM
                `matcherapplication`.`inventory` `I`
        ),
        321.00, -- CASH
        321.00, -- COINS
        CURRENT_TIMESTAMP,
        NULL
    );