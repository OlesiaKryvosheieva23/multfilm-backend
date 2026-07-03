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

//        // Genres extrahieren
//        List<Map<String, Object>> genreList = (List<Map<String, Object>>) details.get("genres");
//        List<String> genres = genreList.stream()
//                .map(g -> (String) g.get("name"))
//                .toList();

//        // Cast extrahieren
//        List<Map<String, Object>> castList = (List<Map<String, Object>>) credits.get("cast");
//        List<String> cast = castList.stream()
//                .limit(10)
//                .map(c -> (String) c.get("name"))
//                .toList();

//        // Regisseur extrahieren
//        List<Map<String, Object>> crewList = (List<Map<String, Object>>) credits.get("crew");
//        String director = crewList.stream()
//                .filter(c -> "Director".equals(c.get("job")))
//                .map(c -> (String) c.get("name"))
//                .findFirst()
//                .orElse("Unbekannt");

        return new MovieDto(
                (String) details.get("title"),
                (String) details.get("overview"),
                "https://image.tmdb.org/t/p/w500" + details.get("poster_path"),
                (String) details.get("release_date"),
                ((Number) details.get("vote_average")).doubleValue()
//                genres,
//                cast,
//                director,

        );
    }
}

