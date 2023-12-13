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
@Table(name = MediaType.TABLE_NAME)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MediaType {

    public static final String TABLE_NAME = "media_types";

    @Id
    @Column(name = "MediaTypeId")
    Integer id;

    String name;

    public MediaType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public MediaType(){}

    public void update(String name) {
        this.name = name;
    }
}
