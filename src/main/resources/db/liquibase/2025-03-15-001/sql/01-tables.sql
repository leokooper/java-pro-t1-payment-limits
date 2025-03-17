CREATE TABLE IF NOT EXISTS "user_limit"
(
    "id"      BIGSERIAL PRIMARY KEY,
    "limit"   NUMERIC(10, 2) NOT NULL,
    "user_id" INT            NOT NULL
);

CREATE TABLE IF NOT EXISTS "limit_transaction"
(
    "id"      BIGSERIAL PRIMARY KEY,
    "amount"  NUMERIC(10, 2) NOT NULL,
    "user_id" INT            NOT NULL
);