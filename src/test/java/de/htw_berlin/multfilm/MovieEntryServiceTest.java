package de.htw_berlin.multfilm;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MovieEntryServiceTest {

    @Mock
    MovieEntryRepository repo;

    @InjectMocks
    MovieEntryService service;

    @Test
    void getSeenReturnsSeenMovieEntries() {
        MovieEntry seenMovie = new MovieEntry();
        seenMovie.setSeen(true);
        when(repo.findBySeenTrue()).thenReturn(List.of(seenMovie));

        List<MovieEntry> result = service.getSeen();

        assertTrue(result.getFirst().isSeen());
    }

    @Test
    void toggleSeenChangesSeenStatusAndSavesMovieEntry() {
        MovieEntry movie = new MovieEntry();
        movie.setMovieID(1L);
        movie.setSeen(false);
        when(repo.findById(1L)).thenReturn(Optional.of(movie));
        when(repo.save(movie)).thenReturn(movie);

        MovieEntry result = service.toggleSeen(1L);

        assertTrue(result.isSeen());
        verify(repo).save(movie);
    }

    @Test
    void markAsToWatchSetsToWatchStatusAndSavesMovieEntry() {
        MovieEntry movie = new MovieEntry();
        movie.setMovieID(1L);
        movie.setToWatch(false);
        when(repo.findById(1L)).thenReturn(Optional.of(movie));
        when(repo.save(movie)).thenReturn(movie);

        MovieEntry result = service.markAsToWatch(1L);

        assertTrue(result.isToWatch());
        verify(repo).save(movie);
    }

    @Test
    void removeFromSeenClearsSeenStatusAndSavesMovieEntry() {
        MovieEntry movie = new MovieEntry();
        movie.setMovieID(1L);
        movie.setSeen(true);
        when(repo.findById(1L)).thenReturn(Optional.of(movie));
        when(repo.save(movie)).thenReturn(movie);

        MovieEntry result = service.removeFromSeen(1L);

        assertFalse(result.isSeen());
        verify(repo).save(movie);
    }
}
