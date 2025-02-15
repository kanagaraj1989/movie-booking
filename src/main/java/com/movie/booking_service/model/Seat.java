package com.movie.booking_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "showSeats", callSuper = true)
@Entity
@Table(name = "seat")
public class Seat extends BaseModel {
    @Enumerated(EnumType.STRING)
    private SeatStatus status;
    private String rowNumber;
    private int columnNumber;
    @Enumerated(EnumType.STRING)
    private SeatType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screen_id", nullable = false)
    private Screen screen;

    @OneToMany(mappedBy = "seat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ShowSeat> showSeats;
}
