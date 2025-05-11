package com.movie.booking_service.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;

@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //objectMapper.registerModule(new Hibernate5JakartaModule()); // Handles lazy loading issues - Jackson ignore to serialize hibernateLazyInitializer
        objectMapper.registerModule(new JavaTimeModule()); // Registers JavaTimeModule for LocalDateTime
        // default jackson is serializing your LocalDateTime as a timestamp array (eg: [2025, 4, 6, 12, 47, 20, 582493000])
        // instead of an ISO-8601 string
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }
}
