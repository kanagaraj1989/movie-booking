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
@Table(name="theater")
public class Theater extends BaseModel {
    @Id
    private UUID id;
    private String name;
    private String city;
    private String state;
    private String pinCode;
    @Enumerated(EnumType.STRING)
    private TheaterStatus status;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Screen> screens;
}
