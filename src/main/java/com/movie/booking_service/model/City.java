package com.movie.booking_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "city")
public class City extends BaseModel {
    private String name;
    private String state;
    private String country;
    @Column(name = "pin_codes", columnDefinition = "TEXT")
    private String pinCodes;
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Cinema> cinema;

    public List<String> getPinCodesList() {
        return pinCodes != null ? Arrays.asList(pinCodes.split(",")) : Collections.emptyList();
    }

    public void setPinCodesList(List<String> pinCodesList) {
        this.pinCodes = pinCodesList != null ? String.join(",", pinCodesList) : "";
    }
}
