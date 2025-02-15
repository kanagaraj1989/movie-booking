package com.movie.booking_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name="screen")
public class Screen extends BaseModel {
    private String name;
    private int NumberOfSeats;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_id", nullable = false)
    private Cinema cinema;

    @OneToMany(mappedBy = "screen", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Seat> seats;

    @Enumerated(EnumType.STRING)
    private ScreenStatus status;

    @OneToMany(mappedBy = "screen", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Show> shows;
}
