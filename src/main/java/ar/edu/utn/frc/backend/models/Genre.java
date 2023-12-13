package ar.edu.utn.frc.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Table(name = Genre.TABLE_NAME)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Genre {

    public static final String TABLE_NAME = "genres";

    @Id
    @Column(name = "GenreId")
    Integer id;

    String name;

    public Genre(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Genre() {
    }

    public void update(String name) {
        this.name = name;
    }
}
