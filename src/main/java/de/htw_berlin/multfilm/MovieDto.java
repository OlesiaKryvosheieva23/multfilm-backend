package de.htw_berlin.multfilm;

//record für die Film-Details, die von der TMDB API zurückgegeben werden
//enthält Titel, Übersicht, Poster-URL, Veröffentlichungsdatum, Genres, Cast 
//und Regisseur
//wird von MovieService erstellt und von TmdbController zurückgegeben
//record ist eine spezielle Klasse in Java, die hauptsächlich Daten enthält 
//und automatisch Getter, equals, hashCode und toString generiert

public record MovieDto(
        String title,
        String overview,
        String posterUrl,
        String releaseDate,
        String director,
        Double voteAverage
) {}

