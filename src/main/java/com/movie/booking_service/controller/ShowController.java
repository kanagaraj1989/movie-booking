package com.movie.booking_service.controller;

import com.movie.booking_service.dto.ShowDTO;
import com.movie.booking_service.model.Show;
import com.movie.booking_service.service.ShowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shows")
public class ShowController {

    private final ShowService showService;

    @Operation(summary = "Get All Show by City and movie name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved shows"),
            @ApiResponse(responseCode = "400", description = "Client error")
    })
    @GetMapping
    public ResponseEntity<ShowDTO> getsAllShowsByCityName(
            @Parameter(description = "Name of the city", example = "Chennai", required = true)
            @RequestParam String cityName,
            @Parameter(description = "Name of the movie", example = "Avenger", required = true)
            @RequestParam String movieName) {
        var shows = showService.getAllShowsByCityAndMovieName(cityName, movieName);
        return ResponseEntity.ok(generateShowDTO(shows));
    }

    private ShowDTO generateShowDTO(List<Show> shows) {
        var cinemas = new HashMap<UUID, ShowDTO.CinemaDTO>();
        for(var show: shows ) {
            var cinemaId = show.getScreen().getCinema().getId();
            if (!cinemas.containsKey(cinemaId)) {
                var cinema = new ShowDTO.CinemaDTO(
                        cinemaId,
                        show.getScreen().getCinema().getName(),
                        new ShowDTO.MovieDTO( show.getMovie().getId(), show.getMovie().getName() ),
                        new ArrayList<>()
                );
                cinema.getScreens().add(new ShowDTO.CinemaDTO.ScreenDTO(
                        show.getScreen().getId(),
                        show.getScreen().getName(),
                        show.getId(),
                        show.getStartTime().toString(),
                        show.getEndTime().toString())
                );
                cinemas.put(cinemaId, cinema);
            } else {
                var cinema = cinemas.get(cinemaId);
                var screen =  new ShowDTO.CinemaDTO.ScreenDTO(
                        show.getScreen().getId(),
                        show.getScreen().getName(),
                        show.getId(),
                        show.getStartTime().toString(),
                        show.getEndTime().toString());
                cinema.getScreens().add(screen);

            }
        }
        return new ShowDTO(cinemas.values().stream().toList(), LocalDateTime.now());
    }
}
