package com.movie.booking_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"showSeats", "screen"}, callSuper = true)
@Entity
@Table(name = "seat")
public class Seat extends BaseModel {
    @Enumerated(EnumType.STRING)
    private SeatStatus status;
    private String rowNumber;
    private int columnNumber;
    @Enumerated(EnumType.STRING)
    private SeatType type;
    @OneToMany(mappedBy = "seat", fetch = FetchType.LAZY)
    private List<ShowSeat> showSeats;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screen_id", nullable = false)
    private Screen screen;
}
