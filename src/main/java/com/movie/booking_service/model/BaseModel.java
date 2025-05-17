package com.movie.booking_service.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@EqualsAndHashCode
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(generator = "UUID" , strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private UUID id;
    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    protected LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(nullable = false)
    protected LocalDateTime updatedAt;
}
