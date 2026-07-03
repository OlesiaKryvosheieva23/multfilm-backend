package de.htw_berlin.multfilm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;



@SpringBootTest
@ActiveProfiles("test")
class MovieServiceTest {


    @Autowired
    private MovieService service;

    @MockitoBean
    private TmdbApiClient tmdb;


    @Test
    void testGetMovie() {

        Map<String, Object> details = Map.of(
                "title", "Boring Movie",
                "overview", "Das ist Filmüberblick.",
                "poster_path", "/poster.jpg",
                "release_date", "2023-01-01",
                "vote_average", 8.5
        );

        doReturn(details).when(tmdb).getMovieDetails(1);

        MovieDto actual = service.getMovie(1);

        assertEquals("Boring Movie", actual.title());
//        assertEquals("Patrick Neuer", actual.director());
        assertEquals(8.5, actual.voteAverage());
    }
}