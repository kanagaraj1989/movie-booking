CREATE TABLE IF NOT EXISTS show (
    id UUID PRIMARY KEY,
    movie_id UUID NOT NULL,
    screen_id UUID NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_show_movie FOREIGN KEY (movie_id) REFERENCES movie(id),
    CONSTRAINT fk_show_screen FOREIGN KEY (screen_id) REFERENCES screen(id)
);

CREATE INDEX IF NOT EXISTS idx_show_movie_id ON show (movie_id);
CREATE INDEX IF NOT EXISTS idx_show_screen_id ON show (screen_id);
CREATE INDEX IF NOT EXISTS idx_show_start_time ON show (start_time);
