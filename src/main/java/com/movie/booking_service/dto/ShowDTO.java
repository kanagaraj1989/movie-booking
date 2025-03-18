package com.movie.booking_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ShowDTO {
    private List<CinemaDTO> cinema;

    @Data
    @AllArgsConstructor
    public static class MovieDTO {
        private UUID id;
        private String name;
    }

    @Data
    @AllArgsConstructor
    public static class CinemaDTO {
        private UUID id;
        private String name;
        private MovieDTO movie;
        private List<ScreenDTO> screens;

        @Data
        @AllArgsConstructor
        public static class ScreenDTO {
            private UUID id;
            private String name;
            private UUID showId;
            private String showStartTime;
            private String showEndTime;
        }
    }
}
