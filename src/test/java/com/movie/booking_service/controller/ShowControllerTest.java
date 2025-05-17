package com.movie.booking_service.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.movie.booking_service.dto.ShowDTO;
import com.movie.booking_service.model.Cinema;
import com.movie.booking_service.model.Movie;
import com.movie.booking_service.model.Screen;
import com.movie.booking_service.model.Show;
import com.movie.booking_service.service.ShowService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class) // used to enable Spring support.
@WebMvcTest(ShowController.class) // sets up the test context for ShowController (test the web layer).
public class ShowControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ShowService showService;

    @Test
    void getsAllShowsByCityName_shouldReturnShowDTO() throws Exception {
        String cityName = "Chennai";
        String movieName = "Avenger";
        var mockShow = Mockito.mock(Show.class);
        var mockScreen = Mockito.mock(Screen.class);
        var mockCinema = mock(Cinema.class);
        var mockMovie = mock(Movie.class);

        var cinemaId = UUID.randomUUID();
        var screenId = UUID.randomUUID();
        var showId = UUID.randomUUID();
        var movieId = UUID.randomUUID();

        var showStartTime = LocalDateTime.now().plusDays(2).withHour(10).withMinute(0).withSecond(0);
        var showEndTime = LocalDateTime.now().plusDays(2).withHour(12).withMinute(45).withSecond(0);
        when(mockShow.getId()).thenReturn(showId);
        when(mockShow.getStartTime()).thenReturn(showStartTime);
        when(mockShow.getEndTime()).thenReturn(showEndTime);
        when(mockShow.getScreen()).thenReturn(mockScreen);
        when(mockShow.getMovie()).thenReturn(mockMovie);

        when(mockScreen.getId()).thenReturn(screenId);
        when(mockScreen.getName()).thenReturn("screen-01");
        when(mockScreen.getCinema()).thenReturn(mockCinema);

        when(mockCinema.getId()).thenReturn(cinemaId);
        when(mockCinema.getName()).thenReturn("cinema-01");

        when(mockMovie.getId()).thenReturn(movieId);
        when(mockMovie.getName()).thenReturn("movie-01");

        when(showService.getAllShowsByCityAndMovieName(cityName, movieName))
                .thenReturn(Collections.singletonList(mockShow));

        var mvcResult = mockMvc.perform(get("/shows")
                .param("cityName", cityName)
                .param("movieName", movieName))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Handle LocalDateTime
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        ShowDTO response = objectMapper.readValue(jsonResponse, ShowDTO.class);

        // Assertions
        assertNotNull(response);
        assertNotNull(response.getCinema());
        assertEquals(1, response.getCinema().size());

        var cinema = response.getCinema().get(0);
        assertEquals(cinemaId, cinema.getId());
        assertEquals("cinema-01", cinema.getName());

        var movie = cinema.getMovie();
        assertEquals(movieId, movie.getId());
        assertEquals("movie-01", movie.getName());

        List<ShowDTO.CinemaDTO.ScreenDTO> screens = cinema.getScreens();
        assertEquals(1, screens.size());

        ShowDTO.CinemaDTO.ScreenDTO screen = screens.get(0);
        assertEquals(screenId, screen.getId());
        assertEquals("screen-01", screen.getName());
        assertEquals(showId, screen.getShowId());
        assertEquals(showStartTime.toString(), screen.getShowStartTime());
        assertEquals(showEndTime.toString(), screen.getShowEndTime());

        verify(showService, times(1)).getAllShowsByCityAndMovieName(cityName, movieName);
    }
}
