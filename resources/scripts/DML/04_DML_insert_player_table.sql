--
-- Dumping data for `PLAYER` table
--

INSERT INTO
    `matcherapplication`.`player` (
    `ID_PLAYER`,
    `CREDENTIAL`,
    `CREATED`,
    `MODIFIED`,
    `LAST_SESSION`,
    `TOTAL_SPENT`,
    `TOTAL_REFUND`,
    `TOTAL_TRANSACTIONS`,
    `LAST_PURCHASE`,
    `LEVEL`,
    `XP`,
    `TOTAL_PLAYTIME`,
    `COUNTRY`,
    `LANGUAGE`,
    `BIRTHDATE`,
    `GENDER`,
    `INVENTORY`,
    `CLAN`,
    `CUSTOM_FIELD`
)
VALUES
    (
        UUID_TO_BIN('97983be2-98b7-11e7-90cf-082e5f28d836'), -- ID_PLAYER
        'apple_credential', -- CREDENTIAL
        '2021-01-10 13:37:17.000000', -- CREATED
        '2021-01-23 13:37:17.000000', -- MODIFIED
        '2021-01-23 13:37:17.000000', -- LAST_SESSION
        400.00, -- TOTAL_SPENT
        0.00, -- TOTAL_REFUND
        5, -- TOTAL_TRANSACTIONS
        '2021-01-22 13:37:17.000000', -- LAST_PURCHASE
        3, -- LEVEL
        1000, -- XP
        144.00, -- TOTAL_PLAYTIME
        'CA', -- COUNTRY
        'fr', -- LANGUAGE
        '2000-01-10 13:37:17.000000', -- BIRTHDATE
        'male', -- GENDER
        1, -- INVENTORY (assuming the ID_INVENTORY from the INVENTORY table)
        123456, -- CLAN (assuming an existing ID_CLAN from the CLAN table)
        'mycustom' -- CUSTOM_FIELD
    );

INSERT INTO
    `matcherapplication`.`player` (
    `ID_PLAYER`,
    `CREDENTIAL`,
    `CREATED`,
    `MODIFIED`,
    `LAST_SESSION`,
    `TOTAL_SPENT`,
    `TOTAL_REFUND`,
    `TOTAL_TRANSACTIONS`,
    `LAST_PURCHASE`,
    `LEVEL`,
    `XP`,
    `TOTAL_PLAYTIME`,
    `COUNTRY`,
    `LANGUAGE`,
    `BIRTHDATE`,
    `GENDER`,
    `INVENTORY`,
    `CLAN`,
    `CUSTOM_FIELD`
)
VALUES
    (
        UUID_TO_BIN('37483be2-98b7-11e7-90cf-082e5f28d836'), -- ID_PLAYER
        'samsung_credential', -- CREDENTIAL
        '2021-01-10 13:37:17.000000', -- CREATED
        '2021-01-23 13:37:17.000000', -- MODIFIED
        '2021-01-23 13:37:17.000000', -- LAST_SESSION
        450.00, -- TOTAL_SPENT
        50.00, -- TOTAL_REFUND
        7, -- TOTAL_TRANSACTIONS
        '2021-01-22 13:37:17.000000', -- LAST_PURCHASE
        8, -- LEVEL
        1000, -- XP
        144.00, -- TOTAL_PLAYTIME
        'RO', -- COUNTRY
        'ro', -- LANGUAGE
        '2000-01-10 13:37:17.000000', -- BIRTHDATE
        'female', -- GENDER
        2, -- INVENTORY (assuming the ID_INVENTORY from the INVENTORY table)
        123456, -- CLAN (assuming an existing ID_CLAN from the CLAN table)
        'mycustom2' -- CUSTOM_FIELD
    );