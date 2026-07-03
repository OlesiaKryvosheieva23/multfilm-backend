package de.htw_berlin.multfilm;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

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
    private Boolean seen = false;
    private Integer personalRating = 0;
    @Column(length = 1000)
    private String commentText = "";


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

    public boolean isToWatch() {
        return toWatch;
    }

    public void setToWatch(boolean toWatch) {
        this.toWatch = toWatch;
    }

    public boolean isSeen() {
        return Boolean.TRUE.equals(seen);
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText == null ? "" : commentText;
    }

    public Integer getPersonalRating() {
        return personalRating == null ? 0 : personalRating;
    }

    public void setPersonalRating(Integer personalRating) {
        this.personalRating = personalRating == null ? 0 : personalRating;
    }

    public String getPosterUrl() {
        return posterUrl;
    }
    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

}
