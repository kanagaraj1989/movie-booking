
CREATE TABLE IF NOT EXISTS booking (
    id UUID PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    -- User Details
    user_id VARCHAR(255) NOT NULL,
    user_name VARCHAR(255),
    user_email VARCHAR(255),

    -- Movie Details
    movie_id VARCHAR(255) NOT NULL,
    movie_name VARCHAR(255),
    genre VARCHAR(100),
    language VARCHAR(100),
    duration INT,

    -- Cinema Details
    cinema_id VARCHAR(255) NOT NULL,
    cinema_name VARCHAR(255),
    screen_id VARCHAR(255) NOT NULL,
    screen_name VARCHAR(255),

    -- Show Details
    show_time TIMESTAMP NOT NULL,

    -- Seat Details
    seat_id VARCHAR(255),
    row_number INT,
    column_number INT,
    seat_type VARCHAR(50),

    -- Payment Details (Order Info)
    payment_status VARCHAR(50),
    transaction_id VARCHAR(100),
    payment_mode VARCHAR(50)
);

CREATE INDEX IF NOT EXISTS idx_booking_user_id ON booking (user_id);
CREATE INDEX IF NOT EXISTS idx_booking_movie_id ON booking (movie_id);
CREATE INDEX IF NOT EXISTS idx_booking_show_time ON booking (show_time);
CREATE INDEX IF NOT EXISTS idx_booking_cinema_id ON booking (cinema_id);
CREATE INDEX IF NOT EXISTS idx_booking_screen_id ON booking (screen_id);

