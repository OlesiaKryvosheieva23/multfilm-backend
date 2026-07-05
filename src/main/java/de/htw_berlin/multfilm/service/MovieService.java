package de.htw_berlin.multfilm.service;

import java.util.Map;

import de.htw_berlin.multfilm.MovieDto;
import de.htw_berlin.multfilm.MovieEntryRepository;
import de.htw_berlin.multfilm.TmdbApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired
    private MovieEntryRepository movieRepository;

    private final TmdbApiClient tmdb;

    public MovieService(TmdbApiClient tmdb) {
        this.tmdb = tmdb;
    }

    public MovieDto getMovie(int tmdbId) {
        Map<String, Object> details = tmdb.getMovieDetails(tmdbId);


        return new MovieDto(
                (String) details.get("title"),
                (String) details.get("overview"),
                "https://image.tmdb.org/t/p/w500" + details.get("poster_path"),
                (String) details.get("release_date"),
                ((Number) details.get("vote_average")).doubleValue()
        );
    }
}

