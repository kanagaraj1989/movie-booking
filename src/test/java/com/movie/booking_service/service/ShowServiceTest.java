package com.movie.booking_service.service;

import com.movie.booking_service.model.Show;
import com.movie.booking_service.repository.ShowRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.List;

public class ShowServiceTest {
    @Mock // create mock instance of repository
    private ShowRepository showRepository;

    @InjectMocks // Inject the mock into ShowService
    private ShowService showService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize the mocks
    }

    @Test
    void getAllShowsByCityAndMovieNameShouldReturnShows() {
        var cityName = "Chennai";
        var movieName = "Avenger";
        var showOne = Mockito.mock(Show.class);
        var showTwo = Mockito.mock(Show.class);
        var mockShowList = List.of(showOne, showTwo);
        when(showRepository.findByScreen_Cinema_City_NameIgnoreCaseAndMovie_NameIgnoreCase(cityName,movieName))
                .thenReturn(mockShowList);
        var shows = showService.getAllShowsByCityAndMovieName(cityName, movieName);
        assertThat(shows).isNotEmpty();
        assertThat(shows.size()).isEqualTo(2);
        assertThat(shows.get(0)).isEqualTo(showOne);
        assertThat(shows.get(1)).isEqualTo(showTwo);
        verify(showRepository, times(1)).findByScreen_Cinema_City_NameIgnoreCaseAndMovie_NameIgnoreCase(cityName, movieName);
    }
}
