package de.htw_berlin.multfilm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieDtoTest {

    @Test
    void testToString() {
        //Eingabedaten
        String title = "Boring Movie";
        String overview = "Das ist Filmüberblick.";
        String posterUrl = "https://example.com/poster.jpg";


        //"System under test" aufsetzen
        MovieDto movie = new MovieDto(title, overview, posterUrl, "2023-01-01", 8.5);


        // Erwartetes Ergebnis
        String expected = "MovieDto[title=" + title +
                ", overview=" + overview +
                ", posterUrl=" + posterUrl +
                ", releaseDate=2023-01-01" +
                ", director=Patrick Neuer" +
                ", voteAverage=8.5]";

        // Tatsächliches Ergebnis
        String actual = movie.toString();

        // Vergleich
        assertEquals(expected, actual);
    }
}







