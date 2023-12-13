package ar.edu.utn.frc.backend.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = Track.TABLE_NAME)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Track {

    public static final String TABLE_NAME = "tracks";

    @Id
    @Column(name = "TrackId")
    Integer id;

    String name;

    @ManyToOne
    @JoinColumn(name = "AlbumId")
    Album album;

    @OneToOne
    @JoinColumn(name = "mediaTypeId")
    MediaType mediaType;

    @OneToOne
    @JoinColumn(name = "genreId")
    Genre genre;

    String composer;

    Integer milliseconds;

    Integer bytes;

    Double unitPrice;

    @ManyToMany
    @JoinTable(name = "playlist_track",
            joinColumns = @JoinColumn(name = "TrackId"),
            inverseJoinColumns = @JoinColumn(name = "PlaylistId"))
    List<Playlist> playlists;

    public Track(Integer id, String name, Album album, MediaType mediaType,
                 Genre genre, String composer, Integer milliseconds, Integer bytes, Double unitPrice) {
        this.id = id;
        this.name = name;
        this.album = album;
        this.mediaType = mediaType;
        this.genre = genre;
        this.composer = composer;
        this.milliseconds = milliseconds;
        this.bytes = bytes;
        this.unitPrice = unitPrice;
    }

    public Track() {

    }

    public void update(String name, Album album, MediaType mediaType, Genre genre,
                 String composer, Integer milliseconds, Integer bytes, Double unitPrice) {
        this.name = name;
        this.album = album;
        this.mediaType = mediaType;
        this.genre = genre;
        this.composer = composer;
        this.milliseconds = milliseconds;
        this.bytes = bytes;
        this.unitPrice = unitPrice;
    }
}
