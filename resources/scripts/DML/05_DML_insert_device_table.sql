--
-- Dumping data for `DEVICE` table
--

INSERT INTO
    `matcherapplication`.`device` (
    `ID_DEVICE`,
    `PLAYER`,
    `MODEL`,
    `CARRIER`,
    `FIRMWARE`,
    `START_DATE`,
    `END_DATE`
)
VALUES
    (
        (
            SELECT
                    coalesce(MAX(`D`.`ID_DEVICE`), 0) + 1
            FROM
                `matcherapplication`.`device` `D`
        ),
        UUID_TO_BIN('97983be2-98b7-11e7-90cf-082e5f28d836'), -- PLAYER (assuming an existing ID_PLAYER from the PLAYER table)
        'apple iphone 11', -- MODEL
        'vodafone', -- CARRIER
        '123', -- FIRMWARE
        CURRENT_TIMESTAMP,
        NULL
    );

INSERT INTO
    `matcherapplication`.`device` (
    `ID_DEVICE`,
    `PLAYER`,
    `MODEL`,
    `CARRIER`,
    `FIRMWARE`,
    `START_DATE`,
    `END_DATE`
)
VALUES
    (
        (
            SELECT
                    coalesce(MAX(`D`.`ID_DEVICE`), 0) + 1
            FROM
                `matcherapplication`.`device` `D`
        ),
        UUID_TO_BIN('37483be2-98b7-11e7-90cf-082e5f28d836'), -- PLAYER (assuming an existing ID_PLAYER from the PLAYER table)
        'samsung 22 ultra', -- MODEL
        'orange', -- CARRIER
        '321', -- FIRMWARE
        CURRENT_TIMESTAMP,
        NULL
    );