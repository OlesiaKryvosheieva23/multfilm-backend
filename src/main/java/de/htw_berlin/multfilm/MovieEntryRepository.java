package de.htw_berlin.multfilm;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieEntryRepository extends CrudRepository<MovieEntry, Long> {

    List<MovieEntry> findByOwner(String owner);

    List<MovieEntry> findByOwnerAndFavoriteTrue(String owner);

    List<MovieEntry> findByOwnerAndSeenTrue(String owner);

    List<MovieEntry> findByOwnerAndToWatchTrue(String owner);
}
