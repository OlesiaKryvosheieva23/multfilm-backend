package de.htw_berlin.multfilm;

import java.util.List;
import java.util.Map;

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
        Map<String, Object> credits = tmdb.getMovieCredits(tmdbId);




        return new MovieDto(
                (String) details.get("title"),
                (String) details.get("overview"),
                "https://image.tmdb.org/t/p/w500" + details.get("poster_path"),
                (String) details.get("release_date"),
                ((Number) details.get("vote_average")).doubleValue()
        );
    }
}

