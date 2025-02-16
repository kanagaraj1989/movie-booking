CREATE TABLE IF NOT EXISTS seat (
    id UUID PRIMARY KEY,
    status VARCHAR(50) NOT NULL,
    row_number VARCHAR(10) NOT NULL,
    column_number INT NOT NULL,
    type VARCHAR(50) NOT NULL,
    screen_id UUID NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_seat_screen FOREIGN KEY (screen_id) REFERENCES screen(id)
);

CREATE INDEX IF NOT EXISTS idx_seat_screen_id ON seat (screen_id);
