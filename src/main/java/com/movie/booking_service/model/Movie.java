package com.movie.booking_service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "movie")
public class Movie extends BaseModel {
    private String name;
    private String summary;
    @Column(name = "language", columnDefinition = "TEXT")
    private String language;
    private String originalLanguage;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private int duration;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
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
