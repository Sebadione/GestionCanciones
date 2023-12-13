package ar.edu.utn.frc.backend.application.response;

import ar.edu.utn.frc.backend.models.Album;
import ar.edu.utn.frc.backend.models.Artist;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArtistResponse {

    Integer id;

    String name;

    List<String> albums;

    public static ArtistResponse from(Artist artist) {
        return ArtistResponse.builder()
                .id(artist.getId())
                .name(artist.getName())
                .albums(artist.getAlbums().stream().map(Album::getTitle).toList())
                .build();
    }
}