package com.movie.booking_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="booking")
public class Booking extends BaseModel {
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ShowSeat> showSeats;

    // User Details
    private String userId;
    private String userName;
    private String userEmail;

    // Movie Details
    private String movieId;
    private String movieName;
    private String genre;
    private String language;
    private int duration;

    // Cinema Details
    private String cinemaId;
    private String cinemaName;
    private String screenId;
    private String screenName;

    // Show Details
    private LocalDateTime showTime;

    // Seat Details
    private String seatId;
    private int rowNumber;
    private int columnNumber;
    private SeatType seatType;

    // Payment Details (Order Info)
    private PaymentStatus paymentStatus;
    private String transactionId;
    private String paymentMode;
}
