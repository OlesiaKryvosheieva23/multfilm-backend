package de.htw_berlin.multfilm;

/*
Alter Stand, nur auskommentiert:

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieEntryController {

    @Autowired
    MovieEntryService service;

    Logger logger = LoggerFactory.getLogger(MovieEntryController.class);

    @CrossOrigin
    @PostMapping("/moviess")
    public MovieEntry createThing(@RequestBody MovieEntry movie) {
        return service.save(movie);
    }

    @CrossOrigin
    @GetMapping("/movies/{id}")
    public MovieEntry getThing(@PathVariable String id) {
        logger.info("GET request on route things with {}", id);
        Long movieId = Long.parseLong(id);
        return service.get(movieId);
    }

    @CrossOrigin
    @GetMapping("/movies")
    public List<MovieEntry> getAllThings(@RequestParam("owner") String owner) {
        return owner.equals("") ? service.getAllWithoutOwner() : service.getAllOwnedBy(owner);
    }

}
*/

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://multfilm-frontend.onrender.com"
})
@RestController
@RequestMapping("/api/movie-entries")
public class MovieEntryController {

    @Autowired
    MovieEntryService service;
    private final Logger logger = LoggerFactory.getLogger(MovieEntryController.class);

    public MovieEntryController(MovieEntryService service) {
        this.service = service;
    }

    @CrossOrigin
    @PostMapping
    public MovieEntry createMovieEntry(@RequestBody MovieEntry movieEntry) {
        System.out.println("POST angekommen");
        return service.save(movieEntry);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public MovieEntry getMovieEntry(@PathVariable Long movieID) {
            logger.info("GET request on route /api/movie-entries/{}", movieID);
        return service.get(movieID);
    }

    @CrossOrigin
    @GetMapping
    public List<MovieEntry> getAllMovieEntries(@RequestParam(defaultValue = "") String owner) {
        return owner.equals("") ? service.getAllWithoutOwner() : service.getAllOwnedBy(owner);
    }

//    @CrossOrigin
//    @GetMapping("/favorites")
//    public List<MovieEntry> getFavorites(@RequestParam String owner) {
//        return service.getFavorites(owner);
//    }

//    @CrossOrigin
//    @GetMapping("/seen")
//    public List<MovieEntry> getSeen(@RequestParam String owner) {
//        return service.getSeen(owner);
//    }
//
//    @CrossOrigin
//    @GetMapping("/watchlist")
//    public List<MovieEntry> getToWatch(@RequestParam String owner) {
//        return service.getToWatch(owner);
//    }

    @CrossOrigin
    @GetMapping("/watchlist")
    public List<MovieEntry> getToWatch() {
        return service.getToWatch();
    }

    @CrossOrigin
    @PutMapping("/{movieID}/remove-watchlist")
    public MovieEntry removeFromWatchlist(@PathVariable Long movieID) {

        MovieEntry movie = service.get(movieID);

        movie.setToWatch(false);

        return service.save(movie);
    }
}
