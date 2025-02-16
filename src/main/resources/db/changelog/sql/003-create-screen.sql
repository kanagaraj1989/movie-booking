CREATE TABLE IF NOT EXISTS screen (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    number_of_seats INT NOT NULL,
    status VARCHAR(50) NOT NULL,
    cinema_id UUID NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_screen_cinema FOREIGN KEY (cinema_id) REFERENCES cinema(id)
);

CREATE INDEX IF NOT EXISTS idx_screen_name ON screen (name);
CREATE INDEX IF NOT EXISTS idx_screen_cinema_id ON screen (cinema_id);
