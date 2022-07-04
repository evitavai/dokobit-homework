DROP TABLE IF EXISTS "statistics";

CREATE TABLE "statistics"
(
    id          BIGINT      NOT NULL GENERATED ALWAYS AS IDENTITY,
    ip_address  VARCHAR(32) NOT NULL,
    usage_count BIGINT,
    PRIMARY KEY (id, ip_address)
);