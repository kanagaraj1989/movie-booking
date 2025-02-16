CREATE TABLE IF NOT EXISTS cinema (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    address_line1 VARCHAR(255),
    address_line2 VARCHAR(255),
    city_id UUID NOT NULL,
    pin_code VARCHAR(20),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_cinema_city FOREIGN KEY (city_id) REFERENCES city(id)
);

CREATE INDEX IF NOT EXISTS idx_cinema_name ON cinema (name);
CREATE INDEX IF NOT EXISTS idx_cinema_city_id ON cinema (city_id);
