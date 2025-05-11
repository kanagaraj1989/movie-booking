package com.movie.booking_service.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Embeddable
@Data
@AllArgsConstructor
public class Address {
    private String addressLine1;
    private String addressLine2;
    private String pinCode;
}
