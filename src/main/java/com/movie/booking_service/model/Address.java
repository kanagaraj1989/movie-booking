package com.movie.booking_service.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
@Data
@AllArgsConstructor
public class Address {
    private String addressLine1;
    private String addressLine2;
    private String pinCode;
}
