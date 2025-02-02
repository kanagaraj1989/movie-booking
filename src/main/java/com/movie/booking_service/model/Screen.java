package com.movie.booking_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="screen")
public class Screen extends BaseModel {
    @Id
    private UUID id;
    private String name;
    private int NumberOfSeats;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_id", nullable = false)
    private Cinema cinema;

    @OneToMany(mappedBy = "screen", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Seat> seats;

    @Enumerated(EnumType.STRING)
    private ScreenStatus status;
}
