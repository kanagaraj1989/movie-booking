package com.movie.booking_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"shows"},callSuper = true)
@Entity
@Table(name = "movie")
public class Movie extends BaseModel {
    private String name;
    private String summary;
    private String language;
    private String originalLanguage;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private int duration;
    private LocalDateTime releaseDate;
    @OneToMany(mappedBy ="movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Show> shows;

    public List<String> getLanguageList() {
        return language != null ? Arrays.asList(language.split(",")) : Collections.emptyList();
    }

    public void setLanguageList(List<String> languages) {
        this.language = languages != null ? String.join(",", languages) : "";
    }
}
