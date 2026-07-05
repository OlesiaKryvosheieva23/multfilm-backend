package de.htw_berlin.multfilm;

import de.htw_berlin.multfilm.controller.TmdbController;
import de.htw_berlin.multfilm.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
@ActiveProfiles("test")
class TmdbControllerTest {

    @Autowired
    private TmdbController controller;

    @MockitoBean
    private MovieService movieService;

    @Test
    void testGetMovie() {

        MovieDto movie = new MovieDto(
                "Boring Movie",
                "Das ist Filmüberblick.",
                "https://example.com/poster.jpg",
                "2023-01-01",
                8.5
        );

        doReturn(movie).when(movieService).getMovie(1);

        MovieDto actual = controller.getMovie(1);

        assertEquals("Boring Movie", actual.title());
        assertEquals("Das ist Filmüberblick.", actual.overview());
        assertEquals("https://example.com/poster.jpg", actual.posterUrl());
        assertEquals("2023-01-01", actual.releaseDate());
        assertEquals(8.5, actual.voteAverage());
    }

    @Test
    void testGetMovieTitle() {

        MovieDto movie = new MovieDto(
                "Boring Movie",
                "Das ist Filmüberblick.",
                "https://example.com/poster.jpg",
                "2023-01-01",
                8.5
        );

        doReturn(movie).when(movieService).getMovie(1);

        String actual = controller.getMovieTitle(1);

        assertEquals("Boring Movie", actual);
    }

    @Test
    void testGetMovieVoteAverage() {

        MovieDto movie = new MovieDto(
                "Boring Movie",
                "Das ist Filmüberblick.",
                "https://example.com/poster.jpg",
                "2023-01-01",
                8.5
        );

        doReturn(movie).when(movieService).getMovie(1);

        Double actual = controller.getMovieVoteAverage(1);

        assertEquals(8.5, actual);
    }
}