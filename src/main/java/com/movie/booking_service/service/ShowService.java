package com.movie.booking_service.service;

import com.movie.booking_service.model.Show;
import com.movie.booking_service.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowService {
    private final ShowRepository showRepository;

    public List<Show> getAllShowsByCityAndMovieName(String cityName, String movieName) {
        return showRepository.findByScreen_Cinema_City_NameIgnoreCaseAndMovie_NameIgnoreCase(cityName, movieName);

    }
}
