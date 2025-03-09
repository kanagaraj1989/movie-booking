package com.movie.booking_service.repository;

import com.movie.booking_service.model.Show;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ShowRepository extends JpaRepository<Show, UUID> {
/*    @Query("SELECT  FROM Show s" +
            " JOIN s.screen sc " +
            " JOIN sc.cinema c " +
            " JOIN c.city ci " +
            " WHERE LOWER(ci.name) = LOWER(:cityName)")
    List<Show> findAllShowsByCityName(@Param("cityName") String cityName);*/

    @EntityGraph(attributePaths = {
            "screen.cinema",
            "screen.cinema.city",
            "movie"
    })
    List<Show> findByScreen_Cinema_City_NameIgnoreCaseAndMovie_NameIgnoreCase(String cityName, String movieName);
}
