CREATE TABLE IF NOT EXISTS show_seat (
    id UUID PRIMARY KEY,
    show_id UUID NOT NULL,
    seat_id UUID NOT NULL,
    booking_id UUID NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_show_seat_show FOREIGN KEY (show_id) REFERENCES show(id),
    CONSTRAINT fk_show_seat_seat FOREIGN KEY (seat_id) REFERENCES seat(id),
    CONSTRAINT fk_show_seat_booking FOREIGN KEY (booking_id) REFERENCES booking (id)
);

CREATE INDEX IF NOT EXISTS idx_show_seat_show_id ON show_seat (show_id);
CREATE INDEX IF NOT EXISTS idx_show_seat_seat_id ON show_seat (seat_id);
CREATE INDEX IF NOT EXISTS idx_show_seat_booking_id ON show_seat (booking_id);
