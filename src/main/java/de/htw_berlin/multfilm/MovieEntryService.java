package de.htw_berlin.multfilm;

/*
Alter Stand, nur auskommentiert:

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieEntryService {

    @Autowired
    MovieEntryRepository repo;

    public MoveEntry save(MoveEntry thing) {
        return repo.save(thing);
    }

    public MoveEntry get(Long id) {
        return repo.findById(id).orElseThrow(RuntimeException::ew);
    }

    public List<MoveEntry> getAllWithoutOwner() {
        Iterable<MoveEntry> iterator = repo.findAll();
        List<MoveEntry> things = new ArrayList<MoveEntry>();
        for (MoveEntry thing : iterator) if(thing.getOwner()==null || thing.getOwner().equals("")) things.add(thing);
        return things;
    }

    public List<MoveEntry> getAllOwnedBy(String owner) {
        Iterable<MoveEntry> iterator = repo.findAll();
        List<MoveEntry> things = new ArrayList<MoveEntry>();
        for (MoveEntry thing : iterator) if(thing.getOwner()!=null && thing.getOwner().equals(owner)) things.add(thing);
        return things;
    }

}
*/

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MovieEntryService {

    @Autowired
    MovieEntryRepository repo;

//    public MovieEntryService(MovieEntryRepository repo) {
//     this.repo = repo;
//  }

    public MovieEntry save(MovieEntry movieEntry) {
        if (movieEntry == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Movie entry darf nicht leer sein.");
        }

        if (movieEntry.getTitle() == null || movieEntry.getTitle().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Movie entry braucht einen Filmtitel.");
        }

        if (movieEntry.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Movie entry braucht eine TMDB-Film-ID.");
        }

        return repo.save(movieEntry);
    }

    public MovieEntry get(Long movieID) {
        validateMovieID(movieID);

        return repo.findById(movieID).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Film mit movieID " + movieID + " wurde nicht gefunden."));
    }

    public List<MovieEntry> getAllWithoutOwner() {
        Iterable<MovieEntry> iterator = repo.findAll();
        List<MovieEntry> movieEntries = new ArrayList<>();

        for (MovieEntry movieEntry : iterator) {
            if (movieEntry.getOwner() == null || movieEntry.getOwner().isEmpty()) {
                movieEntries.add(movieEntry);
            }
        }

        return movieEntries;
    }

    public List<MovieEntry> getAllOwnedBy(String owner) {
        if (owner == null || owner.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Owner darf nicht leer sein.");
        }

        return repo.findByOwner(owner);
    }

//    public List<MovieEntry> getFavorites(String owner) {
//        return repo.findByOwnerAndFavoriteTrue(owner);
//    }
//
//    public List<MovieEntry> getSeen(String owner) {
//        return repo.findByOwnerAndSeenTrue(owner);
//    }
//
//    public List<MovieEntry> getToWatch(String owner) {
//        return repo.findByOwnerAndToWatchTrue(owner);
//    }
    public List<MovieEntry> getToWatch() {
        return repo.findByToWatchTrue();
    }

    public List<MovieEntry> getSeen() {
        return repo.findBySeenTrue();
    }

    public MovieEntry removeFromWatchlist(Long movieID) {
        MovieEntry movie = get(movieID);
        movie.setToWatch(false);
        return repo.save(movie);
    }

    public MovieEntry markAsToWatch(Long movieID) {
        MovieEntry movie = get(movieID);
        movie.setToWatch(true);
        return repo.save(movie);
    }

    public MovieEntry markAsSeen(Long movieID) {
        MovieEntry movie = get(movieID);
        movie.setSeen(true);
        return repo.save(movie);
    }

    public MovieEntry removeFromSeen(Long movieID) {
        MovieEntry movie = get(movieID);
        movie.setSeen(false);
        movie.setCommentText("");
        return repo.save(movie);
    }

    public MovieEntry toggleSeen(Long movieID) {
        MovieEntry movie = get(movieID);
        boolean newSeenStatus = !movie.isSeen();
        movie.setSeen(newSeenStatus);

        if (!newSeenStatus) {
            movie.setCommentText("");
        }

        return repo.save(movie);
    }

    public MovieEntry updateComment(Long movieID, String commentText) {
        MovieEntry movie = get(movieID);

        if (commentText != null && commentText.length() > 1000) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Kommentar darf maximal 1000 Zeichen lang sein.");
        }

        movie.setCommentText(commentText);
        return repo.save(movie);
    }

    private void validateMovieID(Long movieID) {
        if (movieID == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "MovieID darf nicht leer sein.");
        }

        if (movieID <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "MovieID muss groesser als 0 sein.");
        }
    }
}
