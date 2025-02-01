package com.movie.booking_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "seat")
public class Seat extends BaseModel {
    @Id
    private UUID id;
    @Enumerated(EnumType.STRING)
    private SeatStatus status;
    private String rowNumber;
    private int columnNumber;
    @Enumerated(EnumType.STRING)
    private SeatType type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screen_id", nullable = false)
    private Screen screen;
}
