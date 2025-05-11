package com.movie.booking_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"screens", "city"}, callSuper = true)
@Table(name="cinema")
public class Cinema extends BaseModel {
    private String name;
    @Enumerated(EnumType.STRING)
    private CinemaStatus status;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "cinema", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Screen> screens;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;
}
