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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shows")
public class ShowController {

    private final ShowService showService;
    @GetMapping
    public ResponseEntity<List<ShowDTO>> getsAllShowsByCityName(@RequestParam String cityName, @RequestParam String movieName) {
        var shows = showService.getAllShowsByCityName(cityName, movieName);
        return ResponseEntity.ok(shows);
    }
}
