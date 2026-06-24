package de.htw_berlin.multfilm;

//die Klasse MovieEntry, verknüpft einen User mit einem Film und speichert den Status, zum Beispiel TO_WATCH,  SEEN oder FAVORITE
//enthält Titel
//enthält Setter und Getter

/*
Alter Stand, nur auskommentiert:

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jdk.jfr.Enabled;
import org.springframework.data.annotation.Id;

@Enabled
public class MovieEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String title;
    boolean seen;
    boolean toWatch;
    boolean favorite;


    public MovieEntry() {}

    public MovieEntry(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSeen() {
        return seen;
    }

    public boolean isToWatch() {
        return toWatch;
    }

    public  boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public void setToWatch(boolean toWatch) {
        this.toWatch = toWatch;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }
}
*/

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MovieEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieID;

    private String owner;
    private Long id;
    private String title;
    private String posterUrl;
    private boolean toWatch;


    public MovieEntry() {
    }

    public MovieEntry( String title, String owner, Long id, Long movieID, String posterUrl) {
        this.title = title;
        this.owner = owner;
        this.id = id;
        this.movieID = movieID;
        this.posterUrl = posterUrl;

    }

    public Long getMovieID() {
        return movieID;
    }

    public void setMovieID(Long movieID) {
        this.movieID = movieID;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public boolean isSeen() {
//        return seen;
//    }
//
//    public void setSeen(boolean seen) {
//        this.seen = seen;
//    }
//
    public boolean isToWatch() {
        return toWatch;
    }

    public void setToWatch(boolean toWatch) {
        this.toWatch = toWatch;
    }
//
//    public boolean isFavorite() {
//        return favorite;
//    }
//
//    public void setFavorite(boolean favorite) {
//        this.favorite = favorite;
//    }

    public String getPosterUrl() {
        return posterUrl;
    }
    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

}
