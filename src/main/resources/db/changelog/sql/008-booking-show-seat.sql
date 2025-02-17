-- Create join table for show seat IDs
CREATE TABLE booking_show_seat (
    booking_id BIGINT NOT NULL,
    show_seat_id BIGINT NOT NULL,
    CONSTRAINT fk_booking FOREIGN KEY (booking_id) REFERENCES booking (id),
    CONSTRAINT fk_show_seat FOREIGN KEY (show_seat_id) REFERENCES show_seat (id)
);
