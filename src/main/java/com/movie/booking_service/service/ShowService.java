package com.movie.booking_service.service;

import com.movie.booking_service.dto.ShowDTO;
import com.movie.booking_service.model.Show;
import com.movie.booking_service.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowService {
    private final ShowRepository showRepository;

    public List<ShowDTO> getAllShowsByCityName(String cityName, String movieName) {
        var shows = showRepository.findByScreen_Cinema_City_NameIgnoreCaseAndMovie_NameIgnoreCase(cityName, movieName);
        return shows.stream().map(
                show -> new ShowDTO(
                        show.getId(),
                        new ShowDTO.MovieDTO(show.getMovie().getId(), show.getMovie().getName()),
                        new ShowDTO.CinemaDTO(show.getScreen().getCinema().getId(),
                                show.getScreen().getCinema().getName(),
                                List.of(new ShowDTO.CinemaDTO.ScreenDTO(
                                        show.getScreen().getId(),
                                        show.getScreen().getName(),
                                        show.getStartTime().toString(),
                                        show.getEndTime().toString())))
                )
        ).toList();
    }
}
