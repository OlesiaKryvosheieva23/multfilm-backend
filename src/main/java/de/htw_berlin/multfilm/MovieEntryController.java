package de.htw_berlin.multfilm;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(originPatterns = {
        "http://localhost:*",
        "http://127.0.0.1:*",
        "http://192.168.*.*:*",
        "http://10.*.*.*:*",
        "http://172.*.*.*:*",
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
    @GetMapping("/{movieID}")
    public MovieEntry getMovieEntry(@PathVariable Long movieID) {
            logger.info("GET request on route /api/movie-entries/{}", movieID);
        return service.get(movieID);
    }

    @CrossOrigin
    @GetMapping
    public List<MovieEntry> getAllMovieEntries(@RequestParam(defaultValue = "") String owner) {
        return owner.equals("") ? service.getAllWithoutOwner() : service.getAllOwnedBy(owner);
    }

    @CrossOrigin
    @GetMapping("/watchlist")
    public List<MovieEntry> getToWatch() {
        return service.getToWatch();
    }

    @CrossOrigin
    @GetMapping("/seen")
    public List<MovieEntry> getSeen() {
        return service.getSeen();
    }

    @CrossOrigin
    @PutMapping("/{movieID}/remove-watchlist")
    public MovieEntry removeFromWatchlist(@PathVariable Long movieID) {
        return service.removeFromWatchlist(movieID);
    }

    @CrossOrigin
    @PutMapping("/{movieID}/watchlist")
    public MovieEntry markAsToWatch(@PathVariable Long movieID) {
        return service.markAsToWatch(movieID);
    }

    @CrossOrigin
    @PutMapping("/{movieID}/seen")
    public MovieEntry markAsSeen(@PathVariable Long movieID) {
        return service.markAsSeen(movieID);
    }

    @CrossOrigin
    @PutMapping("/{movieID}/remove-seen")
    public MovieEntry removeFromSeen(@PathVariable Long movieID) {
        return service.removeFromSeen(movieID);
    }

    @CrossOrigin
    @PutMapping("/{movieID}/toggle-seen")
    public MovieEntry toggleSeen(@PathVariable Long movieID) {
        return service.toggleSeen(movieID);
    }

    @CrossOrigin
    @PutMapping("/{movieID}/comment")
    public MovieEntry updateComment(@PathVariable Long movieID, @RequestBody Map<String, String> body) {
        if (body == null || !body.containsKey("commentText")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body braucht das Feld commentText.");
        }

        return service.updateComment(movieID, body.get("commentText"));
    }

    @CrossOrigin
    @PutMapping("/{movieID}/rating")
    public MovieEntry updatePersonalRating(@PathVariable Long movieID, @RequestBody Map<String, Integer> body) {
        if (body == null || !body.containsKey("personalRating")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body braucht das Feld personalRating.");
        }

        return service.updatePersonalRating(movieID, body.get("personalRating"));
    }
}
