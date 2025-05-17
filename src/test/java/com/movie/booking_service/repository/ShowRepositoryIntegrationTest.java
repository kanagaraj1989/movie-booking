package com.movie.booking_service.repository;

import com.movie.booking_service.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

// @DataJpaTest runs each test method in a transaction that is rolled back after the test completes, ensuring test isolation.

@DataJpaTest // configures an in-memory database, scans for @Entity classes, and configures Spring Data JPA repositories
public class ShowRepositoryIntegrationTest {
    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private ScreenRepository screenRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Test
    void findByCityAndMovieNameIgnoreCase() {
        // Arrange: Set up test data
        var city = new City();
        city.setName("Madurai");
        city.setState("TamilNadu");
        city.setCountry("India");
        city.setPinCodes("600001,600002");
        cityRepository.save(city);

        var cinema = new Cinema();
        cinema.setName("PVR Cinemas");
        cinema.setCity(city);
        cinema.setStatus(CinemaStatus.ACTIVE);
        var address = new Address("PTC Bus stop", "Opposite to smart works", "600005");
        cinema.setAddress(address);
        cinemaRepository.save(cinema);

        var screen = new Screen();
        screen.setName("Screen 1");
        screen.setStatus(ScreenStatus.ACTIVE);
        screen.setNumberOfSeats(100);
        screen.setCinema(cinema);
        screenRepository.save(screen);

        var movie = new Movie();
        movie.setName("Avenger");
        movie.setDuration(150);
        movie.setLanguage("Tamil");
        movie.setGenre(Genre.COMEDY);
        movie.setReleaseDate(LocalDateTime.of(2025, 5, 20, 0, 0));
        movieRepository.save(movie);

        var show = new Show();
        show.setScreen(screen);
        show.setMovie(movie);
        show.setStartTime(LocalDateTime.of(2025, 5, 20, 10, 0));
        show.setEndTime(LocalDateTime.of(2025, 5, 20, 12, 45));
        showRepository.save(show);

        // Act: Execute the method under test
        List<Show> shows = showRepository.findByScreen_Cinema_City_NameIgnoreCaseAndMovie_NameIgnoreCase("Madurai", "Avenger");

        // Assert: Verify the results
        assertThat(shows).hasSize(1);
        Show retrievedShow = shows.get(0);
        assertThat(retrievedShow.getId()).isEqualTo(show.getId());
        assertThat(retrievedShow.getScreen().getCinema().getCity().getName()).isEqualToIgnoringCase("Madurai");
        assertThat(retrievedShow.getMovie().getName()).isEqualToIgnoringCase("Avenger");
    }
}
