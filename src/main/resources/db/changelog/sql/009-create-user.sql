CREATE TABLE IF NOT EXISTS "user" (
    id UUID PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    name VARCHAR(255),
    email VARCHAR(255) NOT NULL UNIQUE,
    mobile VARCHAR(20) NOT NULL UNIQUE,
    city_id UUID NOT NULL,
    -- Address (Embedded)
    address_line1 VARCHAR(255),
    address_line2 VARCHAR(255),
    city_name VARCHAR(100),
    state VARCHAR(100),
    country VARCHAR(100),
    postal_code VARCHAR(20),
    CONSTRAINT fk_user_city FOREIGN KEY (city_id) REFERENCES city(id)
);

CREATE INDEX IF NOT EXISTS idx_user_city_id ON "user" (city_id);
CREATE INDEX IF NOT EXISTS idx_user_email ON "user" (email);
CREATE INDEX IF NOT EXISTS idx_user_mobile ON "user" (mobile);
