INSERT INTO "user_limit" ("user_id", "limit")
SELECT generate_series(1, 100), 10000.00;