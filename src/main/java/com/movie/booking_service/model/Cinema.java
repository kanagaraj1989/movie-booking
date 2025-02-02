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
@Table(name="cinema")
public class Cinema extends BaseModel {
    @Id
    private UUID id;
    private String name;
    @Enumerated(EnumType.STRING)
    private CinemaStatus status;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "cinema", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Screen> screens;
}
