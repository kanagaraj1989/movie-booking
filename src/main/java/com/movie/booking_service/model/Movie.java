package com.movie.booking_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {
    private String name;
    private String summary;
    private List<String> language;
    private String originalLanguage;
    @Enumerated
    private Genre genre;
    private int duration;
    private LocalDateTime releaseDate;
    @OneToMany(mappedBy ="movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)

    private List<Show> shows;
}
