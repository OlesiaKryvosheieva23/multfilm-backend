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

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Service
public class MovieEntryService {

    @Autowired
    MovieEntryRepository repo;

//    public MovieEntryService(MovieEntryRepository repo) {
//     this.repo = repo;
//  }

    public MovieEntry save(MovieEntry movieEntry) {
        return repo.save(movieEntry);
    }

    public MovieEntry get(Long movieID) {
        return repo.findById(movieID).orElseThrow(RuntimeException::new);
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
    public MovieEntry removeFromWatchlist(Long movieID) {
        MovieEntry movie = repo.findById(movieID).orElseThrow(RuntimeException::new);
        movie.setToWatch(false);
        return repo.save(movie);
    }
}
