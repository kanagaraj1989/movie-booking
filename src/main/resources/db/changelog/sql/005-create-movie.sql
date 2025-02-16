CREATE TABLE IF NOT EXISTS movie (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    summary TEXT,
    language TEXT,
    original_language VARCHAR(50),
    genre VARCHAR(50) NOT NULL,
    duration INT NOT NULL,
    release_date TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_movie_name ON movie (name);
CREATE INDEX IF NOT EXISTS idx_movie_genre ON movie (genre);
