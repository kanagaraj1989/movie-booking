package com.movie.booking_service.controller;

import com.movie.booking_service.dto.ShowDTO;
import com.movie.booking_service.model.Show;
import com.movie.booking_service.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shows")
public class ShowController {

    private final ShowService showService;
    @GetMapping
    public ResponseEntity<ShowDTO> getsAllShowsByCityName(@RequestParam String cityName, @RequestParam String movieName) {
        var shows = showService.getAllShowsByCityName(cityName, movieName);
        return ResponseEntity.ok(generateShowDTO(shows));
    }

    private ShowDTO generateShowDTO(List<Show> shows) {
        var cinemas = new HashMap<UUID, ShowDTO.CinemaDTO>();
        for(var show: shows ) {
            var cinemaId = show.getScreen().getCinema().getId();
            System.out.println("cinemaId:"+ cinemaId);
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
                System.out.println("cinema object added:"+ cinemaId);
                cinemas.put(cinemaId, cinema);
            } else {
                System.out.println("cinema object updated:"+ cinemaId);

                var cinema = cinemas.get(cinemaId);
                System.out.println("screens:"+ cinema.getScreens());
                var screen =  new ShowDTO.CinemaDTO.ScreenDTO(
                        show.getScreen().getId(),
                        show.getScreen().getName(),
                        show.getId(),
                        show.getStartTime().toString(),
                        show.getEndTime().toString());
                System.out.println("screenDTO:"+ screen);
                cinema.getScreens().add(screen);

            }
        }
        return new ShowDTO(cinemas.values().stream().toList());
    }
}
