--
-- Dumping data for `ITEM` table
--

INSERT INTO
    `matcherapplication`.`item` (
    `ID_ITEM`,
    `NAME`,
    `QUANTITY`,
    `INVENTORY`,
    `START_DATE`,
    `END_DATE`
)
VALUES
    (
        (
            SELECT
                    coalesce(MAX(`I`.`ID_ITEM`), 0) + 1
            FROM
                `matcherapplication`.`item` `I`
        ),
        'item_1', -- NAME
        1, -- QUANTITY
        1, -- INVENTORY (assuming an existing ID_INVENTORY from the INVENTORY table)
        CURRENT_TIMESTAMP,
        NULL
    );

INSERT INTO
    `matcherapplication`.`item` (
    `ID_ITEM`,
    `NAME`,
    `QUANTITY`,
    `INVENTORY`,
    `START_DATE`,
    `END_DATE`
)
VALUES
    (
        (
            SELECT
                    coalesce(MAX(`I`.`ID_ITEM`), 0) + 1
            FROM
                `matcherapplication`.`item` `I`
        ),
        'item_34', -- NAME
        3, -- QUANTITY
        1, -- INVENTORY (assuming an existing ID_INVENTORY from the INVENTORY table)
        CURRENT_TIMESTAMP,
        NULL
    );

INSERT INTO
    `matcherapplication`.`item` (
    `ID_ITEM`,
    `NAME`,
    `QUANTITY`,
    `INVENTORY`,
    `START_DATE`,
    `END_DATE`
)
VALUES
    (
        (
            SELECT
                    coalesce(MAX(`I`.`ID_ITEM`), 0) + 1
            FROM
                `matcherapplication`.`item` `I`
        ),
        'item_55', -- NAME
        2, -- QUANTITY
        1, -- INVENTORY (assuming an existing ID_INVENTORY from the INVENTORY table)
        CURRENT_TIMESTAMP,
        NULL
    );