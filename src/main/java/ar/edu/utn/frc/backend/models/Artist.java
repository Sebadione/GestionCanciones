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
@Table(name = Artist.TABLE_NAME)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Artist {

    public static final String TABLE_NAME = "artists";


    @Id
    @Column(name = "ArtistId")
    Integer id;

    String name;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "ArtistId")
    private List<Album> albums;

    public Artist(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Artist() {
    }

    public void update(String name) {

        this.name = name;
    }
}
