package com.movie.booking_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "cinema", callSuper = true)
@Table(name = "city")
public class City extends BaseModel {
    private String name;
    private String state;
    private String country;
    private String pinCodes;
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Cinema> cinema;

    public List<String> getPinCodesList() {
        return pinCodes != null ? Arrays.asList(pinCodes.split(",")) : Collections.emptyList();
    }

    public void setPinCodesList(List<String> pinCodesList) {
        this.pinCodes = pinCodesList != null ? String.join(",", pinCodesList) : "";
    }
}
