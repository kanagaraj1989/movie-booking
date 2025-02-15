package com.movie.booking_service.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @CreationTimestamp
    @Column(updatable = false)
    protected LocalDateTime createdAt;
    @UpdateTimestamp
    protected LocalDateTime updatedAt;
}
